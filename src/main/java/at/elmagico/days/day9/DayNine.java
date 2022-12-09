package at.elmagico.days.day9;

import at.elmagico.days.fileReader.InputReader;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayNine {

    public Integer getNumberOfVisitedPositons(final String filepath) throws IOException {
        List<String> lines = InputReader.readInputFile(filepath);
        Set<Position> visitedPositions = new HashSet<>();

        Position head = new Position(0,0);
        Position tail = new Position(0,0);
        for(String instruction : lines){
            String[] split = instruction.split(" ");
            String direction = split[0];
            int times = Integer.parseInt(split[1]);

            for(int i = 0; i < times; i++){
                movePositionForHead(direction, head);
            }

        }
        return null;
    }

    private Position movePositionForHead(final String direction, final Position head){
        switch (direction) {
            case "U" -> head.setY(head.getY() + 1);
            case "D" -> head.setY(head.getY() - 1);
            case "R" -> head.setX(head.getX() + 1);
            default -> head.setX(head.getX() - 1);
        }
        return head;

    }

}
