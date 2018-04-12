package st.lab2.func.trigonometric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CosineFunctionTest {

    private CosineFunction cosineFunction;
    private static final double ACCURACY = 0.0000000000001;
    private static final double K = 0.001;

    @BeforeEach
    void setUp() {
        cosineFunction = new CosineFunction(new SineFunction());
    }

    @ParameterizedTest(name = "{index} ==> Testing interval ({0}, {1})")
    @CsvSource({
            "0., 0.5",
            "0.5, 1.",
            "1., 1.5",
            "1.5, 2.",
            "-2., -1.5",
            "-1.5, -1.",
            "-1., -0.5",
            "-0.5, -0."
    })
    void testEquivalenceClass(double beg, double end) {
        beg *= Math.PI;
        end *= Math.PI;
        double mid = (end + beg) / 2.;
        beg += K;
        end -= K;

        double leftSideValue = cosineFunction.evaluate(beg, ACCURACY);
        double midValue = cosineFunction.evaluate(mid, ACCURACY);
        double rightSideValue = cosineFunction.evaluate(end, ACCURACY);

        assertTrue(Math.abs(Math.cos(beg) - leftSideValue) < ACCURACY, "Left side check of the interval failed");
        assertTrue(Math.abs(Math.cos(mid) - midValue) < ACCURACY, "Middle of the interval failed");
        assertTrue(Math.abs(Math.cos(end) - rightSideValue) < ACCURACY, "Right side check of the interval failed");
    }

    @ParameterizedTest(name = "{index} ==> Testing point {0}")
    @ValueSource(doubles = {-2., -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.})
    void testEdgePoints(double point) {
        point *= Math.PI;
        double res = cosineFunction.evaluate(point, ACCURACY);
        double expected = Math.cos(point);

        assertTrue(Math.abs(expected - res) < ACCURACY, "Point check failed: expected " + expected + ". Actual: " + res);
    }

}