package com.example;

import java.util.Iterator;

public class StateCensusAnalyzer {
    public CSVStateCensus csvStateCensus;

    public StateCensusAnalyzer(String filePath) {
        this.csvStateCensus = new CSVStateCensus(filePath);
    }

    public int countRecordsInCSVData() {
        try {
            Iterator<String[]> iterator = csvStateCensus.loadDataFromFile();
            int count = 0;
            while (iterator.hasNext()) {
                count++;
                iterator.next();
            }
            return count;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return -1;
        }

    }

    public static void main(String[] args) {
        System.out.println("Welcome to the State Census Analyzer.");
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer("src/main/resources/StateCensus.csv");
        System.out.println("Number of records are: " + stateCensusAnalyzer.countRecordsInCSVData());
    }
}