package st.lab2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import st.lab2.func.logarithmic.*;
import st.lab2.func.trigonometric.CosecantFunction;
import st.lab2.func.trigonometric.CosineFunction;
import st.lab2.func.trigonometric.SecantFunction;
import st.lab2.func.trigonometric.SineFunction;
import st.lab2.subsystems.SubSystem1;
import st.lab2.subsystems.SubSystem2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static st.lab2.tables.FunctionMocker.getNaturalLogarithmStub;

public class IntegrationTest14 {
    private static final double ACCURACY = 0.00000001;
    private static final double LOG_ACCURACY = 0.0001;
    private static MainSystem mainSystem;

    @BeforeAll
    static void init() {

        SineFunction sineFunction = new SineFunction();
        CosineFunction cosineFunction = new CosineFunction(sineFunction);

        SecantFunction secantFunction = new SecantFunction(cosineFunction);
        CosecantFunction cosecantFunction = new CosecantFunction(sineFunction);

        NaturalLogarithm naturalLogarithm = new NaturalLogarithm();

        Base2Logarithm base2Logarithm = new Base2Logarithm(naturalLogarithm);
        Base3Logarithm base3Logarithm = new Base3Logarithm(getNaturalLogarithmStub());
        Base5Logarithm base5Logarithm = new Base5Logarithm(getNaturalLogarithmStub());
        Base10Logarithm base10Logarithm = new Base10Logarithm(getNaturalLogarithmStub());

        SubSystem1 subSystem1 = new SubSystem1(cosecantFunction, sineFunction, secantFunction);
        SubSystem2 subSystem2 = new SubSystem2(base2Logarithm, base3Logarithm,
                base5Logarithm, base10Logarithm);

        mainSystem = new MainSystem(subSystem1, subSystem2);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/values.csv")
    void testSystemWithAllStubs(double x, double yExpected) {
        assertEquals(yExpected, mainSystem.evaluate(x, x <= 0 ? ACCURACY : LOG_ACCURACY), x <= 0 ? ACCURACY : LOG_ACCURACY);
    }
}
