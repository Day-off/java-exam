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

    public final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        Handler fileHandler = new FileHandler();
        LOGGER.addHandler(fileHandler);

        WaterTank tank1 = new WaterTank(11);
        WaterTank tank2 = new WaterTank(6);


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

        System.out.println(caffe1.getCoffeeMachines().size());      // 2
        System.out.println(caffe2.getCoffeeMachines().size());      // 1

        /*
        WATER TANK
         */

        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);
        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);
        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);
        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);
        caffe1.order(m2, Drinks.DrinksTypes.COFFEE);
        caffe1.order(m2, Drinks.DrinksTypes.KAKAO); //error trash is full

        m2.cleanTrashTank();//trash is cleaned
        caffe1.order(m2, Drinks.DrinksTypes.CAPPUCCINO);

        caffe1.order(m2, Drinks.DrinksTypes.CAPPUCCINO);//tank is empty
        caffe1.order(m2, Drinks.DrinksTypes.CAPPUCCINO);//tank is empty

        m2.getWater().refillTank();//refill tank

        //Capsule
        caffe2.order(m3, Drinks.DrinksTypes.KAKAO);//Drink is water
        m3.setCapsule(new Capsule(Drinks.DrinksTypes.LATTE));
        m3.removeCapsule();
        m3.setCapsule(new Capsule(Drinks.DrinksTypes.CAPPUCCINO));//error capsule in machine
        m3.removeCapsule();
        m3.setCapsule(new Capsule(Drinks.DrinksTypes.LATTE));
        caffe2.order(m3, Drinks.DrinksTypes.CAPPUCCINO);

        int one = 1;
        int a = 0;
        int res1 = 9;
        while (a != res1) {
            m3.removeCapsule();
            m3.setCapsule(new Capsule(Drinks.DrinksTypes.LATTE));
            caffe2.order(m3, Drinks.DrinksTypes.CAPPUCCINO);
            a += one;
        }

        m3.cleanTrashTank();
        m3.removeCapsule();
        m3.setCapsule(new Capsule(Drinks.DrinksTypes.LATTE));
        caffe2.order(m3, Drinks.DrinksTypes.KAKAO);

        //AUTO
        int b = 0;
        int res2 = 15;
        while (b != res2) {
            caffe1.order(m1, Drinks.DrinksTypes.CAPPUCCINO);
            b += one;
        }

    }
}
