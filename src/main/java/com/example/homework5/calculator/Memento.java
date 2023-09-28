package com.example.homework5.calculator;

import javafx.util.Pair;

import java.math.BigDecimal;

public class Memento {
    private BigDecimal firstOperator;
    private BigDecimal secondOperator;

    public Pair<BigDecimal, BigDecimal> getState() {
        return new Pair<>(firstOperator, secondOperator);
    }

    public void setState(BigDecimal firstOperator, BigDecimal secondOperator) {
        this.firstOperator = firstOperator;
        this.secondOperator = secondOperator;
    }
}
