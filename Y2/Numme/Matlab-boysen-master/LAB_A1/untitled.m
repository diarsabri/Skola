uvv = [];
theta = 100;
for k=1:theta
    outish=generator(k,0);
    uvv(k)= max(outish);
end
[M,I]=max(uvv)