package st.lab2.func.logarithmic;

import st.lab2.func.MathFunction;

public class Base3Logarithm implements MathFunction {
    private final NaturalLogarithm naturalLogarithm = new NaturalLogarithm();

    @Override
    public double evaluate(double x) {
        return naturalLogarithm.evaluate(x) / naturalLogarithm.evaluate(3.);
    }

    @Override
    public double evaluate(double x, double accuracy) {
        return naturalLogarithm.evaluate(x, accuracy) / naturalLogarithm.evaluate(3., accuracy);
    }
}
