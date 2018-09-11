# Lab A1 Diar Sabri

# Uppgift 1
Gv och fv är i exakt aritmetik alltid noll. Det kan man räkna ut för hands. I Matlab blir gv alltid noll, medan fv inte blir det. Skillnaden mellan gv och fv är nämligen att vi i gv(j) bokstavligen gör gv(j) = z-z; Detta blir alltid noll då dessa är identiska. I fv(j) blir det istället så att vi approximerar z och sedan subtraherar etc. Felet är störst vid sista iterationen av for-loopen. Matlab följer IEEE standarden vilket leder till att vissa avrundningsfel(väldigt små) kan uppstå då man räknar i matlab. (s.14 Sauer)

# Uppgift 2
|            | gausselim         | backslash             |
| :--------: | :---------------: | :-------------------: |
| n = 25     | 0.005318257233611 | 1.658846876145577e-04 |
| n = 50     | 0.005490870929718 | 2.121100503008013e-04 |
| n = 100    | 0.010886365478197 | 7.428240244327373e-04 |
| n = 200    | 0.034316773048390 | 5.974189278817052e-04 |
| n = 400    | 0.241161520486759 | 0.001910453280640     |

Matlabs är tydligast snabbast, och den genererar minst fel dessutom. Matlabs backslash funktion är snabbare än O(k^3) pga. att den är väldigt effektiv. Gauss-funktionen verkar däremot närma sig den komplexiteten.

# Uppgift 3
### b)
% x0 = -1;
%Efter tre iterationer kommer vi till x4 = -0.898356581554583

% x0 = -0.5;
%Efter 2 iterationer kommer vi till x3 = -0.544442395963611

% x0 = 1.5;
%Efter 1 iteration kommer vi till x2 = 1.732069387491808

% x0 = 3;
%Efter 2 iterationer kommer vi till x3 = 3.161826486551824

% x0 = 4.5;
%Efter 1 iteration kommer vi till x2 = 4.517789519604580

Största roten : 4.517789519604580 , Minsta roten : -0.898356581554583

### c)
Man kan testa detta genom att skriva ut varje iteration.

### d)
Sätter man x0 = 7 blir det konstigt. Vid tio iterationer ser man att det går upp och ner i värden. Flera olika värden mellan 6.7 och 7.4 antas.

### e)
Efter 5 iterationer får vi att skillnaden mellan x6 och fzero är mindre än 10^-10. Med andra ord tar det fem iterationer.

# Uppgift 4
Metod: Sätt funktionerna i en matris, räkna ut jakobianen och sätt denna i matris. Kör matlabs backslash-funktion för att beräkna det olinjära ekvationssystemet med Newtons metod i flera variabler. (approximation)
Lösningar med startgissningar x=[20;40] , x=[50;25];
x =

  40.353195003443233      % x-värdet
  46.741958185115713      % y-värdet

x =

  50.542939175325429     % x-värdet
  27.880091313759308     % y-värdet

# Uppgift 5
Vi har tre matriser; testdata, som innehåller kolumner som vi tror är tvåor,C, som innehåller en representation av en tvåa i varje kolumn, och slutligen testdatad som innehåller det rätta antalet tvåor i testdata.
### a)
Matrisen C är 256\*50 och innehåller alltså 50 linjärkombinationer av siffran 2. Matrisen testdata är 256\*1000 där varje kolumn representerar en siffra från 0-9. Då vi måste göra en anpassning för att hitta alla tvåor använder vi minsta kvadrat metoden. Vi får en massa punkter och måste anpassa en kurva till dessa (grundprincipen). Den bästa lösningen är den som minimerar residualen (felet i minstakvadrat-mening).
### b)
Programmet: skapar en tom matris med längd av testdata -> beräknar normen av residualen av anpassningen till testdata -> sätter varje lösning i den tomma matrisen.
Första for-loopen kollar mängden tvåor i testdatad, andra for loopen kollar först om vår anpassning liknar en två, sedan kollar den om det faktist är en tvåa.
### c)
Vi hittar ca 70% av alla tvåor och gissar fel drygt 2% av gångerna.

