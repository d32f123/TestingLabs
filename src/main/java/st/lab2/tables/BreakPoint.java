package st.lab2.tables;

public class BreakPoint {

    private double x;
    private double epsilon;
    private boolean skip;

    public BreakPoint(double x, double epsilon, boolean skip) {
        this.x = x;
        this.epsilon = epsilon;
        this.skip = skip;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}
