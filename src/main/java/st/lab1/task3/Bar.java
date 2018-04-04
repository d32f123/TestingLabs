package st.lab1.task3;

public class Bar {
    private Girl girl;
    private LaughingMan laughingMan;
    private Visitors visitors;
    private boolean isQuiet;

    public Bar() {
        this.visitors = new Visitors();
        this.visitors.sit();
        setQuiet(true);
    }

    void setGirl(Girl girl) {
        this.girl = girl;
    }

    void setLaughingMan(LaughingMan laughingMan) {
        this.laughingMan = laughingMan;
    }

    public Girl getGirl() {
        return girl;
    }

    public LaughingMan getLaughingMan() {
        return laughingMan;
    }

    public Visitors getVisitors() {
        return visitors;
    }

    public boolean isQuiet() {
        return this.isQuiet;
    }

    public void setQuiet(boolean isQuiet) {
        this.isQuiet = isQuiet;
    }
}