# Lab A2 Diar Sabri

# Uppgift 1
Då vi har ett olinjärt överbestämt ekvationssystem måste vi använda Gauss-Newtons metod. Den enda ändringen vi gör i koden är att vi lägger till ytterligare en funktion. 
### b)
Från LabA1, med startgissning:
x=[40;45] -> x = [40.353195003443240;46.741958185115713].
Detta ger (efter 9 iterationer) x = [42.383039694882044;42.837717820273603]
x=[50;25] -> x = [50.542939175325429;27.880091313759308]
Detta ger (efter 10 iteratione) x = [42.383039694882704;42.837717820272275]

### c)
Det finns ingen punkt där cirklarna skär varandra, vilket innebär att resultatet är en approximation där cirklarna nästan skär varandra.

# Uppgift 2
### b)
|      h   | d(h)               | abs(d(h)-y'(1))     |
| :------: | :----------------: | :-----------------: |
| 1        | -0.708073418273571 | 0.133397566534325   |
| 0.5      | -0.80684536022267  | 0.0346256245852267  |
| 0.25     | -0.832733012957104 | 0.00873797185079206 |
| 0.125    | -0.839281365458636 | 0.00218961934926054 |
| 0.0625   | -0.840923259124113 | 0.00054772568378314 |

Då h förminskas förbättras approximationen, däremot finns risken för kancellation i täljaren om  h är för litet. Större h -> större E(x)
Mindre h ( går mot noll) -> kancellation

Då felet = O(h^2) ger det att felet blir O((h/2)^2)=O(h^2/4) om det halveras.

# Uppgift 3
### a)
Värdet blir ca. 2.7 a.e.
### b)
I = 2,7974349.....
### c)
Trapetsregeln är en variant av riemann-summor där man delar upp en funktion i n stycken delintervall med längden h=(b-a)/n och tillämpar (b-a)\*(f(a)+f(b))/2 på vart och ett av delintervallen, vilket ger en approximation av integralen vars fel är proportionellt mot valet av h.
### d)
https://www.youtube.com/watch?v=5MjSAiM_jFU %trapetsregeln i matlab
|      h   | T(h)              | abs(T(h)-I)           |
| :------: | :---------------: | :-------------------: |
| 1        | 2.780238966157534 | 0.017195982313555     |
| 0.5      | 2.793061333816656 | 0.004373614654432     |
| 0.25     | 2.796336176773301 | 0.001098771697787     |
| 0.125    | 2.797159904478356 | 2.750439927328152e-04 |
| 0.0625   | 2.797366165255859 | 6.878321522929554e-05 |

### e)
Teorin säger att felet förminskas i en takt som är proportionerlig mot h^2, vilket innebär att en halvering leder till att felet minskar med en faktor på 1/4.
### d)
Richardson-extrapolation används för att minska fel eller förbättra approximationer vid användning av stegmetoder som t.ex. trapetsregeln.

|      h   | T(h)              | abs(T(h)-I)           |
| :------: | :---------------: | :-------------------: |
| 1        | 2.796301685687086 | 0.001133262784002     |
| 0.5      | 2.797335456369697 | 9.949210139170717e-05 |
| 0.25     | 2.797427791092183 | 7.157378905553458e-06 |
| 0.125    | 2.797434480380040 | 4.680910481447143e-07 |
| 0.0625   | 2.797434918848360 | 2.962272827033985e-08 |

# Uppgift 4
f(z) $\approx$ 1 då z = 0.359481559011896
Vi använder trapetsregeln/metoden som i föregående uppgift för att plotta funktionen, vilket ger oss en approximativ lösning på problemet.
Vi använder därefter sekantmetoden med erhållna värden.

# Uppgift 5
### a)
Vi använder newtons metod i flera variabler, efter 50 iterationer ger detta oss thetav = [1.190051800266113,   0.380744526480744]

### b)


### c)
Vi använder eulers metod för ODE system (enl instruktion).
y<sub>k+1</sub> = yk+f(t<sub>k</sub>, y<sub>k</sub>)\*h<sub>(k)</sub>



dd