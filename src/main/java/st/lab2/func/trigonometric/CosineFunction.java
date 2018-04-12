package st.lab2.func.trigonometric;

import st.lab2.func.MathFunction;

public class CosineFunction implements MathFunction {
    private final SineFunction sineFunction;

    public CosineFunction(SineFunction sineFunction) {
        this.sineFunction = sineFunction;
    }

    @Override
    public double evaluate(double x) {
        double sin = sineFunction.evaluate(x);
        double cos = Math.sqrt(1 - Math.pow(sin, 2));
        return sin < 0 ? -cos : cos;
    }

    @Override
    public double evaluate(double x, double accuracy) {
        int cosNumber = (int)((Math.abs(x) + Math.PI / 2.) / Math.PI);

        double sin = sineFunction.evaluate(x, accuracy);
        double cos = Math.sqrt(1 - Math.pow(sin, 2));
        return cosNumber % 2 == 1 ? -cos : cos;
    }
}
