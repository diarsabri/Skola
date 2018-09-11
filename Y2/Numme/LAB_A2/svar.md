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
### a)
![!](https://i.imgur.com/SWC6RCl.jpg)
### c)

Om h halveras så får vi att:
T(h) = O(h)
=> T(h/2) = O(h/2)

Felet förändras alltså med en halv.

# Uppgift 3
## Svar

**a)**
Cirka 2,7 areaenheter.
Vi delar upp arean under funktionen sqrt(x+2) i en triangel och en rektangel och räknar ut dessa areor separat för att sedan summera ihop dem.

**b)**
2*sqrt(3)-2/3, vilket ungefär är lika med 2.797...

![!](http://i.imgur.com/LEt4ifJ.jpg)

**note)**
Trapetsregeln är en variant av riemann-summor där man delar upp en funktion i N stycken delintervall med längden **h=(b-a)/N** och tillämpar **(b-a)*(f(a)+f(b))/2** på vart och ett av delintervallen, vilket ger en approximation av integralen vars fel är proportionellt mot valet av **h**.

**c)**
Vi kör följande matlab-kod för approx utan Richardson:


|     h   |T(h)         |abs(T(h)-I)  |R(h)         |abs(R(h)-I)  |
|---------|-------------|-------------|-------------|-------------|
| 1       |2.780239     |0.017196     |2.796302     |0.001133     |
| 0.5     |2.783061     |0.004374     |2.797335     |9.949210-e05 |
| 0.25    |2.796336     |0.001099     |2.797428     |7.157379e-06 |
| 0.125   |2.797160     |0.000275     |2.797434     |4.680911e-07 |
| 0.0625  |2.797366     |0.000069     |2.797434     |2.962273e-08 |

Följande kod används för att approximera med Richardson-extrapolation:


**d)**
Enligt teorin så förminskas felet i en takt som är ungefär propertionerlig mot **h<sup>2</sup>**, detta betyder att en halvering av h borde leda till att felet minskar med en faktor 1/4, vilket stämmer med tabellen ovan.

**e)**

Richardson-extrapolation används för att minska fel eller förbättra approximationer vid användning av stegmetoder som t.ex. trapetsregeln.
genom att använda formeln ABS(R(2h)-I))/(ABS(R(h)-I)) så kan får vi att om man minskar h med en faktor av två så minskar felet med en faktor av ungefär 16. 4=2^2 ---- 16=2^4 dvs att nogrannhetsordningen är  O(h^4)

# Uppgift 4
Se matlab

simpson mer exakt för polynom

# Uppgift 5
### a)
Vi använder newtons metod i flera variabler, efter 50 iterationer ger detta oss thetav = [1.190051800266113,   0.380744526480744]
ej överbestämt. 
### b)


### c)
Vi använder eulers metod för ODE system (enl instruktion). Använder inputen från a och b. För att köra: först 5 sen 5_2
y<sub>k+1</sub> = yk+f(t<sub>k</sub>, y<sub>k</sub>)\*h<sub>(k)</sub>
