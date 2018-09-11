function [s] = eulersteg(h, s)
%s is the startvector [x,y,z,xprim, yprim, zprim].
%h is length of the steps we take in Eulers method.

    vk=@(q,s)[0, 0, 0, 0, q*a(s(3)), -9.81]'; % Constants in the system.
    
    ODE=@(q)[0  0  0 1  0 0;  
             0  0  0 0  1 0;
             0  0  0 0  0 1;
             0  0  0 -q 0 0;
             0  0  0 0 -q 0;
             0  0  0 0 0 -q;
             ];
    
         
    %Euler method. 
    while s(3)>0 
        q1=q(s);
        s=s+(ODE(q1)*s+vk(q1, s))*h;
        plot3(s(1), s(2), s(3), '.');
        hold on;
    end
    grid on;
end