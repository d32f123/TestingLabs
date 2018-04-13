package st.lab2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;
import static st.lab2.tables.FunctionMocker.*;

public class IntegrationTest1 {

    private static final double ACCURACY = 0.00000001;
    private static MainSystem mainSystem;

    @BeforeAll
    static void init() {

        mainSystem = new MainSystem(getSubSystem1Stub(), getSubSystem2Stub());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/values.csv")
    void testSystemWithAllStubs(double x, double yExpected) {
        assertEquals(yExpected, mainSystem.evaluate(x, ACCURACY), ACCURACY);
    }

}
