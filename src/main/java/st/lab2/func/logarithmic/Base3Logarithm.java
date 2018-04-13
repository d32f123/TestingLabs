package st.lab2.func.logarithmic;

import st.lab2.func.MathFunction;

public class Base3Logarithm implements MathFunction {
    private final NaturalLogarithm naturalLogarithm;

    public Base3Logarithm(NaturalLogarithm naturalLogarithm) {
        this.naturalLogarithm = naturalLogarithm;
    }


    @Override
    public double evaluate(double x) {
        return naturalLogarithm.evaluate(x) / 1.098612288668109;
    }

    @Override
    public double evaluate(double x, double accuracy) {
        return naturalLogarithm.evaluate(x, accuracy) / 1.098612288668109;
    }
}
