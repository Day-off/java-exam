package ee.taltech.iti0202.files.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputFilesBufferReader implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        Path path = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                lines.add(line);
            }
        } catch (FileReaderException e) {
            throw new FileReaderException("No such file", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}