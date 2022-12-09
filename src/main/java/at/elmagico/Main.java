package at.elmagico;

import at.elmagico.days.day8.DayEight;

import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {
        String filepath = "C:\\Users\\Alexander\\IdeaProjects\\AdventOfCode\\src\\main\\java\\at\\elmagico\\basis\\day8Input.txt";
        filepath = filepath.replace("\\", "/");
        final DayEight day8 = new DayEight();
        System.out.println(day8.getNumberOfBestScenicTree(filepath));
    }
}