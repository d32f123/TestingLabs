package st.lab2.func.logarithmic;

import st.lab2.func.BasicMathFunction;

public class NaturalLogarithm extends BasicMathFunction {
    @Override
    protected double calculateError(double x, int n) {
        return 0;
    }

    @Override
    protected void calculateCoefficients(int n) {

    }

    @Override
    protected double truncateX(double x) {
        return 0;
    }

    @Override
    protected boolean isUndefined(double x) {
        return false;
    }

    @Override
    protected double doCalculation(double x, int n) {
        return 0;
    }
}
