clc; clf;

xprim=@(alpha) cos(pi/6)*cos(alpha)*25;
yprim=@(alpha) cos(pi/6)*sin(alpha)*25;

G=[0;0;1.4;0;0;sin(pi/6)*25]; % Hastighet i x-led
Gd=[0;0;1.4;0;0;sin(pi/6)*25]; % Hastighet i y-led

h = 0.01;
y = 1;
alpha = pi/3;
error = exp(-12);

% Newtons method
iteration = 0; 
while abs(y) > error
    clf;
    G(4) = xprim(alpha);  % Ber�knar utg�ngshastigheten i x-led
    G(5) = yprim(alpha);  % Ber�knar utg�ngshastigheten i y-led
    Gd(4) = xprim(alpha+h);   % Ber�knar utg�ngshastigheten med f�r�ndrad vinkel i x-led
    Gd(5) = yprim(alpha+h);   % Ber�knar utg�ngshastigheten med f�r�ndrad vinkel i y-led
    yv = eulersteg(h, G);     
    dyv = eulersteg(h, Gd);
    y = yv(2);
    dy = dyv(2);
    korr = y/((dy-y)/h); %correction 
    alpha = alpha-korr;
    iteration = iteration + 1;
end
iteration