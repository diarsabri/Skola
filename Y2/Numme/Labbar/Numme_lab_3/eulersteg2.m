function [v] = eulersteg2(h, sv)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
    vk=@(q,s)[0, 0, 0, 0, q*a(s(3)), -9.81]'; %constants in the system. 
    ODE=@(q)[0 0 0 1 0 0; %Second order ODE to a system of first order ODE. 
        0 0 0 0 1 0;
        0 0 0 0 0 1;
        0 0 0 -q 0 0;
        0 0 0 0 -q 0;
        0 0 0 0 0 -q;
        ];
    q1=q(sv);
    v=sv+(ODE(q1)*sv+vk(q1, sv))*h;
end