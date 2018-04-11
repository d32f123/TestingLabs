package st.lab2.func.logarithmic;

import st.lab2.func.MathFunction;

public class Base5Logarithm implements MathFunction {
    private final NaturalLogarithm naturalLogarithm = new NaturalLogarithm();

    @Override
    public double evaluate(double x) {
        return naturalLogarithm.evaluate(x) / naturalLogarithm.evaluate(5.);
    }

    @Override
    public double evaluate(double x, double accuracy) {
        return naturalLogarithm.evaluate(x, accuracy) / naturalLogarithm.evaluate(5., accuracy);
    }
}
