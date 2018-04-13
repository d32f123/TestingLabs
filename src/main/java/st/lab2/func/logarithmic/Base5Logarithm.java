package st.lab2.func.logarithmic;

import st.lab2.func.MathFunction;

public class Base5Logarithm implements MathFunction {
    private final NaturalLogarithm naturalLogarithm;

    public Base5Logarithm(NaturalLogarithm naturalLogarithm) {
        this.naturalLogarithm = naturalLogarithm;
    }

    @Override
    public double evaluate(double x) {
        return naturalLogarithm.evaluate(x) / 1.60943791243;
    }

    @Override
    public double evaluate(double x, double accuracy) {
        return naturalLogarithm.evaluate(x, accuracy) / 1.60943791243;
    }
}
