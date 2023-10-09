package com.example.homework5.calculator;

import com.example.homework5.calculator.commands.Command;
import com.example.homework5.calculator.commands.SetOperationCommand;
import com.example.homework5.calculator.commands.TerminateExpressionCommand;
import com.example.homework5.calculator.state.CalculatorState;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator implements Subject<UIState> {
    private final Stack<Command> commandStack = new Stack<>();
    private final Set<Observer<UIState>> observers = new HashSet<>();
    private CalculatorState currentState = new CalculatorState();
    private int undoRedoPointer = -1;

    public void executeCommand(Command command) {
        if ((command instanceof SetOperationCommand)
                && this.currentState.isNotTerminated()) {
            executeCommand(new TerminateExpressionCommand());
        }
        command.setMemento(new CalculatorMemento(this.currentState));
        this.currentState = command.execute();
        deleteElementsAfterPointer(this.undoRedoPointer);
        commandStack.push(command);
        this.undoRedoPointer++;
        notifyObservers();
    }

    public void undo() {
        this.currentState = this.commandStack.get(this.undoRedoPointer).getPreviousState();
        this.undoRedoPointer--;
        this.notifyObservers();
    }

    public void redo() {
        this.undoRedoPointer++;
        this.currentState = this.commandStack.get(this.undoRedoPointer).execute();
        this.notifyObservers();
    }

    @Override
    public void subscribe(Observer<UIState> observer) {
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer<UIState> observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        UIState uiState = this.currentState.getUIState();
        uiState.setUndoButtonActive(this.undoRedoPointer >= 0);
        uiState.setRedoButtonActive(this.undoRedoPointer < this.commandStack.size() - 1);
        uiState.setExpressionHistory(this.commandStack.stream().map(Command::getCommandHistoryLine).collect(Collectors.toList()));
        this.observers.forEach(observer -> observer.update(uiState));
    }

    private void deleteElementsAfterPointer(int undoRedoPointer) {
        if (this.commandStack.isEmpty()) {
            return;
        }
        if (this.commandStack.size() > undoRedoPointer + 1) {
            this.commandStack.subList(undoRedoPointer + 1, this.commandStack.size()).clear();
        }
    }
}
