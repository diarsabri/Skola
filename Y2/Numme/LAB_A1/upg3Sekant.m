f=@(x) x-4*sin(2*x)-3;


x0=4;
x1=5;
TOL=1e-10;
h=inf;
k=0
while (abs(h)>TOL)
    h=((x1-x0)/(f(x1)-f(x0)))*f(x1);
    x2=x1-h;
    x0=x1;
    x1=x2;
    h
    k=k+1
end
h
x2