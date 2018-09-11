public class Participant
{
    public String name;
    public String id;

    public Participant(String fullName, String Pid)
    {
        name = fullName;
        id = Pid;
    }

    public String getName()
    {
        return name;
    }

    public void changeName(String replacementName)
    {
        name = replacementName;
    }

    public String getStudentID()
    {
        return id;
    }

    public String getLoginName()
    {
        return name.substring(0,4) + id.substring(0,3);
    }
}