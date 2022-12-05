package at.elmagico;

import at.elmagico.days.day5.DayFive;

import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {
        String filepath = "C:\\Users\\Alexander\\IdeaProjects\\AdventOfCode\\src\\main\\java\\at\\elmagico\\basis\\day5Input.txt";
        filepath = filepath.replace("\\", "/");
        System.out.println(DayFive.rearrangeCratesOver9000Method(filepath));
    }
}