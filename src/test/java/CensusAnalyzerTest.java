import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.example.*;
import com.exceptions.*;

public class CensusAnalyzerTest {
    StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer("src/main/resources/StateCensus.csv");

    // TC 1.1: check if number of records matches
    // TC 2.1: check if number of state records matches
    @Test
    public void testNumberOfRecords() {
        int records_fetched = stateCensusAnalyzer.countRecordsInCSVData();
        assertEquals(37, records_fetched);

        int state_records_fetched = stateCensusAnalyzer.countRecordsInCSVStatesData();
        assertEquals(37, state_records_fetched);
    }

    // TC 1.2: check for incorrect file
    // TC 2.2: check for incorrect file for state data
    @Test
    public void testIncorrectFile() {
        CSVStateCensus dummy = new CSVStateCensus("src/main/resources/DUMMY_FILE.csv");
        try {
            dummy.loadDataFromFile();
            fail("FileReaderException expected.");
        } catch (Exception exception) {
            assertEquals(FileReaderException.class, exception.getClass());
        }

        CSVStates dummy2 = new CSVStates("src/main/resources/DUMMY_FILE.csv");
        try {
            dummy2.loadDataFromFile();
            fail("FileReaderException expected.");
        } catch (Exception exception) {
            assertEquals(FileReaderException.class, exception.getClass());
        }
    }

    // TC 1.3: check for incorrect file type
    // TC 2.3: check for incorrect file type for state data
    @Test
    public void testIncorrectFileType() {
        CSVStateCensus dummy = new CSVStateCensus("src/main/resources/DUMMY_FILE.txt");
        try {
            dummy.loadDataFromFile();
            fail("FileFormatException expected.");
        } catch (Exception exception) {
            assertEquals(FileFormatException.class, exception.getClass());
        }

        CSVStates dummy2 = new CSVStates("src/main/resources/DUMMY_FILE.txt");
        try {
            dummy2.loadDataFromFile();
            fail("FileFormatException expected.");
        } catch (Exception exception) {
            assertEquals(FileFormatException.class, exception.getClass());
        }
    }

    // TC 1.4: check for incorrect delimiter in csv file
    @Test
    public void testIncorrectDelimiter() {
        CSVStateCensus dummy = new CSVStateCensus("src/main/resources/StateCensusTest1.csv");
        try {
            dummy.loadDataFromFile();
            fail("DelimiterException expected");
        } catch (Exception exception) {
            assertEquals(DelimiterException.class, exception.getClass());
        }
    }

    // TC 1.5: check for incorrect csv header
    @Test
    public void testIncorrectHeader() {
        CSVStateCensus dummy = new CSVStateCensus("src/main/resources/StateCensusTest2.csv");
        try {
            dummy.loadDataFromFile();
            fail("IncorrectHeaderException expected");
        } catch (Exception exception) {
            assertEquals(IncorrectHeaderException.class, exception.getClass());
        }
    }
}
