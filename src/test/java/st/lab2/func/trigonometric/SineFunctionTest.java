package st.lab2.func.trigonometric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SineFunctionTest {

    private SineFunction sineFunction;
    private static final double ACCURACY = 0.00000001;
    private static final double K = 0.001;

    @BeforeEach
    void setUp() {
        sineFunction = new SineFunction();
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

        double leftSideValue = sineFunction.evaluate(beg, ACCURACY);
        double midValue = sineFunction.evaluate(mid, ACCURACY);
        double rightSideValue = sineFunction.evaluate(end, ACCURACY);

        assertTrue(Math.abs(Math.sin(beg) - leftSideValue) < ACCURACY, "Left side of the interval failed");
        assertTrue(Math.abs(Math.sin(mid) - midValue) < ACCURACY, "Middle of the interval failed");
        assertTrue(Math.abs(Math.sin(end) - rightSideValue) < ACCURACY, "Right side of the interval failed");
    }

    @ParameterizedTest(name = "{index} ==> Testing point {0}")
    @ValueSource(doubles = {-2., -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.})
    void testEdgePoints(double point) {
        point *= Math.PI;
        double res = sineFunction.evaluate(point, ACCURACY);
        double expected = Math.sin(point);

        assertTrue(Math.abs(expected - res) < ACCURACY, "Point check failed. Expected: "
                + expected + ". Actual: " + res);
    }
}