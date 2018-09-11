clear all;clc
th = 0:pi/50:2*pi;
xunit = 55.1*cos(th)+93;
yunit = 55.1*sin(th)+63;
plot(xunit,yunit);
hold on
xunit2 = 46.2*cos(th)+6;
yunit2 = 46.2*sin(th)+16;
plot(xunit2,yunit2);
hold on
xunit3 = 46.2*cos(th)+20;
yunit3 = 46.2*sin(th)+83;
plot(xunit3,yunit3);

xL = xlim;
yL = ylim;
line([0 0], yL);  %x-axis
line(xL, [0 0]);  %y-axis

f=@(x) [((93-x(1))^2)+((63-x(2))^2)-(55.1)^2; 
    ((6-x(1))^2)+((16-x(2))^2)-(46.1)^2; 
    ((20-x(1))^2)+((83-x(2))^2)-(46.2)^2];
J=@(x) [2*(x(1)-93), 2*(x(2)-63); 
    2*(x(1)-6),  2*(x(2)-16); 
    2*(x(1)-20), 2*(x(2)-83)];
h = inf;
TOL=1e-10;
x = [50.542939175325429;27.880091313759308];   
% startgissningar: x=[40;45] , x=[50;25] ,
% x = [40.353195003443240;46.741958185115713] (lab A1)
% x = [50.542939175325429;27.880091313759308] (lab A1)
counter = 0;
while (norm(h)>TOL)
h=J(x)\f(x);
x=x-h;
counter = counter +1;
end
counter
x
hold on
plot(x(1),x(2),'.')
