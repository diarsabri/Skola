% Diar Sabri 2020-08-24
% DD2424 Lab2
% Params
lambda = 0.01;
GDparams.n_epochs = 200;
GDparams.n_batch = 100;
GDparams.cycles = 3;
GDparams.n_s = 800;
GDparams.eta_min = 1e-5;
GDparams.eta_max = 1e-1;

%1
% Read data, pre-process, initialize parameters
% X = 3072 x 10000  (d x n)
% Y = 10 x 10000    (K x n)
% y = 10000 x 1     (n x 1)
[tra_X,tra_Y,tra_y] = LoadBatch('../Datasets/cifar-10-batches-mat/data_batch_1.mat');
[val_X,val_Y,val_y] = LoadBatch('../Datasets/cifar-10-batches-mat/data_batch_2.mat');
[tes_X,tes_Y,tes_y] = LoadBatch('../Datasets/cifar-10-batches-mat/test_batch.mat');

% Pre-process data. Normalization wrt mean & std
tra_X = ProcessData(tra_X);
val_X = ProcessData(val_X);
tes_X = ProcessData(tes_X);

% Initializing parameters W1,W2 & b1,b2 with random starting values. 
% W1 = 50 x 3072     (m x d)    W2 = 10 x 50       (K x m)
% b1 = 50 x 1        (m x 1)    b2 = 10 x 1        (K x 1)
cells = {randn([50 3072]) * (1/sqrt(3072)),randn([10 50]) * (1/sqrt(50));zeros(50,1),zeros(10,1)};

%2 Network function, cost function, gradients.
% P = 10 x 10000    (K x n)
% h = 50 x 10000    (m x n)
% J = scalar, h = (m x n)
%[P,h] = EvaluateClassifier(tra_X,cells);
%[J,h] = ComputeCost(tra_X,tra_Y,cells,lambda);
%[grad_W1,grad_W2,grad_b1,grad_b2] = ComputeGradients(tra_X,tra_Y,P,cells,h,lambda);

%%%%% Debugging
% Xt = tra_X(1:20, 1:2);
% Yt = tra_Y(:,1:2);
% Wt = {cells{1,1}(:,1:20),cells{1,2}(:,:)};
% bt = {cells{2,1},cells{2,2}};
% cellst = {Wt{1},Wt{2};bt{1},bt{2}};
% [Pt,ht] = EvaluateClassifier(Xt,cellst);
% 
% [grad_Wt1,grad_Wt2,grad_bt1,grad_bt2] = ComputeGradients(Xt,Yt,Pt,cellst,ht,lambda);
% [ngrad_b, ngrad_W] = ComputeGradsNumSlow(tra_X(1:20, 1:2), tra_Y(:, 1:2),cellst, lambda, 1e-5);
% 
% eps = 1e-10;
% errW1 = norm(ngrad_W{1} - grad_Wt1) ./ max(eps,norm(grad_Wt1) + norm (ngrad_W{1}));
% errW2 = norm(ngrad_W{2} - grad_Wt2) ./ max(eps,norm(grad_Wt2) + norm (ngrad_W{2}));
% errb1 = norm(ngrad_b{1} - grad_bt1) ./ max(eps,norm(grad_bt1) + norm (ngrad_b{1}));
% errb2 = norm(ngrad_b{2} - grad_bt2) ./ max(eps,norm(grad_bt2) + norm (ngrad_b{2}));

% Testing whether I can overfit
% lambda = 0;
% GDparams.n_epochs = 200;
% 
% [tra_X,tra_Y,~] = LoadBatch('../Datasets/cifar-10-batches-mat/data_batch_1.mat');
% [val_X,val_Y,~] = LoadBatch('../Datasets/cifar-10-batches-mat/data_batch_2.mat');
% 
% tra_X = tra_X(:,1:100); tra_Y = tra_Y(:,1:100); 
% val_X = val_X(:,1:100); val_Y = val_Y(:,1:100); 
% 
% tra_X = ProcessData(tra_X);
% val_X = ProcessData(val_X);
% 
% cells = {randn([50 3072]) * (1/sqrt(3072)),randn([10 50]) * (1/sqrt(50));zeros(50,1),zeros(10,1)};
% 
% [cost,Vcost,loss,Vloss] = Overfit(tra_X, tra_Y, val_X, val_Y, GDparams, cells, lambda);
% figure()
% subplot(1,2,1)
% plot(1 : 200, cost,1 : 200, Vcost)
% legend('training','validation')
% xlabel('Updates')
% ylabel('Cost')
% subplot(1,2,2)
% plot(1 : 200, loss,1 : 200, Vloss)
% legend('training','validation')
% xlabel('Updates')
% ylabel('Loss')

%%%%

