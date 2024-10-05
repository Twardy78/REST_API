package com.crud.tasks.patterns2.decorator.pizza;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PIzzaTestSuite {

    @Test
    public void testBasicPizzaGetCost() {
        //Given
        Pizza pizza = new BasicPizza();

        //When
        BigDecimal pizzaCost = pizza.getPrice();

        //Then
        assertEquals(new BigDecimal(15) , pizzaCost);
    }

    @Test
    public void testBasicPizzaGetContent() {
        //Given
        Pizza pizza = new BasicPizza();

        //When
        String pizzaContent = pizza.getContent();

        //Then
        assertEquals(pizzaContent, "I want order pizza with thick cake + tomato sausage + cheese");
    }

    @Test
    public void testSalamiPizzaGetCost() {
        //Given
        Pizza pizza = new BasicPizza();
        pizza = new SalamiDecorator(pizza);
        BigDecimal ec = new BigDecimal("17.55");
        BigDecimal expectedCost = ec.setScale(2, RoundingMode.UNNECESSARY);

        //When
        BigDecimal pizzaCost = pizza.getPrice();

        //Then
        assertEquals(expectedCost , pizzaCost);
    }

    @Test
    public void testSalamiPizzaGetContent() {
        //Given
        Pizza pizza = new BasicPizza();
        pizza = new SalamiDecorator(pizza);

        //When
        String pizzaContent = pizza.getContent();

        //Then
        assertEquals(pizzaContent, "I want order pizza with thick cake + tomato sausage + cheese + salami");
    }
    @Test
    public void testBroccoliPizzaGetCost() {
        //Given
        Pizza pizza = new BasicPizza();
        pizza = new BroccoliDecorator(pizza);
        BigDecimal ec = new BigDecimal("16.75");
        BigDecimal expectedCost = ec.setScale(2, RoundingMode.UNNECESSARY);

        //When
        BigDecimal pizzaCost = pizza.getPrice();

        //Then
        assertEquals(expectedCost , pizzaCost);
    }

    @Test
    public void testBroccoliPizzaGetContent() {
        //Given
        Pizza pizza = new BasicPizza();
        pizza = new BroccoliDecorator(pizza);

        //When
        String pizzaContent = pizza.getContent();

        //Then
        assertEquals(pizzaContent, "I want order pizza with thick cake + tomato sausage + cheese + broccoli");
    }
    @Test
    public void testMushroomsPizzaGetCost() {
        //Given
        Pizza pizza = new BasicPizza();
        pizza = new MushroomsDecorator(pizza);
        BigDecimal ec = new BigDecimal("20.25");
        BigDecimal expectedCost = ec.setScale(2, RoundingMode.UNNECESSARY);

        //When
        BigDecimal pizzaCost = pizza.getPrice();

        //Then
        assertEquals(expectedCost , pizzaCost);
    }

    @Test
    public void testMushroomsPizzaGetContent() {
        //Given
        Pizza pizza = new BasicPizza();
        pizza = new MushroomsDecorator(pizza);

        //When
        String pizzaContent = pizza.getContent();

        //Then
        assertEquals(pizzaContent, "I want order pizza with thick cake + tomato sausage + cheese + mushrooms");
    }
    @Test
    public void testHawaiiPizzaWithDoubleMushroomsGetCost() {
        //Given
        Pizza pizza = new BasicPizza();
        pizza = new HawaiiPizzaDecorator(pizza);
        pizza = new MushroomsDecorator(pizza);
        pizza = new MushroomsDecorator(pizza);
        BigDecimal ec = new BigDecimal("71.49");
        BigDecimal expectedCost = ec.setScale(2, RoundingMode.UNNECESSARY);

        //When
        BigDecimal pizzaCost = pizza.getPrice();

        //Then
        assertEquals(expectedCost , pizzaCost);
    }

    @Test
    public void testHawaiiPizzaWithDoubleMushroomsGetContent() {
        //Given
        Pizza pizza = new BasicPizza();
        pizza = new HawaiiPizzaDecorator(pizza);
        pizza = new MushroomsDecorator(pizza);
        pizza = new MushroomsDecorator(pizza);

        //When
        String pizzaContent = pizza.getContent();

        //Then
        assertEquals(pizzaContent, "I want order pizza with thick cake + tomato sausage + cheese + mozzarella cheese  + pineapple + mango + shrimp + oregano + mushrooms + mushrooms");
    }
}
