package st.lab1.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisitorsTest {

    private Visitors visitors;
    @BeforeEach
    void setUp() {
        visitors = new Visitors();
    }

    @Test
    void sitTest() {
        visitors.sit();
        assertTrue(visitors.isSitting(), "Sit status error!");
    }

    @Test
    void standUpTest() {
        visitors.sit();
        visitors.stand();

        assertFalse(visitors.isSitting(), "Standing visitors are sitting for some reason");
    }

}