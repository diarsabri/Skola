clf; clc;

h2=0.01;
i=0;
% Draw the pole. 
while i < 3.5
    plot3(6,2,i, 'b.');
    hold on;
    i=i+h2;
end
plot3(6 , 2, 3.6, 'y*');
grid on;

alfa = [1.047, 0.2618];
startvector = @(vector) [ 0; 0; 1.4; cos(vector(1))*cos(vector(2))*15; 15*cos(vector(1))*sin(vector(2)); sin(vector(1))*15];
vector = startvector(alfa);

h = 0.01;
n=0;
while vector(3) < 3.5
    vector=eulersteg(h, vector);
    plot3(vector(1), vector(2), vector(3), '.');
    vector;
    n=n+1;
end
n


%function
g2=@(theta, h) [eulersteg2(h, startvector(theta))-2, eulersteg2(h, startvector(theta))-3];
a=g2(alfa, 0.01);
J2=@(theta, h) [];


% function g for angles. 
g=@(theta) [(-15*cos(theta(1))*sin(theta(2)))-2; (15*sin(theta(1)))-3.5];
% Jacobian
J=@(theta) [-sin(theta(1))*sin(theta(2))*-15, -15*cos(theta(2))*cos(theta(1));cos(theta(1))*15, 0];


theta=[pi/3;pi/12];
ERROR=1e-10;
iteration=0;
h2=inf;
while abs(norm(h2)) > ERROR
    h2=J(theta)\g(theta);
    theta=theta-h2;
    iteration=iteration+1;
    theta;
end
iteration;
% Executing Euler's method 
theta1=[1, -0.58];
startvector=@(vector) [0;0;1.4;cos(vector(1))*cos(vector(2))*15; 15*cos(vector(1))*sin(vector(2));sin(vector(1))*15];
vector=startvector(theta1);
% v=eulersteg(0.01, startvector(theta1));


