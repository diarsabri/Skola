kv=0:15; fv=[]; gv=[];
for j=1:length(kv)
z=pi+10^kv(j)
fv(j)=(z-10^kv(j))-pi;
gv(j)=z-(10^kv(j)+pi);
end
figure(1); semilogy(kv,abs(fv-gv))