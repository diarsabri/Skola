# Numeriska metoder: Laboration A1

### 1. Flyttalsaritmetik
Kör det här programmet i MATLAB:
```=
kv=0:10; fv=[];
for j=1:length(kv)
fv(j)=((pi+10^kv(j))-10^kv(j))-pi
end
semilogy(kv,abs(fv))


kv = 0:15; fv =[]; gv =[];
for j=1:length(kv)
    z = (pi+10^kv(j));
    fv(j) = (z-10^kv(j))-pi;
    gv(j)=z-(10^kv(j)+pi);

end
fv
gv
figure(1); semilogy(kv,abs(fv-gv))

```
Vad skulle gv och fv vara i exakt aritmetik? Vad blir gv och varför? För vilka värden på k gör vi störst fel? Varför?


### Svar:
I exakt aritmetik så ska det vara 0 för båda. Uttrycket i gv blir 0 då det står z-z för att andra delen av uttrycket är ekvivalent till z. 
Det blir störst fel på k då den är som störst och i vårt fall är det 15. Detta är ett resultat av trunkering som sker i beräkningen pga begränsad mantissa längd för antal signifikan siffror som leder till kancellation (subtrahering av nästan lika tal). Det sker en noggranhetsförlust.




### 2.
Det här programmet löser ett linjärt ekvationssystem, mäter beräkningstiden och beräknar normen av residualvektorn.
```=
n=50; A=randn(n,n); b=randn(n,1);
tic();
x=gausselim(A,b);
t=toc()
norm(A*x-b)
```

(a) Kör programmet ovan. Jämför det med MATLABs inbyggda metod för linjära
ekvationsystem, den s.k. backslashoperatorn: A\b, genom att ändra programmet.
Vilket program är snabbast och generar en lösning med minst fel?


(b) Fyll i tabellen nedan med beräkningstider. När ni fördubblar n, med vilken faktor
förväntas beräkningstiden öka enligt teorin? Stämmer det alltid med teorin?
Varför inte?

### Svar:


```=
clc; clear; close all; % clear command window, clear system memory, close all figures;
nv = [25, 50, 100, 200]; 
n = 50;
A = randn(n, n); b=randn(n, 1);


err_gauss = []; err_backslash = []; time_gauss = []; time_backslash = [];
for j=1:length(nv)
    n = nv(j);
    A = randn(n, n); 
    b=randn(n, 1);
    
    tic();
    x0 = gausselim(A,b);
    t_gausselim = toc();
    x0_norm = norm(A*x0-b);

    tic();
    x1 = A\b;
    t_matlab = toc();
    x1_norm = norm(A*x1-b);
    
    err_gauss(j) = x0_norm;
    err_backslash(j) = x1_norm;
    time_gauss(j) = t_gausselim;
    time_backslash(j) = t_matlab;
    
end

err_gauss
err_backslash
time_gauss
time_backslash

figure
semilogy(nv, time_gauss, nv, time_backslash)
title('Graph of time difference')
legend({'y = Gausseliminiation'; 'y = A/b'})


figure
semilogy(nv, err_gauss, nv, err_backslash)
title('Graph  of err in result (residual vector)')
legend({'y = Gausseliminiation'; 'y = A/b'})
```

### Svar(a)
Backslash generates a lesser error value and is faster.

### Svar(b)

|          |gausselim    | backslash   |
|----------|-------------|-------------|
| n = 25   |0.0005       |0.0991E-03   |
| n = 50   |0.0005       |0.0988E-03   |
| n = 100  |0.0038       |0.2816E-03   |
| n = 200  |0.0326       |0.5202E-03   |

När indata dubbleras så förväntas beräkningstiden att öka med en faktor av 2<sup>3</sup> = 8.

T(n) => O(n<sup>3</sup>) => c<sub>0</sub>n<sup>3</sup>
T(2n) => O((2n)<sup>3</sup>) => c<sub>1</sub>(2n)<sup>3</sup> = 2<sup>3</sup>c<sub>1</sub>n<sup>3</sup> = 8</sup>c<sub>1</sub>n<sup>3</sup>

Koden optimeras så det stämmer inte. 



### 3.
Vi vill bestämma samtliga rötter till följande ekvation
x − 4 sin 2x − 3 = 0

(a) Rita grafen för y(x) = x − 4 sin 2x − 3 med kommandot plot. Experimentera
med olika intervall i x-led och olika tabellsteg så att figuren till slut har med alla
nollställen till y(x).

(b) Bestäm den största och den minsta roten till ekvationen x − 4 sin 2x − 3 = 0 med
Newton-Raphsons metod. Redovisa startgissningar, antal iterationer och resultat
för de två rötterna.

(c<sup></sup>) Newton-Raphsons metod sägs ha kvadratisk konvergens. Förklara hur man kan
avläsa detta ur resultatutskriften.

