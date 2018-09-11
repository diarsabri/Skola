1.

WITH CTE (countrynoisland) AS
(
SELECT code FROM country
EXCEPT
SELECT country FROM geo_island
)
SELECT * FROM CTE;


2.

WITH CTE1 (x) AS
( SELECT name FROM province EXCEPT SELECT province FROM geo_sea ),
CTE2 (y) AS
( SELECT name FROM province )
SELECT ROUND(((SELECT COUNT(x) FROM CTE1) / (SELECT COUNT(y)*1.0000 FROM CTE2)),4) AS ratio;


3.

WITH CTE1 (lake,cont,perc,area) AS
(
  SELECT DISTINCT islandin.lake AS lake, encompasses.continent AS cont, encompasses.percentage AS perc, lake.area AS area FROM islandin 

  INNER JOIN geo_lake ON islandin.lake = geo_lake.lake
  INNER JOIN encompasses on encompasses.country = geo_lake.country
  INNER JOIN lake ON islandin.lake = lake.name

  ORDER BY islandin.lake
)
SELECT list.cont AS Continent,ROUND(SUM(list.area*(list.perc/100)),2) AS Area FROM CTE1 AS list GROUP BY list.cont;


4.

WITH CTE1 (country,continent,population,population_growth) AS 
(
  SELECT encompasses.country, encompasses.continent, country.population * (encompasses.percentage/100) AS population, population.population_growth 
  FROM country
  INNER JOIN encompasses ON country.code = encompasses.country
  INNER JOIN population ON population.country = encompasses.country
),
CTE2 (continent,current_pop, future_pop) AS 
(
  SELECT continent, SUM(population) AS current_pop, 
  SUM(population*(POWER(1.00+population_growth/100,50))) AS future_pop FROM CTE1
  GROUP BY continent
),
CTE3 (continent,ratio,pop_inc) as
(
  SELECT continent, future_pop / current_pop AS ratio, future_pop-current_pop AS pop_inc FROM CTE2
)

SELECT continent,ROUND(ratio,3) AS ratio FROM CTE3 WHERE
pop_inc = (SELECT MAX(pop_inc) FROM CTE3) OR
pop_inc = (SELECT MIN(pop_inc) FROM CTE3);


5.

WITH CTE1(org,country,abbr) AS
(
  SELECT organization.name,organization.country,organization.abbreviation FROM organization 
  WHERE organization.name LIKE '%International%'
),
CTE2 (org,country,abbr) AS
(
  SELECT cte1.org,encompasses.country,cte1.abbr FROM encompasses 
  INNER JOIN CTE1 ON CTE1.country = encompasses.country 
  WHERE encompasses.continent = 'Europe'
),
CTE3  AS
(
  SELECT cte2.org,ismember.country FROM ismember
  INNER JOIN cte2 ON ismember.organization = cte2.abbr
  WHERE ismember.country IN (SELECT * FROM (
    SELECT encompasses.country FROM encompasses
    WHERE encompasses.continent = 'Europe'
  ) AS derived_table)
),
CTE4 AS
(
  SELECT DISTINCT cte3.org,COUNT(cte3.country) AS number_countries FROM CTE3
  GROUP BY cte3.org
)
SELECT cte4.org FROM CTE4 WHERE
number_countries = (SELECT MAX(number_countries) FROM CTE4);


6.

select city_name, airport_name from
    ((select name as city_name, country, province from city where country like 'USA') a
    inner join (select distinct on (city, country, province)
                    population as newest_pop, city, country, province 
                    from citypops
                    order by city, country, province, year DESC) b 
        on b.city = city_name and
           b.country = a.country and
           b.province = a.province
    inner join (select name as airport_name, city, country, elevation from airport as c) c on c.city = city_name and elevation > 500) where newest_pop >= 100000;


7.

SELECT country_name, round((newest_population / oldest_population), 1) AS ratio 
    FROM ((SELECT name as country_name, code as country_code FROM country) a
    INNER JOIN (SELECT DISTINCT ON (country)
        country, year, population as oldest_population
        FROM countrypops
        ORDER BY country, year) b 
        ON b.country = country_code
    INNER JOIN (SELECT DISTINCT ON (country)
        country, population as newest_population
        FROM countrypops
        ORDER BY country, year desc) c
        ON c.country = country_code
    ) WHERE (newest_population / oldest_population) >= 10;


8.

WITH big_cities AS 
(
    SELECT * FROM city
    WHERE population >= 5000000
)
SELECT name1, name2, name3, (perimeter * 40075.16 / 360) AS ToDist FROM (
    SELECT name1, name2, name3, (length1 + length2 + length3) as perimeter,
    row_number() over (order by (length1 + length2 + length3) desc) rank
     FROM (
        SELECT sqrt((abs(lat1 - lat2) % 180)^2 + (abs(lon1 - lon2) % 180)^2) AS length1,
               sqrt((abs(lat2 - lat3) % 180)^2 + (abs(lon2 - lon3) % 180)^2) AS length2,
               sqrt((abs(lat3 - lat1) % 180)^2 + (abs(lon3 - lon1) % 180)^2) AS length3,
               name1, name2, name3
        FROM (
    (SELECT name as name1, latitude as lat1, longitude as lon1 FROM big_cities) a
    CROSS JOIN (SELECT name as name2, latitude as lat2, longitude as lon2 FROM big_cities) b
    CROSS JOIN (SELECT name as name3, latitude as lat3, longitude as lon3 FROM big_cities) c
) d) e) f WHERE f.rank = 1;


9.

WITH RECURSIVE CTE (n,name,length) AS
(
  SELECT river.name AS n, river.name, river.length FROM river
  WHERE river.name LIKE 'Rhein' OR river.name LIKE 'Nile' OR river.name LIKE 'Amazonas'
  UNION ALL
  SELECT rec.n, river.name, river.length + rec.length FROM river
  INNER JOIN CTE AS rec ON rec.name = river.river
)
SELECT cte.n AS river, MAX (cte.length) AS length FROM cte
GROUP BY cte.n;