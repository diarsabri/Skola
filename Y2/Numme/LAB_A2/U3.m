clear all;clc
f = @(x) sqrt(x+2);
xb = -1; xt = 1; 
h = 0.0625 ; %byt till motsvarande h
actual = integral(f,-1,1);
area = 0;
while(xb<xt)
    area = area+(h/2)*(f(xb)+f(xb+h));
    xb = xb+h;
end
err = abs(area-actual)
area
