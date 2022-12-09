package at.elmagico;

import at.elmagico.days.day9.DayNine;

import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {
        String filepath = "C:\\Users\\Alexander\\IdeaProjects\\AdventOfCode\\src\\main\\java\\at\\elmagico\\basis\\day8Input.txt";
        filepath = filepath.replace("\\", "/");
        final DayNine day9 = new DayNine();
        System.out.println(day9.getNumberOfVisitedPositons(filepath));
    }
}