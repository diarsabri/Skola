import numpy as np
from numpy import *
import math as math
from collections import Counter
import tqdm
import matplotlib.pyplot as plot
from scipy.stats import mode
from statsmodels.graphics.tsaplots import plot_acf

def generateSequences(alphaListBg, alphaListMw, N, M, W):
    thetaBg = random.dirichlet(alphaListBg)
    thetaMw = random.dirichlet(alphaListMw, size=W) # Generates Theta_j for each motif position
    seqList = zeros((N, M))
    startList = zeros(N)
    for s in range(N):
        # Get the starting point of motif
        r = random.randint(M - W + 1)
        startList[s] = r
        for temp in range(M):
            # Sample from Background
            if temp < r or temp >= r + W:
                value = where(random.multinomial(1, thetaBg) == 1)[0][0]
            # Sample from Motif
            else:
                value = where(random.multinomial(1, thetaMw[temp - r]) == 1)[0][0]
            seqList[s, temp] = value
    seqList = seqList.astype(int).tolist()
    startList = startList.astype(int).tolist()
    #print('Start list: ', startList)
    #print('Seq list: ', seqList)
    return seqList, startList

def dna(N,M):
    f = np.loadtxt("data.txt", dtype=str)
    seqList = np.delete(f, s_[N:], axis=0)
    seqList = np.delete(seqList, s_[M:], axis=1)
    seqTemp = zeros((N,M))
    dict = {'a':0, 'c':1, 'g':2, 't':3}
    for i in range(N):
        for y in range(M):
            seqTemp[i,y] = dict[seqList[i,y]]
    seqTemp = seqTemp.astype(int).tolist()
    return seqTemp

def posEstimater(alphaListBg, alphaListMw, data, prevPos, seqID, W, alphabet):
    """
    Returns a list of probabilities for an element being the starting position for the magic word for a
    given seq
    """
    pPos = []
    for mw in range(M - W + 1):
        B = N * (M - W)
        positions = list(prevPos)
        positions[seqID] = mw
        bg, motifs, countOccurMotif, computeProb, pMotif = ([] for _ in range(5))
        for n in range(N):
            bg.append(data[n][0:positions[n]] + data[n][positions[n] + W:])
            motifs.append(data[n][positions[n]:positions[n] + W])
        countOccurBg = Counter([item for elem in bg for item in elem]) #counts nr of occ of each elem in bg
        motifs = array(motifs)
        for mw in range(W):
            countOccurMotif.append(Counter(motifs[:, mw]))
        bgCalc = math.lgamma(sum(alphaListBg)) - math.lgamma(B * sum(alphaListBg))
        motifCalc = math.lgamma(sum(alphaListMw)) - math.lgamma(N * W * sum(alphaListMw))
        for k in range(K):
            computeProb.append(math.lgamma(countOccurBg[alphabet[k]] + alphaListBg[k]) -
            math.lgamma(alphaListBg[k]))
        pBg = bgCalc + sum(computeProb)
        for mw in range(W):
            prod = []
            for k in range(K):
                prod.append(math.lgamma(countOccurMotif[mw][alphabet[k]] + alphaListMw[k]) -
                math.gamma(alphaListMw[k]))
            prod = motifCalc + sum(prod)
            pMotif.append(prod)
        p = pBg + sum(pMotif)
        pPos.append(p)
    return pPos

def gibbs(data, alphaListBg, alphaListMw, M, N, W, iter, alphabet,plott):
    """
    Estimates the start positions of the motif in each row of data.
    """
    res = []
    res.append(random.randint(0, M - W + 1, size=N))#random initial guess
    for _ in tqdm.tqdm(range(iter)):
        pos = []
        for n in range(N):
            posEst = posEstimater(alphaListBg, alphaListMw, data, res[-1], n, W, alphabet)
            # estimation is normalized
            norm = np.exp(posEst - max(posEst))
            posEst = norm / sum(norm)
            temp = np.argmax(random.multinomial(1, posEst))
            pos.append(temp)
        res.append(np.array(pos))
    resBurned = [res[j] for j in range(int(iter*0.50), iter)]#burn 50%
    usual = [Counter([res[j][i] for j in range(len(res))]).most_common(1)[0][0] for i in range(N)]
    return resBurned,usual,res[:iter]

