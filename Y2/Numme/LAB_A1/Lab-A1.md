

# Uppgift 1
Gv och fv är i exakt aritmetik alltid noll. Det kan man räkna ut för hands. I Matlab blir gv alltid noll, medan fv inte blir det. Skillnaden mellan gv och fv är nämligen att vi i gv(j) bokstavligen gör gv(j) = z-z; Detta blir alltid noll då dessa är identiska. I fv(j) blir det istället så att vi approximerar z och sedan subtraherar etc. Felet är störst vid sista iterationen av for-loopen. Matlab följer IEEE standarden vilket leder till att vissa avrundningsfel(väldigt små) kan uppstå då man räknar i matlab. (s.14 Sauer)

# Uppgift 2

### a)
Normen för Gauss-elim ger då  n=25 värdet 3.3064e-13
Normen med backslash-operatorn då n=25 ger värdet 3.4758e-14
Alltså är felet mindre och detta gäller oavsett vilket n vi stoppar in.

Backslash-operatorn är även mycket mer effektiv än traditionell gauss-eliminering eftersom den är väldigt optimerad.



### b)

|            | gausselim         | backslash             | inv(A)*b              |
| :--------: | :---------------: | :-------------------: | :--------------------:|
| n = 25     | 0.004838000000000 | 1.810000000000000e-04 | 2.420000000000000e-04 |
| n = 50     | 0.005954000000000 | 2.470000000000000e-04 | 2.780000000000000e-04 |
| n = 100    | 0.011520000000000 | 4.120000000000000e-04 | 5.740000000000000e-04 |
| n = 200    | 0.041894000000000 | 7.490000000000000e-04 | 0.001094000000000     |
| n = 400    | 0.211223000000000 | 0.002334000000000     | 0.003832000000000     |
| n = 800    | 1.692753000000000 | 0.009053000000000     | 0.016440000000000     |

Matlabs är tydligast snabbast, och den genererar minst fel dessutom. Matlabs backslash-funktion är snabbare än O(k^3) pga. att den är väldigt effektiv. Gauss-funktionen verkar däremot närma sig den komplexiteten som beskrivs i teorin.



# Uppgift 3

### a)
See figur

### b)
minsta roten: 5 iterationer, gissning: -1,  x= -0.898356581545585
största roten: 4 iteration, gissing: x=4.5,  x= 4.517789514180033
### c)
Genom att skriva ut varje iteration och kolla resultatet som ser man att konvergensen är ungefär kvadratisk.

### d)
Sätter man x0 = 7 blir det konstigt. Vid tio iterationer ser man att det går upp och ner i värden. Flera olika värden mellan 6.7 och 7.4 antas. Den hoppar eftersom med startgissning lyckas den inte konvergera.

### e)
Efter 6 iterationer får vi att skillnaden mellan x6 och fzero är mindre än 10^-10. Med andra ord tar det 6 iterationer vilket är fler än med newton-raphson

newton-raphonson har kvadratisk konvergens medans sekantmetoden har "linjär"


# uppgift 4
Använder sekantmetoden





# Uppgift 5
Metod: Sätt funktionerna i en matris, räkna ut jakobianen och sätt denna i matris. Kör matlabs backslash-funktion för att beräkna det olinjära ekvationssystemet med Newtons metod i flera variabler. (approximation)
Lösningar med startgissningar x=[20;40] , x=[50;25];
x =

  40.353195003443233      % x-värdet
  46.741958185115713      % y-värdet

x =

  50.542939175325429     % x-värdet
  27.880091313759308     % y-värdet

# Uppgift 6
Vi har tre matriser; testdata, som innehåller kolumner som vi tror är tvåor,C, som innehåller en representation av en tvåa i varje kolumn, och slutligen testdatad som innehåller det rätta antalet tvåor i testdata.
### a)
Matrisen C är 256\*50 och innehåller alltså 50 linjärkombinationer av siffran 2. Matrisen testdata är 256\*1000 där varje kolumn representerar en siffra från 0-9. Då vi måste göra en anpassning för att hitta alla tvåor använder vi minsta kvadrat metoden. Vi får en massa punkter och måste anpassa en kurva till dessa (grundprincipen). Den bästa lösningen är den som minimerar residualen (felet i minstakvadrat-mening).
### b)
Programmet: skapar en tom matris med längd av testdata -> beräknar normen av residualen av anpassningen till testdata -> sätter varje lösning i den tomma matrisen.
Första for-loopen kollar mängden tvåor i testdatad, andra for loopen kollar först om vår anpassning liknar en två, sedan kollar den om det faktist är en tvåa.
### c)
Vi hittar ca 70% av alla tvåor och gissar fel drygt 2% av gångerna.

