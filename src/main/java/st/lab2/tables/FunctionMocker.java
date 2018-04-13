package st.lab2.tables;

import st.lab2.func.MathFunction;
import st.lab2.func.logarithmic.*;
import st.lab2.func.trigonometric.CosecantFunction;
import st.lab2.func.trigonometric.CosineFunction;
import st.lab2.func.trigonometric.SecantFunction;
import st.lab2.func.trigonometric.SineFunction;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class FunctionMocker {
    private static final String SineFile = "sin.csv";
    private static final String CosineFile = "cos.csv";
    private static final String SecantFile = "sec.csv";
    private static final String CosecantFile = "csc.csv";
    private static final String Log2File = "log2.csv";
    private static final String Log3File = "log3.csv";
    private static final String Log5File = "log5.csv";
    private static final String Log10File = "log10.csv";
    private static final String NaturalLogFile = "ln.csv";


    private static SineFunction sineFunctionStub;
    private static CosineFunction cosineFunctionStub;
    private static CosecantFunction cosecantFunctionStub;
    private static SecantFunction secantFunctionStub;
    private static Base2Logarithm base2LogarithmStub;
    private static Base3Logarithm base3LogarithmStub;
    private static Base5Logarithm base5LogarithmStub;
    private static Base10Logarithm base10LogarithmStub;
    private static NaturalLogarithm naturalLogarithmStub;

    static {
        sineFunctionStub = mock(SineFunction.class);
        cosineFunctionStub = mock(CosineFunction.class);
        cosecantFunctionStub = mock(CosecantFunction.class);
        secantFunctionStub = mock(SecantFunction.class);
        base2LogarithmStub = mock(Base2Logarithm.class);
        base3LogarithmStub = mock(Base3Logarithm.class);
        base5LogarithmStub = mock(Base5Logarithm.class);
        base10LogarithmStub = mock(Base10Logarithm.class);
        naturalLogarithmStub = mock(NaturalLogarithm.class);

        do_mock(sineFunctionStub, SineFile);
        do_mock(cosineFunctionStub, CosineFile);
        do_mock(secantFunctionStub, SecantFile);
        do_mock(cosecantFunctionStub, CosecantFile);
        do_mock(base2LogarithmStub, Log2File);
        do_mock(base3LogarithmStub, Log3File);
        do_mock(base5LogarithmStub, Log5File);
        do_mock(base10LogarithmStub, Log10File);
        do_mock(naturalLogarithmStub, NaturalLogFile);
    }

    private static void do_mock(MathFunction function, List<Point> points) {
        for (Point point : points) {
            when(function.evaluate(point.getX())).thenReturn(point.getY());
            when(function.evaluate(eq(point.getX()), anyDouble())).thenReturn(point.getY());
        }
    }

    private static void do_mock(MathFunction function, String filename) {
        List<Point> points = new TableReader().readCsv(filename);
        do_mock(function, points);
    }

    public static SineFunction getSineFunctionStub() {
        return sineFunctionStub;
    }

    public static CosineFunction getCosineFunctionStub() {
        return cosineFunctionStub;
    }

    public static CosecantFunction getCosecantFunctionStub() {
        return cosecantFunctionStub;
    }

    public static SecantFunction getSecantFunctionStub() {
        return secantFunctionStub;
    }

    public static Base2Logarithm getBase2LogarithmStub() {
        return base2LogarithmStub;
    }

    public static Base3Logarithm getBase3LogarithmStub() {
        return base3LogarithmStub;
    }

    public static Base5Logarithm getBase5LogarithmStub() {
        return base5LogarithmStub;
    }

    public static Base10Logarithm getBase10LogarithmStub() {
        return base10LogarithmStub;
    }

    public static NaturalLogarithm getNaturalLogarithmStub() {
        return naturalLogarithmStub;
    }
}
