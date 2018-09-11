load minidigits.mat
% row = 256,col = 50 (number of twos)
[row, col] = size(testdata);
% for each column, set b to that column.
% find the linear combination for the vector x
% set nv to the norm of the residual
for j=1:col
    b=testdata(:,j);
    x = C\b;
    nv(j)=norm(C*x-b);
end
% fixed point p for which we are to test so that if its less
% than p, that means the column looks like a 2
p=(mean(nv)+min(nv))/2;

corGuess=0;
wroGuess=0;
amtTwo=0;

% for loop that finds the amount of twos in testdatad
for j=1:length(testdatad)
    if testdatad(j)==2;
        amtTwo = amtTwo+1;
    end
end

% for loop that first checks if the column resembles a two,
% then checks if it actually IS a two.
% adds to the corresponding int.
for j=1:length(nv)
    if nv(j) <=p
        if testdatad(j)==2
            corGuess=corGuess+1;
        else
            wroGuess=wroGuess+1;
        end
    end
end

wroGuess
corGuess
amtTwo

% calculates the percentages for right and wrong guesses
right=corGuess/amtTwo
wrong=wroGuess/1000
