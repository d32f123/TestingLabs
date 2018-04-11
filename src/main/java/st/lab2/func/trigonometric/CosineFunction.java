package st.lab2.func.trigonometric;

import st.lab2.func.MathFunction;

public class CosineFunction implements MathFunction {
    private final SineFunction sineFunction = new SineFunction();

    @Override
    public double evaluate(double x) {
        return Math.sqrt(1 - Math.pow(sineFunction.evaluate(x), 2));
    }

    @Override
    public double evaluate(double x, double accuracy) {
        return Math.sqrt(1 - Math.pow(sineFunction.evaluate(x, accuracy), 2));
    }
}
