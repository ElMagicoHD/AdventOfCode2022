package at.elmagico.days.day2;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DayTwo {
    enum Shape {
        ROCK,
        PAPER,
        SCISSOR
    }

    enum Strategy {
        WIN,
        TIE,
        LOSE
    }

    public static Integer calculateGameResults(final String filePath) {
        final Integer result;
        try (final Stream<String> fileInputStream = Files.lines(Paths.get(filePath))) {

            result = fileInputStream.map(line -> {
                        final Shape opponent = mapStringToShape(line.substring(0, 1));
                        final String playerString = line.substring(2);
                        final Strategy strategy = mapStringToStrategy(playerString);
                        final Shape player = mapOpponentWithStrategyToShape(opponent, strategy);
                        return new ImmutablePair<>(opponent, player);
                    }).map(pair -> evaluateShapes(pair) + getShapeBonus(pair.getRight()))
                    .reduce(0, Integer::sum);


        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static Integer evaluateShapes(final ImmutablePair<Shape, Shape> shapePair) {
        final Shape opponent = shapePair.getLeft();
        final Shape player = shapePair.getRight();

        if (player.equals(Shape.ROCK)) {
            if (opponent.equals(Shape.SCISSOR)) return 6;
            if (opponent.equals(Shape.ROCK)) return 3;
        }
        if (player.equals(Shape.SCISSOR)) {
            if (opponent.equals(Shape.PAPER)) return 6;
            if (opponent.equals(Shape.SCISSOR)) return 3;
        }
        if (player.equals(Shape.PAPER)) {
            if (opponent.equals(Shape.ROCK)) return 6;
            if (opponent.equals(Shape.PAPER)) return 3;
        }
        return 0;
    }

    private static Integer getShapeBonus(final Shape shape) {
        return switch (shape) {
            case ROCK -> 1;
            case PAPER -> 2;
            case SCISSOR -> 3;
        };
    }

    private static Shape mapStringToShape(final String s) {
        return switch (s) {
            case "A", "X" -> Shape.ROCK;
            case "B", "Y" -> Shape.PAPER;
            case "C", "Z" -> Shape.SCISSOR;
            default -> throw new IllegalArgumentException();
        };
    }

    private static Strategy mapStringToStrategy(final String s) {
        return switch (s) {
            case "X" -> Strategy.LOSE;
            case "Y" -> Strategy.TIE;
            case "Z" -> Strategy.WIN;
            default -> throw new IllegalArgumentException();
        };
    }

    private static Shape mapOpponentWithStrategyToShape(final Shape opponent, final Strategy strategy) {
        if (strategy.equals(Strategy.TIE)) return opponent;


        if (strategy.equals(Strategy.LOSE)) {
            if (opponent.equals(Shape.ROCK)) return Shape.SCISSOR;
            if (opponent.equals(Shape.PAPER)) return Shape.ROCK;
            if (opponent.equals(Shape.SCISSOR)) return Shape.PAPER;
        }
        if (strategy.equals(Strategy.WIN)) {
            if (opponent.equals(Shape.ROCK)) return Shape.PAPER;
            if (opponent.equals(Shape.PAPER)) return Shape.SCISSOR;
            if (opponent.equals(Shape.SCISSOR)) return Shape.ROCK;
        }
        return null;
    }
}
