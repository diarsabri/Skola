# DD2421 Lab 1

## Diar Sabri

## P1 2019

### Q0

 **Q:** Each one of the datasets has properties which makes
 them hard to learn. Motivate which of the three problems is most
 difficult for a decision tree algorithm to learn.

* Since a1 & a2 are related it makes a split hard on these attributes
* A similar situation here, where two attributes are related and thus hard to split.
* Smallest amount of training data, as well as more noise than the other datasets.

The hardest one to learn would therefore be the second dataset, since it has the most depth (6).

### Q1

| Dataset | Entropy |
| ------- | ------- |
| MONK1   | 1.0     |
| MONK2   | 0.957117428265 |
| MONK3   | 0.999806132805 |

### Q2

**Q:** Explain entropy for a uniform distribution and a
non-uniform distribution, present some example distributions with
high and low entropy.

* A uniform distribution always has the same probability and thus the entropy grows logarithmically in relation with the number of outcomes. An example would be a perfect die or a coin flip.
* A non-uniform distribution (where the probability is different for different tries) is always less than or equal to log2(n). Examples are non-perfect dies. The entropy is therefore lower here than in a uniform distribution.

### Q3

| Dataset | a1 | a2 | a3 | a4 | a5 | a6 |
|-----| ---------------------  | --------------------- | --------------------- | --------------------  | ------------------- | --------------------- |
|MONK1| 0.07527255560831925    | 0.005838429962909286  | 0.00470756661729721   | 0.02631169650768228   | **0.28703074971578435** | 0.0007578557158638421 |
|MONK2| 0.0037561773775118823  | 0.0024584986660830532 | 0.0010561477158920196 | 0.015664247292643818  | **0.01727717693791797** | 0.006247622236881467  |
|MONK3| 0.007120868396071844   | **0.29373617350838865**   | 0.0008311140445336207 | 0.002891817288654397  | 0.25591172461972755 | 0.007077026074097326  |


### Q4

Since Entropy(S) is fixed, we need to minimize the rest of the equation to maximize the whole equation (aka minimize Entropy(Sk). We choose to split on the attribute with the highest gain and this will give us the "fastest road" to the solution. High entropy -> high uncertainty, hence if we reduce entropy we know more(higher information gain). This will in turn reduce the entropy in the subsets the most, and might lead to a more qualitative subset with a higher yield of information. 

### Q5

||1|2|3|
|-|-------------------|--------------------|--------------------|
|Train|0.0|0.0|0.0|
|Test|0.17129629629629628| 0.30787037037037035| 0.05555555555555558|

Obviously, the training dataset has no error since it's... the training set, and thus overfit. It's clear that the second MONK dataset would yield the highest error and thus the assumptions were correct. Although the first and last datasets have quite distinct results, even though one could assume that they would have similar results. Even though the last dataset has 5% more noise, it still came out ahead.

### Q6

Pruning reduces the model complexity, since the size of the decision tree is reduced. Since decision trees often has a high variance and low bias, due to the overfitting nature of the model, the variance will thus be reduced by pruning. When variance is reduced, bias is increased. This also makes the model more general and usable.



||Error on complete tree|Error on Pruned tree|
|-|-|-
|MONK1| 0.17129629629629628| 0.14071759259259273|
|MONK3| 0.05555555555555558| 0.05248456790123455|


||a1|a2|a3|a4|a5|a6|
|-|-|-|-|-|-|-|
|Mean MONK1| 0.232168 |0.207685 |0.175864 |0.162168 |0.150131 |0.138449| 
|STD  MONK1| 0.044512 |0.040978 |0.048363 |0.046571 |0.045468 |0.046972| 
|Mean MONK3| 0.090664 |0.078573 |0.063642 |0.055409 |0.051713 |0.053704| 
|STD  MONK3| 0.053049 |0.046397 |0.035403 |0.032224 |0.02827  |0.029465|


![](Figure_1.png)

