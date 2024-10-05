package com.crud.tasks.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class AbstractPizzaDecorator implements Pizza{

    private Pizza pizza;

    public AbstractPizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public BigDecimal getPrice() {
        return pizza.getPrice();
    }

    public String getContent() {
        return pizza.getContent();
    }
}
