clear all;clc
f=@(x) sqrt(x+2);
actual = integral(f, -1, 1);
h=0.0625;

%Richardson extrapolation
T = trapzoidrule(h);
R = trapzoidrule(h)+(trapzoidrule(h)-trapzoidrule(2*h))/(2^2-1)
T_error = abs(T-actual);
R_error = abs(R-actual)


function [ivalue] = trapzoidrule(h)

f=@(x) sqrt(x+2);

xb=-1;
xt=1;
ivalue=0;
while(xb<xt)
    ivalue=ivalue+(h/2)*(f(xb)+f(xb+h));
    xb=xb+h;
end
ivalue;
end