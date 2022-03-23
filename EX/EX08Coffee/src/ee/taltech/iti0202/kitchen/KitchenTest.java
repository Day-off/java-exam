package ee.taltech.iti0202.kitchen;

import ee.taltech.iti0202.coffeeMachine.Capsule;
import ee.taltech.iti0202.coffeeMachine.CoffeeMachine;
import ee.taltech.iti0202.coffeeMachine.CoffeeMachineBuilder;
import ee.taltech.iti0202.coffeeMachine.WaterTank;
import ee.taltech.iti0202.drinks.Drinks;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KitchenTest {

    private final int one = 1;
    private final int NINE = 9;
    private int a = 0;

    private int b = 0;
    private final int FIFTEEN = 15;

    private final WaterTank tank1 = new WaterTank(11);
    private final WaterTank tank2 = new WaterTank(6);


    private final CoffeeMachine m1 = new CoffeeMachineBuilder().setType(CoffeeMachine.Type.AUTOMATIC)
            .setWater(tank1).createCoffeeMachine();
    private final CoffeeMachine m2 = new CoffeeMachineBuilder()
            .setType(CoffeeMachine.Type.ORDINARY).setWater(tank2).createCoffeeMachine();
    private final CoffeeMachine m3 = new CoffeeMachineBuilder().setType(CoffeeMachine.Type.CAPSULE)
            .setWater(tank1).createCoffeeMachine();

    private final Kitchen caffe1 = new Kitchen(new ArrayList<>());
    private final Kitchen caffe2 = new Kitchen(List.of(m3));

    @Test
    public void addStuff() {
        caffe1.addCoffeeMachines(m1);
        caffe1.addCoffeeMachines(m2);
        assertEquals(2, caffe1.getCoffeeMachines().size());
        assertEquals(1, caffe2.getCoffeeMachines().size());

        caffe1.removeCoffeeMachines(m2);
        assertEquals(1, caffe1.getCoffeeMachines().size());
        caffe1.addCoffeeMachines(m2);
    }


    @Test
    public void ordMachineTrash() {
        addStuff();
        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);

        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);

        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);

        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);

        assertFalse(m2.checkTrash());

        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);

        caffe1.order(m2, Drinks.DrinksTypes.KAKAO);//error trash is full
        assertTrue(m2.checkTrash());

    }

    @Test
    public void cleanTrash() {
        ordMachineTrash();
        m2.cleanTrashTank();//trash is cleaned
        assertFalse(m2.checkTrash());
    }

    @Test
    public void testWaterTank() {
        cleanTrash();
        caffe1.order(m2, Drinks.DrinksTypes.CAPPUCCINO);
        caffe1.order(m2, Drinks.DrinksTypes.CAPPUCCINO);
        assertFalse(m2.getWater().checkVolume());
        assertEquals(0, m2.getWater().getLitersOfWater());
    }

    @Test
    public void refillTank() {
        testWaterTank();
        m2.getWater().refillTank();//refill tank
        assertTrue(m2.getWater().checkVolume());
    }

    @Test
    public void testEmptyCapsuleMachine() {
        addStuff();
        Drinks wat = caffe2.order(m3, Drinks.DrinksTypes.KAKAO);
        assertEquals(Drinks.DrinksTypes.WATER, wat.getType());
    }

    @Test
    public void testWithCapsule() {
        testEmptyCapsuleMachine();
        m3.setCapsule(new Capsule(Drinks.DrinksTypes.LATTE));

        assertFalse(m3.setCapsule(new Capsule(Drinks.DrinksTypes.CAPPUCCINO)));//error capsule in machine

        m3.removeCapsule();

        m3.setCapsule(new Capsule(Drinks.DrinksTypes.LATTE));

        assertEquals(Drinks.DrinksTypes.LATTE, caffe2.order(m3, Drinks.DrinksTypes.LATTE).getType());

    }

    @Test
    public void capsuleTrash() {
        testWithCapsule();
        while (a != NINE) {
            m3.removeCapsule();
            m3.setCapsule(new Capsule(Drinks.DrinksTypes.LATTE));
            caffe2.order(m3, Drinks.DrinksTypes.CAPPUCCINO);
            a += one;
        }
        assertTrue(m3.checkTrash());
    }

    @Test
    public void capsuleClean() {
        capsuleTrash();
        m3.cleanTrashTank();
        assertFalse(m3.checkTrash());
        m3.removeCapsule();
        m3.setCapsule(new Capsule(Drinks.DrinksTypes.KAKAO));
        assertEquals(Drinks.DrinksTypes.KAKAO, caffe2.order(m3, Drinks.DrinksTypes.KAKAO).getType());
    }

    @Test
    public void autoMachine() {
        addStuff();
        while (b != FIFTEEN) {
            caffe1.order(m1, Drinks.DrinksTypes.CAPPUCCINO);
            b += 1;
        }
        assertEquals(Drinks.DrinksTypes.CAPPUCCINO, caffe1.order(m1, Drinks.DrinksTypes.CAPPUCCINO).getType());
    }


}
