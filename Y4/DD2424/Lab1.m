% Diar Sabri 2020-03-30
% DD2424 Lab1
% Params
lambda = 1;
GDparams.n_epochs = 40;
GDparams.n_batch = 100;
GDparams.eta = 0.001;

%1
% Read data
% X = 3072 x 10000  (d x n)
% Y = 10 x 10000    (K x n)
% y = 10000 x 1     (n x 1)
[tra_X,tra_Y,tra_y] = LoadBatch('Datasets/cifar-10-batches-mat/data_batch_1.mat');
[val_X,val_Y,val_y] = LoadBatch('Datasets/cifar-10-batches-mat/data_batch_2.mat');
[tes_X,tes_Y,tes_y] = LoadBatch('Datasets/cifar-10-batches-mat/test_batch.mat');


%2
% Pre-process data. Normalization wrt mean & std
tra_X = ProcessData(tra_X);
val_X = ProcessData(val_X);
tes_X = ProcessData(tes_X);

%3
% Initializing parameters W & b with random starting values. 
% W = 10 x 3072     (K x d)
% b = 10 x 1        (K x 1)
W = randn([10 3072]) * 0.01;
b = randn([10 1]) * 0.01;

%4 Evaluate the network function (softmax) -> P
% P = 10 x 10000    (K x n)
% X = tra_X;
%P = EvaluateClassifier(X,W,b);

%5 Compute cost J
% J = scalar
% Y = tra_Y;
%J = ComputeCost(X,Y,W,b,lambda);

%6 Compute accuracy of the network's predictions -> acc
% acc = scalar
% y = tra_y;
%acc = ComputeAccuracy(X,y,W,b);

%7 Evaluates the gradients of the cost function of a mini-batch ->
%[grad_W,grad_b] = [10 x 3072 (KxD), 10 x 1 (Kx1)]
%[grad_W,grad_b] = ComputeGradients(X,Y,P,W,lambda);

%%%%% Debugging
Xt = tra_X(:, 1);
Yt = tra_Y(:,1);
Wt = W;
Pt = EvaluateClassifier(Xt,Wt,b);

[grad_Wt,grad_bt] = ComputeGradients(Xt,Yt,Pt,Wt,lambda);
[ngrad_b, ngrad_W] = ComputeGradsNumSlow(tra_X(:, 1), tra_Y(:, 1),W, b, lambda, 1e-6);

eps = 1e-10;
errW = norm(ngrad_W - grad_Wt) ./ max(eps,norm(grad_Wt) + norm (ngrad_W));
errb = norm(ngrad_b - grad_bt) ./ max(eps,norm(grad_bt) + norm (ngrad_b));
%%%%

%8 Mini-batch GD function
%[W, b] = MiniBatchGD(tra_X, tra_Y, GDparams, W, b, lambda);
n = size(tra_X,2);

tra_J = zeros(1, 40);
val_J = zeros(1, 40);

for i = 1:40
    p = randperm(n);
    tra_X = tra_X(:,p);
    tra_Y = tra_Y(:,p);
        
    [W, b] = MiniBatchGD(tra_X, tra_Y, GDparams, W, b, lambda);
    tra_J(i) = ComputeCost(tra_X, tra_Y, W, b, lambda);
    val_J(i) = ComputeCost(val_X, val_Y, W, b, lambda);
end

% Accuracy
acc_tra = ComputeAccuracy(tra_X,tra_y, W, b);
acc_val = ComputeAccuracy(val_X,val_y, W, b);
acc_tes = ComputeAccuracy(tes_X,tes_y, W, b);

% Plot weight
imageW(W);

