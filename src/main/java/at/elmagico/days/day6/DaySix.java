package at.elmagico.days.day6;

import at.elmagico.days.fileReader.InputReader;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySix {

    public static Integer getBeginOfPacket(final String filepath) throws IOException {
        final List<String> input = InputReader.readInputFile(filepath);
        if (input.size() != 1) throw new IllegalArgumentException("Wrong file!");

        final String line = input.get(0);

        for (int i = 0; i + 3 < line.length(); i++) {
            final String one = line.substring(i, i + 1);
            final String two = line.substring(i + 1, i + 2);
            final String three = line.substring(i + 2, i + 3);
            final String four = line.substring(i + 3, i + 4);

            final Set<String> setOfSubstrings = new HashSet<>();
            setOfSubstrings.add(one);
            setOfSubstrings.add(two);
            setOfSubstrings.add(three);
            setOfSubstrings.add(four);
            if (setOfSubstrings.size() == 4) return i + 4;
        }
        throw new RuntimeException("no Start could be found");
    }

    public static Integer getBeginOfMessage(final String filepath) throws IOException {
        final List<String> input = InputReader.readInputFile(filepath);
        if (input.size() != 1) throw new IllegalArgumentException("Wrong file!");

        final String line = input.get(0);

        for (int i = 0; i + 14 < line.length(); i++) {
            final Set<String> setOfStrings = new HashSet<>();
            for (int j = 0; j < 14 && i + j < line.length(); j++) {
                setOfStrings.add(line.substring(i + j, i + j + 1));
            }
            if (setOfStrings.size() == 14) return i + 14;
        }
        throw new RuntimeException("no Start could be found");
    }
}
