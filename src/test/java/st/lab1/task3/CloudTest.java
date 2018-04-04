package st.lab1.task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloudTest {

    @Test
    void cloudTest() {
        Cloud cloud = new Cloud();

        assertEquals(1, cloud.getCarbonOxide(), "Bad cloud after vaporizing(carbonOxide)");
        assertEquals(1, cloud.getOzone(), "Bad cloud after vaporizing(ozone)");
        assertEquals(1, cloud.getHydrogen(), "Bad cloud after vaporizing(hydrogen)");

    }

}