package at.elmagico;

import at.elmagico.days.day7.DaySeven;

import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {
        String filepath = "C:\\Users\\Alexander\\IdeaProjects\\AdventOfCode\\src\\main\\java\\at\\elmagico\\basis\\day7Input.txt";
        filepath = filepath.replace("\\", "/");
        final DaySeven daySeven = new DaySeven();
        daySeven.readFile(filepath);
        final Integer sizeOfDirectoryUnderThreshold = daySeven.getSizeOfDirectoriesUnderThreshold(100000);
        System.out.println("first part: " + sizeOfDirectoryUnderThreshold);
        final Integer sizeOfDirectoryToDelete = daySeven.getRequiredSpaceOfSmallestDirectoryToFreeUpEnoughSpace(30000000);
        System.out.println("second part: " + sizeOfDirectoryToDelete);
    }
}