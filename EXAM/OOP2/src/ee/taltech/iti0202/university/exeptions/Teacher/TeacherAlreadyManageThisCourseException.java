package ee.taltech.iti0202.university.exeptions.Teacher;

public class TeacherAlreadyManageThisCourseException extends Exception {

    public static final String ERROR = "Teacher already manage this course";

    public TeacherAlreadyManageThisCourseException() {
        super(ERROR);
    }
}
