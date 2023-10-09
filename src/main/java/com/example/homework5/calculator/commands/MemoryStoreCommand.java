package com.example.homework5.calculator.commands;

import com.example.homework5.calculator.state.CalculatorState;

public class MemoryStoreCommand extends AbstractCommand {
    @Override
    public CalculatorState execute() {
        CalculatorState newCalculatorState = this.getPreviousState();
        newCalculatorState.memoryStore();
        return newCalculatorState;
    }

    @Override
    public String getCommandHistoryLine() {
        return "MS";
    }
}
