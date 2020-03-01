import numpy as np 
import math 
import DataGenerator as DG
import matplotlib.pyplot as plt
from scipy.special import softmax
from tempfile import TemporaryFile
import time




## help func ##
def count(r_n):
    bg = []
    wr = []
    for i in range(N):
        r_i=r_n[i]
        b = []
        w = []
        b = list(S_N[i])
        for r in range(W):
            w.append(b.pop(r_i))
        bg.append(b)
        wr.append(w)

    ## This loop takes ~0.03s with the data from git.
    count_b = 0
    st = time.time()
    N_k=np.zeros((K,W))
    B_k = np.zeros(K)
    for bg_m in bg:
        for b in bg_m:
            B_k[b]=B_k[b]+1
    #print(time.time() - st)

    for i in range(W):
        for w in wr:
            N_k[w[i]][i]+=1
    
    return B_k, N_k.astype(int)

## Gibbs sampler ##

def pr_magic_word(N_k,j):
    tmp = ( math.lgamma(sum(alpha)) - math.lgamma(N*W*sum(alpha)) )
    tmp2 =[]
    for i in range(K):
        tmp2.append( (math.lgamma(N_k[i][j] + alpha[i]) - math.lgamma(alpha[i])) )
    return tmp + sum(tmp2)

def pr_backround(B_k):
    tmp = math.lgamma(sum(alpha_prime)) - math.lgamma(N*(M-W)*sum(alpha_prime)) 
    tmp2 =[]
    for i in range(K):
        tmp2.append( (math.lgamma(B_k[i] + alpha_prime[i]) - math.lgamma(alpha_prime[i])) )
    return tmp + sum(tmp2)

def gibbs_sampler(iterations=50):
    samples = []
    rn = np.zeros(N).astype(int)
    for it in range(iterations):
        st = time.time()
        for k in range(N):
            r_i = []
            #st = time.time()
            for i in range(M-W+1):
                np.put(rn, k,i)
                B_k, N_k = count(rn)
                f = []
                for j in range(W):
                    f.append(pr_magic_word(N_k,j))
                r_i.append(pr_backround(B_k) + sum(f))
            #print('one r_n: ', time.time() - st,'s')
            np.put(rn,k, np.random.choice(range(len(r_i)),1,p=softmax(r_i) )[0] )
        samples.append(list(rn))
        print('iteration: ', it, 'Time: ',time.time() - st)
    return samples

## Data from git. ##
def readData():
    N=25
    M=1000
    K=4
    W=10
    alpha=[0.8,0.8,0.8,0.8]
    alpha_prime=[1,1,1,1]
    code = {'a':0, 'c':1, 'g':2, 't':3}
    S_N=np.zeros((N,M))
    f = open("data.txt", "r")
    for i,line in enumerate(f):
        if i == N:
            break
        for j,char in enumerate(line.split(" ")):
            S_N[i][j] = code[char.rstrip("\n")]
    return S_N.astype(int), N, M, alpha, alpha_prime, K, W


## Run ##


dg = DG.DataGenerator()
N = 100
S_N, R_N, alpha, alpha_prime, W, M, K = dg.generate_sequences(N)


#S_N, N, M, alpha, alpha_prime, K, W = readData()
#R_N = np.zeros(N)

start = time.time()

samples = []
chains = 3
for i in range(chains):
    sampleTime = time.time()
    samples.append(gibbs_sampler())
    print('sample: ', i)
    sampleTime = time.time() - sampleTime
    print('Estimated time left: ',(chains-i)*(sampleTime), 's')
    print()
print('Final time: ', time.time() - start,'s')


for i,sample in enumerate(samples):
    for j, s in enumerate(sample):
        np.square(np.subtract(R_N,s)).mean()



x=[]
y=[]
for i,sample in enumerate(samples):
    for j, s in enumerate(sample):
        x.append(j)
        y.append(np.square(np.subtract(R_N,s)).mean())
    plt.plot(x,y)
    x=[]
    y=[]
plt.show()



for r in range(20):
    x=[]
    y=[]
    print('R: ', R_N[r])
    for i,sample in enumerate(samples):
        for j, s in enumerate(sample):
            x.append(j)
            y.append(s[r])
        plt.plot(x,y)
        x=[]
        y=[]
    plt.title("r_"+str(r))
    plt.ylabel("Actual R: " + str(R_N[r]))
    plt.xlabel("Iteration")
    plt.show()

