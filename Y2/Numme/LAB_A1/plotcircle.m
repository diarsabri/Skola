plotcircle2(93,63,55.1)
plotcircle2(16,6,46.1)

function plotcircle2(x,y,r)
tv=0:0.01:2*pi
hold on;
plot(x+r*cos(tv),y+r*sin(tv))
axis equal;

end