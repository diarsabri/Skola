public class Instructor extends Participant
{
    private String mail;

    public Instructor(String fullName,String Pid)
    {
        super(fullName,Pid);
        mail = fullName +"@kth.se";
    }

    public String info()
    {
        return "Instructor: "+name + ", Instructor ID: " + id +", Mail: "+mail ;
    }
}
