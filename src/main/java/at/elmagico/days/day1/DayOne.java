package at.elmagico.days.day1;


import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;


public class DayOne {

    public static Integer calculateMaxCalories(String filePath) throws IOException {
        File file = new File(filePath);

        Scanner fileScanner = new Scanner(new FileReader(file));

        String line;
        int tmp = 0;
        int index;
        int[] topThreeCalories = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        while(fileScanner.hasNextLine()){
            line = fileScanner.nextLine();
            if(StringUtils.isEmpty(line)){
                index = getMinIndex(topThreeCalories);
                if(topThreeCalories[index] < tmp) topThreeCalories[index] = tmp;
                tmp = 0;
                continue;
            }
            tmp += Integer.parseInt(line);
        }
        fileScanner.close();
        //one last time
        index =  getMinIndex(topThreeCalories);
        if(topThreeCalories[index] < tmp) topThreeCalories[index] = tmp;

        return Arrays.stream(topThreeCalories).sum();

    }

    private static Integer getMinIndex(int[] array) {
        int minValue = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i]<minValue){
                minValue = array[i];
                index = i;
            }
        }
        return index;
    }

}