% Plot cost
PlotCost(tra_J,val_J);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% FUNCTIONS %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
function [X,Y,y] = LoadBatch(filename)
% Loads data by reading provided filename.
% Returns X (image pixel data), Y (one-hot representation of each image
% label), y (labels for each image).
    A = load(filename);
   
    lab = A.labels' + 1; %adding 1 because of matlabs inits
    permitted = [1:10];
    [found,idx] = ismember(lab,permitted);
    assert(all(found),"not permitted");
    onehot = ind2vec(idx);

    X = double(A.data');
    y = double(A.labels + 1);
    Y = onehot;
end

function [X] = ProcessData(X)
% Pre-processing the raw data, normalization wrt mean & std.
    X = X - repmat(mean(X,2),[1,size(X,2)]);
    X = X ./ repmat(std(X,0,2),[1,size(X,2)]);
end

function [P] = EvaluateClassifier(X,W,b)
% Evaluates the network function, returns P containing probabilities for
% each label for the image.
    s = W*X + b;
    
    P = exp(s) ./ sum(exp(s)); 
    %P = softmax(s);
end

function [J] = ComputeCost(X,Y,W,b,lambda)
% Computes the cost by calculating the cross-entropy loss and adding a
% regularization term.
    P = EvaluateClassifier(X,W,b);
    n = size(X,2);
    %lcross = -sum(log(P) .* Y);
    lcross = diag(-log(Y' * P));
    regularization = sum(sum(W.^2));

    J = sum(lcross) / n + lambda * regularization;
end

function [acc] = ComputeAccuracy(X,y,W,b)
% Simple function that calculates the accuracy of the predictions, returns
% the accuracy as a scalar.
    P = EvaluateClassifier(X,W,b);
    [~,i] = max(P);
    I = i';
    acc = sum(y==I)/length(y);
end

function [grad_W,grad_b] = ComputeGradients(X,Y,P,W,lambda)
% Evaluates the gradients of the cost function. Returns gradients in the
% form of a matrix and vector, for W & b. Uses the efficient calculation
% provided in the lecture.
    n = size(X,2);
    G = -(Y-P);
    
    grad_W = (1/n) * (G * X') + 2 * lambda * W;
    grad_b = (1/n) * (G * ones(n,1));
end

function [Ws,bs] = MiniBatchGD(X,Y,GDparams,W,b,lambda)
% Finally, compute the GD on mini-batches to learn the parameters W & b.
% I use the random permutation technique when calling this function. 
    n_batch  = GDparams.n_batch;
    eta      = GDparams.eta;
    %n_epochs = GDparams.n_epochs;
    n = size(X,2);

    for j=1:n/n_batch
        j_start = (j-1) * n_batch + 1;
        j_end = j * n_batch;
        Xbatch = X(:, j_start:j_end);
        Ybatch = Y(:, j_start:j_end);
        
        P = EvaluateClassifier(Xbatch,W,b);
        [grad_W, grad_b] = ComputeGradients(Xbatch, Ybatch, P, W, lambda);
        
        W = W - eta * grad_W;
        b = b - eta * grad_b;
    end
    
    Ws = W;
    bs = b;    
end

function [] = imageW(W)
% Plotting an image with montage
    for i=1:10
        im = reshape(W(i, :), 32, 32, 3);
        s_im{i} = (im - min(im(:))) / (max(im(:)) - min(im(:)));
        s_im{i} = permute(s_im{i}, [2, 1, 3]);
    end
      montage(s_im, 'size', [1, 10]);
end

function [] = PlotCost(tra_J,val_J)
% Plotting the cost function on all epochs
    figure()
    plot(1 : 40, tra_J, 'color', '#0072BD')
    hold on
    plot(1 : 40, val_J, 'color', '#77AC30')
    hold off
    xlabel('Number of epochs')
    ylabel('Cost')
    legend('Training', 'Validaton');
    title('Comparing datasets');
end

function [grad_b, grad_W] = ComputeGradsNumSlow(X, Y, W, b, lambda, h)
% Given function, computes Gradients numerically
    no = size(W, 1);
    
    grad_W = zeros(size(W));
    grad_b = zeros(no, 1);

    for i=1:length(b)
        b_try = b;
        b_try(i) = b_try(i) - h;
        c1 = ComputeCost(X, Y, W, b_try, lambda);
        b_try = b;
        b_try(i) = b_try(i) + h;
        c2 = ComputeCost(X, Y, W, b_try, lambda);
        grad_b(i) = (c2-c1) / (2*h);
    end

    for i=1:numel(W)

        W_try = W;
        W_try(i) = W_try(i) - h;
        c1 = ComputeCost(X, Y, W_try, b, lambda);

        W_try = W;
        W_try(i) = W_try(i) + h;
        c2 = ComputeCost(X, Y, W_try, b, lambda);

        grad_W(i) = (c2-c1) / (2*h);
    end
end








