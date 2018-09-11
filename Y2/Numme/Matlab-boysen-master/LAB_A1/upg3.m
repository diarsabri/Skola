x = -5:0.00001:10;
y = @(x) x-4*sin(2*x)-3;
yp = @(x) 1-8*cos(2*x);
plot(x,y(x));

xL = xlim;
yL = ylim;
line([0 0], yL);  %x-axis
line(xL, [0 0]);  %y-axis

x = -1;
x1 = fzero(y,x);

NR = @(x) x-(x-4*sin(2*x)-3)./(1-8*cos(2*x));

% x0 = -1;
%Efter tre iterationer kommer vi till x4 = -0.898356581554583

% x0 = -0.5;
%Efter 2 iterationer kommer vi till x3 = -0.544442395963611

% x0 = 1.5;
%Efter 1 iteration kommer vi till x2 = 1.732069387491808

% x0 = 3;
%Efter 2 iterationer kommer vi till x3 = 3.161826486551824

% x0 = 4.5;
%Efter 1 iteration kommer vi till x2 = 4.517789519604580

% Sätter man x0 = 7 blir det konstigt. Vid tio iterationer ser
% man att det går upp och ner i värden. Flera olika värden mellan
% 6.7 och 7.4 antas.

SM = @(x0,x1) x1 -((x1-x0)*(x1-4*sin(2*x1)-3))/((x1-4*sin(2*x1)-3)-((x0-4*sin(2*x0)-3)));
% Använd fzero(y,4) (det är det korrekta värdet)
% Efter 5 iterationer får vi att skillnaden mellan x6 och fzero
% är mindre än 10^-10. Med andra ord tar det fem iterationer.

TOL= 1e-10;
h = inf;
k = 0;
felv = [];
xv = [];
while (abs(h) > TOL)
    h = y(x)/yp(x);
    x = x-h;
    fel = abs(x-x1);
    k = k+1;
    xv = [xv;k];
    felv = [felv;fel];
end
%plot(xv,felv)
fel
felv
k
x
