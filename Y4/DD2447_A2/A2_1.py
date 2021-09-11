import numpy as np
import pdb
import matplotlib.pyplot as plot
import scipy.stats
import math

def main():
    phi = 1.0
    sigma = 0.16
    beta = 0.64
    T = 100
    seed = 57832
    np.random.seed(seed)
    particles = [100,200,400,800]
    for p in particles:
        xT,y = generateData(T,phi,sigma,beta)
        data = list()
        print('\nNumber of particles: ',p)
        #SIS
        w1, x1 = sequentialImportanceSampling(T, y, p, phi, sigma, beta)
        data.append(w1[-1]) #weight vector of last iteration
        xx_1 = np.divide(w1[T-1],np.sum(w1[T-1]))
        print('\nx_hat1',round(sum(x1[T-1]*xx_1),8))
        print('var1',np.var(xx_1))
        print('MSE1',round(np.sum(np.multiply(xx_1, np.power(x1[-1] - xT[-1], 2))),8))
        #Multinomial resampling
        w2, x2 = BPF_multinomialResampling(T, y, p, phi, sigma, beta)
        data.append(w2[-1]) #weight vector of last iteration
        xx_2 = np.divide(w2[T-1],np.sum(w2[T-1]))
        print('\nx_hat2',round(sum(x2[T-1]*xx_2),8))
        print('var2',np.var(xx_2))
        print('MSE2',round(np.sum(np.multiply(xx_2, np.power(x2[-1] - xT[-1], 2))),8))
        #Stratified resampling
        w3, x3 = BPF_stratifiedResampling(T, y, p, phi, sigma, beta)
        data.append(w3[-1]) #weight vector of last iteration
        xx_3 = np.divide(w3[T-1],np.sum(w3[T-1]))
        print('\nx_hat3',round(sum(x3[T-1]*xx_3),8))
        print('var3',np.var(xx_3))
        print('MSE3',round(np.sum(np.multiply(xx_3, np.power(x3[-1] - xT[-1], 2))),8))
    #Plots
    bs = np.arange(0,0.03,0.0025)
    plot.hist(data[0],bins=bs)
    plot.title('Sequential Importance Sampling')
    plot.xlabel('Bins(normalized weights)')
    plot.ylabel('Occurrences per bin')
    plot.show()
    plot.hist(data[1],bins=bs)
    plot.title('Multinomial resampling')
    plot.xlabel('Bins(normalized weights)')
    plot.ylabel('Occurrences per bin')
    plot.show()
    plot.hist(data[2],bins=bs)
    plot.title('Stratified Resampling')
    plot.xlabel('Bins(normalized weights)')
    plot.ylabel('Occurrences per bin')
    plot.show()

def generateData(T,phi,sigma,beta):
    x = np.zeros(T)
    y = np.zeros(T)
    x[0] = np.random.normal(0,sigma)
    y[0] = np.random.normal(0,math.sqrt((beta**2)*(math.e**x[0])))
    for t in range(1, T):
        x[t] = np.random.normal(phi * x[t-1], sigma)
        y[t] = np.random.normal(0, math.sqrt((beta**2)*(math.e**x[t])))
    return x, y

def sequentialImportanceSampling(T, y, particles, phi, sigma, beta):
    x = np.zeros([T, particles])
    w = np.zeros([T, particles])
    for n in range(particles):
        x[0][n] = np.random.normal(0,sigma)
        w[0][n] = scipy.stats.norm.pdf(y[0], scale = math.sqrt((beta**2)*(math.e**x[0,n])))
    w[0] = w[0]/np.sum(w[0])
    for t in range(1,T):
        for n in range(particles):
            x[t][n] = np.random.normal(phi * x[t-1][n], sigma)
            w[t][n] = scipy.stats.norm.pdf(y[t], scale = math.sqrt((beta**2)*(math.e**x[t,n])))* w[t-1][n]
        w[t] = w[t]/np.sum(w[t]) #normalize weights
    return w, x

def BPF_multinomialResampling(T, y, particles, phi, sigma, beta):
    x = np.zeros([T, particles])
    w = np.zeros([T, particles])
    for n in range(particles):
        x[0][n] = np.random.normal(0, sigma)
        w[0][n] = scipy.stats.norm.pdf(y[0], scale = math.sqrt((beta**2)*(math.e**x[0,n])))
    w[0] = w[0]/np.sum(w[0])
    for t in range(1,T):
        for n in range(particles):
            resampling = np.argmax(np.random.multinomial(1, w[t-1]))
            x[t][n] = np.random.normal(phi * x[t-1][resampling], sigma)
            w[t][n] = scipy.stats.norm.pdf(y[t], scale = math.sqrt((beta**2)*(math.e**x[t,n])))
        w[t] = w[t] /np.sum(w[t]) #normalize weights
    return w, x

def BPF_stratifiedResampling(T, y, particles, phi, sigma, beta):
    x = np.zeros([T, particles])
    w = np.zeros([T, particles])
    u1 = np.random.uniform(low=0, high = 1/particles)
    u = np.array([u1 + (i-1)/particles for i in range(particles)])
    for t in range(T):
        if t > 0:
            count = 0
        for n in range(particles):
            if t == 0:
            x[t][n] = np.random.normal(0, sigma)
            w[t][n] = scipy.stats.norm.pdf(y[t], scale = math.sqrt((beta**2)*(math.e**x[t,n])))
            w[0] = w[0]/np.sum(w[0])
        else:
            lower = np.sum(w[t-1][:n])
            higher = np.sum(w[t-1][:n+1])
            strat = np.sum((lower <= u) & (u < higher))
        if strat != 0:
            for k in range(strat):
                x[t][k+count] = np.random.normal(phi * x[t-1][n], sigma)
                w[t][k+count] = scipy.stats.norm.pdf(y[t], scale = math.sqrt((beta**2)
                *(math.e**x[t,k+count])))* w[t-1][n]
            count += strat
        #normalize
        w[t] = w[t]/np.sum(w[t])
    return w,x

if __name__ == '__main__':
    main()    