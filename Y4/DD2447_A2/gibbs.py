from numpy import *
from collections import Counter

'''
def dna(N, M, W):
    seqList = open("data.txt", "r").read()
    seqList = [item.split(" ") for item in seqList.split('\n')[:N]]
    for j in seqList:
        del j[(M - 1):999]
    startList = []
    for s in range(N):
        r = random.randint(0, M - W + 1)
        startList.append(r)
    print('Start list: ', startList)

    return seqList, startList
'''

# This function generates sample sequences for the Magic Word problem.
# Inputs: numSeq (number of sequences - N), lenSeq (length of each sequence - M), lenMotif (length of motif - W)
#         alphaListBg (prior of Background dirichlet), alphaListMw (prior of Magic Word dirichlet)
def generateSequences(alphaListBg, alphaListMw, N, M, W):
    thetaBg = random.dirichlet(alphaListBg)
    thetaMw = random.dirichlet(alphaListMw, size=W)  # Generates Theta_j for each motif position

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
    print('Start list: ', startList)

    return seqList, startList

def posEstimater(alphaListBg, alphaListMw, data, prevPos, seqID, W, alphabet):
    """
    Returns a list of probabilities for an element being the starting position for the magic word for a given seq
    """
    pPos = []
    B = N * (M - W)
    for j in range(M - W):
        bg = []
        positions = list(prevPos)
        positions[seqID] = j
        for n in range(N):
            bg.append(data[n][0:positions[n]] + data[n][positions[n] + W:]) #counts nr of occurences of each elem in bg
        countOccurBg = Counter([item for elem in bg for item in elem])

        motifs = []
        for n in range(N):
            motifs.append(data[n][positions[n]:positions[n]+W])
        countOccurMotif = []
        for w in range(W):
            countOccurMotif.append(Counter(motifs[:, w])) #counts nr of occurences of each elem in motifs

        bgCalc = math.lgamma(sum(alphaListBg)) - math.lgamma(B * sum(alphaListBg))
        motifCalc = math.lgamma(sum(alphaListMw)) - math.lgamma(N * W * sum(alphaListMw))

        computeProb = []
        for k in range(K):
            computeProb.append(math.lgamma(countOccurBg[alphabet[k]] + alphaListBg[k]) - math.lgamma(alphaListBg[k]))

        pBg = bgCalc + sum(computeProb)

        for j in range(W):
            pMotif = []
            for k in range(K):
                pMotif.append(
                    math.lgamma(countOccurMotif[j][alphabet[k]] + alphaListMw[k]) - math.lgamma(alphaListMw[k]))
            col = motifCalc + sum(pMotif)
            pBg += col

        pPos.append(pBg)
    return pPos

def gibbs(data, alphaListBg, alphaListMw, W, iter, alphabet):
    """
    Estimates the start positions of the motif in each row of data.
    """
    M = len(data[0])
    N = len(data)
    res = []
    res.append(random.randint(M - W + 1) for _ in range(N))

    for i in range(iter):
        pos = []
        for n in range(N):
            posEst = posEstimater(alphaListBg, alphaListMw, data, res[-1], n, W, alphabet)

            # estimation is normalized
            norm = exp(posEst - max(posEst))
            posEst = norm / sum(norm)

            temp = argmax(random.multinomial(1, posEst))
            pos.append(temp)

        res.append(array(pos))

    res = []
    for j in range(iter):
        res.append(res[j])

    chain = []
    rg = range(len(res))

    for i in range(N):
        chain.append(Counter([res[j][i] for j in rg]).most_common(1)[0][0])
    return chain

if __name__ == '__main__':
    alphaListBg = [1,1,1,1]
    alphaListMw = [0.8,0.8,0.8,0.8]
    alphabet = [1, 2, 3, 4]
    N = 25
    M = 100
    W = 10
    K = len(alphabet)

    #seqList, startList = dna(N, M, W)
    seqList, startList = generateSequences(alphaListBg, alphaListMw, N, M, W)

    #print(seqList)
    #print(startList)
    #iter = 100
    #estPos = gibbs(seqList, alphaListBg, alphaListMw, W, iter, alphabet)
    #print(estPos)

    #Compare the estimations to the initial magic word positions
    #print('Accuracy check for N = {}, M = {}, W = {}, iterations={}'.format(N, M, W, iter))
    #for (realPos, guessedPos) in zip(startList, estPos):
    #    print('Real starting position was {}, gibbs guessed {} so we have {}'.format(realPos, guessedPos, 'a match!'
    #    if realPos == guessedPos else 'no match.'))