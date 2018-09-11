clc;
clf;
sv=[0;0;1.4;cos(pi/6)*25;0;sin(pi/6)*25]; %Startgissning utifrån frågan. 
vk=@(q,s)[0, 0, 0, 0, q*a(s(3)), -9.81]';

ODE=@(q)[0 0 0 1 0 0;
    0 0 0 0 1 0;
    0 0 0 0 0 1;
    0 0 0 -q 0 0;
    0 0 0 0 -q 0;
    0 0 0 0 0 -q;
    ];

h=0.005;
iteration = 0;
while sv(3) >= 0 
    q1=q(sv);
    
    sv=sv+(ODE(q1)*sv+vk(q1, sv))*h;
  
    plot3(sv(1), sv(2), sv(3), '*');
    hold on;
    grid on;
    iteration=iteration+1;
end
sv
iteration