%3 Cyclical learning rates
% [cells2,~,costs,losses,accs,Vcosts,Vlosses,Vaccs] = MiniBatchGD(tra_X, tra_Y, tra_y, val_X, val_Y, val_y, GDparams, cells, lambda);
% 
% [cost0,~,loss0] = ComputeCost(tra_X,tra_Y,cells,lambda);
% acc0 = ComputeAccuracy(tra_X,tra_y,cells);
% [Vcost0,~,Vloss0] = ComputeCost(val_X,val_Y,cells,lambda);
% Vacc0 = ComputeAccuracy(val_X,val_y,cells);
% 
% costs = [cost0;costs]; losses = [loss0;losses]; accs = [acc0;accs];
% Vcosts = [Vcost0;Vcosts]; Vlosses = [Vloss0;Vlosses]; Vaccs = [Vacc0;Vaccs];
% 
% figure()
% subplot(1,3,1)
% plot(1 : 100: 4900, costs,1 : 100: 4900, Vcosts)
% legend('training','validation')
% ylim([0,4])
% xlim([0,5000])
% xlabel('Updates')
% ylabel('Cost')
% 
% subplot(1,3,2)
% plot(1 : 100: 4900, losses,1 : 100: 4900, Vlosses)
% legend('training','validation')
% ylim([0,4])
% xlim([0,5000])
% xlabel('Updates')
% ylabel('Loss')
% 
% subplot(1,3,3)
% plot(1 : 100: 4900, accs,1 : 100: 4900, Vaccs)
% legend('training','validation')
% xlim([0,5000])
% xlabel('Updates')
% ylabel('Accuracy')
% 
% ComputeAccuracy(tes_X,tes_y,cells2)


%4 Training the network
%Params
lambda = 0.000296316045865915;
GDparams.n_epochs = 40;
GDparams.n_batch = 100;
GDparams.n_s = 800;
GDparams.cycles = 5;
GDparams.eta_min = 1e-5;
GDparams.eta_max = 1e-1;

%Data
[tra_X1,tra_Y1,tra_y1] = LoadBatch('../Datasets/cifar-10-batches-mat/data_batch_1.mat');
[tra_X2,tra_Y2,tra_y2] = LoadBatch('../Datasets/cifar-10-batches-mat/data_batch_2.mat');
[tra_X3,tra_Y3,tra_y3] = LoadBatch('../Datasets/cifar-10-batches-mat/data_batch_3.mat');
[tra_X4,tra_Y4,tra_y4] = LoadBatch('../Datasets/cifar-10-batches-mat/data_batch_4.mat');
[tra_X5,tra_Y5,tra_y5] = LoadBatch('../Datasets/cifar-10-batches-mat/data_batch_5.mat');
tra_X = [tra_X1 tra_X2 tra_X3 tra_X4 tra_X5(:,1:9000)];
tra_Y = [tra_Y1 tra_Y2 tra_Y3 tra_Y4 tra_Y5(:,1:9000)];
tra_y = [tra_y1;tra_y2;tra_y3;tra_y4;tra_y5(1:9000)];
val_X = tra_X5(:,9001:10000);
val_Y = tra_Y5(:,9001:10000);
val_y = tra_y5(9001:10000);
[tes_X,tes_Y,tes_y] = LoadBatch('../Datasets/cifar-10-batches-mat/test_batch.mat');

clearvars tra_X1 tra_X2 tra_X3 tra_X4 tra_X5 tra_Y1 tra_Y2 tra_Y3 tra_Y4 tra_Y5 tra_y1 tra_y2 tra_y3 tra_y4 tra_y5

% Pre-process data. Normalization wrt mean & std
tra_X = ProcessData(tra_X);
val_X = ProcessData(val_X);
tes_X = ProcessData(tes_X);

% Initializing parameters W1,W2 & b1,b2 with random starting values. 
cells = {randn([50 3072]) * (1/sqrt(3072)),randn([10 50]) * (1/sqrt(50));zeros(50,1),zeros(10,1)};

%Finding initial best lambda
l_min = 0.000296359972083708-10^-7;
l_max = 0.000296359972083708+10^-7;
iterations = 10;
lambdas = zeros(1,iterations);
accs = zeros(1,iterations);
Vaccs = zeros(1,iterations);

for i=1:iterations
    lambda = l_min + (l_max - l_min)*rand(1, 1);
    [~,~,~,~,acc,~,~,Vacc] = MiniBatchGD(tra_X, tra_Y, tra_y, val_X, val_Y, val_y, GDparams, cells, lambda);
    
    lambdas(i)=lambda;
    accs(i)=acc(end);
    Vaccs(i)=Vacc(end);
    
end


%Final run
[cells2,~,costs,losses,accs,Vcosts,Vlosses,Vaccs] = MiniBatchGD(tra_X, tra_Y, tra_y, val_X, val_Y, val_y, GDparams, cells, lambda);

