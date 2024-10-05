package com.crud.tasks.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class BasicPizza implements Pizza {

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.valueOf(15);
    }

    @Override
    public String getContent() {
        return "I want order pizza with thick cake + tomato sausage + cheese";
    }
}
