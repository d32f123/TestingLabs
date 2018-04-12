package st.lab2.func.trigonometric;

import st.lab2.func.MathFunction;

public class CosecantFunction implements MathFunction {
    private final CosineFunction cosineFunction;

    public CosecantFunction(CosineFunction cosineFunction) {
        this.cosineFunction = cosineFunction;
    }

    @Override
    public double evaluate(double x) {
        return  1. / this.cosineFunction.evaluate(x);
    }

    @Override
    public double evaluate(double x, double accuracy) {
        return 1. / this.cosineFunction.evaluate(x, accuracy);
    }
}
