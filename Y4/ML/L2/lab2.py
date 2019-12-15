import numpy, random , math
from scipy . optimize import minimize
import matplotlib . pyplot as plt

numpy.random.seed(100)

classA = numpy.concatenate((
    numpy.random.randn(10, 2) * 0.3 + [1.5, 0.5],
    numpy.random.randn(5, 2) * 0.3 + [-1.5, 0.5],
    numpy.random.randn(5, 2) * 0.7 + [0.0, -1.0],
    ))
classB=numpy.random.randn(20, 2) * 0.3 + [0.0, -0.5]

inputs=numpy.concatenate((classA, classB))
targets=numpy.concatenate((numpy.ones(classA.shape[0]), -numpy.ones(classB.shape[0])))

N = inputs.shape[0] # Number of rows (samples)

permute = list(range(N))
random.shuffle(permute)
inputs = inputs[permute,:]
targets = targets[permute]

def LinearKernel(x, y):
    # Todo: Transform x
    return numpy.dot(x, y)

Kernel = LinearKernel

Pmatrix = numpy.zeros((N,N))
for i in range(N):
    for j in range(N):
        Pmatrix[i][j] = targets[i] * targets[j] * Kernel(inputs[i], inputs[j])


print(N)
#print (inputs)
#print (targets)


