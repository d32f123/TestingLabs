package st.lab1.task3;

public class Time {
    private int seconds = 0;
    private int instances = 0;
    private Bar bar;

    private boolean firstConditionMet = false;
    private boolean secondConditionMet = false;
    private boolean thirdConditionMet = false;

    public Time(Bar bar){
        if (bar == null)
            throw new NullPointerException("Bar is null");
        this.bar = bar;
    }

    public void passInstance() {
        this.instances++;
        testEvents();
    }

    private void passSeconds(int count) {
        this.instances = 0;
        this.seconds += count;
        testEvents();
    }

    public void passMinuteAndAHalf() {
        passSeconds(90);
    }

    public void passHour() {
        passSeconds(3600);
    }

    private void testEvents() {

         if (bar.getGirl() == null || bar.getLaughingMan() == null) {
             if (seconds == 0)
                 return;
             seconds = 0;
             instances = 0;
             throw new NullPointerException();
         }

        // За последний час всем сердцем возненавидела его
        if (seconds >= 3600 && instances >= 0 && !firstConditionMet) {
            bar.getLaughingMan().startLaughing(true);
            bar.getGirl().hateWithAllHeart(bar.getLaughingMan());
            firstConditionMet = true;
        }

        // Еще мгновение люди сидели молча, а потом человек, громко смеявшейся, засмеялся снова
        if (((seconds == 3600 && instances >= 1) || seconds > 3600) && !secondConditionMet) {
            bar.getLaughingMan().startLaughing(false);
            bar.setQuiet(false);
            secondConditionMet = true;
        }

        // возможно, была бы рада узнать, что через полторы минуты он испарится, превратившись в облачко...
        // однако, когда момент наступил, она была слишком занята собственным испарением
        if (seconds >= 3690 && instances >= 0 && !thirdConditionMet) {
            bar.getGirl().vaporize();
            bar.getLaughingMan().vaporize();
            thirdConditionMet = true;
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public int getInstances() {
        return instances;
    }
}
