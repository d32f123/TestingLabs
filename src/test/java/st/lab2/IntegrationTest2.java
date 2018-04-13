package st.lab2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import st.lab2.func.trigonometric.CosecantFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static st.lab2.tables.FunctionMocker.*;

public class IntegrationTest2 {

    private static final double ACCURACY = 0.00000001;
    private static MainSystem mainSystem;

    @BeforeAll
    static void init() {

        CosecantFunction cosecantFunction = new CosecantFunction(getSineFunctionStub());

        mainSystem = new MainSystem(cosecantFunction, getSecantFunctionStub(), getSineFunctionStub(),
                getBase2LogarithmStub(), getBase3LogarithmStub(), getBase5LogarithmStub(), getBase10LogarithmStub());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/values.csv")
    void testSystemWithAllStubs(double x, double yExpected) {
        assertEquals(yExpected, mainSystem.evaluate(x, ACCURACY), ACCURACY);
    }


}
