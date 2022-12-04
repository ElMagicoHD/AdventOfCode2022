package at.elmagico.days.day4;

import at.elmagico.days.fileReader.InputReader;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.List;

public class DayFour {

    public static Long getNumberOfDoubleAssignedPairs(final String filepath) throws IOException {
        final List<String> inputList = InputReader.readInputFile(filepath);

        return inputList.stream().map(line -> {
                    final String[] splitInPairs = line.split(",");
                    final String[] firstElf = splitInPairs[0].split("-");
                    final String[] secondElf = splitInPairs[1].split("-");
                    final ImmutablePair<Integer, Integer> firstElfPair = new ImmutablePair<>(Integer.parseInt(firstElf[0]), Integer.parseInt(firstElf[1]));
                    final ImmutablePair<Integer, Integer> secondElfPair = new ImmutablePair<>(Integer.parseInt(secondElf[0]), Integer.parseInt(secondElf[1]));
                    return new ImmutablePair<>(firstElfPair, secondElfPair);
                }).map(pair -> {
                    final ImmutablePair<Integer, Integer> leftElf = pair.getLeft();
                    final ImmutablePair<Integer, Integer> rightElf = pair.getRight();
                    if (leftElf.getLeft() <= rightElf.getLeft() && leftElf.getRight() >= rightElf.getRight()) {
                        return Boolean.TRUE;
                    }
                    if (leftElf.getLeft() >= rightElf.getLeft() && leftElf.getRight() <= rightElf.getRight()) {
                        return Boolean.TRUE;
                    }
                    return Boolean.FALSE;
                }).filter(result -> result.equals(Boolean.TRUE))
                .count();
    }

    public static Long getNumberOfPartiallyDoubleAssignedPairs(final String filepath) throws IOException {
        final List<String> inputList = InputReader.readInputFile(filepath);

        return inputList.stream().map(line -> {
                    final String[] splitInPairs = line.split(",");
                    final String[] firstElf = splitInPairs[0].split("-");
                    final String[] secondElf = splitInPairs[1].split("-");
                    final ImmutablePair<Integer, Integer> firstElfPair = new ImmutablePair<>(Integer.parseInt(firstElf[0]), Integer.parseInt(firstElf[1]));
                    final ImmutablePair<Integer, Integer> secondElfPair = new ImmutablePair<>(Integer.parseInt(secondElf[0]), Integer.parseInt(secondElf[1]));
                    return new ImmutablePair<>(firstElfPair, secondElfPair);
                }).filter(pair -> {
                    final ImmutablePair<Integer, Integer> leftElf = pair.getLeft();
                    final ImmutablePair<Integer, Integer> rightElf = pair.getRight();
                    if (leftElf.getLeft() <= rightElf.getLeft() && leftElf.getRight() >= rightElf.getRight())
                        return Boolean.TRUE;
                    if (leftElf.getLeft() >= rightElf.getLeft() && leftElf.getRight() <= rightElf.getRight())
                        return Boolean.TRUE;
                    if (rightElf.getLeft() <= leftElf.getLeft() && leftElf.getLeft() <= rightElf.getRight())
                        return Boolean.TRUE;
                    if (rightElf.getLeft() <= leftElf.getRight() && leftElf.getRight() <= rightElf.getRight())
                        return Boolean.TRUE;
                    return Boolean.FALSE;
                })
                .count();
    }

}
