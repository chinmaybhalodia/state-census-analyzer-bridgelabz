import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.*;

public class CensusAnalyzerTest {
    StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();

    // TC 1.1: check if number of records matches
    @Test
    public void testNumberOfRecords() {
        int records_fetched = stateCensusAnalyzer.countRecordsInCSVData();
        assertEquals(37, records_fetched);
    }
}
