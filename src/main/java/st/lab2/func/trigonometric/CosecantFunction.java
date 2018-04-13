package st.lab2.func.trigonometric;

import st.lab2.func.MathFunction;

public class CosecantFunction implements MathFunction {
    private final SineFunction sineFunction;

    public CosecantFunction(SineFunction sineFunction) {
        this.sineFunction = sineFunction;
    }

    @Override
    public double evaluate(double x) {
        return  1. / this.sineFunction.evaluate(x);
    }

    @Override
    public double evaluate(double x, double accuracy) {
        return 1. / this.sineFunction.evaluate(x, accuracy);
    }
}
