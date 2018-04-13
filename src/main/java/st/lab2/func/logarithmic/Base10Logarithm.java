package st.lab2.func.logarithmic;

import st.lab2.func.MathFunction;

public class Base10Logarithm implements MathFunction {
    private final NaturalLogarithm naturalLogarithm;

    public Base10Logarithm(NaturalLogarithm naturalLogarithm) {
        this.naturalLogarithm = naturalLogarithm;
    }

    @Override
    public double evaluate(double x) {
        return naturalLogarithm.evaluate(x) / 2.302585092994;
    }

    @Override
    public double evaluate(double x, double accuracy) {
        return naturalLogarithm.evaluate(x, accuracy) / 2.302585092994;
    }
}
