package st.lab2.func.trigonometric;

import st.lab2.func.MathFunction;

import java.util.ArrayList;

public class SineFunction implements MathFunction {
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

    private boolean isNegative;

    private double factorial(int n) {
        double f = 1;
        for (int i=1; i<=n; i++) {
            f *= i;
        }
        return f;
    }

    private double calculateError(double x, int n) {
        // maybe 2*(n+1)
        return Math.abs(Math.pow(x, n + 1) / factorial(n + 1));
    }

    private void calculateCoefficients(int n) {
        if (coeffs.size() > n)
            return;
        for (int i = coeffs.size(); i <= n; ++i) {
            double val = 1. / factorial(2*i + 1);
            coeffs.add((i % 2) != 0 ? -val : val);
        }
    }

    private double truncateX(double x) {
        // if x is larger than the first sine wave, truncate it
        if (Math.abs(x) >= Math.PI) {
            int waveNumber = (int)(Math.abs(x) / Math.PI);

            // if odd sine wave, then it is negative
            if (waveNumber % 2 == 1) {
                this.isNegative = true;
            }

            if (x < 0)
                waveNumber = (-waveNumber);

            // do the truncation
            x = x - Math.PI * waveNumber;
        }
        return x;
    }

    private double doCalculation(double x, int n) {
        double answer = 0.0;
        for (int i = 0; i <= n; ++i)
        {
            answer += coeffs.get(i) * Math.pow(x, i<<1);
        }
        if (isNegative)
            answer = (-answer);
        return answer;
    }

    /* Evaluate at X */
    @Override
    public double evaluate(double x) {
        x = truncateX(x);

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
