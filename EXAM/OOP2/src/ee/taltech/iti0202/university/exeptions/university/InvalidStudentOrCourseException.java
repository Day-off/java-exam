package ee.taltech.iti0202.university.exeptions.university;

public class InvalidStudentOrCourseException extends Exception {

    public static final String error = "Student or course don't exist in this university";

    public InvalidStudentOrCourseException() {
        super(error);
    }
}
