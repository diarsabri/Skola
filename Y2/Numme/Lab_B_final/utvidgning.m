clear all; clc;

%Utg�ngshastighet
v0=15;

vx0=@(a) cos(a(1))*cos(a(2))*v0; %hastighet i x-led
vy0=@(a) cos(a(1))*sin(a(2))*v0; %hastighet i y-led
vz0=@(a) sin(a(1))*v0; %hastighet i z-led

%initialv�rdesvektor f�r rk4
F=@(a)[0, vx0(a), 0, vy0(a), 1.4, vz0(a)];

%Pelles startvinkel, 60 respektive 15 grader.
sa=[pi/3;pi/12];




%stegl�ngd, 10^-4, f�r RK4 och differens i jacobian.
h=1e-4;

%korrektion f�r Newtons metod. 
h2=inf;

%initiering av Jacobianen
Jf=[0,0;0,0];

%antalet iterationer f�r Newton
i = 0;

%tolerans som kr�vdes f�r att f� ett absolutfel mindre �n 10^-4
%vid anv�ndning av Newtons metod
TOL = exp(-9.3);

%kollar om toleransen uppn�tts
while norm(h2)>TOL
    
    v=F(sa);
    
    %approximera diffen d� x=6
    v=rk4loopx(v, h);
    y=v(3);
    z=v(5);
    
    %addera steg p� f�rsta vinkeln
    sa(1)=sa(1)+h;
    v1_step=F(sa);
    
    %approximera diffen igen
    v1_step=rk4loopx(v1_step, h);
   
    y1=v1_step(3);
    z1=v1_step(5);
    
    %�terst�ll f�rsta, addera steg p� andra vinkeln
    sa(1)=sa(1)-h;
    
    sa(2)=sa(2)+h;
    v2_step=F(sa);
    
    %approximera diff igen. 
    v2_step=rk4loopx(v2_step, h);
    
    y2=v2_step(3);
    z2=v2_step(5);
    
    %newton i flera variabler
    %jacobian approximerad med fram�tdifferens
    f=[y-2;z-3.5];
    Jf(1,1)=(y1-y)/h;
    Jf(1,2)=(z1-z)/h;
    Jf(2,1)=(y2-y)/h;
    Jf(2,2)=(z2-z)/h;
    
    h2=Jf\f;
    sa=sa-h2;
    
    i=i+1;
    
    
end

i

%resultat i grader
radtodeg(sa)

%plotta stolpe
s=0;
while s <= 3.5
   
   plot3(6,2,s,'black.');
   hold on;
   
   s=s+0.001;
end

%plotta resultatdiffen med r�tt startvinklar
v=F(sa);
rk4loopxplot(v, h);

grid on;