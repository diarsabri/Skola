clear all;clc
%max function
T=@(t) max(generator(30 + t, 0));

%funktion för nämnaren
f_namnare=@(t) exp(-t.^2);
%räkna ut nämnaren numeriskt med trapets
namnare=@(h) (h/2)*(f_namnare(-5)+f_namnare(5)+2*sum(f_namnare(-5+(1:10/h-1)*h)));
f_n = namnare(0.01)

%täljaren
f_taljare=@(t) exp(-t.^2).*T(t);

%täljaren uträknat numeriskt med trapets
taljare=@(h) (h/2)*(f_taljare(-5)+f_taljare(5)+2*sum(arrayfun(f_taljare, -5+(1:10/h-1)*h)));
%räkna ut tid
t=cputime;
f_t = taljare(0.4);
t1=cputime-t;

%räkna ut numeriskt med hjälp av simpsons
simpsons=@(h) (h/3)*(f_taljare(-5)+f_taljare(5)+4*sum(arrayfun(f_taljare, -5+(0.5:5/h-0.5)*2*h))+2*sum(arrayfun(f_taljare, -5+(1:5/h-1)*2*h)));
t2=cputime;
simpsons(0.39);
t3=cputime-t2;

tid_taljare = t1
tid_simpsons = t3
