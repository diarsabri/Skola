clear all; clc;


v0=25; %utg�ngshastighet m/s

%vinkel fr�n b)
vx0 = v0*cos(-0.575829542784826)*cos(pi/6);
vy0 = v0*sin(-0.575829542784826)*cos(pi/6);
vz0 = v0*sin(pi/6);

%initialv�rdesvektor
u0 = [0, vx0, 0, vy0, 1.4, vz0]; %[x, x', y, y', z, z']

%stegl�ngd
h = 1e-3;

x = u0;

%Antalet loopar = antalet studsar
for i=1:5
    
    %K�r tills bollen sl�r i marken
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
    
    %byt riktning d� boll sl�r i marken
    x(5) = x(5)*-1;
    %d�mpningsfaktor, z-hastighet. 
    x(6) = x(6)*-0.8;
end
grid on;