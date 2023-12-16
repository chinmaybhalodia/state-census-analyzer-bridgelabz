package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import com.exceptions.*;

public class CSVStateCensus {
    private String filepath;

    public CSVStateCensus(String filePath) {
        this.filepath = filePath;
    }

    public Iterator<String[]> loadDataFromFile()
            throws FileFormatException, IncorrectHeaderException, FileReaderException, DelimiterException {
        if (!filepath.endsWith(".csv")) {
            throw new FileFormatException("Given file is not CSV file.");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath));) {
            String header = reader.readLine();
            if (header == null || !header.equals("SrNo,StateName,TIN,StateCode")) {
                throw new IncorrectHeaderException("CSV header is not valid");
            }

            ArrayList<String[]> records = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineData = line.split(",");
                if (lineData.length != 4) {
                    throw new DelimiterException("Number of expected records not found. Check delimeters in the file.");
                }
                records.add(lineData);
            }
            return records.iterator();
        } catch (IOException exception) {
            throw new FileReaderException("Error in reading the file: " + exception.getMessage());
        }
    }
}