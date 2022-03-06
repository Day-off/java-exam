package ee.taltech.iti0202.files.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputFilesLines implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        Path path = Paths.get(filename);
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(path)) {
            lines = stream.collect(Collectors.toList());
        } catch (FileReaderException | IOException e) {
            System.out.println("No such file");
        }
        return lines;
    }
}