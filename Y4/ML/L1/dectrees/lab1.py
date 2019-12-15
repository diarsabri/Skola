import sys
sys.path.insert(1,'./python/')
import monkdata as m
import dtree
import drawtree_qt4 as draw
import numpy as np
import matplotlib.pyplot as plt
import partition as prt


sets = [[m.monk1],[m.monk2],[m.monk3]]
tests = [[m.monk1test],[m.monk2test],[m.monk3test]]

#Q1
#print(dtree.entropy(m.monk1))
#print(dtree.entropy(m.monk2))
#print(dtree.entropy(m.monk3))


#Q3
setGain = [["MONK1"],["MONK2"],["MONK3"]]
maxgain = [[0,0],[0,0],[0,0]]
for i in range(0,3):
    for j in range(0,6):
        gain = dtree.averageGain(tuple(sets[i][0]),m.attributes[j])
        setGain[i].append(gain)
        if gain > maxgain[i][0]:
            maxgain[i] = [gain,j+1]
#print(setGain)
#print(maxgain)

#Q3 - omgjord
gains_sets = []
for i in range(0,3):
    gains = [(dtree.averageGain(tuple(sets[i][0]),a),a) for a in m.attributes]
    gains_sets.append(gains)
    #print gains

#attributes med hogst gains i varje set
#print([max(gains_sets[a]) for a in range(0,3)])


#Q5_prep
selected_subset = [dtree.select(tuple(sets[0][0]),max(gains_sets[0])[1],a) for a in m.attributes[4].values]
subset = [val for sublist in selected_subset for val in sublist] #flattened sublist
popped_a5_list = m.attributes[:4] + m.attributes[5:]    #removing A5 from the list of attributes
gainss = [(dtree.averageGain(tuple(subset),a),a) for a in popped_a5_list]   #info gain for attributes
#print(max(gainss)) #(0.07527255560831925, A1)
#print(gainss)
sel_subset_2 = [dtree.select(subset,m.attributes[0],a) for a in m.attributes[0].values]
subset_2 = [val for sublist in sel_subset_2 for val in sublist] #flattened sublist
new_attrs = popped_a5_list[1:]
gainsss = [(dtree.averageGain(tuple(subset_2),a),a) for a in new_attrs]
#print(gainsss)
#print(new_attrs)
#print(max(gainsss))
#gainsss = 
#print(dtree.mostCommon(subset_2))



#Q5

trees = [dtree.buildTree(sets[i][0],m.attributes) for i in range(0,3)]
#print([1-dtree.check(trees[i],tuple(sets[i][0])) for i in range(0,3)])    #training
#print([1-dtree.check(trees[i],tuple(tests[i][0])) for i in range(0,3)]) #testing


#dtree.select(tuple(sets[0][0]),m.attributes[0],)
#print(dtree.select(tuple(sets[0][0]),m.attributes[5],3))

#draw.drawTree(trees[0])

#Q7



fracs = [0.3, 0.4, 0.5, 0.6, 0.7, 0.8]
tries = 300


def prune_trees(dataset,testset):
    pruned = []

    for f in fracs :
        trn,val = prt.partition(dataset,f)
        tree = dtree.buildTree(trn,m.attributes)
        forr = dtree.allPruned(tree)
        best_pick = dtree.check(tree,val)
    
        best_tr = tree

        for t in forr:
            tmp_pick = dtree.check(t,val)
            if best_pick < tmp_pick:
                best_pick = tmp_pick
                best_tr = t
        
        err = 1 - dtree.check(best_tr,testset)
        pruned.append(err)

    return pruned


mean_m1,mean_m3 = 0,0
std_m1,std_m3   = 0,0
def foo(plot,std):
    m1_pruned,m3_pruned = [],[]

    for i in range(tries):
        m1_pruned.append(prune_trees(m.monk1,m.monk1test))
        m3_pruned.append(prune_trees(m.monk3,m.monk3test))

    m1_pruned,m3_pruned = np.transpose(m1_pruned),np.transpose(m3_pruned)

    mean_m1,mean_m3 = np.mean(m1_pruned,axis=1),np.mean(m3_pruned,axis=1)
    std_m1,std_m3 = np.std(m1_pruned,axis=1),np.std(m3_pruned,axis=1)

    print("Mean MONK1" ,np.around(mean_m1,decimals=6),"\n",  "STD MONK1" , np.around(std_m1,decimals=6) ,"\n",  "Mean MONK3" , np.around(mean_m3,decimals=6) ,"\n", "STD MONK3" , np.around(std_m3,decimals=6))

    tree1,tree3 = dtree.buildTree(m.monk1,m.attributes),dtree.buildTree(m.monk3,m.attributes)

    err1,err3 = dtree.check(tree1,m.monk1test),dtree.check(tree3,m.monk3test)
    print("MONK1",1-err1,np.min(mean_m1))
    print("MONK3",1-err3,np.min(mean_m3))

    if plot == 1:
        if std == 0:
            plt.plot(fracs, mean_m1, color='blue', marker='o', label="Means")
            plt.plot(fracs, mean_m3, color='red', marker='o', label="Means")

            plt.title("Mean on MONK-datasets")
            plt.xlabel("Fractions")
            plt.ylabel("Mean of Error")
            plt.legend(['MONK1','MONK3'],loc='upper right', frameon=False)
            plt.show()
        else:
            plt.plot(fracs, std_m1, color='blue', marker='o', label="STD")
            plt.plot(fracs, std_m3, color='red', marker='o', label="STD")

            plt.title("STD on MONK-datasets")
            plt.xlabel("Fractions")
            plt.ylabel("STD of Error")
            plt.legend(['MONK1','MONK3'],loc='upper right', frameon=False)
            plt.show()


foo(1,0)


