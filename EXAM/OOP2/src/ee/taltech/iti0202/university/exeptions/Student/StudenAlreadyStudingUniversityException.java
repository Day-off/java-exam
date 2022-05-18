package ee.taltech.iti0202.university.exeptions.Student;

public class StudenAlreadyStudingUniversityException extends Exception {

    public static final String error = "Student already studying at any university";

    public StudenAlreadyStudingUniversityException() {
        super(error);
    }
}
