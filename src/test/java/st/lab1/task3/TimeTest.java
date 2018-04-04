package st.lab1.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    Time time;
    @BeforeEach
    void setUp() {
        LaughingMan laughingMan = new LaughingMan();
        Girl girl = new Girl();
        Bar bar = new Bar();
        laughingMan.dragGirlIntoTheBar(girl, bar);
        time = new Time(bar);
    }

    @Test
    void passInstancesTest() {
        time.passInstance();
        assertEquals(1, time.getInstances(), "Instances pass error");
    }

    @Test
    void passHoursTest() {
        time.passHour();
        assertEquals(3600, time.getSeconds(), "Hours pass error");
    }

    @Test
    void passMinutesTest() {
        time.passMinuteAndAHalf();
        assertEquals(90, time.getSeconds(), "Minutes pass error");
    }

}