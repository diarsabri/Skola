clear all; clc;


v0=25; %utgångshastighet m/s

%vinkel från b)
vx0 = v0*cos(-0.575829542784826)*cos(pi/6);
vy0 = v0*sin(-0.575829542784826)*cos(pi/6);
vz0 = v0*sin(pi/6);

%initialvärdesvektor
u0 = [0, vx0, 0, vy0, 1.4, vz0]; %[x, x', y, y', z, z']

%steglängd
h = 1e-3;

x = u0;

%Antalet loopar = antalet studsar
for i=1:5
    
    %Kör tills bollen slår i marken
    while (x(5) >= 0)
    
    %RK4
    f1 = kast_mat(x); 
    f2 = kast_mat(x+(f1*h)/2); 
    f3 = kast_mat(x+(f2*h)/2); 
    f4 = kast_mat(x+(f3*h)); 
    
    fsum = (f1+2*f2+2*f3+f4)/6;
    x = x + fsum*h;
    plot3(x(1), x(3), x(5), 'r.');
    hold on
    end
    
    %byt riktning då boll slår i marken
    x(5) = x(5)*-1;
    %dämpningsfaktor, z-hastighet. 
    x(6) = x(6)*-0.8;
end
grid on;