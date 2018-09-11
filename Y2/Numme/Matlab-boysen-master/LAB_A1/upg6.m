clear all; clc;

load('minidigits')
[r, col]=size(testdata);

for j=1:col
    t=testdata(:,j);
    x=C\t;
    nv(j)=norm(C*x-t);
end
nv