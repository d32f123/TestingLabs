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
import st.lab2.subsystems.SubSystem1;
import st.lab2.subsystems.SubSystem2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static st.lab2.tables.FunctionMocker.*;
import static st.lab2.tables.FunctionMocker.getBase10LogarithmStub;

public class IntegrationTest9 {
    private static final double ACCURACY = 0.00000001;
    private static MainSystem mainSystem;

    @BeforeAll
    static void init() {

        SecantFunction secantFunction = new SecantFunction(getCosineFunctionStub());
        CosecantFunction cosecantFunction = new CosecantFunction(getSineFunctionStub());
        SineFunction sineFunction = new SineFunction();

        Base2Logarithm base2Logarithm = new Base2Logarithm(getNaturalLogarithmStub());
        Base3Logarithm base3Logarithm = new Base3Logarithm(getNaturalLogarithmStub());
        Base5Logarithm base5Logarithm = new Base5Logarithm(getNaturalLogarithmStub());

        SubSystem1 subSystem1 = new SubSystem1(cosecantFunction, sineFunction, secantFunction);
        SubSystem2 subSystem2 = new SubSystem2(base2Logarithm, base3Logarithm,
                base5Logarithm, getBase10LogarithmStub());

        mainSystem = new MainSystem(subSystem1, subSystem2);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/values.csv")
    void testSystemWithAllStubs(double x, double yExpected) {
        assertEquals(yExpected, mainSystem.evaluate(x, ACCURACY), ACCURACY);
    }
}
