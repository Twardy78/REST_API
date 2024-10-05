package com.crud.tasks.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class SalamiDecorator extends AbstractPizzaDecorator{

    private Pizza pizza;

    public SalamiDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal("2.55"));
    }

    @Override
    public String getContent() {
        return super.getContent() + " + salami";
    }
}
