package ee.taltech.iti0202.files.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFilesScanner implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(filename)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
        } catch (FileReaderException e) {
            e.printStackTrace();
        }
        return lines;
    }
}