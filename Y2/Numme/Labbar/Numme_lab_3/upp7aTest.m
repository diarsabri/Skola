clc; clf;
x0 = 0;
y0 = 0;
z0 = 1.4;

v0 = 25;

xp0 = v0*cos(pi/6);
yp0 = 0;
zp0 = v0*sin(pi/6);

ODE=@(q, t)[0 0 0 1 0 0;
    0 0 0 0 1 0;
    0 0 0 0 0 1;
    0 0 0 -q 0 0;
    0 0 0 0 -q 0;
    0 0 0 0 0 -q;
    ];

sv = [x0,y0,z0,xp0,yp0,zp0];
a = 0;
b = 1;
n = 10;
h = (b-a)/n;
x = a:h:40;
iteration = 0;
for i = x 
   sv = 
end




