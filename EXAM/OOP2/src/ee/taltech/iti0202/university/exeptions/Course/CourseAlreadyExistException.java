package ee.taltech.iti0202.university.exeptions.Course;

public class CourseAlreadyExistException extends Exception {

    public static final String error = "Course already exist and connected with some university.";

    public CourseAlreadyExistException() {
        super(error);
    }
}
