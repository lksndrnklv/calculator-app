package com.example.homework5.calculator.commands;

import com.example.homework5.calculator.state.CalculatorState;

public class MemoryAddCommand extends AbstractCommand {
    @Override
    public CalculatorState execute() {
        CalculatorState newCalculatorState = this.getPreviousState();
        newCalculatorState.memoryAdd();
        return newCalculatorState;
    }

    @Override
    public String getCommandHistoryLine() {
        return "M+";
    }
}
