package backend.Classes;

public class StudentClass {
    public String student;
    public String course;

    public StudentClass(){}
    public StudentClass(String pStudent, String pCourse){
        this.course = pCourse;
        this.student = pStudent;
    }

    public void setStudent(String pStudent){student = pStudent;}
    public String getStudent(){return student;}

    public void setCourse(String pCourse){course = pCourse;}
    public String getCourse(){return course;}
}
