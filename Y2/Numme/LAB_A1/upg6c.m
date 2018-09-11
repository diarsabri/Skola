[row, col]=size(testdata);

for j=1:col
    t=testdata(:,j);
    x=C\t;
    nv(j)=norm(C*x-t);
    
end    

nv;
p=(min(nv)+ mean(nv))/2
%p=min(nv):0.01:max(nv);

right_guess=0;
wrong_guess=0;
amount_of_two = 0;

for i=1:1000
    if testdatad(i) == 2;
        amount_of_two = amount_of_two +1;
    end
    
end

amount_of_two


for i=1:1000
    if nv(i) <= p
        if testdatad(i) == 2
            right_guess = right_guess +1
        else 
                wrong_guess = wrong_guess +1
        end
    end    
end

wrong_guess
right_guess

percentright = right_guess/amount_of_two
percentwrong = wrong_guess/1000
test=find(nv==min(nv()))
plotdigit(testdata(:,test));

%e=norm(A*x-yd)/sqrt(n);

nv(j)=norm(C*x-testdata(:,j))