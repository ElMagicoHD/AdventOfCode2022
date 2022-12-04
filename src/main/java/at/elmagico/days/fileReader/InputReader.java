package at.elmagico.days.fileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;


public class InputReader {

    public static List<String> readInputFile(final String path) throws IOException {
        try (final Stream<String> lines = Files.lines(Paths.get(path))) {
            return lines.toList();
        }
    }
}
