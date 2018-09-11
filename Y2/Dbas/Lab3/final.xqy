(: 1 :)

(: 
1. Väljer ut öar och deras respektive länder 
2. Väljer ut alla länder
3. Returnerar namnet på (2) där de inte är lika med (1)
:)

let $countryi := doc("mondial_xml.xml")/mondial/island/@country
for $country in doc("mondial_xml.xml")/mondial/country
order by $country/name
return 
  if ($countryi = $country/@car_code) then ()
  else
    $country/name

(: 2 :)

(:
1. Väljer alla provinser som har en sjö genom en loop som går genom alla provinser och ser till att ingen sjö är lokerad på det landet. Returnerar antalet länder detta matchar på.
2. Väljer alla provinser
3. Delar dessa på varandra för att få förhållandet
:)

let $prov_sea := count(
  for $x in doc("mondial_xml.xml")/mondial/country/province
  where not(some $y in doc("mondial_xml.xml")/mondial/sea/located/@province satisfies contains($y,$x/@id))
  return $x/name
)
let $prov_tot :=count(doc("mondial_xml.xml")/mondial/country/province/@id)
return <ratio>{$prov_sea div $prov_tot}</ratio>

(: 3 :)

(: Returnerar kontinenterna och arean för varje sjö som uppfyller kraven :)

