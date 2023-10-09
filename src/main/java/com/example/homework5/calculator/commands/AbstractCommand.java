package com.example.homework5.calculator.commands;

import com.example.homework5.calculator.CalculatorMemento;
import com.example.homework5.calculator.state.CalculatorState;

public abstract class AbstractCommand implements Command {
    private CalculatorMemento calculatorMemento;

    @Override
    public final void setMemento(CalculatorMemento calculatorMemento) {
        this.calculatorMemento = calculatorMemento;
    }

    @Override
    public final CalculatorState getPreviousState() {
        return this.calculatorMemento.getState().clone();
    }
}
