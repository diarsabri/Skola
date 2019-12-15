import numpy as np
from labfuns import *




# fetch datapoints for class i
def data_class(i):    # Iterate over both index and value
    #for jdx,clss in enumerate(classes):
        i = i-1
        idx = y==i # Returns a true or false with the length of y
        
        # Or more compactly extract the indices for which y==class is true,
        # analogous to MATLAB’s find
        idx = np.where(y==i)[0]
        xlc = X[idx,:] # Get the x for the class labels. Vectors are rows
        
        return xlc




# NOTE: you do not need to handle the W argument for this part!
# in:      X - N x d matrix of N data points
#     labels - N vector of class labels
# out:    mu - C x d matrix of class means (mu[i] - class i mean)
#      sigma - C x d x d matrix of class covariances (sigma[i] - class i sigma)
def mlParams(X, labels, W=None):
    assert(X.shape[0]==labels.shape[0])
    Npts,Ndims = np.shape(X)
    classes = np.unique(labels)
    Nclasses = np.size(classes)

    if W is None:
        W = np.ones((Npts,1))/float(Npts)

    mu = np.zeros((Nclasses,Ndims)) # Contains the class means
    sigma = np.zeros((Nclasses,Ndims,Ndims)) # Contains the class covariances




    # TODO: fill in the code to compute mu and sigma!
    # ==========================
    
    for jdx, classname in enumerate(classes):
        
        idx = labels == classname 
        idx = np.where(labels==classname)[0]
    
        weightsum = sum(W[idx,:])
        
        # Mean
        mu[jdx] += np.sum(X[idx,:]*W[idx,:], axis=0) / weightsum
                
        # Sigma - contains the covariance
        for dim in range(Ndims):
            totsum = 0
            for ind in idx:
                totsum += W[ind]*(X[ind][dim] - mu[jdx][dim]) ** 2
            sigma[jdx][dim][dim] = totsum / weightsum

    # ==========================

    return mu, sigma

#print(mlParams(X,y))


X,y,pcadim = fetchDataset() #iris




xx,yy = genBlobs(centers=5)

mu,sigma = mlParams(xx,yy)

plotGaussian(xx,yy,mu,sigma)

