import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.example.*;
import com.exceptions.*;

public class CensusAnalyzerTest {
    StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer("src/main/resources/StateCensus.csv");

    // TC 1.1: check if number of records matches
    @Test
    public void testNumberOfRecords() {
        int records_fetched = stateCensusAnalyzer.countRecordsInCSVData();
        assertEquals(37, records_fetched);
    }

    // TC 1.2: check for incorrect file
    @Test
    public void testIncorrectFile() {
        CSVStateCensus dummy = new CSVStateCensus("src/main/resources/DUMMY_FILE.csv");
        try {
            dummy.loadDataFromFile();
            fail("FileReaderException expected.");
        } catch (Exception exception) {
            assertEquals(FileReaderException.class, exception.getClass());
        }
    }
}
