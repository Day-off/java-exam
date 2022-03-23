package ee.taltech.iti0202.kitchen;

import ee.taltech.iti0202.coffeeMachine.CoffeeMachine;
import ee.taltech.iti0202.coffeeMachine.CoffeeBeans;
import ee.taltech.iti0202.coffeeMachine.CoffeeMachineBuilder;
import ee.taltech.iti0202.coffeeMachine.WaterTank;
import ee.taltech.iti0202.coffeeMachine.Capsule;
import ee.taltech.iti0202.drinks.Drinks;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class KitchenTest {

    private final int one = 1;
    private final int nine = 9;
    private int a = 0;

    private int b = 0;
    private final int fifteen = 15;
    private final int six = 6;

    private final WaterTank tank1 = new WaterTank(11);
    private final WaterTank tank2 = new WaterTank(six);

    private final CoffeeBeans beansTank = new CoffeeBeans(six);


    private final CoffeeMachine m1 = new CoffeeMachineBuilder().setType(CoffeeMachine.Type.AUTOMATIC)
            .setWater(tank1).createCoffeeMachine();
    private final CoffeeMachine m4 = new CoffeeMachineBuilder().setType(CoffeeMachine.Type.ORDINARY).setMaxTrash(4)
            .setWater(tank1).createCoffeeMachine(); //specific coffee machine or light version
    private final CoffeeMachine m2 = new CoffeeMachineBuilder()
            .setType(CoffeeMachine.Type.ORDINARY).setBeans(beansTank).setWater(tank2).createCoffeeMachine();
    private final CoffeeMachine m3 = new CoffeeMachineBuilder().setType(CoffeeMachine.Type.CAPSULE)
            .setWater(tank1).createCoffeeMachine();

    //try creat capsule machine with diff trash tank
    private final CoffeeMachine m5 = new CoffeeMachineBuilder().setType(CoffeeMachine.Type.CAPSULE)
            .setWater(tank1).setMaxTrash(six).createCoffeeMachine();


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

        assertFalse(m2.checkTrash()); //no trash

        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);

        caffe1.order(m2, Drinks.DrinksTypes.KAKAO); //error trash is full

        assertTrue(m2.checkTrash()); //trash tank is full

    }

    @Test
    public void cleanTrash() {
        ordMachineTrash();

        m2.cleanTrashTank(); //trash is cleaned

        assertFalse(m2.checkTrash()); //cleaning is works
    }

    @Test
    public void testWaterTank() {
        cleanTrash();
        caffe1.order(m2, Drinks.DrinksTypes.CAPPUCCINO);
        caffe1.order(m2, Drinks.DrinksTypes.CAPPUCCINO); //Water tank is empty
        assertFalse(m2.getWater().checkVolume()); //check is empty
        assertEquals(0, m2.getWater().getLitersOfWater()); // check amount of water in water tank
    }

    @Test
    public void refillTank() {
        testWaterTank();
        m2.getWater().refillTank(); //refill tank
        assertTrue(m2.getWater().checkVolume());
    }

    @Test
    public void checkBeansTank() {
        refillTank();
        caffe1.order(m2, Drinks.DrinksTypes.CAPPUCCINO); //error beans are empty
        assertFalse(m2.getBeans().checkVolume()); //check is empty
        assertEquals(0, m2.getBeans().getBeansAmount()); // check amount of water in water tank
    }

    @Test
    public void refillBeansTank() {
        checkBeansTank();
        m2.getBeans().refillBeansTank();
        assertTrue(m2.getBeans().checkVolume());
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

        assertFalse(m3.setCapsule(new Capsule(Drinks.DrinksTypes.CAPPUCCINO))); //error capsule in machine

        m3.removeCapsule();

        m3.setCapsule(new Capsule(Drinks.DrinksTypes.LATTE));

        assertEquals(Drinks.DrinksTypes.LATTE, caffe2.order(m3, Drinks.DrinksTypes.LATTE).getType());

    }

    @Test
    public void capsuleTrash() {
        testWithCapsule();
        while (a != nine) {
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
        while (b != fifteen) {
            caffe1.order(m1, Drinks.DrinksTypes.CAPPUCCINO);
            b += 1;
        }
        assertEquals(Drinks.DrinksTypes.CAPPUCCINO, caffe1.order(m1, Drinks.DrinksTypes.CAPPUCCINO).getType());
    }

    @Test
    public void machineNotExist() {
        addStuff();
        assertNull(caffe2.order(m4, Drinks.DrinksTypes.KAKAO));
    }


}
