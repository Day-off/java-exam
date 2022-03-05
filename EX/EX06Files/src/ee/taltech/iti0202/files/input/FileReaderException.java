package ee.taltech.iti0202.files.input;

public class FileReaderException extends RuntimeException{

    private final String message;
    private final Throwable reason;


    public FileReaderException(String message, Throwable reason){
        this.message = message;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getReason() {
        return reason;
    }
}
