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
print(max(gainss)) #(0.07527255560831925, A1)
print(gainss)
sel_subset_2 = [dtree.select(subset,m.attributes[0],a) for a in m.attributes[0].values]
subset_2 = [val for sublist in sel_subset_2 for val in sublist] #flattened sublist
print(dtree.mostCommon(subset_2))


#Q5
t=dtree.buildTree(m.monk1, m.attributes)
print(dtree.check(t, m.monk1test))



#dtree.select(tuple(sets[0][0]),m.attributes[0],)
#print(dtree.select(tuple(sets[0][0]),m.attributes[5],3))


