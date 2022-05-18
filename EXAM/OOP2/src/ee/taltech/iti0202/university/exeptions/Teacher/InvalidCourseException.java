package ee.taltech.iti0202.university.exeptions.Teacher;

public class InvalidCourseException extends Exception {

    public static final String error = "You are not a teacher of this course.";

    public InvalidCourseException() {
        super(error);
    }
}
