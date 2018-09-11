clear all;clc;clf

thetav=[pi/3;pi/5]; %startvï¿½rden

fx=@(x) [cos(x(1)); cos(x(1))+cos(x(2))-1.3;
    sin(x(1)); sin(x(1))+sin(x(2))-1.3];

%jacobimatrisen av vï¿½rt ï¿½verbestï¿½mda ekv.system
Jx=@(x) [-sin(x(1)), 0; -sin(x(1)), -sin(x(2));
    cos(x(1)), 0; cos(x(1)), cos(x(2))];

TOL=1e-12;
h=inf;
plot_robotarm(thetav); %plot fï¿½re korrigering
hold on;
while norm(h) > TOL
    h=Jx(thetav)\fx(thetav); %lï¿½ser ett minstakvadrat-problem
    thetav=thetav-h; %korrigering
end

thetav
plot_robotarm(thetav); %plot efter korrigering
hold on;
plot(1.3, 1.3, '-mo','MarkerSize',25); %ref.punkt fï¿½r att se att jag hamnat rï¿½tt
