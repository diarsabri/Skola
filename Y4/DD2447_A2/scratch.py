from __future__ import division
import matplotlib.pyplot as plt
import tqdm

import numpy as np
from collections import Counter
import math as math
import functools

plt.style.use('ggplot')


class DataGenerator:

    def __init__(self, alphabet=['a', 'b', 'c', 'd', 'e'], alpha=[1, 1, 1, 1, 1], alpha_prime=[1, 1, 1, 1, 1], M=10,
                 W=5):
        self.alphabet = alphabet
        self.alpha = alpha
        self.alpha_prime = alpha_prime
        self.M = M
        self.W = W
        self.generate_new_parameters()

    def generate_sequences(self, N=5, gen_new_params=False):
        if gen_new_params:
            self.generate_new_parameters()

        sample = []
        positions = []
        for i in range(N):
            seq = []
            pos = np.random.randint(0, self.M - self.W + 1)  # random positions for magic words
            for j in range(self.M):
                if pos <= j and j < pos + self.W:  # sample for magic words
                    seq.append(self.sample(self.thetas_magic[j - pos]))
                else:  # sample for background
                    seq.append(self.sample(self.theta_back))

            sample.append(seq)
            positions.append(pos)

        return sample, positions

    def sample(self, categorical):
        return self.alphabet[np.argmax(np.random.multinomial(1, categorical))]

    def generate_new_parameters(self):
        # background
        self.theta_back = np.random.dirichlet(self.alpha_prime)
        # magic-word
        self.thetas_magic = np.random.dirichlet(self.alpha, self.W)


class GibbsSampler:

    def __init__(self, alphabet, data, alpha, alpha_prime, W):
        self.alphabet = alphabet
        self.data = data
        self.alpha = alpha
        self.alpha_prime = alpha_prime
        self.W = W
        self.N = len(self.data)
        self.M = len(self.data[0])
        self.K = len(self.alphabet)
        self.B = self.N * (self.M - self.W)

    def p_background(self, positions, display=False):
        if display:
            print('backgorund')
        seq_backgrounds = [self.data[n][0:positions[n]] + self.data[n][positions[n] + self.W:] for n in range(self.N)]
        B = Counter([token for seq in seq_backgrounds for token in seq])

        sum_alpha_prime = sum(self.alpha_prime)
        C = math.lgamma(sum_alpha_prime) - math.lgamma(self.B * sum_alpha_prime)
        if display:
            print('C=', C)

        class_probs = [math.lgamma(B[self.alphabet[k]] + self.alpha_prime[k]) - math.lgamma(self.alpha_prime[k]) for k
                       in range(self.K)]
        if display:
            print('classes: ', class_probs)

        p = C + sum(class_probs)
        if display:
            print('p=', p)

        return p

    def p_magicword(self, positions, display=False):
        if display:
            print('magic word')
        seq_magic_words = [self.data[n][positions[n]:positions[n] + self.W] for n in range(self.N)]
        seq_magic_words = np.array(seq_magic_words)

        N_j = [Counter(seq_magic_words[:, w]) for w in range(self.W)]

        sum_alpha = sum(self.alpha)
        C = math.lgamma(sum_alpha) - math.lgamma(self.N * self.W * sum_alpha)
        if display:
            print('C=', C)

        class_probs_j = []
        for j in range(self.W):
            class_probs_jk = [math.lgamma(N_j[j][self.alphabet[k]] + self.alpha[k]) - math.lgamma(self.alpha[k]) for k
                              in range(self.K)]
            if display:
                print('classes{} = '.format(j), class_probs_jk)

            class_probs_jk = C + sum(class_probs_jk)
            if display:
                print('p{} = '.format(j), class_probs_jk)
            class_probs_j.append(class_probs_jk)

        return class_probs_j

    def estimate_posterior(self, sequence_id, prev_positions, display=False):
        # number of background positions
        pos_proba = []
        if display:
            print('estimate')
        for r_i in range(M - self.W):
            positions = list(prev_positions)
            positions[sequence_id] = r_i
            # print(positions)

            p_b = self.p_background(positions)
            p_mw = self.p_magicword(positions)
            p = p_b + sum(p_mw)
            if display:
                print('P( r_i = {} ) = {}'.format(r_i, p))
            pos_proba.append(p)

        # print(pos_proba)
        if display:
            print('---')
        return pos_proba

    def _normalize(self, p):
        p = np.exp(p - np.max(p))
        p = p / np.sum(p)
        return p

    def sampler(self, sample_count=100, min=50, step=10, display=False):

        iterations = sample_count * step + min
        samples = []

        # init positions
        init_pos = []
        init_pos = np.random.randint(0, self.M - self.W + 1, size=self.N)

        samples.append(init_pos)

        for i in tqdm.tqdm(range(iterations)):
            # is this Gibbs? shoudn't the new position be updated for every N
            # therefore implying that in this loop we reuse thre previuosly sampeld
            # position?
            positions = []
            gibbs_state = samples[-1]
            for n in range(self.N):
                # print('samples',samples[-1])
                p_pos = self.estimate_posterior(n, gibbs_state)
                p_pos = self._normalize(np.asarray(p_pos))
                # p_pos = np.exp(p_pos)
                if display:
                    print('Pos probs for seqeunce {}'.format(n))
                    print('Pos_Prob', p_pos)

                # sample
                multi_samp = np.random.multinomial(1, p_pos)
                if display:
                    print('Pos_Samp', multi_samp)

                position = np.argmax(multi_samp)
                if display:
                    print('Pos -> ', p_pos)
                    print('---')
                gibbs_state[n] = position
                positions.append(position)

            samples.append(np.array(positions))
        # lag
        chain = [samples[j] for j in range(min, iterations, step)]

        return chain


