package ee.taltech.iti0202.university.exeptions.Course;

public class CourseAlreadyExistException extends Exception {

    public static final String ERROR = "Course already exist and connected with some university.";

    public CourseAlreadyExistException() {
        super(ERROR);
    }
}