(d) Är Newton-Raphson idiotsäker eller spårar den ur någon gång? Pröva t.ex. med
startvärdet x<sub>0</sub> = 7. Förklara beteendet. Motivera med hjälp av en lämplig figur.

(e) Bestäm den största roten med sekantmetoden med x<sub>0</sub> = 4 och x<sub>1</sub> = 5. Behöver
vi fler eller färre steg med sekantmetoden än Newton-Raphsons metod, för att till
exempel nå ett fel som är mindre än 10<sup>-10</sup>. Motivera.

### Svar:

Finding roots
```=
clc; clear; close all; % clear command window, clear system memory, close all figures;

% Function
f=@(x) x - 4*sin(2*x) - 3;
fprime=@(x) 1 - 8*cos(2*x);

% Plot function
plotXmin=-1; 
plotXmax=9;

ft =@(x) (fprime(7)*(x-7))+f(7);

% Variables and constants
TOLERANCE = 1e-10;
EPSILON = 1e-14; % safeguard against fprime becoming to small, this would imply fprime approx lim zero
initNewtonMin = -1;
initNewtonMax = 5;
initSecantX0 = 4.5;
initSecantX1 = 5;


[rootNewtonMin, numIterNewtonMin] = newton_raphson(f, fprime, initNewtonMin, TOLERANCE, EPSILON)
[rootNewtonMax, numIterNewtonMax] = newton_raphson(f, fprime, initNewtonMax, TOLERANCE, EPSILON)
[rootSecantMax, numIterSecantMax] = secant_method(f, initSecantX0, initSecantX1, TOLERANCE)


initNewtonRoot2 = -0.6;
initNewtonRoot3 = 1.6;
initNewtonRoot4 = 3.15;

[rootNewtonRoot2, numIterNewtonRoot2] = newton_raphson(f, fprime, initNewtonRoot2, TOLERANCE, EPSILON)
[rootNewtonRoot3, numIterNewtonRoot3] = newton_raphson(f, fprime, initNewtonRoot3, TOLERANCE, EPSILON)
[rootNewtonRoot4, numIterNewtonRoot4] = newton_raphson(f, fprime, initNewtonRoot4, TOLERANCE, EPSILON)

fplot(f, [plotXmin plotXmax]) % tabellsteg?
hold on;
fplot(0, [plotXmin plotXmax])
hold on;
fplot(ft, [plotXmin plotXmax])
hold on;

```

Newton Raphsons Method
```javascript=
function [x_ref, numIterations] = newton_raphson(f, fprime, initValue, tolerance, epsilon)

x=initValue;
dx=inf;
numIterations=0;
while abs(dx) > tolerance

    dx = f(x)/fprime(x);
    if (abs(fprime(x)) < epsilon)
        break;
    end
    x = x - dx;
    numIterations = numIterations + 1;
end

x_ref = x;
x = initValue;
format long;
errv=[];
dx=inf;

while abs(dx) > tolerance
    dx = f(x)/fprime(x);
    if (abs(fprime(x)) < epsilon)
        break;
    end    
    x = x - dx;
    absfel = abs(x-x_ref);
    errv = [errv absfel];
end
errv
end
```

Secant Method
```javascript=
function [xNext, iterCount] = secant_method(f, x0, x1, TOLERANCE)
% SECANT METHOD
iterCount = 0;
while (abs(x0-x1) > TOLERANCE)
    xNext = x1-( (f(x1)*(x1-x0))/(f(x1)-f(x0)));
    x0 = x1;
    x1 = xNext;
    iterCount = iterCount + 1;
end

```
**A)**
Kör koden för figur!

**B)**
| Description  | Value   |
|---|---|
|  rootNewtonMin     | -0.8984   |
|  numIterNewtonMin  | 5   |
|   rootNewtonMax     | 4.5178   |
|  numIterNewtonMax  | 5 |
| rootSecantMax | 4.5178   |
|   numIterSecantMax | 6  |

**C)**
Då man har löst f(x<sub>*</sub>)=0 en gång så kan man använda resultatet för att beräkna |x<sub>n</sub>>-(x<sub>*</sub>| och avläsa/beräkna hur snabbt metoden konvergerar mot x<sub>*</sub>.
(1)
0.017837538932826   
0.000803660380127   
0.000001795103171   
0.000000000008999
0

(2)
0.059254062920263   
0.001585673687428   
0.000000902735474   
0.000000000000294                   
0
**D)**
Den fastnar i en loop eller divergerar ut mot oändligheten. Förklara med ritning!

**E)**

Betraktar vi tabellen i B) framgår det att 

Vi behöver fler steg för sekantmetoden och det stämmer med teorin då sekantmetoden konvergerar med en exponentiellt med ~1.6. (Abs felvärdet)
Den är även mer känslig för dåliga start gissningar då den kräver 2 start värden. 



