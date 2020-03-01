import numpy as np

# This function generates sample sequences for the Magic Word problem. 
# Inputs: N (number of sequences - N), M (length of each sequence - M), W (length of motif - W)
#         alphaListBg (prior of Background dirichlet), alphaListMw (prior of Magic Word dirichlet)
def generateSequences(alphaListBg, alphaListMw, N, M, W):
    
    # Generate thetas for Background and Motif with corresponding Dirichlet priors
    thetaBg = np.random.dirichlet(alphaListBg)
    thetaMw = np.random.dirichlet(alphaListMw, size=W) # Generates Theta_j for each motif position
    
    seqList = np.zeros((N, M))
    startList = np.zeros(N)

    for s in range(N):
        # Get the starting point of motif
        r = np.random.randint(M-W+1)
        startList[s] = r

        for pos in range(M):
            # Sample from Background
            if pos < r or pos >= r+W:
                value = np.where(np.random.multinomial(1,thetaBg)==1)[0][0]
            # Sample from Motif
            else:
                j = pos - r # index of motif letter
                value = np.where(np.random.multinomial(1,thetaMw[j])==1)[0][0]
                
            seqList[s,pos] = value
 
    seqList = seqList.astype(int)  
    startList = startList.astype(int) 

    
    # Store the motifs in the sequences into a multidimensional array for debugging.
    motifList = np.zeros((N,W))
    for i in range(N):
        r = startList[i]
        motifList[i] = seqList[i,r:r+W]
    motifList = motifList.astype(int)
            
    return seqList, startList, motifList, thetaBg, thetaMw


#Estimates the posterior over start positions after observations.
def estimatePosterior():
    return NULL

def main():    
    alphaListBg = [1,1,1,1]
    alphaListMw = [0.8,0.8,0.8,0.8]
    N = 30  #Number sequences
    M = 20  #Length sequence
    W = 5   #Length magic word
    K = 4   #Length alphabet

    seqList, startList, motifList, thetaBg, thetaMw = generateSequences(alphaListBg, alphaListMw, N, M, W)


if __name__ == "__main__": 
    main()
