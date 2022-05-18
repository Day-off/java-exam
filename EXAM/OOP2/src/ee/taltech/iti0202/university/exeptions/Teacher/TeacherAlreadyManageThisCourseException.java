package ee.taltech.iti0202.university.exeptions.Teacher;

public class TeacherAlreadyManageThisCourseException extends Exception {

    public static final String error = "Teacher already manage this course";

    public TeacherAlreadyManageThisCourseException(){
        super(error);
    }
}
