package st.lab2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import st.lab2.func.logarithmic.Base10Logarithm;
import st.lab2.func.logarithmic.Base2Logarithm;
import st.lab2.func.logarithmic.Base3Logarithm;
import st.lab2.func.logarithmic.Base5Logarithm;
import st.lab2.func.trigonometric.CosecantFunction;
import st.lab2.func.trigonometric.CosineFunction;
import st.lab2.func.trigonometric.SecantFunction;
import st.lab2.func.trigonometric.SineFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static st.lab2.tables.FunctionMocker.getNaturalLogarithmStub;

public class IntegrationTest11 {
    private static final double ACCURACY = 0.00000001;
    private static MainSystem mainSystem;

    @BeforeAll
    static void init() {

        SineFunction sineFunction = new SineFunction();

        CosineFunction cosineFunction = new CosineFunction(sineFunction);
        SecantFunction secantFunction = new SecantFunction(cosineFunction);

        CosecantFunction cosecantFunction = new CosecantFunction(sineFunction);

        Base2Logarithm base2Logarithm = new Base2Logarithm(getNaturalLogarithmStub());
        Base3Logarithm base3Logarithm = new Base3Logarithm(getNaturalLogarithmStub());
        Base5Logarithm base5Logarithm = new Base5Logarithm(getNaturalLogarithmStub());
        Base10Logarithm base10Logarithm = new Base10Logarithm(getNaturalLogarithmStub());

        mainSystem = new MainSystem(cosecantFunction, secantFunction, sineFunction,
                base2Logarithm, base3Logarithm, base5Logarithm, base10Logarithm);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/values.csv")
    void testSystemWithAllStubs(double x, double yExpected) {
        assertEquals(yExpected, mainSystem.evaluate(x, ACCURACY), ACCURACY);
    }
}
