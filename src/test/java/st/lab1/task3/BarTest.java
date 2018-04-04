package st.lab1.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarTest {

    private Bar bar;

    @BeforeEach
    void setUp() {
        bar = new Bar();
    }

    @Test
    void setGirlTest() {
        Girl girl = new Girl();

        bar.setGirl(girl);
        assertEquals(girl, bar.getGirl(), "Girl not equal");
    }

    @Test
    void setLaughingManTest() {
        LaughingMan laughingMan = new LaughingMan();

        bar.setLaughingMan(laughingMan);
        assertEquals(laughingMan, bar.getLaughingMan());
    }

    @Test
    void getVisitorsNotNullTest() {
        assertNotNull(bar.getVisitors());
    }


    @Test
    void setQuietTest() {
        bar.setQuiet(true);
        assertTrue(bar.isQuiet());
        bar.setQuiet(false);
        assertFalse(bar.isQuiet());
    }
}