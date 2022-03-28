package ee.taltech.iti0202.kt.ship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainerTest {

    private static final int maxW = 7;

    @Test
    public void createContainer() {
        Container con = new Container(maxW);
        assertEquals(maxW, con.getWeight());
        assertFalse(con.getStatus());
        con.changeStatuseOnShip();
        assertTrue(con.getStatus());
    }


}