# Generate data
categorical = np.ones(4) * 1 / 4
alphabet = ['a', 'b', 'c', 'd']
M = 30
W = 10
N = 10

alpha_prime = [1, 1, 1, 1]
alpha = [12, 7, 3, 1]

dg = DataGenerator(alphabet, alpha, alpha_prime, M, W)
data, positions = dg.generate_sequences(N)

num_of_chains = 10
num_of_samples = 500
gs_samplers = [GibbsSampler(alphabet, data, alpha, alpha_prime, W) for _ in range(num_of_chains)]
gs_samples = [gs.sampler(min=0, sample_count=num_of_samples, step=1) for gs in gs_samplers]

gs_samples = np.array(gs_samples)

for m in range(N):  # for each position
    f, axarr = plt.subplots(3, 1, figsize=(16, 12), dpi=80)
    for c in range(num_of_chains):
        axarr[0].plot(gs_samples[c, 0:50, m], label='$c={}$'.format(c))
        axarr[0].set_title('First {} samples'.format(50))

        axarr[1].plot(gs_samples[c, num_of_samples - 100:, m], label='$c={}$'.format(c))
        axarr[1].set_title('Last {} samples'.format(100))

        axarr[2].plot(gs_samples[c, :, m], label='$c={}$'.format(c))
        axarr[2].set_title('All samples'.format(50))

        f.suptitle('Position $m={}$'.format(m), fontsize=18)
        f.tight_layout(rect=[0, 0.03, 1, 0.95])
    plt.savefig('figures/T_2_4/Q2/convergence_pos{}.png'.format(m))
    plt.show()

for m in range(N):  # for each position
    f, axarr = plt.subplots(3, 1, figsize=(16, 12), dpi=80)
    for c in range(num_of_chains):
        axarr[0].hist(gs_samples[c, 0:50, m], label='$c={}$'.format(c), bins=100)
        axarr[0].set_title('First {} samples'.format(50))

        axarr[1].hist(gs_samples[c, num_of_samples - 100:, m], label='$c={}$'.format(c), bins=100)
        axarr[1].set_title('Last {} samples'.format(100))

        axarr[2].hist(gs_samples[c, :, m], label='$c={}$'.format(c), bins=100)
        axarr[2].set_title('All samples'.format(50))

        f.suptitle('Position $m={}$'.format(m), fontsize=18)
        f.tight_layout(rect=[0, 0.03, 1, 0.95])
    plt.savefig('figures/T_2_4/Q2/distribution_pos{}.png'.format(m))
    plt.show()

    '''
    Calculate the estimated potential scale reduction statistics for the given list of chain samples of vector varaibles.

    data : np.array of dimension CxSxD where C is the number of chains, S the number of samples in each chain and D being the dimension of the sampled variable.

    return : float, espr metric
    '''
    C, S, D = data.shape

    y_dotc = np.mean(data, axis=1)

    y_dotdot = np.mean(y_dotc, axis=0)

    B = S / (C - 1.) * np.sum((y_dotc - y_dotdot) ** 2, axis=0)

    W_inner = np.sum(np.array([(data[c, :, :] - y_dotc[c, :]) ** 2 for c in range(C)]), axis=1)
    W = 1. / C * np.sum(1. / (S - 1) * W_inner, axis=0)

    V_hat = (S - 1.) / S * W + 1. / S * B

    epsr_score = np.sqrt(V_hat / W)

    return epsr_score

C, S, D = gs_samples.shape

convergene_over_time = np.array([epsr(gs_samples[:, 0:t, :]) for t in range(2, S)])

plt.figure(figsize=(16, 8))
for pos in range(D):  # for each position plot a new graph
    plt.plot(convergene_over_time[:, pos], label='$position = {}$'.format(pos))
plt.title('Estimated potential scale reduction over sample number')
plt.legend(loc=1)
plt.savefig('figures/T_2_4/Q2/convergence_epsr.png')
plt.show()
