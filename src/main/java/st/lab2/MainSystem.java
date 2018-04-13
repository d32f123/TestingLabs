package st.lab2;

import st.lab2.func.MathFunction;
import st.lab2.func.logarithmic.Base10Logarithm;
import st.lab2.func.logarithmic.Base2Logarithm;
import st.lab2.func.logarithmic.Base3Logarithm;
import st.lab2.func.logarithmic.Base5Logarithm;
import st.lab2.func.trigonometric.CosecantFunction;
import st.lab2.func.trigonometric.SecantFunction;
import st.lab2.func.trigonometric.SineFunction;

public class MainSystem implements MathFunction {
    private static final double DEFAULT_ACCURACY = 0.000000001;

    private CosecantFunction cosecantFunction;
    private SecantFunction secantFunction;
    private SineFunction sineFunction;

    private Base2Logarithm base2Logarithm;
    private Base3Logarithm base3Logarithm;
    private Base5Logarithm base5Logarithm;
    private Base10Logarithm base10Logarithm;

    public MainSystem(CosecantFunction cosecantFunction, SecantFunction secantFunction,
                      SineFunction sineFunction, Base2Logarithm base2Logarithm,
                      Base3Logarithm base3Logarithm, Base5Logarithm base5Logarithm,
                      Base10Logarithm base10Logarithm) {
        this.cosecantFunction = cosecantFunction;
        this.secantFunction = secantFunction;
        this.sineFunction = sineFunction;
        this.base2Logarithm = base2Logarithm;
        this.base3Logarithm = base3Logarithm;
        this.base5Logarithm = base5Logarithm;
        this.base10Logarithm = base10Logarithm;
    }


    @Override
    public double evaluate(double x) {
        return evaluate(x, DEFAULT_ACCURACY);
    }

    @Override
    public double evaluate(double x, double accuracy) {
        if (x <= 0) {
            return cosecantFunction.evaluate(x, accuracy)
                    / sineFunction.evaluate(x, accuracy)
                    / secantFunction.evaluate(x, accuracy);
        } else {
            return (
                    (Math.pow(base3Logarithm.evaluate(x, accuracy)
                            / base10Logarithm.evaluate(x, accuracy), 3)
                    + base3Logarithm.evaluate(x, accuracy)) *
                    base10Logarithm.evaluate(x, accuracy)) *
                    ( Math.pow(base5Logarithm.evaluate(x, accuracy) +
                            ((base10Logarithm.evaluate(x, accuracy) * base2Logarithm.evaluate(x, accuracy))
                            + base2Logarithm.evaluate(x, accuracy)),
                            2)
                    );
        }
    }
}
