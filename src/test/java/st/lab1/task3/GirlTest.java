package st.lab1.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GirlTest {

    private Girl girl;

    @BeforeEach
    void setGirl(){
        girl = new Girl();
    }

    @Test
    void hateStatusTest(){
        LaughingMan laughingMan = new LaughingMan();
        girl.hateWithAllHeart(laughingMan);
        assertEquals(laughingMan, girl.getManWhoWasHated(), "Laughing man doesn't match!");
    }
    @Test
    void gladAfterVaporizingStatusTest(){
        girl.vaporize();
        girl.setGlad(true);
        assertFalse(girl.isGlad(), "Girl is glad after being vaporized!");
    }

    @Test
    void vaporizingStatusTest(){
        girl.vaporize();
        assertTrue(girl.isVaporized(), "Girl wasn't vaporized!");
    }


    @Test
    void vaporizeTwiceThrowsTest() {
        girl.vaporize();
        assertThrows(IllegalArgumentException.class, () -> girl.vaporize());
    }

    @Test
    void vaporizeTwiceReturnFirstTest() {
        Cloud cloud = girl.vaporize();
        Cloud secondCloud = null, thirdCloud;
        try {
            secondCloud = girl.vaporize();
        } catch(Exception e) {}
        thirdCloud = girl.getLeftovers();

        assertNull(secondCloud, "Failed to throw?");
        assertEquals(cloud, thirdCloud, "Failed to keep the same cloud because of multiple vaporize calls");
    }

}