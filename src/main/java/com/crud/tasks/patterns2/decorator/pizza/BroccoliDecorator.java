package com.crud.tasks.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class BroccoliDecorator extends AbstractPizzaDecorator{

    private Pizza pizza;

    public BroccoliDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal("1.75"));
    }

    @Override
    public String getContent() {
        return super.getContent() + " + broccoli";
    }

}
