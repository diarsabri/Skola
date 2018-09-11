clear all; clc;

%steglängd
h = 1e-3;

%begynnelsehastighet
v0 = 25; %m/s

%kastvinkel i vertikalplan
a0 = pi/6;

%begynnelsehastighet i x- och y-led
%beroende av b, startvinkel i xy-planet
vx0 = @(b) cos(b)*cos(a0)*v0;
vy0 = @(b) sin(b)*cos(a0)*v0;

b = pi/3; %startgissning

%initialvärden
u0 = [0, vx0(b), 0, vy0(b), 1.4, v0*sin(pi/6)]; % [x, x', y, y', z, z']

y=inf;
TOL=1e-10;

u=u0;
v=u0;

%iterera tills bollen landar så nära y=0 som möjligt m.h.a Newtons metod
while(abs(y)>TOL)
    
    u(2)=vx0(b);
    u(4)=vy0(b);
    
    v(2)=vx0(b+h);
    v(4)=vy0(b+h);
    
    %landningsposition
    %kommentera ut plottning i rk4loop för snabbare körning
    uy = rk4loop(u, h);
    
    %landningsposition för bollen med lite större vinkel
    vy = rk4loop(v, h);
    
    
    y = uy(3);
    y_new = vy(3);
    
    %Newtons metod, x1=x0-f(x0)/f'(x0)
    b = b-y/((y_new-y)/h);
    
end

b
radtodeg(b)



