package st.lab2.subsystems;

import st.lab2.func.MathFunction;
import st.lab2.func.logarithmic.Base10Logarithm;
import st.lab2.func.logarithmic.Base2Logarithm;
import st.lab2.func.logarithmic.Base3Logarithm;
import st.lab2.func.logarithmic.Base5Logarithm;

public class SubSystem2 implements MathFunction {

    private Base2Logarithm base2Logarithm;
    private Base3Logarithm base3Logarithm;
    private Base5Logarithm base5Logarithm;
    private Base10Logarithm base10Logarithm;

    private static final double ACCURACY = 0.000001;

    public SubSystem2(Base2Logarithm base2Logarithm, Base3Logarithm base3Logarithm, Base5Logarithm base5Logarithm, Base10Logarithm base10Logarithm) {
        this.base2Logarithm = base2Logarithm;
        this.base3Logarithm = base3Logarithm;
        this.base5Logarithm = base5Logarithm;
        this.base10Logarithm = base10Logarithm;
    }

    @Override
    public double evaluate(double x) {
        return evaluate(x, ACCURACY);
    }

    @Override
    public double evaluate(double x, double accuracy) {
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