let $lake_area := (
  (: Väljer ut länder,öar,sjöar. Matchar på deras id'n :)
  for $country in doc("mondial_xml.xml")/mondial/country
  for $island in doc("mondial_xml.xml")/mondial/island
  for $lake in doc("mondial_xml.xml")/mondial/lake
  
  (: Ser till att vi matchar så att landet har en sjö som har en ö :)
  where $country/@car_code = $island/@country and $island/@lake = $lake/@id
  
  (: Väljer ut kontinenterna för länderna och deras "encompasses" :)
  let $continents := $country/encompassed/@continent
  let $percentage := $country/encompassed/@percentage
  
  (: Loopar genom kontinenterna ovan :)
  for $continent at $i in $continents
  
  (: Returnerar varje kontinent och sjöarean för varje sjö :)
  return <area>{$continent, ($percentage[$i] div 100)*$lake/area}</area>
)

(: Summerar i princip bara sjöareorna för varje kontinent :)
for $area in $lake_area
  let $continent := $area/@continent
  group by $continent
  return <continent><name>{$continent}</name><lake_area>{sum($area)}</lake_area></continent>

(: 4 :)

let $ratios := (

(: Väljer kontinenter och länder :)
for $continent in doc("mondial_xml.xml")/mondial/continent
let $countries := doc("mondial_xml.xml")/mondial/country[encompassed/@continent = $continent/@id]

(: Summerar nuvarande population i en kontinent genom att summera ländernas senaste population :)
let $current_population := 
  sum(
    for $country in $countries
    return $country/population[last()] 
  )

(: Tar fram populationen om 50 år genom att använda growth datan. Summerar på samma sätt som innan :)
let $future_population :=
  sum(
    for $country in $countries 
    
    let $growth_rate := $country/population_growth/data() div 100 + 1
        
    return $country/population[last()]*math:pow($growth_rate, 50)
  )

  (: Returnerar alla kontinenter och deras ratios :)
  return <continent name = "{$continent/name}"><ratio>{$future_population div $current_population}</ratio></continent>
)

(: Väljer ut de två kontinenterna med min/max ökning :)
return <result>{$ratios[data() = max(data($ratios)) or data() = min(data($ratios))]}</result>

(: 5 :)

let $eu_member_count :=
(
(: Väljer orgs med International i namnet vars hq är i europa :)
let $orgs := doc("mondial_xml.xml")/mondial/organization[contains(name, 'International')]
let $eu_countries := doc("mondial_xml.xml")/mondial/country[encompassed/@continent = 'europe']

for $org in $orgs
where $eu_countries/province/city/@id = $org/@headq

(: Returnerar organisation samt antalet eu länder. :)
return
<organization name="{$org/name}">
        <eu_members>{count(
          (: Tokeniseringen splittar länderna en org tillhör så att det blir en lista som vi kan loopa över i stället :)
          let $eu_members := tokenize($org/members[@type = "member"]/@country, "\s")
          for $member in $eu_members
          where $member = $eu_countries/@car_code
          return $member  
        )}
        </eu_members>
</organization>
)
return $eu_member_count[data() = max($eu_member_count)]

(: 6 :)

(: Flygplatser med elevation > 500. Städer i america med pop > 100000 :)
let $airports := (
  for $i in //airport[elevation > 500]
  return $i
)
let $cities := (
  for $i in //country[encompassed/@continent = "america"]/province/city
  where $i/population > 100000
  return $i
)
(: Tar fram kombinationen av ovanstående CTE's :)
let $combination := (
  for $i in $airports
  for $j in $cities
    where $i/@city = $j/@id
    return
    <result>
      <airport> {$i/name} </airport>
      <city> {$j/name} </city>
    </result>
)
return $combination

(: 7 :)

for $country in doc("mondial_xml.xml")/mondial/country
(: Avrundar mha r-h-t-e funktionen till en decimal högst. Väljer ut senaste och första och dividerar dessa :)
let $ratio := round-half-to-even($country/population[last()] div $country/population[1], 1)
where $ratio > 10
return <country>{$country/name}<ratio>{$ratio}</ratio></country>

(: 8 :)

(: Funktion baserad på https://www.movable-type.co.uk/scripts/latlong.html . Räknar ut längden mha. haversine formeln :)
(: Arg: lat1, lon1, lat2, lon2 :)
declare function local:haversine($lat1 as xs:float, $lon1 as xs:float, $lat2 as xs:float, $lon2 as xs:float)
    as xs:float
{
    let $rlat1 := $lat1 * math:pi() div 180
    let $rlat2 := $lat2 * math:pi() div 180
    let $dlat  := ($lat2 - $lat1) * math:pi() div 180
    let $dlon  := ($lon2 - $lon1) * math:pi() div 180
    let $a     := math:sin($dlat div 2) * math:sin($dlat div 2) + math:sin($dlon div 2) * math:sin($dlon div 2) * math:cos($rlat1) * math:cos($rlat2)
    let $c     := 2 * math:atan2(math:sqrt($a), math:sqrt(1-$a))
    return xs:float($c * 6371.0)
};

(: Väljer ut alla städer med pop > 5000000 :)
let $city := (for $city in //country/province/city
let $max_year := max($city/population/@year/data())
where $city/population/data() > 5000000 and $city/population/@year = $max_year
return $city)

(: Alla kombinationer av städer (trios) :)
let $comb :=(
for $city1 in $city
return (
for $city2 in $city where $city2 != $city1 
return (
for $city3 in $city where $city3 != $city1 and $city3 != $city2
return
<result>
<city1>{$city1}</city1>
<city2>{$city2}</city2>
<city3>{$city3}</city3>
</result>)))

(: Räknar ut avståndet mellan städerna mha. funktionen ovan. :)
let $calc :=
(
for $row in $comb
let $distance1_2 := 
local:haversine($row/city1/city/latitude/data(),$row/city1/city/longitude/data(),
$row/city2/city/latitude/data(),$row/city2/city/longitude/data())

let $distance1_3 := 
local:haversine($row/city1/city/latitude/data(),$row/city1/city/longitude/data(),
$row/city3/city/latitude/data(),$row/city3/city/longitude/data())


let $distance2_3 := 
local:haversine($row/city2/city/latitude/data(),$row/city2/city/longitude/data(),
$row/city3/city/latitude/data(),$row/city3/city/longitude/data())

(: Returnerar det som en lista (där vi bara kan ha ett namn på en stad) :)
return 
<result>
<city1>{$row/city1/city/name[1]}</city1>
<city2>{$row/city2/city/name[1]}</city2>
<city3>{$row/city3/city/name[1]}</city3>
<distance>{$distance1_2 + $distance1_3 + $distance2_3}</distance>
</result>
)

(: Väljer ut max-längden ur listan ovan :)
let $max_distance := max($calc/distance)
let $a8 := $calc[distance = $max_distance]
return <results>{$a8[1]}</results>

(: 9 :)

(: Starts with one of the rivers and goes through the rivers that are the origin of that specific river :)
(: Recursive function. Args: river,length :)
declare function local:longestRiver($riverName as xs:string?, $length as xs:double?)
as xs:double*
{
(: Starts with Rhein,Amazonas or Nil and length 0. :)
(: Origin börjar med alla sjöar som man kan nå rhein,amazonas,nil från :)
let $originRivers := doc("mondial_xml.xml")//river[to/@water = $riverName]
(: Längden börjar med 0 + längden på en av de tre floderna :)
let $sumLength := $length + doc("mondial_xml.xml")//river[@id=$riverName]/length/data()

(: Keeps adding to result(length) until there are no more rivers to pass as args. Finally returns result from the function :)
let $result := (
  (: If there are more rivers that are the origin of the current river :)
  if (not(empty($originRivers)))
  then max(for $origin in $originRivers
    return local:longestRiver($origin/@id/string(),$sumLength))
  else $sumLength)
return $result
};

<result>
<river>
<name>Rhein</name>
<length>
{local:longestRiver("river-Rhein",0)}
</length>
</river>
<river>
<name>Amazonas</name>
<length>
{local:longestRiver("river-Amazonas",0)}
</length>
</river>
<river>
<name>Nile</name>
<length>
{local:longestRiver("river-Nil",0)}
</length>
</river>
</result>

(: B :)

(: Tar fram alla orgs som har hq i europa och vars namn börjar på International :)
let $all_org := (for $org in //organization
where //country[encompassed/@continent = "europe"]/province/city/@id = $org/@headq and starts-with($org/name,"International")
return $org)

for $country in //country
(: Loopar genom varje land och kollar om det finns en organisation som uppfyller kraven. :)
(: Kravet är att varje organisation ska vara med i landet vi loopar över för tillfället :)
(: Vi kollar att landet vi loopar över existerar i org's members :)
where every $org in $all_org satisfies ($org/members[exists(index-of(tokenize(@country),$country/@car_code))])
return $country/name

(: C. Function for both C questions :)

(: Args: current country, resulting countries (recursive), xml formating, levels :)
declare function local:border(
$currCountry as element(country)*, 
$resultCountries as element(country)*,
$xml,
$level as xs:integer)

{
(: Final element check, if there is no more currentcountry element,return $xml :)
if (empty($currCountry))
then ($xml)
else (
  (: Check so that $borderingCountries aren't in the list of $resultCountries or the current country itself. Otherwise add to $borderingCountries :)
  let $borderingCountries := $currCountry/border/@country[not(. = ($currCountry, $resultCountries)/@car_code)]
  (: If there are any more $borderingCountries, create a new level :)
  let $newLevel := (
    if (empty($borderingCountries))
    then $level
    else $level + 1
  )
  (: If there are any more $borderingCountries, add to $xml. :)
  let $resultXml := (
    if (empty($borderingCountries))
    then ($xml)
    else (
      (: We add a new level to the $xml variable. In the countries belonging to that level, we distinctly select them  :)
      let $resultXml := ( $xml, <level>{$newLevel}</level>, <countries>{for $i in distinct-values($borderingCountries) return <country>{$i}</country>}</countries>)
      return $resultXml))

  (: We recursively call the function with the new (or old) xml variable. :)
  (: Input: $borderingCountries, (previous + current country), xml, current level :)
  return local:border(
    ( doc("mondial_xml.xml")//country[@car_code = $borderingCountries]),
    ($resultCountries, $currCountry),
    ($resultXml),
    ($newLevel)
  )  
)  
};

(: C1 :)

(: Set current country to sweden, call above function :)
let $country := //country[name = "Sweden"]
let $r := local:border($country,(),<base>{$country/name}</base>,0)
return <result>{$r}</result>

(: C2 :)

(: Call above function for each country :)
let $result := (for $country in //country
let $r := <result>{local:border($country,(),<base>{$country/name}</base>,0)}</result>
return $r)

(: Get the max levels, and only return those :)
let $max := max($result/level)
let $c2 := $result[level = $max]
return <results>{$c2}</results>

(: D :)

for $element in //music/*

let $subelements := (for $subelement in $element/*
return <subelement name="{$subelement/name()}" value ="{$subelement/data()}"></subelement>)

let $attributes := (for $attribute in $element/@*
return <attribute name="{$attribute/name()}" value="{$attribute/data()}"></attribute>)

return (
  (:Tar bort noden som stämmer överens med attribut namnet vi skapa innan:)
  for $attribute in $attributes return delete node $element/@*[name() = $attribute/@name],
  (:Samma sak fast för element:)
  for $subelement in $subelements return delete node $element/*[name() = $subelement/@name],
  (:Skapa attribut av de subelementen vi skapa innan :)
  for $newAttribute in $subelements return insert node (attribute { data($newAttribute/@name) } { data($newAttribute/@value) }) into $element,
  (:Skapar subelements av attributen vi skapa innan:)
  for $newSubelement in $attributes return insert node (element { data($newSubelement/@name) } { data($newSubelement/@value) }) into $element
)

