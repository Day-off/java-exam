package ee.taltech.iti0202.kt.ship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    private static final int maxW1 = 10;
    private static final int maxW2 = 3;
    private final Container con1 = new Container(maxW1);
    private final Container con2 = new Container(maxW2);
    private final Container con3 = new Container(maxW2);

    private final Ship tan = new ShipBuilder().setType(Ship.Type.TANKER).setMaxWeight(10).setMaxContainerAmount(2)
            .setCin("EE-ABC00042-A3-99").createShip();
    private final Ship pas = new ShipBuilder().setType(Ship.Type.PASSENGER).setMaxWeight(5)
            .setCin("EE-ABC00042-A3-99").createShip();

    @Test
    public void createDiffShips() {
        assertEquals("EE-ABC00042-A3-99", tan.getCin());
        assertEquals(Ship.Type.TANKER, tan.getType());
        assertEquals(10, tan.getMaxWeight());
        assertEquals(2, tan.getMaxContainerAmount());
    }

    @Test
    public void checkWeight() {
        pas.addContainer(con1);
        assertEquals(0, pas.getContainers().size());
        pas.addContainer(con3);
        assertEquals(1, pas.getContainers().size());
        tan.addContainer(con3);
        tan.addContainer(con2);
        assertEquals(2, tan.getContainers().size());
    }

}