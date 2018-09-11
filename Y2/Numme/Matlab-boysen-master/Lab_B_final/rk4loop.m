function [ z ] = rk4loop(z, h)
u=z;
    %stoppar när u(5), dvs z-komponenten är nära 0.
    while (u(5) > 0)
     
     %Runge-Kutta 4
     %Samma som förra årtets inlämning
     f1 = kast_mat(u); 
     f2 = kast_mat(u+(f1*h)/2); 
     f3 = kast_mat(u+(f2*h)/2); 
     f4 = kast_mat(u+(f3*h)); 
    
     fsum = (f1+2*f2+2*f3+f4)/6;
     u = u + fsum*h;
     
     %kommentera ut för bättre performance på b)
     plot3(u(1), u(3), u(5), 'r.');
     hold on;
     
    end
    grid on;
z=u;    
end