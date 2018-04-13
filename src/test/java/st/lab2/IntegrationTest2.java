package st.lab2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import st.lab2.subsystems.SubSystem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static st.lab2.tables.FunctionMocker.*;

public class IntegrationTest2 {

    private static final double ACCURACY = 0.00000001;
    private static MainSystem mainSystem;

    @BeforeAll
    static void init() {
        SubSystem1 subSystem1 = new SubSystem1(getCosecantFunctionStub(), getSineFunctionStub(), getSecantFunctionStub());

        mainSystem = new MainSystem(subSystem1, getSubSystem2Stub());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/values.csv")
    void testSystemWithAllStubs(double x, double yExpected) {
        assertEquals(yExpected, mainSystem.evaluate(x, ACCURACY), ACCURACY);
    }


}
