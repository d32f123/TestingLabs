package st.lab1.task3;

public class Visitors {
    private boolean isSitting;

    public boolean isSitting() { return this.isSitting; }

    public void sit(){
        this.isSitting = true;
    }

    public void stand() {
        this.isSitting = false;
    }
}
