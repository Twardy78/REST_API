package com.crud.tasks.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class MushroomsDecorator extends AbstractPizzaDecorator{

    private Pizza pizza;

    public MushroomsDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal("5.25"));
    }

    @Override
    public String getContent() {
        return super.getContent() + " + mushrooms";
    }
}
