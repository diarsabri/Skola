### Github Task: Time Complexity

#### Exercise: 1
Assume that we have four classes: `Person`, `Teacher`, `Student`, and `PhDStudent`. `Teacher` and `Student` are both subclasses of `Person`. `PhDStudent` is a subclass of `Student`.

a. Which of the following assignments are legal, and why or why not?

    The following are true because the hierarchy is correct.
    Person p1 = new Student();
    Person p2 = new PhDStudent();
    Student s1 = new PhDStudent();
    
    
    And the following are not true because a phdstudent is below a student, and the same argument for the teacher/person relation.
    PhDStudent phd1 = new Student();
    Teacher t1 = new Person();

b. Suppose that we have the following legal declarations and assignments:

    Person p1 = new Person();
    Person p2 = new Person();
    PhDStudent phd1 = new PhDStudent();
    Teacher t1 = new Teacher();
    Student s1 = new Student();

Based on those just mentioned, which of the following assignments are legal, and why or why not?

    s1 = p1; | False, we cannot assign a person to a student, it's the other way around. We assign a student to a person.
    s1 = p2; | False, same reason as above.
    p1 = s1; | True, see explanation for first one.

#### Exercise: 2
What has to change in the `NewsFeed` class when another `Post` subclass (for example, a class `EventPost`) is added? Why?

Nothing has to change, the Post class is still a superclass to eventPost, and it will not effect the relationship to NewsFeed.

#### Exercise: 3
Exercise 8.15 Use the documentation of the Java standard class libraries to find out about the inheritance hierarchy of the collection classes. Draw a diagram showing the hierarchy.

Uploaded a picture of the diagram.

#### Exercise: 4
Go back to the lab-classes project from Chapter 1. Add instructors to the project (every lab class can have many students and a single instructor). Use inheritance to avoid code duplication between students and instructors (both have a name, contact details, etc.).

Code provided in lab-class directory.

#### Exercise: 5
Here are two algorithms that calculate x<sup>n</sup>, where x is a real number and n is a non-negative integer.
Argue the correctness of both algorithms. You could for example use a loop invariant, and a proof by induction, respectively.
Calculate the time-complexity as a function of n for both algorithms. Give the result using Big-O notation.

    double expIterativ(double x, int n) {
        double res = 1.0;

        for (int i = 0; i < n; i++)
            res *= x;
        return res;
    }

    Den här första algoritmen kommer köras från i = 0 till n-1. Med andra ord kommer den köras n gånger.
    Ska bevisas: expIterativ(x,n) = x^n
    Basfall: res = 1, i = 0 -> x^0=1=res
    Antag algoritm sann för alla n.
    x^(n+1)=x*x^n | Då x^n=1 i basfallet kan vi använda det här.
    x*x^n=1*x=x*res => O(n)

    double expRekursiv(double x, int n) {
        if (n <= 4)
            return expIterativ(x, n);

        return expRekursiv(x, n/2) *
               expRekursiv(x, (n + 1)/2);
    }
    
    Vi använder mästersatsen:
    T(n) = aT(n/b) + f(n) | a=1, b>1.
    Algoritmen måste endas bevisas för fall då n>4 eftersom det är den föregående 
    algoritmen som körs i de fallen, och den är redan bevisad.
    f(n)=1 då funktionen körs mas fyra gånger. f(n)=1=T(n^d) => d=0.
    I andra fall anropas den rekursiva funktionen två gånger, a = 2 = b.
    T(n) = 2*T(n/2) + 1
    Enligt mästersatsen: om a>b^d : 2>2^0 -> Korrekt.
    T(n)= T(n^(log[b](a))) = T(n^(log[2](2)))=T(n)
    

