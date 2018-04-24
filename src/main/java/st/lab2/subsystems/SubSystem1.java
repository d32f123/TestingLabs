package st.lab2.subsystems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import st.lab2.func.MathFunction;
import st.lab2.func.trigonometric.CosecantFunction;
import st.lab2.func.trigonometric.SecantFunction;
import st.lab2.func.trigonometric.SineFunction;

public class SubSystem1 implements MathFunction {
    private CosecantFunction cosecantFunction;
    private SineFunction sineFunction;
    private SecantFunction secantFunction;
    private final static Logger log = LogManager.getLogger("SubSystem1");

    private static final double ACCURACY = 0.000001;

    public SubSystem1(CosecantFunction cosecantFunction, SineFunction sineFunction, SecantFunction secantFunction) {
        this.cosecantFunction = cosecantFunction;
        this.sineFunction = sineFunction;
        this.secantFunction = secantFunction;
    }

    @Override
    public double evaluate(double x) {
        return evaluate(x, ACCURACY);
    }

    @Override
    public double evaluate(double x, double accuracy) {
        double res = cosecantFunction.evaluate(x, accuracy)
                / sineFunction.evaluate(x, accuracy)
                / secantFunction.evaluate(x, accuracy);
        log.info("Subsystem1: {} {}", x, res);
        return res;
    }
}
