package at.elmagico.days.day8;

import at.elmagico.days.fileReader.InputReader;

import java.io.IOException;
import java.util.List;

public class DayEight {

    private Integer[][] grid;
    private boolean[][] countedTrees;

    public Integer getNumberOfBestScenicTree(final String filepath) throws IOException {
        final List<String> lines = InputReader.readInputFile(filepath);
        fillArray(lines);
        int bestScenicView = Integer.MIN_VALUE;
        for (int row = 1; row < grid.length - 1; row++) {
            for (int column = 1; column < grid[0].length - 1; column++) {
                final int scenicViewForThisTree = calculateScenicView(row, column);
                if (scenicViewForThisTree > bestScenicView) bestScenicView = scenicViewForThisTree;
            }
        }
        return bestScenicView;
    }

    private int calculateScenicView(final int row, final int column) {
        final int treeHeight = grid[row][column];
        int upwards = 0;
        int downwards = 0;
        int left = 0;
        int right = 0;

        for (int i = row + 1; i < grid.length; i++) {
            downwards++;
            if (grid[i][column] >= treeHeight) {
                break;
            }
        }

        for (int i = row - 1; i > -1; i--) {
            upwards++;
            if (grid[i][column] >= treeHeight) {
                break;
            }
        }
        for (int i = column + 1; i < grid[0].length; i++) {
            right++;
            if (grid[row][i] >= treeHeight) {
                break;
            }
        }
        for (int i = column - 1; i > -1; i--) {
            left++;
            if (grid[row][i] >= treeHeight) {
                break;
            }
        }
        return upwards * downwards * left * right;
    }

    public Integer getNumberOfVisibleTrees(final String filepath) throws IOException {
        final List<String> lines = InputReader.readInputFile(filepath);
        fillArray(lines);
        countedTrees = new boolean[grid.length][grid[0].length];
        final Integer visibleFromLeftOrRight = getNumberOfVisibleTreesFromLeftOrRight();
        final Integer visibleFromTopOrBottom = getNumberOfVisibleTreesFromTopOrBottom();
        return visibleFromLeftOrRight + visibleFromTopOrBottom;
    }

    private void fillArray(final List<String> lines) {
        grid = new Integer[lines.size()][lines.get(0).length()];
        int row = 0;
        for (final String line : lines) {
            final char[] chars = line.toCharArray();
            for (int column = 0; column < chars.length; column++) {
                grid[row][column] = Character.getNumericValue(chars[column]);
            }
            row++;
        }
    }

    private Integer getNumberOfVisibleTreesFromLeftOrRight() {
        int visibleFromLeftOrRight = 0;
        for (int row = 0; row < grid.length; row++) {
            int visibleFromLeft = 0;
            int temporaryHighestTree = Integer.MIN_VALUE;
            for (int column = 0; column < grid[0].length; column++) {
                if (temporaryHighestTree < grid[row][column]) {
                    if (!countedTrees[row][column]) {
                        visibleFromLeft++;
                    }
                    temporaryHighestTree = grid[row][column];
                    countedTrees[row][column] = true;
                }
            }
            int visibleFromRight = 0;
            temporaryHighestTree = Integer.MIN_VALUE;
            for (int column = grid[0].length - 1; column > -1; column--) {
                if (temporaryHighestTree < grid[row][column]) {
                    if (!countedTrees[row][column]) {
                        visibleFromRight++;
                    }
                    temporaryHighestTree = grid[row][column];
                    countedTrees[row][column] = true;
                }
            }
            visibleFromLeftOrRight += visibleFromLeft + visibleFromRight;
        }
        return visibleFromLeftOrRight;
    }

    private Integer getNumberOfVisibleTreesFromTopOrBottom() {
        int visibleFromTopOrBottom = 0;

        for (int column = 0; column < grid[0].length; column++) {
            int visibleFromTop = 0;
            int temporaryHighestTree = Integer.MIN_VALUE;
            for (int row = 0; row < grid.length; row++) {
                if (temporaryHighestTree < grid[row][column]) {
                    if (!countedTrees[row][column]) {
                        visibleFromTop++;
                    }
                    temporaryHighestTree = grid[row][column];
                    countedTrees[row][column] = true;
                }
            }
            int visibleFromBottom = 0;
            temporaryHighestTree = Integer.MIN_VALUE;
            for (int row = grid.length - 1; row > -1; row--) {
                if (temporaryHighestTree < grid[row][column]) {
                    if (!countedTrees[row][column]) {
                        visibleFromBottom++;

                    }
                    temporaryHighestTree = grid[row][column];
                    countedTrees[row][column] = true;
                }
            }
            visibleFromTopOrBottom += visibleFromTop + visibleFromBottom;
        }
        return visibleFromTopOrBottom;
    }
}
