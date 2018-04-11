package st.lab2.func.trigonometric;

import st.lab2.func.BasicMathFunction;

public class SineFunction extends BasicMathFunction {

    private boolean isNegative;

    private double factorial(int n) {
        double f = 1;
        for (int i=1; i<=n; i++) {
            f *= i;
        }
        return f;
    }

    @Override
    protected double calculateError(double x, int n) {
        // maybe 2*(n+1)
        return Math.abs(Math.pow(x, n + 1) / factorial(n + 1));
    }

    @Override
    protected void calculateCoefficients(int n) {
        if (coeffs.size() > n)
            return;
        for (int i = coeffs.size(); i <= n; ++i) {
            double val = 1. / factorial(2*i + 1);
            coeffs.add((i % 2) != 0 ? -val : val);
        }
    }

    @Override
    protected double truncateX(double x) {
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

    @Override
    protected boolean isUndefined(double x) {
        return false;
    }

    @Override
    protected double doCalculation(double x, int n) {
        double answer = 0.0;
        for (int i = 0; i <= n; ++i)
        {
            answer += coeffs.get(i) * Math.pow(x, i<<1);
        }
        if (isNegative)
            answer = (-answer);
        return answer;
    }
}
