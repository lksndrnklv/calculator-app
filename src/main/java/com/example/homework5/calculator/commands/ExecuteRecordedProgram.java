package com.example.homework5.calculator.commands;

import com.example.homework5.calculator.state.CalculatorState;

public class ExecuteRecordedProgram extends AbstractCommand {
    @Override
    public CalculatorState execute() {
        CalculatorState newCalculatorState = this.getPreviousState();
        newCalculatorState.executeRecordedProgram();
        return newCalculatorState;
    }

    @Override
    public String getCommandHistoryLine() {
        return "RCL";
    }
}
