4.50: 

If we remove a lot at let's say index 3 then index 4 would
take its' place. If we call the getLot method on index 3 it would 
instead show us the lot that was previously index 4.

4.73:

public int numberOfAccesses()
{
  int total = 0;
    for(int hour = 0; hour <= hourCounts.length -1; hour = hour +1)
      total = total + hourCounts[hour];
}
  return total;
}

4.77: It returns the first one in the array that pops up.