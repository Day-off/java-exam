package ee.taltech.iti0202.files.input;

import java.util.List;

public interface InputFilesReader {

    /***
     * reader
     */
    List<String> readTextFromFile(String filename);
}
