package com.example.homework5.calculator.commands;

import com.example.homework5.calculator.state.CalculatorState;

public class AddDigitCommand extends AbstractCommand {
    private final int digit;

    public AddDigitCommand(int digit) {
        this.digit = digit;
    }

    @Override
    public CalculatorState execute() {
        CalculatorState newCalculatorState = this.getPreviousState();
        newCalculatorState.addDigit(this.digit);
        return newCalculatorState;
    }

    @Override
    public String getCommandHistoryLine() {
        return String.valueOf(digit);
    }
}