def runGenerated(alphaListBg,alphaListMw,alphabet,N,M,W,K,chains,iter,plotHistogram,plotConvergence,
printAccuracy):
    #Generated data
    seqList, startList = generateSequences(alphaListBg, alphaListMw, N, M, W)
    #Gibbs sampler
    res,usual,rfull = [],[],[]
    for c in range(chains):
        r, u,rf = gibbs(seqList, alphaListBg, alphaListMw, M, N, W, iter, alphabet,plott=False)
        res.append(array(r))
        usual.append(u)
        rfull.append(rf)
    usualAllChains,_ = mode(usual,axis=0) #usual estimate across all chains
    #Plot histogram
    if plotHistogram:
        for motif in range(N): # for each position
            plot.clf()
            plot.plot()
            for c in range(chains):
                plot.hist(np.array(rfull)[c,:,motif], label='$c={}$'.format([c,motif]), bins=M)
                plot.title('Position m={}, Estimated m={}'.format(startList[motif],usualAllChains
                [0,motif]), fontsize=18)
            #plot.show()
            figname = './Plots/Gen/histo_gen'
            plot.savefig(figname.format(motif)+str(motif)+'.png',format='PNG')

    #Plot convergence
    if plotConvergence:
        #Plot sequences
        plot.clf()
        plot.title('Convergence over each sequence across all chains')
        for c in range(chains):
            #plot.plot(res[c][::max(int(len(res[c])/100),1)][:])
            plot.plot(res[c][:][:])
        #plot.show()
        figname = './Plots/Gen/conv_gen'
        plot.savefig(figname+'.png',format='PNG')

    #Plot convergence with full autocorrelation and 95% confidence-interval
    plot.clf()
    fig = plot.figure()
    ax = fig.add_subplot()
    #plot.ylim(-1.1,1.1)
    for c in range(chains):
        for s in range(N):
            plot_acf(np.array(res)[c,:,s],ax=ax,use_vlines=True,title='Autocorrelation',lags=
            len(np.array(res)[:,:,:][1])-2,alpha=0.05,zero=False,missing='drop')
    #plot.show()
    figname = './Plots/Gen/conv_ac_gen'
    plot.savefig(figname+'.png',format='PNG')

    #Accuracy
    if printAccuracy:
        corr = 0
        print('Accuracy check for N = {}, M = {}, W = {}, iterations = {}, chains = {}'.format(N, M, W,
        iter,chains))
        for (realPos, guessedPos) in zip(startList, usualAllChains[0]):
            print('Start = {} \tEstimated = {}\t{}'.format(realPos, guessedPos, '\tMatch'
            if realPos == guessedPos else '\tNo Match'))
            if realPos == guessedPos:
                corr+=1
        print('Correct:\t',100*round(corr*1.0/N,3),'%')
    return

def runDNA(alphaListBg,alphaListMw,alphabet,N,M,W,K,chains,iter,plotHistogram,plotConvergence):
    #DNA data
    seqList, startList = dna(N,M), []
    #Gibbs sampler
    res,usual,rfull = [],[],[]
    for c in range(chains):
        r, u,rf = gibbs(seqList, alphaListBg, alphaListMw, M, N, W, iter, alphabet,plott=False)
        res.append(array(r))
        usual.append(u)
        rfull.append(rf)
    usualAllChains,_ = mode(usual,axis=0) #usual estimate across all chains

    #Plot histogram
    if plotHistogram:
        for motif in range(N): # for each position
            plot.clf()
            plot.plot()
            for c in range(chains):
                plot.hist(np.array(rfull)[c,:,motif], label='$c={}$'.format([c,motif]), bins=M)
                plot.title('Estimated m={}'.format(usualAllChains[0,motif]), fontsize=18)
            #plot.show()
            figname = './Plots/DNA/histo_dna'
            plot.savefig(figname.format(motif)+str(motif)+'.png',format='PNG')

    #Plot convergence
    if plotConvergence:
        #Plot sequences
        plot.clf()
        plot.title('Convergence over each sequence across all chains')
        for c in range(chains):
            #plot.plot(res[c][::max(int(len(res[c])/100),1)][:])
            plot.plot(res[c][:][:])
        #plot.show()
        figname = './Plots/DNA/conv_dna'
        plot.savefig(figname+'.png',format='PNG')
        #Plot convergence with full autocorrelation and 95% confidence-intervall
        plot.clf()
        fig = plot.figure()
        ax = fig.add_subplot()
        #plot.ylim(-1.1,1.1)
        for c in range(chains):
            for s in range(N):
                plot_acf(np.array(res)[c,:,s],ax=ax,use_vlines=True,title='Autocorrelation',lags=
                len(np.array(res)[:,:,:][1])-2,alpha=0.05,zero=False,missing='drop')
        #plot.show()
        figname = './Plots/DNA/conv_ac_dna'
        plot.savefig(figname+'.png',format='PNG')
    return

if __name__ == '__main__':
    alphaListBg = [1,1,1,1]
    alphaListMw = [0.8,0.8,0.8,0.8]
    alphabet = [0,1, 2, 3]
    N = 25 #Number of sequences
    M = 500 #Number of words in each sequence
    W = 10 #Length of magic word
    K = 4 #Length of alphabet
    chains = 3
    iter = 30
    plotHistogram = True
    plotConvergence = True
    printAccuracy = True
    runDNA(alphaListBg,alphaListMw,alphabet,N,M,W,K,chains,iter,plotHistogram,plotConvergence)
    runGenerated(alphaListBg,alphaListMw,alphabet,N,M,W,K,chains,iter,plotHistogram,
    plotConvergence,printAccuracy)
