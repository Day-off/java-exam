package ee.taltech.iti0202.kt.ship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainerTest {

    private static final int MAX_W = 7;

    @Test
    public void createContainer() {
        Container con = new Container(MAX_W);
        assertEquals(MAX_W, con.getWeight());
        assertFalse(con.getStatus());
        con.changeStatuseOnShip();
        assertTrue(con.getStatus());
    }


}
