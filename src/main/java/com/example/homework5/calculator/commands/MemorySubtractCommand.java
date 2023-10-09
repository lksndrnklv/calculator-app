package com.example.homework5.calculator.commands;

import com.example.homework5.calculator.state.CalculatorState;

public class MemorySubtractCommand extends AbstractCommand {
    @Override
    public CalculatorState execute() {
        CalculatorState newCalculatorState = this.getPreviousState();
        newCalculatorState.memorySubtract();
        return newCalculatorState;
    }

    @Override
    public String getCommandHistoryLine() {
        return "M-";
    }
}
