package st.lab1.task3;

public class Girl {
    private boolean isGlad = false;
    private Cloud leftovers = null;
    private LaughingMan manWhoWasHated = null;

    public void hateWithAllHeart(LaughingMan laughingMan) {
        manWhoWasHated = laughingMan;
    }

    public LaughingMan getManWhoWasHated() { return manWhoWasHated; }

    public void setGlad(boolean glad) {
        if (!this.isVaporized())
            isGlad = glad;
    }

    public boolean isGlad() {
        return this.isGlad;
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
        leftovers = new Cloud();
        return leftovers;
    }

}