[cost0,~,loss0] = ComputeCost(tra_X,tra_Y,cells,lambda);
acc0 = ComputeAccuracy(tra_X,tra_y,cells);
[Vcost0,~,Vloss0] = ComputeCost(val_X,val_Y,cells,lambda);
Vacc0 = ComputeAccuracy(val_X,val_y,cells);

costs = [cost0;costs]; losses = [loss0;losses]; accs = [acc0;accs];
Vcosts = [Vcost0;Vcosts]; Vlosses = [Vloss0;Vlosses]; Vaccs = [Vacc0;Vaccs];

figure()
plot(1 : 100: 8100, costs,1 : 100: 8100, Vcosts)
legend('training','validation')
ylim([0,4])
xlim([0,8100])
xlabel('Updates')
ylabel('Cost')

figure()
plot(1 : 100: 8100, losses,1 : 100: 8100, Vlosses)
legend('training','validation')
ylim([0,4])
xlim([0,8100])
xlabel('Updates')
ylabel('Loss')

figure()
plot(1 : 100: 8100, accs,1 : 100: 8100, Vaccs)
legend('training','validation')
xlim([0,8100])
xlabel('Updates')
ylabel('Accuracy')

ComputeAccuracy(tes_X,tes_y,cells2)



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% FUNCTIONS %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
function [acc] = ComputeAccuracy(X,y,cells)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here

    
    
    [P,~] = EvaluateClassifier(X,cells);
    [~,i] = max(P);
    I = i';
    acc = sum(y==I)/length(y);
    
end

