package st.lab1.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaughingManTest {

    private LaughingMan laughingMan;
    @BeforeEach
    void setUp() {
        laughingMan = new LaughingMan();
    }

    @Test
    void loudLaughingCountTest() {
        laughingMan.startLaughing(true);
        assertEquals(1, laughingMan.getLaughCount(), "Loud laughing counter error!");
    }

    @Test
    void notLoudLaughingCountTest() {
        laughingMan.startLaughing(false);
        assertEquals(1, laughingMan.getLaughCount(), "Not loud laughing counter error!");
    }

    @Test
    void loudLaughingStatusTest() {
        laughingMan.startLaughing(true);
        assertTrue(laughingMan.isLaughing(), "Laughing status error!");
        assertTrue(laughingMan.isLaughingLoud(), "Loud status error!");
    }

    @Test
    void notLoudLaughingStatusTest() {
        laughingMan.startLaughing(false);
        assertTrue(laughingMan.isLaughing(), "Laughing status error!");
        assertFalse(laughingMan.isLaughingLoud(), "Loud status error!");
    }

    @Test
    void stopLaughingStatusTest(){
        laughingMan.startLaughing(true);
        laughingMan.stopLaughing();
        assertFalse(laughingMan.isLaughing(), "Laughing status error!");
        assertFalse(laughingMan.isLaughingLoud(), "Loud status error!");
    }
    @Test
    void vaporizingStatusTest(){
        laughingMan.vaporize();
        assertTrue(laughingMan.isVaporized(), "Man wasn't vaporized!");
    }

    @Test
    void dragGirlTwiceThrowsTest() {
        Girl girl = new Girl();
        Bar bar = new Bar();
        laughingMan.dragGirlIntoTheBar(girl, bar);
        assertThrows(IllegalArgumentException.class, () -> laughingMan.dragGirlIntoTheBar(girl, bar));
    }

    @Test
    void draggedIntoTheNullBarTest() {
        Girl girl = new Girl();
        assertThrows(NullPointerException.class, () -> laughingMan.dragGirlIntoTheBar(girl, null));
    }

    @Test
    void draggedNullGirlIntoTheBarTest() {
        Bar bar = new Bar();
        assertThrows(NullPointerException.class, () -> laughingMan.dragGirlIntoTheBar(null, bar));
    }

    @Test
    void draggedNullGirlIntoNullBarTest() {
        assertThrows(NullPointerException.class, () -> laughingMan.dragGirlIntoTheBar(null, null));
    }

    @Test
    void vaporizeTwiceThrowsTest() {
        laughingMan.vaporize();
        assertThrows(IllegalArgumentException.class, () -> laughingMan.vaporize());
    }

    @Test
    void vaporizeTwiceReturnFirstTest() {
        Cloud cloud = laughingMan.vaporize();
        Cloud secondCloud = null, thirdCloud;
        try {
            secondCloud = laughingMan.vaporize();
        } catch(Exception e) {}
        thirdCloud = laughingMan.getLeftovers();

        assertNull(secondCloud, "Failed to throw?");
        assertEquals(cloud, thirdCloud, "Failed to keep the same cloud because of multiple vaporize calls");
    }

    @Test
    void vaporizingGirlGladTest(){
        Girl girl = new Girl();
        Bar bar = new Bar();
        laughingMan.dragGirlIntoTheBar(girl, bar);
        laughingMan.vaporize();

        assertTrue(girl.isGlad(), "Girl isn't glad of the man being vaporized!");
    }

}