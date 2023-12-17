package com.example;

import java.util.Iterator;
import java.util.Map;

public class StateCensusAnalyzer {
    public CSVStateCensus csvStateCensus;
    public CSVStates csvStates;

    public StateCensusAnalyzer(String filePath) {
        this.csvStateCensus = new CSVStateCensus(filePath);
        this.csvStates = new CSVStates(filePath);
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

    public int countRecordsInCSVStatesData() {
        try {
            Iterator<Map.Entry<Integer, String>> iterator = csvStates.loadDataFromFile();
            int count = 0;
            while (iterator.hasNext()) {
                count++;
                iterator.next();
            }
            return count;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return -1;
        }
    }

    public String getStateCodeByTIN(int TIN) {
        try {
            Iterator<Map.Entry<Integer, String>> iterator = csvStates.loadDataFromFile();
            String stateCode = null;
            while (iterator.hasNext()) {
                Map.Entry<Integer, String> entry = iterator.next();
                if (entry.getKey() == TIN) {
                    stateCode = entry.getValue();
                }
            }
            return stateCode == null ? "STATECODE NOT FOUND" : stateCode;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the State Census Analyzer.");
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer("src/main/resources/StateCensus.csv");
        System.out.println("Number of records are: " + stateCensusAnalyzer.countRecordsInCSVData());
        System.out.println("Number of different state codes are: " + stateCensusAnalyzer.countRecordsInCSVStatesData());
        System.out.println("State Code for TIN 24 is: " + stateCensusAnalyzer.getStateCodeByTIN(24));
    }
}