clear all; clc;

%utgångshastighet
v0 = 25;

%initialvärde
u0 = [0, v0*cos(pi/6), 0, 0, 1.4, v0*sin(pi/6)]; %[x, x', y, y', z, z'];

%steglängd
h = 1e-4;

x = u0;

%loopa rk4 tills bollen nuddar marken.
x=rk4loop(x, h);

nedslag = [x(1), x(3), x(5)]

