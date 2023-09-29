package com.example.homework5.calculator;

import javafx.util.Pair;

import java.math.BigDecimal;

public abstract class Command {
    protected BigDecimal firstOperand = new BigDecimal(0);
    protected BigDecimal secondOperand;
    protected Memento memento;
    public abstract void execute();
    public abstract String getDisplayLabel();

    public void unexecute() {
        Pair<BigDecimal, BigDecimal> operands = memento.getState();
        this.firstOperand = operands.getKey();
        this.secondOperand = operands.getValue();
    }
    public BigDecimal getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(BigDecimal firstOperand) {
        this.firstOperand = firstOperand;
    }

    public void setSecondOperand(BigDecimal secondOperand) {
        this.secondOperand = secondOperand;
    }
}
