z0=[pi/4, 0, 0, 0];
t=0;
n=10000;
h=0.001;
iter = 0;
tic();
for t=0:h:50
    z0=z0+h*f_robotarm(z0, t, thetav(1), thetav(2), 2, 0.3, 1, 2*pi);
    iter = iter + 1;
    
    if(mod(iter,10)==1 && iter > 5000)
        clf;
        axis([0 2 0 2]) %fï¿½rsta kvadranten , till 2
        hold on
        plot_robotarm([z0(1),z0(3)]);
    end
end
time = toc()
z = f_robotarm(z0, 50, thetav(1), thetav(2), 2, 0.3, 1, 2*pi)
