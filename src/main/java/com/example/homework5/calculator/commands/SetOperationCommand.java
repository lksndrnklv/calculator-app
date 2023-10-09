package com.example.homework5.calculator.commands;

import com.example.homework5.calculator.Operation;
import com.example.homework5.calculator.state.CalculatorState;

public class SetOperationCommand extends AbstractCommand {
    private final Operation operation;

    public SetOperationCommand(Operation operation) {
        this.operation = operation;
    }

    @Override
    public CalculatorState execute() {
        CalculatorState newCalculatorState = this.getPreviousState();
        newCalculatorState.setNextOperation(this.operation);
        return newCalculatorState;
    }

    @Override
    public String getCommandHistoryLine() {
        return this.operation.getOperationSymbol();
    }
}
