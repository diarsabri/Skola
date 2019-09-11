import sys
sys.path.insert(1,'./python/')
import monkdata as m
import dtree

sets = [[m.monk1],[m.monk2],[m.monk3]]

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


#Q5
print(dtree.select(tuple(sets[0][0]),m.attributes[5],3))


