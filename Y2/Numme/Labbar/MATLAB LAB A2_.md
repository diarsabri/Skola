# MATLAB LAB A2:

# 1.

**Uppgiftsbeskrivning**

## Svar
**a)**


Vi sätter in de kända värdena i ekvationerna och beräknar den jacobianska matrisen, därefter använder vi Gauss-Newton för att approximera en lösning för det överbestämda systemet.


**MATLAB**
```=
f=@(x) [((93-x(1))^2)+((63-x(2))^2)-(55.1)^2; ((6-x(1))^2)+((16-x(2))^2)-(46.1)^2; ((20-x(1))^2)+((83-x(2))^2)-(46.2)^2];
J=@(x) [2*(x(1)-93), 2*(x(2)-63); 2*(x(1)-6),  2*(x(2)-16); 2*(x(1)-20), 2*(x(2)-83)];
h=inf;
TOL=1e-10;
x=[20;10];
count = 0;
while (norm(h)>TOL)
h=J(x)\f(x);
x=x-h;
count = count + 1;
end
x
h
count
```

Eftersom vi har ett överbestämt ekvationssystem måste vi använda Gauss-Newton för att approximera en lösning till systemet.

**b)** 
Med startgissning **X<sub>1,2</sub> = (41.0, 44.7)** ges, efter 9 iterationer, approximationen: **X<sub>1,2</sub> = (42.4134, 42.8719)**

Med startgissning **X<sub>1,2</sub> = (60.7, 18.7)** ges, efter 11 iterationer, approximationen: **X<sub>1,2</sub> = (42.4134, 42.8719)**

**c)**
Ritar upp cirklarna m.h.a följande kodstycke:
```=
plot(x0, x1, '.');
plotcircle(93,63,55.1)
plotcircle(16,6,46.2)
plotcircle(20,83,46.2)

function plotcircle(x,y,r)
tv=0:0.01:2*pi;
hold on;
plot(x+r*cos(tv),y+r*sin(tv));
axis equal

end
```

Det finns ingen punkt där alla cirklarna skär varandra samtidigt, vilket är fullt rimligt, ty vårt ekvationssystem är överbestämt och saknar en lösning. Punkten som ges är ett approximativt värde på där cirklarna nästan skär varandra i mitten.

# 2.

**Uppgiftsbeskrivning**

## Svar

**a)** Härledning
![!](http://i.imgur.com/9bESqAr.jpg)

**b)**

**MATLAB**
```
clc; clear all;
f=@(x) cos(1+x);
g=@(x) (f(x)-f(-x))/(2*x);
ref_x = -sin(1)
kv = [1 0.5 0.25 0.125 0.0625];
dhv = [];
errv = [];
format long;
for j = 1:length(kv)
    dhv(j) = g(kv(j));
    errv(j) = abs(dhv(j)-ref_x);
end

d_h = dhv'
err = errv'

```
| h    | d(h)     | abs[d(h) - y'(1)] |
|------|----------|----------------|
| 1    | -0.70807 | 0.13339        |
| 1/2  | -0.80684 | 0.03462        |
| 1/4  | -0.83273 | 0.00873        |
| 1/8  | -0.83928 | 0.00218        |
| 1/16 | -0.84092 | 0.00054        |

**c)**
Om h halveras så får vi:
T(h) = O(h<sup>2</sup>) => T(h/2) = O(h<sup>2</sup>/2<sup>2</sup>) = O(h<sup>2</sup>/4) 

# 3.
**Uppgiftsbeskrivning**

## Svar

**a)** 
Cirka 2,7 areaenheter. 
Vi delar upp arean under funktionen sqrt(x+2) i en triangel och en rektangel och räknar ut dessa areor separat för att sedan summera ihop dem.

**b)**
2*sqrt(3)-2/3, vilket ungefär är lika med 2.797...

![!](http://i.imgur.com/LEt4ifJ.jpg)

**c)** 
Trapetsregeln är en variant av riemann-summor där man delar upp en funktion i N stycken delintervall med längden **h=(b-a)/N** och tillämpar **(b-a)*(f(a)+f(b))/2** på vart och ett av delintervallen, vilket ger en approximation av integralen vars fel är proportionellt mot valet av **h**.

**d)** 
Vi kör följande matlab-kod:
**MATLAB**
```=
clc
close all
f=@(x) sqrt(x+2);

x0=-1;
xn=1;
h=0.0625;
area=0;
error=0;

exact=integral(f,-1,1);

while(x0<xn)
    area=area+(h/2)*(f(x0)+f(x0+h));
    x0=x0+h;
end

fprintf('Area =%f', area)
fprintf('Error =%f', abs(area-exact))
```


|     h   |T(h)         |T(h)-I)      |
|---------|-------------|-------------|
| 1       |2.780239     |0.017196     |
| 0.5     |2.783061     |0.004374     |
| 0.25    |2.796336     |0.001099     |
| 0.125   |2.797160     |0.000275     |
| 0.0625  |2.797366     |0.000069     |

**e)**
Enligt teorin så förminskas felet i en takt som är ungefär propertionerlig mot **h<sup>2</sup>**, detta betyder att en halvering av h borde leda till att felet minskar med en faktor 1/4, vilket stämmer med tabellen ovan.

**f)**

