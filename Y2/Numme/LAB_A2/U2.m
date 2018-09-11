%http://www.math.chalmers.se/Math/Grundutb/CTH/tmv225/1213/bm/bm_kapitel_6.pdf
clear all;clc
f = @(x) tan(x);
g = @(h) (f(1+h)-f(1))/h; %Approximations-ekvationen
hP = [1 0.5 0.25 0.125 0.0625]; %varje h
dhP = [];
errP = [];
format long;
for j = 1:length(hP)
    dhP(j) = g(hP(j));  %approximationsfunktionen på varje h, sätts i en vektor
    errP(j) = abs(dhP(j)-(1/cos(1)^2)); %felet
end
%allt under är för att rita en tabell
dh = dhP';
err = errP';
A =[hP',dh,err];
T = array2table(A);
T.Properties.VariableNames = {'h' 'Approximation' 'Fel'}
