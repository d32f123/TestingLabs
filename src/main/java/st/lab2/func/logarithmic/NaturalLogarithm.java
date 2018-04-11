package st.lab2.func.logarithmic;

import st.lab2.func.MathFunction;

import java.util.ArrayList;

public class NaturalLogarithm implements MathFunction {
    /* Minimum allowed error */
    private static final double EPSILON = 0.00000000000000001;

    private double allowedError;
    private ArrayList<Double> coeffs = new ArrayList<>(5);

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

    private double calculateError(double x, int n) {
        return Math.abs(Math.pow(x - 1, n + 1) / (n + 1));
    }

    private void calculateCoefficients(int n) {
        if (coeffs.size() > n)
            return;
        for (int i = coeffs.size(); i <= n; ++i) {
            double val = 1. / (i + 1);
            coeffs.add((i % 2) != 0 ? -val : val);
        }
    }

    private double doCalculation(double x, int n) {
        double answer = 0.0;
        for (int i = 0; i <= n; ++i)
        {
            answer += coeffs.get(i) * Math.pow(x - 1, i + 1);
        }
        return answer;
    }

    @Override
    public double evaluate(double x) {
        if (x == 0.0)
            return Double.NaN;

        boolean isNegative = x < 0;
        x = Math.abs(x);

        int n = 1;

        // calculate needed accuracy
        double prevError = Double.MAX_VALUE;
        double currError;
        while ((currError = calculateError(x, n)) > allowedError) {
            if (currError > prevError)
                throw new IllegalArgumentException("Function diverges at this point");

            prevError = currError;
            ++n;
        }

        // calculate coefficients for needed accuracy
        calculateCoefficients(n);

        return isNegative ? -doCalculation(x, n) : doCalculation(x, n);
    }

    @Override
    public double evaluate(double x, double accuracy) {
        setAllowedError(accuracy);
        return evaluate(x);
    }
}