Richardson-extrapolation används för att minska fel eller förbättra approximationer vid användning av stegmetoder som t.ex. trapetsregeln.
Eftersom minskningen är med faktor 4 kan vi använda extrapolation för att få en bättre uppskattning på integralvärdena.

Vi använder formeln T(h)<sub>r</sub>=T(h)+(T(h)-T(2h))/(2<sup>2</sup>-1)
eller följande kod i matlab:

**MATLAB**
```=
f=@(x) sqrt(x+2);
correct = integral(f, -1, 1)
h=1

%Richardson extrapolation
T = trapzoid(h);
R = trapzoid(h)+(trapzoid(h)-trapzoid(2*h))/(2^2-1)
Terror = abs(T-correct);
Rerror = abs(R-correct)


function [area] = trapzoid(h)
clc
close all;

f=@(x) sqrt(x+2);

x0=-1;
xn=1;
area=0;
while(x0<xn)
    area=area+(h/2)*(f(x0)+f(x0+h));
    x0=x0+h;
end
area;
end
```
Efter att ha applicerat Richardson-extrapolation på uppgift d) ser vi att felet blir betydligt mindre:

|     h   |T(h)         |T(h)-I       |
|---------|-------------|-------------|
| 1       |2.796302     |0.001133     |
| 0.5     |2.797335     |9.949210-e05 |
| 0.25    |2.797428     |7.157379e-06 |
| 0.125   |2.797434     |4.680911e-07 |
| 0.0625  |2.797434     |2.962273e-08 |


# 4.
**Uppgiftsbeskrivning**

## Svar

**a)** 
```
clf;

f=@(z, x) (sin(pi*z.*x).*exp(x));
a = 0;
b = 1;
n = 1000;           % antal intervall

x = a:h:b;
h = (b-a)/n;        % steglängd
zv = 1:0.1:10;

count = 1;


for i=-3:0.01:3
    y=(sum(f(i, x))-(f(i, a)+f(i, b))/2)*h;
    plot(i,y,'*');
    hold on;
end

I=@(z) (sum(f(z, x))-(f(z, a)+f(z, b))/2)*h-1;

z0 = 0.3;
z1 = 0.5;
TOLERANCE = 1e-10;
iterCount= 0;

while (abs(z0-z1) > TOLERANCE)
    zNext = z1-( (I(z1)*(z1-z0))/(I(z1)-I(z0)));
    z0 = z1;
    z1 = zNext;
    iterCount = iterCount + 1;
end
```
f(z) då z 0.3595 är ~= 1 inom ett intervall av 1e-10

Vi utvärderar f(z) stegvis från -3 till +3 med en steglängd på 0.01 och vidare plottar våra värden för att få fram våra startgissningar som behövs för sekantmetoden. Vi använder sekantmetoden för att lösa f(z) = 1 => f(z) - 1 = 0 och hitta z värdet.

Varje metod som används ger ett approximationsfel. 

# 5.
**Uppgiftsbeskrivning**

## Svar

**a)** 
Vi löser problemet med att använda Newtons Metoder i flera variablar.
```
f=@(x) [cos(x(1)) + cos(x(2)) - 1.3; sin(x(1)) + sin(x(2)) - 1.3;
    cos(x(1)); sin(x(1))]
J=@(x) [-sin(x(1)), -sin(x(2)); 
    cos(x(1)), cos(x(2)); 
    -sin(x(1)), 0; 
    cos(x(1)), 0]

x = [pi/3; pi/5];

h = inf;
TOL = 1e-10;
count = 0;
while (norm(h) > TOL)
    h=J(x)\f(x);
    x = x-h;
    count = count + 1;
end

x
```
x1 = 1.1901
x2 = 0.3807

**b)**
![Second order -> First Order DiffEQ](http://i.imgur.com/pV6twL8.jpg)



**c)** 
Eulers metod i diskret form:
y<sub>k+1</sub> = y<sub>k</sub>+f(t<sub>k</sub>, y<sub>k</sub>)*h(<sub>k</sub>)

Vi använder begynnelsenvärden som vi fick utifrån instruktionerna och utnyttjar föregående uppgift då vi fick fram ett system av första ordningens differentialekvationer för att fortsättningvis kunna tillämpa ODE. 
```
clc; clear; close all;
f=@(x) [cos(x(1)) + cos(x(2)) - 1.3; sin(x(1)) + sin(x(2)) - 1.3;
    cos(x(1)); sin(x(1))]
J=@(x) [-sin(x(1)), -sin(x(2)); 
    cos(x(1)), cos(x(2)); 
    -sin(x(1)), 0; 
    cos(x(1)), 0]

x = [pi/3; pi/5];

h = inf;
TOL = 1e-10;
count = 0;
while (norm(h) > TOL)
    h=J(x)\f(x);
    x = x-h;
    count = count + 1;
end

thetav = x % set angles

u = [pi/4, 0, 0, 0]; % u at t = 0 // initial condition
h = 1e-3; % h step size
t = 0;
t_final = 50;
alpha = 2;
gamma = 1;
omega = 2*pi;
beta = 0.3;

k = 0;
hold on

for k = 1:length(t:h:t_final)
    u = u + h*f_robotarm(u,t,thetav(1),thetav(1),alpha,beta,gamma,omega)
    if (mod(k, 10)==1)
        axis([0 3 0 3])
        hold on
        plot_robotarm([u(1), u(3)])
        clf
    end
    t = t + h;
end

```