function [J,h,loss] = ComputeCost(X,Y,cells,lambda)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

    [P,h] = EvaluateClassifier(X,cells);
    n = size(X,2);
    lcross = -sum(log(P) .* Y);
    %lcross = diag(-log(Y' * P));
    loss = sum(lcross) / n;
    regularization = sum(sum(cells{1,1}.^2)) + sum(sum(cells{2,1}.^2));
    
    J = loss + lambda * regularization;

end

function [grad_W1,grad_W2,grad_b1,grad_b2] = ComputeGradients(X,Y,P,cells,h,lambda)
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here

    n = size(X,2);
    G = -(Y-P);

    grad_W2 = (1/n) * G * h';
    grad_b2 = (1/n) * (G * ones(n,1));

    G = cells{1,2}' * G;
    G = G .* (h > 0);

    grad_W1 = (1/n) * (G * X') + 2 * lambda * cells{1,1};
    grad_b1 = (1/n) * (G * ones(n,1));
    
end

function [grad_b, grad_W] = ComputeGradsNumSlow(X, Y, cells, lambda, h)

W = {cells{1,1},cells{1,2}};
b = {cells{2,1},cells{2,2}};

grad_W = cell(numel(W), 1);
grad_b = cell(numel(b), 1);
for j=1:length(b)
    grad_b{j} = zeros(size(b{j}));
    for i=1:length(b{j})
        b_try = b;
        b_try{j}(i) = b_try{j}(i) - h;
        c1 = ComputeCost(X, Y, {cells{1,1},cells{1,2};b_try{1},b_try{2}}, lambda);
        b_try = b;
        b_try{j}(i) = b_try{j}(i) + h;
        c2 = ComputeCost(X, Y, {cells{1,1},cells{1,2};b_try{1},b_try{2}}, lambda);
        grad_b{j}(i) = (c2-c1) / (2*h);
    end
end
for j=1:length(W)
    grad_W{j} = zeros(size(W{j}));
    for i=1:numel(W{j})
        W_try = W;
        W_try{j}(i) = W_try{j}(i) - h;
        c1 = ComputeCost(X, Y, {W_try{1},W_try{2};cells{2,1},cells{2,2}}, lambda);
        W_try = W;
        W_try{j}(i) = W_try{j}(i) + h;
        c2 = ComputeCost(X, Y, {W_try{1},W_try{2};cells{2,1},cells{2,2}}, lambda);
        grad_W{j}(i) = (c2-c1) / (2*h);
    end
end

function [P,h] = EvaluateClassifier(X,cells)
    %UNTITLED Summary of this function goes here
    %   Detailed explanation goes here
    
    s1 = cells{1,1}*X + cells{2,1};
    h = max(0,s1);
    s = cells{1,2} * h + cells{2,2};
    P = exp(s) ./ sum(exp(s)); 

end

function [] = imageW(W)
    for i=1:10
        im = reshape(W(i, :), 32, 32, 3);
        s_im{i} = (im - min(im(:))) / (max(im(:)) - min(im(:)));
        s_im{i} = permute(s_im{i}, [2, 1, 3]);
    end
      montage(s_im, 'size', [1, 10]);
end

function [X,Y,y] = LoadBatch(filename)
    %UNTITLED Summary of this function goes here
    %   Detailed explanation goes here
    
    A = load(filename);
   
    lab = A.labels' + 1;
    permitted = [1:10];
    [found,idx] = ismember(lab,permitted);
    assert(all(found),"not permitted");
    onehot = ind2vec(idx);

    X = double(A.data');
    y = double(A.labels + 1);
    Y = onehot;
end

function [res,etas,costs,losses,accs,Vcosts,Vlosses,Vaccs] = MiniBatchGD(X,Y,y,VX,VY,Vy,GDparams,cells,lambda)
%UNTITLED4 Summary of this function goes here
%   Detailed explanation goes here

    n_batch  = GDparams.n_batch;
    eta      = GDparams.eta_min;
    cycles   = GDparams.cycles;
    n = size(X,2);
    l = 0;
    t = 0;
    %n_s = 2*floor(n/n_batch);
    n_s = GDparams.n_s; % = k * n / n_batch (k=2)
    updates = 2*cycles*n_s;% = 2 * cycles * k * n / n_batch
    %updates = (n/n_batch);
    etas = zeros(1,n);
    
    costs = zeros(updates/100,1); losses = zeros(updates/100,1); accs = zeros(updates/100,1);%measuring performance
    Vcosts = zeros(updates/100,1); Vlosses = zeros(updates/100,1); Vaccs = zeros(updates/100,1);%measuring performance
    plotpoint =1;
    
    for j=1:updates
        etas(j) = eta;
        batchmods = mod(j,n/n_batch)+1;
        j_start = (batchmods-1) * n_batch + 1;
        j_end = batchmods * n_batch;
        Xbatch = X(:, j_start:j_end);
        Ybatch = Y(:, j_start:j_end);

        [P,h] = EvaluateClassifier(Xbatch,cells);%                tra_X,tra_Y,P,cells,h,lambda
        [grad_W1,grad_W2,grad_b1,grad_b2] = ComputeGradients(Xbatch, Ybatch, P, cells,h, lambda);

        cells{1,1} = cells{1,1} - eta * grad_W1;
        cells{1,2} = cells{1,2} - eta * grad_W2;
        cells{2,1} = cells{2,1} - eta * grad_b1;
        cells{2,2} = cells{2,2} - eta * grad_b2;

        if (2*l*n_s) <= t && t < ((2*l + 1)*n_s)
            eta = GDparams.eta_min + ((t-2*l*n_s)/n_s)*(GDparams.eta_max-GDparams.eta_min);
        else
            eta = GDparams.eta_max - ((t-(2*l+1)*n_s)/n_s)*(GDparams.eta_max-GDparams.eta_min);
        end

        t = t + 1;
        l = floor(t/(2*n_s));            
        
        if mod(j,100)==0
            [costs(plotpoint),~,losses(plotpoint)] = ComputeCost(X,Y,cells,lambda);
            accs(plotpoint) = ComputeAccuracy(X,y,cells);
            
            [Vcosts(plotpoint),~,Vlosses(plotpoint)] = ComputeCost(VX,VY,cells,lambda);
            Vaccs(plotpoint) = ComputeAccuracy(VX,Vy,cells);
            
            plotpoint = plotpoint+1;

        end
    end
    
    res = cells;
end

function [cost,Vcost,loss,Vloss] = Overfit(X,Y,VX,VY,GDparams,cells,lambda)
%OVERFIT Summary of this function goes here
%   Detailed explanation goes here

cost = zeros(1,GDparams.n_epochs);
Vcost = zeros(1,GDparams.n_epochs);
loss = zeros(1,GDparams.n_epochs);
Vloss = zeros(1,GDparams.n_epochs);
eta = 0.01;
    for i=1:GDparams.n_epochs
        [P,h] = EvaluateClassifier(X,cells);%                tra_X,tra_Y,P,cells,h,lambda
        [grad_W1,grad_W2,grad_b1,grad_b2] = ComputeGradients(X, Y, P, cells,h, lambda);

        cells{1,1} = cells{1,1} - eta * grad_W1;
        cells{1,2} = cells{1,2} - eta * grad_W2;
        cells{2,1} = cells{2,1} - eta * grad_b1;
        cells{2,2} = cells{2,2} - eta * grad_b2;

        [cost(i),~,loss(i)] = ComputeCost(X,Y,cells,lambda);
        [Vcost(i),~,Vloss(i)] = ComputeCost(VX,VY,cells,lambda);
    end
end

function [X] = ProcessData(X)
%PROCESSDATA Summary of this function goes here
%   Detailed explanation goes here

    X = X - repmat(mean(X,2),[1,size(X,2)]);
    X = X ./ repmat(std(X,0,2),[1,size(X,2)]);
end


