package st.lab1.task3;

public class LaughingMan {
    private int laughCount = 0;
    private boolean isLaughing = false;
    private boolean isLoud = false;
    private Cloud leftovers = null;
    private Girl girlWhoWasDragged = null;

    public int getLaughCount() { return laughCount; }

    public boolean isLaughing() { return this.isLaughing; }

    public boolean isLaughingLoud() { return this.isLaughing && this.isLoud; }

    public Girl getGirlWhoWasDragged() { return girlWhoWasDragged; }
    public void dragGirlIntoTheBar(Girl girl, Bar bar) {
        if (girl == null || bar == null)
            throw new NullPointerException("Girl or bar is null");
        if (this.girlWhoWasDragged != null)
            throw new IllegalArgumentException("Girl is already dragged");
        girlWhoWasDragged = girl;
        bar.setGirl(girl);
        bar.setLaughingMan(this);
    }

    public void startLaughing(boolean loud) {
        ++laughCount;
        this.isLaughing = true;
        this.isLoud = loud;
    }

    public void stopLaughing(){
        this.isLoud = false;
        this.isLaughing = false;
    }

    public boolean isVaporized() {
        return leftovers != null;
    }

    public Cloud getLeftovers() {
        return leftovers;
    }

    Cloud vaporize() {
        if (leftovers != null)
            throw new IllegalArgumentException("Already vaporized");
        stopLaughing();
        leftovers = new Cloud();
        if (girlWhoWasDragged != null)
            girlWhoWasDragged.setGlad(true);
        return leftovers;
    }
}
