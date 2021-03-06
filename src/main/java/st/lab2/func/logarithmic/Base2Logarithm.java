package st.lab2.func.logarithmic;

import st.lab2.func.MathFunction;

public class Base2Logarithm implements MathFunction {
    private final NaturalLogarithm naturalLogarithm;

    public Base2Logarithm(NaturalLogarithm naturalLogarithm) {
        this.naturalLogarithm = naturalLogarithm;
    }

    @Override
    public double evaluate(double x) {
        return naturalLogarithm.evaluate(x) / 0.69314718056;
    }

    @Override
    public double evaluate(double x, double accuracy) {
        return naturalLogarithm.evaluate(x, accuracy) / 0.69314718056;
    }
}