### 4.
Koordinaterna till punkten P skall bestämmas genom att man mäter avstånden till två kända punkter A och B. Se ﬁgur till höger (Detta sätt att bestämma en punkts okända koordinater kallas inom geodesin för inbindningsmetoden och är det som satellitnavigeringssystemet GPS utnyttjar.) Då gäller

(x<sub>A</sub> - x<sub>P</sub>)<sup>2</sup> + (y<sub>A</sub> - y<sub>P</sub>)<sup>2</sup> = L<sub>A</sub><sup>2</sup>

(x<sub>B</sub> - x<sub>P</sub>)<sup>2</sup> + (y<sub>B</sub> - y<sub>P</sub>)<sup>2</sup> = L<sub>B</sub><sup>2</sup>

Detta ekvationssystem har två lösningar, då det ju ﬁnns två punkter, P och P', som båda ligger på detta avstånd till punkterna A och B. 
Antag att man vet att de kända punkternas koordinater är A = (93, 63) och B = (6, 16), och de uppmätta avstånden är ungefär L<sub>A</sub> = 55.1 och L<sub>B</sub> = 46.2. Ekvationssystemet består av två ekvationer med två obekanta. Lös detta olinjära ekvationssystem med Newtons metod i ﬂera variabler. Hitta båda lösningarna.

### Svar:

Vi sätter in det som är känt i ekvationssystemet

(93 - x<sub>P</sub>)<sup>2</sup> + (63 - y<sub>P</sub>)<sup>2</sup> = (55.1)<sup>2</sup>
(6 - x<sub>P</sub>)<sup>2</sup> + (16 - y<sub>P</sub>)<sup>2</sup> = (46.2)<sup>2</sup>

Vi har nu två ekvationer med två obekanta, för att hitta lösningen till ekvationssystemet använder vi oss nu av Newton Raphson's metod. 

För att använde Newton's metod i flera variabler i matlab använder vi oss av följande kodstycke.
där x = x<sub>1</sub>, y = x<sub>2</sub> 

```=
f=@(x) [((93-x(1))^2)+((63-x(2))^2)-(55.1)^2; ((6-x(1))^2)+((16-x(2))^2)-(46.1)^2];
J=@(x) [2*(x(1)-93), 2*(x(2)-63); 2*(x(1)-6),  2*(x(2)-16)];
h=inf;
TOL=1e-10;
x=[20;10];
count = 0;
while (norm(h)>TOL)
h=J(x)\f(x);
x=x-h;
count = count + 1
end
x
h
count

```

Vi väljer startvärden genom att plotta upp de två cirklarna och ta ungefärliga mått, koden för plottningen ges här.

```=
plotcircle(93,63,55.1)
plotcircle(16,6,46.1)

function plotcircle(x,y,r)
tv=0:0.01:2*pi
hold on;
plot(x+r*cos(tv),y+r*sin(tv))
axis equal;

end
```

Startvärden för P blir följande:
x<sub>1</sub>, x<sub>2</sub> = (41.0, 44.7)

Efter 5 iterationer är toleransen uppnådd och vi ser att P:s lösning blir:

x<sub>1</sub> = x = 40.3532
x<sub>2</sub> = y = 46.7420

Startvärden för P' blir:
x<sub>1</sub>, x<sub>2</sub> = (60.7, 18.7)

Efter 6 iterationer är toleransen återigen uppnådd och vi får följande lösningar till P':

x<sub>1</sub> = x = 50.5429
x<sub>2</sub> = y = 27.8801

### 5. Sifferigenkänning
 Vi ska klassiﬁcera siffrorna som sparas som kolumner i matrisen testdata.
Genom att analysera stora mängder data har vi förberett så kallade centroider som sparas som kolumner i matrisen C. Centroider är bilder som motsvarar i princip ett genomsnitt av många handskrivna siffror, i detta fall för siffran 2. Vi ska nu försöka automatiskt känna igen alla tvåor i vår testdata. Eftersom alla centroider i C ser ut som en tvåa, kommer även en summa av centroider se ut som en tvåa. För att testa om en siffra (dvs en kolumn i matrisen testdata) är en tvåa försöker vi hitta den bästa linjärkombinationen av centroider som passar till testdata-vektorn. Kom ihåg att en linjärkombination skrivs som (med C = [c<sub>1</sub>, . . . , c<sub>k</sub>])
x<sub>1</sub>c<sub>1</sub> + · · · + x<sub>k</sub>c<sub>k</sub>.

a) 
Formulera problemet att hitta den bästa linjärkombinationen (dvs x1, . . . , xk) som ett överbestämt linjärt ekvationssystem. Hur många okända variabler har vi? Vad är högerledet? Här krävs ingen programmering.

