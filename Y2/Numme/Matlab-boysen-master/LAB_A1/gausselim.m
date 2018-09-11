function x = gausselim(A, b)
%  En referensimplementation av den enklaste
%  varianten av Gausseliminering. 
%    
%
%  Undervisningsmaterial SF1547
%  
    
n = size(A, 1); 
x = zeros(n, 1); 
for k=1:n 
    for i=1:n
        if i~=k
            z=A(i,k)/A(k, k);
            for j = k : 1 : n
                A(i, j) =A(i, j)-A(k, j)*z;
            end
            b(i)=b(i)-b(k)*z;
        end
    end
end

for i = 1 :n
    x(i) = b(i) / A(i, i);
end

end