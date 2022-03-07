package ee.taltech.iti0202.files.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFilesScanner implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
        } catch (FileReaderException | FileNotFoundException e) {
//            System.out.println(e.getMessage());
            throw new FileReaderException("No such file", e.getCause());
        }
        return lines;
    }

}