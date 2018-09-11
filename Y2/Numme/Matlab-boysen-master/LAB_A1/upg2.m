clear all;
clc;

n=800; A=randn(n,n); b=randn(n,1);
tic();
%x=A\b;

x=inv(A)*b;
%x = gausselim(A,b);
t=toc()
%norm(A*x-b)