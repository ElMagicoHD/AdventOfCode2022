package at.elmagico.days.day3;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class DayThree {
    //First Part
    public static Integer findDuplicateAndMapToAsciiValue(final String filePath) {
        Integer result;
        try (final Stream<String> fileInputStream = Files.lines(Paths.get(filePath))) {

            result = fileInputStream.map(s -> {
                        final String left = s.substring(0, s.length() / 2);
                        final String right = s.substring(s.length() / 2);
                        return new ImmutablePair<>(left, right);
                    }).map(DayThree::getDuplicateChar)
                    .map(DayThree::mapStringToValue)
                    .reduce(0, Integer::sum);


        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    //Second Part
    public static Integer findGroupsAndMapToAsciiValue(final String filePath) throws FileNotFoundException {
        final File file = new File(filePath);

        final Scanner fileScanner = new Scanner(new FileReader(file));

        int i = 0;
        String firstElf, secondElf, thirdElf;
        firstElf = secondElf = "";
        String line;
        final List<ImmutableTriple<String, String, String>> tripleList = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            line = fileScanner.nextLine();
            if (i % 3 == 0) firstElf = line;
            if (i % 3 == 1) secondElf = line;
            if (i % 3 == 2) {
                thirdElf = line;
                tripleList.add(new ImmutableTriple<>(firstElf, secondElf, thirdElf));
            }
            i++;
        }
        fileScanner.close();

        return tripleList.stream()
                .map(DayThree::getWhichIsInAllThree)
                .map(DayThree::mapStringToValue)
                .reduce(0, Integer::sum);
    }

    private static String getDuplicateChar(final ImmutablePair<String, String> rucksack) {
        final Set<Character> leftPart = new HashSet<>();
        final Set<Character> rightPart = new HashSet<>();
        final char[] leftCharArray = rucksack.getLeft().toCharArray();
        final char[] rightCharArray = rucksack.getRight().toCharArray();

        for (int i = 0; i < leftCharArray.length; i++) {
            leftPart.add(leftCharArray[i]);
            rightPart.add(rightCharArray[i]);
        }
        final Set<Character> combined = new HashSet<>(leftPart);

        for (final char c : rightPart) {
            if (!combined.add(c)) {
                return String.valueOf(c);
            }
        }
        return "";
    }

    private static String getWhichIsInAllThree(final ImmutableTriple<String, String, String> group) {
        final Set<String> firstElf = new HashSet<>();
        final Set<String> secondElf = new HashSet<>();
        final Set<String> thirdElf = new HashSet<>();

        final char[] firstRucksack = group.getLeft().toCharArray();
        final char[] secondRucksack = group.getMiddle().toCharArray();
        final char[] thirdRucksack = group.getRight().toCharArray();

        for (final char c : firstRucksack) firstElf.add(String.valueOf(c));
        for (final char c : secondRucksack) secondElf.add(String.valueOf(c));
        for (final char c : thirdRucksack) thirdElf.add(String.valueOf(c));

        firstElf.retainAll(secondElf);
        firstElf.retainAll(thirdElf);
        return firstElf.stream().findFirst().orElse("");

    }

    private static Integer mapStringToValue(final String item) {
        final char c = item.charAt(0);
        if (StringUtils.isAllLowerCase(item)) {
            return ((int) c) - 96;
        }
        return ((int) c) - 38;

    }

}
