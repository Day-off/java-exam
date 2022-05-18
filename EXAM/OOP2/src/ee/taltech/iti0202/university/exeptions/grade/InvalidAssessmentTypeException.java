package ee.taltech.iti0202.university.exeptions.grade;

public class InvalidAssessmentTypeException extends Exception {

    public static final String ERROR = "This type of assessment does not exist";

    public InvalidAssessmentTypeException() {
        super(ERROR);
    }
}
