package st.lab2.func.logarithmic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class NaturalLogarithmTest {

    private NaturalLogarithm naturalLogarithm;
    private static final double ACCURACY = 0.0001;

    @BeforeEach
    void setUp() {
        naturalLogarithm = new NaturalLogarithm();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ln.csv")
    void mainTest(double x, double yExpected) {
        assertEquals(yExpected, naturalLogarithm.evaluate(x, ACCURACY), ACCURACY);
    }
}