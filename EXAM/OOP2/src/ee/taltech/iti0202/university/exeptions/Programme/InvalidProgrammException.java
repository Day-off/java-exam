package ee.taltech.iti0202.university.exeptions.Programme;

public class InvalidProgrammException extends Exception {

    public static final String ERROR = "Choose curriculum from your university";

    public InvalidProgrammException() {
        super(ERROR);
    }
}
