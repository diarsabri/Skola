clc; clf;

v0 = 15;    % Utgångshastighet
theta = [pi/3 pi/12];   % Utkastvinkel & vridning
sv = [ 0; 0; 1.4; cos(theta(1))*v0; v0*cos(theta(1))*sin(theta(2)); sin(theta(1))*v0];  % Startvektor
sv =@(alpha, beta )

G=[0;0;1.4;0;0;sin(pi/6)*25]; % Hastighet i x-led
Gd=[0;0;1.4;0;0;sin(pi/6)*25]; % Hastighet i y-led

posv = [6, 2, 3.5]; % Position för stolpe
posv0 = [0, 0, 0]; % Position för kurvans närmsta punkt till stolpen

alpha = pi/3;
xprim=@(alpha) cos(pi/6)*cos(alpha)*25;
yprim=@(alpha) cos(pi/6)*sin(alpha)*25;

eulersteg(h, sv)

% Ritar stolpen
zpryl = 0;
while zpryl <= 3.5
    plot3(posv(1), posv(2), zpryl, 'b.');
    zpryl = zpryl + h;
end
plot3(posv(1), posv(2), zpryl, 'y*'); % Högsta punkt

% sv=[0,0,1.4,0,0,sin(pi/3)*15];
% xprim=@(alfa, beta) cos(alfa)*cos(beta)*15;
% yprim=@(alfa, beta) cos(alfa)*sin(beta)*15;
% 
% % startvärde
% alfa=pi/3;
% beta=pi/12;
% h=0.01;
% xstart=[0,0,1.4,0,0,sin(pi/3)*15];
% dxstart=[0,0,1.4,0,0,sin(pi/3)*15];
% ystart=[0,0,1.4,0,0,sin(pi/3)*15];
% dystart=[0,0,1.4,0,0,sin(pi/3)*15];
% 
% xstart(4)=xprim(alfa, beta);
% dxstart(4)=xprim(alfa+h, beta+h);
% ystart(4)=yprim(alfa, beta);
% dystart(4)=yprim(alfa+h, beta);
