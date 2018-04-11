package st.lab2.func;

import java.util.ArrayList;

public interface MathFunction {
    double DEFAULT_ACCURACY = 0.0000001;

    double evaluate(double x);
    double evaluate(double x, double accuracy);
}
