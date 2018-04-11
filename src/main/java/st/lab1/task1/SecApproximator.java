package st.lab1.task1;

import java.util.ArrayList;

public class SecApproximator {
    private static final double EPSILON = 0.00000000000000001;
    private double allowedError;
    private ArrayList<Double> coeffs = new ArrayList<>(5);

    public SecApproximator() { }

    public SecApproximator(double allowedError) {
        setAllowedError(allowedError);
    }


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

    public double evaluate(double x) {
        int n = 1;

        boolean negative = false;


        // if x is larger than the first secant, truncate it
        if (Math.abs(x) >= Math.PI / 2) {

            int secNumber = (int)((Math.abs(x) - Math.PI / 2.) / Math.PI) + 1;

            // if odd secant, then it is negative
            if (secNumber % 2 == 1) {
                negative = true;
            }

            if (x < 0)
                secNumber = (-secNumber);


            // do the truncation
            x = x - Math.PI * secNumber;
            /*
            x = (x - Math.PI / 2.) % Math.PI;
            if (x < 0)
                x += Math.PI / 2.;
            else
                x -= Math.PI / 2.;
            */
        }

        // if x is on the boundary of two secants, then it is not going to end well for the algorithm
        // (the function is undefined at this point(dividing by zero))
        if (Math.abs(x) == Math.PI / 2.)
            return Double.NaN;

        // calculate needed accuracy
        while (calculateError(x, n) > allowedError)
            ++n;

        // calculate coefficients for needed accuracy
        calculateCoefficients(n);

        double answer = 0.0;
        for (int i = 0; i <= n; ++i)
        {
            answer += coeffs.get(i) * Math.pow(x, i<<1);
        }
        if (negative)
            answer = (-answer);
        return 1. / answer;
    }

    private void calculateCoefficients(int n) {
        if (coeffs.size() > n)
            return;
        for (int i = coeffs.size(); i <= n; ++i) {
            double val = 1. / factorial(2*i);
            coeffs.add((i % 2) != 0 ? -val : val);
        }
    }

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
}
