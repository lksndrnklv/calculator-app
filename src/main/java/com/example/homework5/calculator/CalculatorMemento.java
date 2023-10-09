package com.example.homework5.calculator;

import com.example.homework5.calculator.state.CalculatorState;

public class CalculatorMemento {
    private final CalculatorState state;

    public CalculatorMemento(CalculatorState state) {
        this.state = state;
    }

    public CalculatorState getState() {
        return state;
    }
}