b) 
Skriv ett program som för varje kolumn i testdata beräknar den bästa linjär-kombinationen och sedan beräknar hur bra linjärkombinationen passar till testbil-den, genom att beräkna ```nv(j)=norm(C*x-testdata(:,j))```, dvs normen av residualen av anpassningen.

c)
Ni har nu en vektor nv som är ett mått på hur bra linjärkombinationen passar till testsiffran. Om nv(j) är litet så borde testdata(:,j) se ut som en tvåa. Vi säger nu att om nv(j)<p för ett ﬁxerat värde p klassiﬁceras siffran som en tvåa. Välj
p som medelvärdet mellan mean(nv) och min(nv). Den korrekta klassiﬁcering av
siffran testdata(:,j) lagras i testdatad(j). Analysera hur bra er klassiﬁcerings-metod fungerar. Hur många procent av (alla) siffror klassiﬁceras felaktigt som en tvåa? Hur många procent tvåor missar ni?

### Svar a)
Vi vill hitta den bästa linjärkombinationen av centroider som passar till testdata-vektorn.
Vi har 50 okända variabler, högerledet blir våra testdata. 
Cx=t 

### Svar b)

```=
clear all; clc;

load('minidigits')
[r, col]=size(testdata);

for j=1:col
    t=testdata(:,j);
    x=C\t;
    nv(j)=norm(C*x-t);
end
```


### Svar c)
```=
clear all; clc;

load('minidigits')
[r, col]=size(testdata);

for j=1:col
    t=testdata(:,j);
    x=C\t;
    nv(j)=norm(C*x-t);
end

format short;

p=(mean(nv)+min(nv))/2
numGuessesTwo = 0;
numCorrectGuesses = 0;
numOfRealTwos = 0;

kv = []; % test data 
jv = []; % fin data index

for j=1:1000
    if testdatad(j) == 2
        numOfRealTwos = numOfRealTwos + 1;
        jv = [jv [j;1]];
    else
        jv = [jv [j;0]];
    end
end

jv = jv';

for j=1:length(nv)
    if nv(j)<p
            numGuessesTwo = numGuessesTwo + 1;
            if jv(j,2)
                numCorrectGuesses = numCorrectGuesses + 1;
            end
    end
end

jv;


numOfRealTwos;
numGuessesTwo;
numCorrectGuesses;


%%percentage of false positives
falsePositivesPercentages = (numGuessesTwo-numCorrectGuesses)/col
%percentage of missing two's
missingTwosPercentages = (numOfRealTwos-numCorrectGuesses)/col



clear all; clc;

load('minidigits')
[r, col]=size(testdata);

for j=1:col
    t=testdata(:,j);
    x=C\t;
    nv(j)=norm(C*x-t);
end

format short;

p=(mean(nv)+min(nv))/2
numGuessesTwo = 0;
numCorrectGuesses = 0;
numOfRealTwos = 0;

kv = []; % test data 
jv = []; % fin data index

for j=1:1000
    if testdatad(j) == 2
        numOfRealTwos = numOfRealTwos + 1;
        jv = [jv [j;1]];
    else
        jv = [jv [j;0]];
    end
end

jv = jv';

for j=1:length(nv)
    if nv(j)<p
            numGuessesTwo = numGuessesTwo + 1;
            if jv(j,2)
                numCorrectGuesses = numCorrectGuesses + 1;
            end
    end
end

jv;


numOfRealTwos;
numGuessesTwo;
numCorrectGuesses;


%%percentage of false positives
falsePositivesPercentages = (numGuessesTwo-numCorrectGuesses)/col
%percentage of missing two's
missingTwosPercentages = (numOfRealTwos-numCorrectGuesses)/col


clear all; clc;

load('minidigits')
[r, col]=size(testdata);

for j=1:col
    t=testdata(:,j);
    x=C\t;
    nv(j)=norm(C*x-t);
end

format short;

p=(mean(nv)+min(nv))/2
numGuessesTwo = 0;
numCorrectGuesses = 0;
numOfRealTwos = 0;

kv = []; % test data 
jv = []; % fin data index

for j=1:1000
    if testdatad(j) == 2
        numOfRealTwos = numOfRealTwos + 1;
        jv = [jv [j;1]];
    else
        jv = [jv [j;0]];
    end
end

jv = jv';

for j=1:length(nv)
    if nv(j)<p
            numGuessesTwo = numGuessesTwo + 1;
            if jv(j,2)
                numCorrectGuesses = numCorrectGuesses + 1;
            end
    end
end

jv;


numOfRealTwos;
numGuessesTwo;
numCorrectGuesses;


%%percentage of false positives
falsePositivesPercentages = (numGuessesTwo-numCorrectGuesses)/col
%percentage of missing two's
missingTwosPercentages = (numOfRealTwos-numCorrectGuesses)/col


```



