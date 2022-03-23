package ee.taltech.iti0202.example;

import ee.taltech.iti0202.coffeeMachine.Capsule;
import ee.taltech.iti0202.coffeeMachine.CoffeeMachine;
import ee.taltech.iti0202.coffeeMachine.CoffeeMachineBuilder;
import ee.taltech.iti0202.coffeeMachine.WaterTank;
import ee.taltech.iti0202.drinks.Drinks;
import ee.taltech.iti0202.kitchen.Kitchen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;


public class Main {

    public static Logger logger = Logger.getLogger(Main.class.getName());

    private static final int ELEVEN = 11;
    private static final int SIX = 6;
    private static final int ONE = 1;
    private static final int NINE = 9;
    private static final int FIFTEEN = 15;


    public static void main(String[] args) throws IOException {
        Handler fileHandler = new FileHandler();
        logger.addHandler(fileHandler);

        WaterTank tank1 = new WaterTank(ELEVEN);
        WaterTank tank2 = new WaterTank(SIX);


        CoffeeMachine m1 = new CoffeeMachineBuilder().setType(CoffeeMachine.Type.AUTOMATIC)
                .setWater(tank1).createCoffeeMachine();
        CoffeeMachine m2 = new CoffeeMachineBuilder()
                .setType(CoffeeMachine.Type.ORDINARY).setWater(tank2).createCoffeeMachine();
        CoffeeMachine m3 = new CoffeeMachineBuilder().setType(CoffeeMachine.Type.CAPSULE)
                .setWater(tank1).createCoffeeMachine();

        Kitchen caffe1 = new Kitchen(new ArrayList<>());
        Kitchen caffe2 = new Kitchen(List.of(m3));


        caffe1.addCoffeeMachines(m1);
        caffe1.addCoffeeMachines(m2);

        System.out.println(caffe1.getCoffeeMachines().size()); // 2
        System.out.println(caffe2.getCoffeeMachines().size()); // 1

        /*
        WATER TANK
         */

        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);

        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);

        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);

        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);

        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);

        caffe1.order(m2, Drinks.DrinksTypes.KAKAO); //error trash is full

        m2.cleanTrashTank(); //trash is cleaned
        caffe1.order(m2, Drinks.DrinksTypes.CAPPUCCINO);

        caffe1.order(m2, Drinks.DrinksTypes.CAPPUCCINO); //tank is empty

        caffe1.order(m2, Drinks.DrinksTypes.CAPPUCCINO); //tank is empty

        m2.getWater().refillTank(); //refill tank

        //Capsule
        caffe2.order(m3, Drinks.DrinksTypes.KAKAO); //Drink is water

        m3.setCapsule(new Capsule(Drinks.DrinksTypes.LATTE));

        m3.setCapsule(new Capsule(Drinks.DrinksTypes.CAPPUCCINO)); //error capsule in machine

        m3.removeCapsule();

        m3.setCapsule(new Capsule(Drinks.DrinksTypes.LATTE));

        caffe2.order(m3, Drinks.DrinksTypes.CAPPUCCINO);

        int a = 0;
        while (a != NINE) {
            m3.removeCapsule();
            m3.setCapsule(new Capsule(Drinks.DrinksTypes.LATTE));
            caffe2.order(m3, Drinks.DrinksTypes.CAPPUCCINO);
            a += ONE;
        }

        m3.cleanTrashTank();
        m3.removeCapsule();
        m3.setCapsule(new Capsule(Drinks.DrinksTypes.KAKAO));
        caffe2.order(m3, Drinks.DrinksTypes.KAKAO);

        //AUTO
        int b = 0;
        while (b != FIFTEEN) {
            caffe1.order(m1, Drinks.DrinksTypes.CAPPUCCINO);
            b += ONE;
        }

    }
}
