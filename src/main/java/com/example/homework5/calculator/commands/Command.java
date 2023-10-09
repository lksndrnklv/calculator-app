package com.example.homework5.calculator.commands;

import com.example.homework5.calculator.CalculatorMemento;
import com.example.homework5.calculator.state.CalculatorState;

public interface Command {
    CalculatorState execute();

    void setMemento(CalculatorMemento calculatorMemento);

    CalculatorState getPreviousState();

    String getCommandHistoryLine();
}
