function [ x ] = rk4loopx(x, h)
u=x;
    %stoppar när u(1), dvs x-komponenten är nära 6.
    while (u(1) < 6)
     
     %Runge-Kutta 4
     %samma som inlämningen förra året
     f1 = kast_mat(u); 
     f2 = kast_mat(u+(f1*h)/2); 
     f3 = kast_mat(u+(f2*h)/2); 
     f4 = kast_mat(u+(f3*h)); 
    
     fsum = (f1+2*f2+2*f3+f4)/6;
     u = u + fsum*h;
    end
x=u;    
end