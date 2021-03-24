package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName){
        List<String> competitors = new ArrayList<>();
        FileReader fileReader;
        BufferedReader bufferedReader;
        Map<String, Integer> finalScores = new HashMap<>();

        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] splitLine = line.split(",");
                String name = splitLine[0];
                int score = 0;
                for (int i = 1; i < splitLine.length; i++) {
                    score += Integer.parseInt(splitLine[i]);
                }
                finalScores.put(name, score);
            }

            List<Map.Entry<String, Integer>> sorted = finalScores.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toList());
            for (Map.Entry<String, Integer> entry : sorted) {
                competitors.add(entry.getKey());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return competitors;
    }

}
