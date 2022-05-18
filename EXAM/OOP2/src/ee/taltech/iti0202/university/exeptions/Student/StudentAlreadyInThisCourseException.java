package ee.taltech.iti0202.university.exeptions.Student;

public class StudentAlreadyInThisCourseException extends Exception {

    public static final String error = "Student already studying this course.";

    public StudentAlreadyInThisCourseException(){
        super(error);
    }
}
