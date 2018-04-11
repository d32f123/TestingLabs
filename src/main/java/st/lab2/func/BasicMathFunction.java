package st.lab2.func;

import java.util.ArrayList;

public abstract class BasicMathFunction implements MathFunction {
    /* Minimum allowed error */
    private static final double EPSILON = 0.00000000000000001;

    private double allowedError;
    protected ArrayList<Double> coeffs = new ArrayList<>(5);


    public double getAllowedError() {
        return allowedError;
    }

    public void setAllowedError(double allowedError) {
        if (allowedError > 0.5)
            this.allowedError = 0.5;
        else if (allowedError < EPSILON)
            this.allowedError = EPSILON;
        else
            this.allowedError = allowedError;
    }


    /* Check what the error is for given n taylor components */
    protected abstract double calculateError(double x, int n);
    /* Calculate coefficients up to given N */
    protected abstract void calculateCoefficients(int n);
    /* Truncate X to a more viable point on the graph */
    protected abstract double truncateX(double x);
    /* Check if function is undefined at this point */
    protected abstract boolean isUndefined(double x);
    /* Do the actual calculation */
    protected abstract double doCalculation(double x, int n);
    /* Evaluate at X */
    @Override
    public double evaluate(double x) {
        x = truncateX(x);

        if (isUndefined(x))
            return Double.NaN;

        int n = 1;

        // calculate needed accuracy
        while (calculateError(x, n) > allowedError)
            ++n;

        // calculate coefficients for needed accuracy
        calculateCoefficients(n);

        return doCalculation(x, n);
    }

    @Override
    public double evaluate(double x, double accuracy) {
        setAllowedError(accuracy);
        return evaluate(x);
    }

}
