package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import com.exceptions.*;

public class CSVStates {
    private String filepath;

    public CSVStates(String filepath) {
        this.filepath = filepath;
    }

    public Iterator<Map.Entry<Integer, String>> loadDataFromFile()
            throws FileFormatException, IncorrectHeaderException, FileReaderException, DelimiterException {
        if (!filepath.endsWith(".csv")) {
            throw new FileFormatException("Given file is not CSV file.");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath));) {
            String header = reader.readLine();
            if (header == null || !header.equals("SrNo,StateName,TIN,StateCode")) {
                throw new IncorrectHeaderException("CSV header is not valid");
            }

            HashMap<Integer, String> records = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineData = line.split(",");
                if (lineData.length != 4) {
                    throw new DelimiterException("Number of expected records not found. Check delimeters in the file.");
                }
                records.put(Integer.parseInt(lineData[2]), lineData[3]); // getting TIN number and statecode only
            }
            return records.entrySet().iterator();
        } catch (IOException exception) {
            throw new FileReaderException("Error in reading the file: " + exception.getMessage());
        }
    }
}
