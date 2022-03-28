package ee.taltech.iti0202.kt.ship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortTest {

    private static final int maxW1 = 10;
    private static final int maxW2 = 3;
    private final Container con1 = new Container(maxW1);
    private final Container con2 = new Container(maxW2);
    private final Container con3 = new Container(maxW2);
    private final Container con4 = new Container(maxW1);

    private final Ship tan = new ShipBuilder().setType(Ship.Type.TANKER).setMaxWeight(10).setMaxContainerAmount(2)
            .setCin("EE-ABC00042-A3-99").createShip();
    private final Ship pas = new ShipBuilder().setType(Ship.Type.PASSENGER).setMaxWeight(5)
            .setCin("EE-ABC00042-A3-99").createShip();
    private final Ship bul = new ShipBuilder().setType(Ship.Type.BULK).setMaxWeight(15).setCin("EE-ABC00042-A3-99").createShip();

    private final Port sadam = new Port();

    @Test
    public void addStuff() {
        sadam.addContainer(con1);
        sadam.addContainer(con2);
        sadam.addContainer(con3);
        sadam.addContainer(con4);

        assertEquals(4, sadam.getContainers().size());

        sadam.addShip(tan);
        sadam.addShip(pas);
        sadam.addShip(bul);
        sadam.addShip(bul);

        assertEquals(3, sadam.getShips().size());

    }

    @Test
    public void dividingCont() {
        addStuff();
        sadam.divideContainers();
        assertEquals(1, tan.getContainers().size());
        assertEquals(1, pas.getContainers().size());
        assertEquals(2, bul.getContainers().size());
    }

}