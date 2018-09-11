sv=[ 0; 0; 1.4; cos(pi/6)*25; 0; sin(pi/6)*25 ]; %startvector
h=0.01;

i=0;
v=eulersteg(h, sv);
while i<4
    v(3)=v(3)*-1;
    v(6)=v(6)*-0.8;
    v=eulersteg(h, v);
    i=i+1;
end