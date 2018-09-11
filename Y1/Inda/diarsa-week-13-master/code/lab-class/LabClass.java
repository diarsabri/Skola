import java.util.*;

public class LabClass
{
    private Instructor instructor;
    private String room;
    private String timeAndDay;
    private ArrayList<Student> students;
    private int capacity;

    public LabClass(int maxNumberOfStudents)
    {
        room = "unknown";
        timeAndDay = "unknown";
        students = new ArrayList<Student>();
        capacity = maxNumberOfStudents;
    }

    public void enrollStudent(Student newStudent)
    {
        if(students.size() == capacity) {
            System.out.println("The class is full, you cannot enrol.");
        }
        else {
            students.add(newStudent);
        }
    }

    public int numberOfStudents()
    {
        return students.size();
    }

    public void setRoom(String roomNumber)
    {
        room = roomNumber;
    }

    public void setTime(String timeAndDayString)
    {
        timeAndDay = timeAndDayString;
    }

    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }

    public void printList()
    {
        System.out.println("Lab class " + timeAndDay);
        System.out.println(instructor.info());
        System.out.println("Room: " +room);
        System.out.println("Class list:");
        for(Student student : students) {
            student.print();
        }
        System.out.println("Number of students: " + numberOfStudents());
    }
}
