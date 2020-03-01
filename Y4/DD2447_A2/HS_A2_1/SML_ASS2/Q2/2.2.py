import numpy as np
import math 
import scipy.stats
import matplotlib.pyplot as plt



def  generateData(phi = 1.0, sigma = 0.16 ,beta = 0.64, T = 100):
    X = np.zeros(T)
    Y = np.zeros(T)
    
    X[0] = np.random.normal(0,sigma**2)
    for i in range(1,T):
        X[i] = np.random.normal(phi*X[i-1],sigma**2)
    for i in range(T):
        Y[i] = np.random.normal(0,(beta**2)*(math.e**X[i]))
    return X, Y, phi, sigma, beta, T

def stratified_sampling(W, N = 5):
    mean = np.mean(W)
    a = [i for i in W if i<=mean]
    b = [i for i in W if i>mean]
    c = b[-5:]
    b = b[:-5].copy()
    max_a = np.max(a)
    max_b = np.max(b)
    max_c = np.max(c)
    samples = []
    for _ in range(N):
        i = scipy.stats.uniform.rvs()
        if i<=max_a:
            samples.append(list(W).index(np.random.choice(a)))
        elif i<=max_b:
            samples.append(list(W).index(np.random.choice(b)))
        else:
            samples.append(list(W).index(np.random.choice(c)))
    return np.random.choice(samples)


def sequential_importance_sampling(Y,T,phi,sigma,beta,particals = 100):
    X_Predictions = np.zeros((T,particals))
    W = np.zeros((T,particals))
    for i in range(particals):
        X_Predictions[0][i] = np.random.normal(0,sigma**2)
        W[0][i] = scipy.stats.norm.pdf(Y[0], scale = (beta**2) * (math.e**X_Predictions[0][i]) )
    W[0] = W[0] / np.sum(W[0])
    for i in range(1,T):
        for j in range(particals):
            X_Predictions[i][j] = np.random.normal(phi*X_Predictions[i-1][j],sigma**2)
            W[i][j] = scipy.stats.norm.pdf(Y[i], scale = (beta**2) * math.e**X_Predictions[i][j] ) * W[i-1][j]
        W[i] = W[i]/np.sum(W[i])
    return X_Predictions, W

def BPF_Multi(Y,T,phi,sigma,beta,particals = 100):
    X_Predictions = np.zeros((T,particals))
    W = np.zeros((T,particals))
    for i in range(particals):
        X_Predictions[0][i] = np.random.normal(0,sigma**2)
        W[0][i] = scipy.stats.norm.pdf(Y[0], scale = (beta**2) * (math.e**X_Predictions[0][i]) )
    W[0] = W[0] / np.sum(W[0])
    for i in range(1,T):
        for j in range(particals):
            resample = np.argmax( np.random.multinomial(50, W[i-1] ) ) #multinomial resampling
            X_Predictions[i][j] = np.random.normal(phi*X_Predictions[i-1][resample],sigma**2)
            W[i][j] = scipy.stats.norm.pdf(Y[i], scale = (beta**2) * (math.e**X_Predictions[i][j]) )
        W[i] = W[i]/np.sum(W[i])
    return X_Predictions, W

def BPF_Strat(Y,T,phi,sigma,beta,particals = 100):
    X_Predictions = np.zeros((T,particals))
    W = np.zeros((T,particals))
    for i in range(particals):
        X_Predictions[0][i] = np.random.normal(0,sigma**2)
        W[0][i] = scipy.stats.norm.pdf(Y[0], scale = (beta**2) * (math.e**X_Predictions[0][i]) )
    W[0] = W[0] / np.sum(W[0])
    for i in range(1,T):
        for j in range(particals):
            resample = stratified_sampling(W[i-1]) #Stratified resampling
            X_Predictions[i][j] = np.random.normal(phi*X_Predictions[i-1][resample],sigma**2)
            W[i][j] = scipy.stats.norm.pdf(Y[i], scale = (beta**2) * (math.e**X_Predictions[i][j]) )
        W[i] = W[i]/np.sum(W[i])
    return X_Predictions, W

X, Y, phi, sigma, beta, T = generateData()

'''plt.plot(range(T),X, color = 'green', label = 'xT')
plt.plot(range(T),Y, color = 'black', label = 'yT')
plt.legend(loc='upper right')
plt.show()
'''

#SIS - Q4 - Do some more to ans question, coding done
X_SIS, W = sequential_importance_sampling(Y,T,phi,sigma,beta)
SIS_predictions = np.zeros(T)
for i in range(T):
    SIS_predictions[i] = X_SIS[i][np.argmax(W[-1])]
#plt.plot(range(T),X, color = 'green', label = 'xT')
#plt.plot(range(T),SIS_predictions,color = 'black', label = 'SIS point estimate')
plt.hist(W[-1], bins = np.arange(0,0.3,0.005), label = 'SIS point estimate')
#print('MSE: ', np.square(np.subtract(X,SIS_predictions)).mean())
#plt.legend(loc='upper right')
#plt.show()
print(np.max(W[-1]))

#BPF_Multi
X_BPF_Multi ,W = BPF_Multi(Y,T,phi,sigma,beta)
BPF_Multi_predictions = np.zeros(T)
for i in range(T):
    BPF_Multi_predictions[i] = X_BPF_Multi[i][np.argmax([W[-1]])]
    #print('MSE: ', np.square(np.subtract(X[:i],BPF_Multi_predictions[:i])).mean())
#plt.plot(range(T),X,color = 'green', label = 'xT')
#plt.plot(range(T), BPF_Multi_predictions,color ='black', label = 'BPF_Multi point estimate')
#plt.legend(loc='upper right')
plt.hist(W[-1], bins = np.arange(0,0.2,0.01), label = 'BPF_Multi point estimate')
print('MSE: ', np.square(np.subtract(X,BPF_Multi_predictions)).mean())
#plt.show()

#STRATIFIED, https://towardsdatascience.com/stratified-sampling-and-how-to-perform-it-in-r-8b753efde1ef
X_BPF_Strat ,W = BPF_Strat(Y,T,phi,sigma,beta)
BPF_Strat_predictions = np.zeros(T)
for i in range(T):
    BPF_Strat_predictions[i] = X_BPF_Strat[i][np.argmax([W[-1]])]
    #print('MSE: ', np.square(np.subtract(X[:i],BPF_Strat_predictions[:i])).mean())
#plt.plot(range(T),X,color = 'green', label="xT")
#plt.plot(range(T), BPF_Strat_predictions,color ='black', label="Stratified point estimate")
#plt.title("Stratified resampling")
plt.hist(W[-1], bins = np.arange(0,0.2,0.01), label="Stratified point estimate")
plt.legend(loc='upper right')

#plt.hist(W[-1], bins = np.arange(0,0.2,0.001))
print('MSE: ', np.square(np.subtract(X,BPF_Strat_predictions)).mean())
plt.show()