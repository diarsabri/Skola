%samma som inlämningen förra året, funktion för att skapa u(t)
function w = kast_mat(u)
%givet
c = 0.070;
a = 7 + (0.35 * u(5));
q = c*sqrt(u(2)^2 + (u(4) - a)^2 + u(6)^2);

%skapa n*n-matris, n=size(w)
w=zeros(size(u));

%stoppa in allt givet, ger matrisen på första sliden
w(1) = u(2);        % x'
w(2) = -q*u(2);     % x''
w(3) = u(4);
w(4) = -q*(u(4)-a);
w(5) = u(6);
w(6) = -9.81 - (q*u(6));

end