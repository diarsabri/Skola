public class Student extends Participant
{
    private int credits;

    public Student(String fullName, String Pid)
    {
        super(fullName,Pid);
        credits = 0;
    }

    public void addCredits(int additionalPoints)
    {
        credits += additionalPoints;
    }

    public int getCredits()
    {
        return credits;
    }

    public void print()
    {
        System.out.println(name + ", student ID: " + id + ", credits: " + credits);
    }
}
