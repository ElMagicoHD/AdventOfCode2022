package at.elmagico.days.day5;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static at.elmagico.days.fileReader.InputReader.readInputFile;

public class DayFive {

    public static String rearrangeCrates(final String filePath) throws IOException {
        final List<String> inputList = readInputFile(filePath);
        final List<String> stackInput = inputList.subList(0, 8);
        final List<String> moves = inputList.subList(10, inputList.size());

        final List<Stack<Character>> stacks = mapToStacks(stackInput);
        final List<List<Integer>> instructions = getInstructions(moves);

        for (final List<Integer> steps : instructions) {
            final int times = steps.get(0);
            final int from = steps.get(1) - 1;
            final int to = steps.get(2) - 1;

            for (int i = 0; i < times; i++) {
                stacks.get(to).push(stacks.get(from).pop());
            }
        }
        final StringBuilder finalResults = new StringBuilder();
        for (final Stack<Character> stack : stacks) finalResults.append(stack.peek());
        return finalResults.toString();
    }

    public static String rearrangeCratesOver9000Method(final String filePath) throws IOException {
        final List<String> inputList = readInputFile(filePath);
        final List<String> stackInput = inputList.subList(0, 8);
        final List<String> moves = inputList.subList(10, inputList.size());

        final List<Stack<Character>> stacks = mapToStacks(stackInput);
        final List<List<Integer>> instructions = getInstructions(moves);

        for (final List<Integer> steps : instructions) {
            final int times = steps.get(0);
            final int from = steps.get(1) - 1;
            final int to = steps.get(2) - 1;
            final List<Character> tmp = new ArrayList<>();

            for (int i = 0; i < times; i++) {
                tmp.add(0, stacks.get(from).pop());
            }
            stacks.get(to).addAll(tmp);
        }
        final StringBuilder finalResults = new StringBuilder();
        for (final Stack<Character> stack : stacks) finalResults.append(stack.peek());
        return finalResults.toString();
    }

    private static List<Stack<Character>> mapToStacks(final List<String> inputList) {
        final List<Stack<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < 9; i++) stacks.add(new Stack<>());

        for (int i = inputList.size() - 1; i > -1; i--) {
            final char[] line = inputList.get(i).toCharArray();
            int stackNumber = 0;
            for (int group = 1; group < line.length; group += 4) {
                if (line[group] != ' ') stacks.get(stackNumber).push(line[group]);
                stackNumber++;
            }
        }
        return stacks;
    }

    private static List<List<Integer>> getInstructions(final List<String> moves) {
        return moves.stream()
                .map(line -> line.replace("move ", "").replace("from ", "").replace("to ", ""))
                .map(line -> {
                    final List<Integer> moveList = new ArrayList<>();
                    final String[] splitUp = line.split(" ");
                    for (final String number : splitUp) moveList.add(Integer.parseInt(number));
                    return moveList;
                }).toList();
    }
}
