package ee.taltech.iti0202.university.exeptions.Programme;

public class InvalidProgrammException extends Exception {

    public static final String error = "No such programme in such uni or you are not a student";

    public InvalidProgrammException() {
        super(error);
    }
}
