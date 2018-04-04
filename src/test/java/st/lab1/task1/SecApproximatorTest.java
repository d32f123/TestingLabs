package st.lab1.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import st.lab1.task1.SecApproximator;

import static org.junit.jupiter.api.Assertions.*;

class SecApproximatorTest {
    private final SecApproximator approximator = new SecApproximator();
    private static final double ALLOWED_ERROR = 0.0001;

    @BeforeEach
    void setUp() {
        approximator.setAllowedError(ALLOWED_ERROR);
    }

    @Test
    void setAllowedErrorTest() {
        double factor = 10.e-4;
        // act
        approximator.setAllowedError(ALLOWED_ERROR / factor);
        // assert
        assertEquals(ALLOWED_ERROR / factor, approximator.getAllowedError(), "Failed to set allowed error property");
    }


    // class 1
    @Test
    void NegativeSmallValueLeftTest() {
        double x = -0.95;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void NegativeSmallValueMiddleTest() {
        double x = -0.5;

        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void NegativeSmallValueRightTest() {
        double x = -0.05;

        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 2
    @Test
    void PositiveSmallValueLeftTest() {
        double x = 0.05;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void PositiveSmallValueMiddleTest() {
        double x = 0.5;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void PositiveSmallValueRightTest() {
        double x = 0.95;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 3
    @Test
    void CloseToNegativeBoundaryLeftTest() {
        double x = -Math.PI / 2 + 0.005;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void CloseToNegativeBoundaryMiddleTest() {
        double x = -Math.PI / 2 + 0.286;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void CloseToNegativeBoundaryRightTest() {
        double x = -1.1;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 4
    @Test
    void CloseToPositiveBoundaryLeftTest() {
        double x = 1.1;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void CloseToPositiveBoundaryMiddleTest() {
        double x = Math.PI / 2 - 0.286;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void CloseToPositiveBoundaryRightTest() {
        double x = Math.PI / 2 - 0.005;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 5
    @Test
    void ZeroTest() {
        double x = 0.;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    //class 6
    @Test
    void PositiveSecantCloseToZeroLeftSideLeftTest() {
        double x = -0.95 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void PositiveSecantCloseToZeroLeftSideMiddleTest() {
        double x = -0.5 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void PositiveSecantCloseToZeroLeftSideRightTest() {
        double x = -0.05 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 7
    @Test
    void PositiveSecantCloseToZeroRightSideLeftTest() {
        double x = 0.05 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void PositiveSecantCloseToZeroRightSideMiddleTest() {
        double x = 0.5 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void PositiveSecantCloseToZeroRightSideRightTest() {
        double x = 0.95 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 8
    @Test
    void PositiveSecantCloseToBoundaryLeftSideLeftTest() {
        double x = -Math.PI / 2 + 0.005 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void PositiveSecantCloseToBoundaryLeftSideMiddleTest() {
        double x = -Math.PI / 2 + 0.286 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void PositiveSecantCloseToBoundaryLeftSideRightTest() {
        double x = -1.1 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 9
    @Test
    void PositiveSecantCloseToBoundaryRightSideLeftTest() {
        double x = 1.1 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void PositiveSecantCloseToBoundaryRightSideMiddleTest() {
        double x = Math.PI / 2 - 0.286 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void PositiveSecantCloseToBoundaryRightSideRightTest() {
        double x = Math.PI / 2 - 0.005 + Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 10
    @Test
    void PositiveZeroTest() {
        double x = Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 11
    @Test
    void NegativeSecantCloseToZeroLeftSideLeftTest() {
        double x = -0.95 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void NegativeSecantCloseToZeroLeftSideMiddleTest() {
        double x = -0.5 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void NegativeSecantCloseToZeroLeftSideRightTest() {
        double x = -0.05 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 12
    @Test
    void NegativeSecantCloseToZeroRightSideLeftTest() {
        double x = 0.05 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void NegativeSecantCloseToZeroRightSideMiddleTest() {
        double x = 0.5 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void NegativeSecantCloseToZeroRightSideRightTest() {
        double x = 0.95 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 13
    @Test
    void NegativeSecantCloseToBoundaryLeftSideLeftTest() {
        double x = -Math.PI / 2 + 0.005 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void NegativeSecantCloseToBoundaryLeftSideMiddleTest() {
        double x = -Math.PI / 2 + 0.286 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void NegativeSecantCloseToBoundaryLeftSideRightTest() {
        double x = -1.1 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 14
    @Test
    void NegativeSecantCloseToBoundaryRightSideLeftTest() {
        double x = 1.1 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void NegativeSecantCloseToBoundaryRightSideMiddleTest() {
        double x = Math.PI / 2 - 0.286 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    @Test
    void NegativeSecantCloseToBoundaryRightSideRightTest() {
        double x = Math.PI / 2 - 0.005 - Math.PI;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }

    // class 15
    @Test
    void NegativeZeroTest() {
        double x = - Math.PI ;
        double yExpected = 1. / Math.cos(x);

        double yActual = approximator.evaluate(x);

        assertTrue(Math.abs(yExpected - yActual) < ALLOWED_ERROR, "Expected: " + yExpected +
                "\nActual: " + yActual + "\nInput: " + x);
    }
}