package com.crud.tasks.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class HawaiiPizzaDecorator extends AbstractPizzaDecorator {

    private Pizza pizza;

    public HawaiiPizzaDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal("45.99"));
    }

    @Override
    public String getContent() {
        return super.getContent() + " + mozzarella cheese  + pineapple + mango + shrimp + oregano";
    }
}
