package ee.taltech.iti0202.university.exeptions.Programme;

public class InvalidProgrammException extends Exception {

    public static final String ERROR = "No such programme in such uni or you are not a student";

    public InvalidProgrammException() {
        super(ERROR);
    }
}
