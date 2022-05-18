package ee.taltech.iti0202.university.exeptions.Student;

public class StudentToYoungOrOldException extends Exception {

    public static final String error = "Incorrect age";

    public StudentToYoungOrOldException(){
        super(error);
    }
}
