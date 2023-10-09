package com.example.homework5.calculator.commands;

import com.example.homework5.calculator.state.CalculatorState;

public class TerminateExpressionCommand extends AbstractCommand {
    @Override
    public CalculatorState execute() {
        CalculatorState newCalculatorState = this.getPreviousState();
        newCalculatorState.terminateExpression();
        return newCalculatorState;
    }

    @Override
    public String getCommandHistoryLine() {
        return "=";
    }
}
