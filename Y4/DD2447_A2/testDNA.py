from __future__ import division
import numpy as np
from collections import Counter
import math as math
import matplotlib.pyplot as plt
import tqdm as tqdm

plt.style.use('ggplot')

def dna(numSeq, lenSeq, lenMotif):
    seqList = open("data.txt", "r").read()
    seqList = [item.split(" ") for item in seqList.split('\n')[:numSeq]]
    for j in seqList:
        del j[(lenSeq - 1):999]
    startList = []
    for s in range(numSeq):
        r = np.random.randint(0, lenSeq - lenMotif + 1)
        startList.append(r)
    print('Start list: ', startList)

    return seqList, startList

def generate_sequences(alphabet, alphaMw, alphaBg, N, M, W):
    thetaBg = np.random.dirichlet(alphaBg)
    thetaMw = np.random.dirichlet(alphaMw, W)
    samples = []
    positions = []
    for i in range(N):
        seq = []
        pos = np.random.randint(0, M - W + 1)  # Mw randomize init positions
        for j in range(M):
            if pos<=j and j<pos+W: #Mwsampling
                seq.append(sampleIt(alphabet, thetaMw[j - pos]))

            else:  # bg sampling
                seq.append(sampleIt(alphabet, thetaBg))
        samples.append(seq)
        positions.append(pos)
    print('Start list: ', positions)

    return samples, positions
def sampleIt(alphabet, cat):
    return alphabet[np.argmax(np.random.multinomial(1, cat))]

def estimate_prior(alphabet, alphaMw, alphaBg, N, M, W, data, positions):
    B = N * (M - W)

    # estimating background:
    bgData = [data[n][0:positions[n]] + data[n][positions[n] + W:] for n in range(N)]
    Bg = Counter([token for seq in bgData for token in seq])
    alphaBgSum = sum(alphaBg)
    calc = math.lgamma(alphaBgSum) - math.lgamma(B + alphaBgSum)
    probs = [math.lgamma(Bg[alphabet[k]] + alphaBg[k]) - math.lgamma(alphaBg[k]) for k in range(K)]
    p = calc + sum(probs)
    # estimating magic word:
    magicWordData = [data[n][positions[n]:positions[n] + W] for n in range(N)]
    magicWordData = np.array(magicWordData)
    N_j = [Counter(magicWordData[:, w]) for w in range(W)]
    alphaSum = sum(alphaMw)
    calc = math.lgamma(alphaSum) - math.lgamma(N * W + alphaSum)
    probsj = []
    for j in range(W):
        probskOverj = [math.lgamma(N_j[j][alphabet[k]] + alphaMw[k]) - math.lgamma(alphaMw[k]) for k
                          in range(K)]
        probskOverj = calc + sum(probskOverj)
        probsj.append(probskOverj)
    return p, probsj

def estPosterior(alphabet, alphaMw, alphaBg, sequence_id, M, W, data, prev_positions):
    pos_proba = []
    for r_i in range(M - W):
        positions = list(prev_positions)
        positions[sequence_id] = r_i
        p_b, p_mw = estimate_prior(alphabet, alphaMw, alphaBg, N, M, W, data, positions)
        p = p_b + sum(p_mw)
        pos_proba.append(p)
    return pos_proba

def p_normalize(p):
    p = np.exp(p - np.max(p))
    p = p/np.sum(p)
    return p

def gibbsSampler(alphabet, data, alphaMw, alphaBg, W, itrr):
    iterations = itrr
    samples = []
    initPos = np.random.randint(0, M - W + 1, size=N)
    samples.append(initPos)
    for _ in tqdm.tqdm(range(iterations)):
        positions = []
        gibbsCondition = samples[-1]


        for n in range(N):
            posProb = estPosterior(alphabet, alphaMw, alphaBg, n, M, W, data, gibbsCondition)
            posProb = p_normalize(np.asarray(posProb))
            multSamp = np.random.multinomial(1, posProb)
            position = np.argmax(multSamp)
            gibbsCondition[n] = position
            positions.append(position)
        samples.append(np.array(positions))
    # lag
    chain = [samples[j] for j in range(0, iterations)]
    return chain

def estPotentialScaleReduction(data):
    '''
    Returns a float representing the estimated PSR for given data.
    '''
    Chains, Iter, Dimension = data.shape
    yAx1 = np.mean(data, axis=1)
    yAx2 = np.mean(yAx1, axis=0)
    B = Iter / (Chains - 1.) * np.sum((yAx1 - yAx2) ** 2, axis=0)
    W = np.sum(np.array([(data[c, :, :] - yAx1[c, :]) ** 2 for c in range(Chains)]), axis=1)
    W = 1. / Chains * np.sum(1. / (Iter - 1) * W, axis=0)
    V = (Iter - 1.) / Iter * W + 1. / Iter * B
    psr = np.sqrt(V / W)
    return psr

if __name__ == '__main__':
    cat = np.ones(4) * 1 / 4
    alphabet = ['a', 'b', 'c', 'd']
    M = 100
    W = 10
    N = 25
    K = len(alphabet)
    alphaBg = [1, 1, 1, 1]
    alphaMw = [0.8, 0.8, 0.8, 0.8]
    data, positions = generate_sequences(alphabet, alphaMw, alphaBg, N, M, W) #
    #data, positions = dna(N, M, W)
    chains = 5
    iter = 10
    gibbs = np.array([gibbsSampler(alphabet, data, alphaMw, alphaBg, W, itrr=iter) for _ in range(chains)])
    #find most guessed guesses!
    axis=0

    u, index = np.unique(gibbs, return_inverse=True)
    print(u[np.argmax(np.apply_along_axis(np.bincount, axis, index.reshape(gibbs.shape), None, np.max(index) + 1), axis=axis)])
    #histograms
    for motif in range(N):  # for each position
        plt.plot()
    for c in range(chains):
        plt.hist(gibbs[c, :, motif], label='$c={}$'.format(c), bins=100)
        plt.title('Position $m={}$'.format(motif), fontsize=18)
    plt.show()
    #"convergogram"
    Chains, Iter, Data = gibbs.shape
    convergence = np.array([estPotentialScaleReduction(gibbs[:][0:t][:]) for t in range(2, Iter)])
    plt.figure(figsize=(20, 10))
    plt.plot()
    for pos in range(Data):  # for each position plot a new graph
        plt.plot(convergence[:,pos], label='$position = {}$'.format(pos))
    plt.title('Potential scale reduction estimated over {} sequences with length {}, chains = {}, iter = {}'.format(N,M,chains,iter))
    plt.legend(loc=1)
    plt.axis([0,20,0.8,1.3])
    plt.show()
