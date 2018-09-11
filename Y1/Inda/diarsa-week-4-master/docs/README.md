3.2: the class diagram can change if you change something in the source code of the class. For example if you add another class.

3.3: the object diagram can change if you change the variables in the object. For example if you have an object reference then the different variables that are stored can change the object diagram.

3.9: the second and last expressions. 

3.10: !(a||b)||(a&&b)

3.11: a^b

3.12: !(!a || !b)

3.21: I can't see why one would be better than the other. Maybe its better to use mathematical operators than to use if statements?

3.26: This calls for the object Editor, sets the string variable to [readme.txt] and the int variable to [-1].
The signature would be:
public Editor(String file, int number)

3.27: 
public Shape(Rectangle window)
{
window = new Rectangle(1, 2);
}

3.30: 
Call 1:
public void print(String filename, boolean doubleSided)
{
String filename = "RapportPM";
System.out.println("The name of this file is:" +filename);
}

Call 2:
p1.print("RapportPM", false);

Call 3:
p1.getStatus(2);

Call 4:
public int getStatus(int delay)
{
delay = lagtime
if(delay >= 2)
return delay;
}