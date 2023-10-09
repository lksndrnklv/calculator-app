package com.example.homework5.calculator.commands;

import com.example.homework5.calculator.state.CalculatorState;

public class MemoryClearCommand extends AbstractCommand {
    @Override
    public CalculatorState execute() {
        CalculatorState newCalculatorState = this.getPreviousState();
        newCalculatorState.memoryClear();
        return newCalculatorState;
    }

    @Override
    public String getCommandHistoryLine() {
        return "MC";
    }
}
