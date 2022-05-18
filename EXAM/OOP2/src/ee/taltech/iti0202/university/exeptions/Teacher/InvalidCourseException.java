package ee.taltech.iti0202.university.exeptions.Teacher;

public class InvalidCourseException extends Exception {

    public static final String ERROR = "You are not a teacher of this course.";

    public InvalidCourseException() {
        super(ERROR);
    }
}
