package com.example.homework5.calculator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Calculator implements Subject<String> {

    private int undoRedoPointer = -1;
    private final Stack<Command> commandStack = new Stack<>();

    private Command currentCommand = null;

    private String operandBuilder = "";

    private Set<Observer<String>> observers = new HashSet<>();

    public void addDigit(int digit) {
        this.operandBuilder = this.operandBuilder.concat(String.valueOf(digit));
        this.updateDisplayText();
    }

    public void setNextOperation(Command command) {
        if (this.undoRedoPointer < 0 && this.currentCommand == null) {
            command.setFirstOperand(new BigDecimal(0));
            if (!this.operandBuilder.isBlank()) {
                command.setFirstOperand(new BigDecimal(this.operandBuilder));
            }
            this.operandBuilder = "";
        } else if (this.currentCommand != null
                && !this.commandStack.contains(this.currentCommand)) {
            this.terminateOperation();
            this.setNextOperation(command);
        } else if (!this.commandStack.isEmpty() && this.undoRedoPointer >= 0) {
            command.setFirstOperand(this.commandStack.get(this.undoRedoPointer).firstOperand);
        }
        this.currentCommand = command;
        this.updateDisplayText();
    }

    public void terminateOperation() {
        if (this.operandBuilder.isBlank()) {
            return;
        }
        this.currentCommand.setSecondOperand(new BigDecimal(this.operandBuilder));
        this.operandBuilder = "";

        deleteElementsAfterPointer(this.undoRedoPointer);
        this.currentCommand.execute();
        this.commandStack.push(this.currentCommand);
        this.undoRedoPointer++;
        this.updateDisplayText();
    }

    public void undo() {
        this.operandBuilder = "";
        if (this.undoRedoPointer < 0) {
            this.currentCommand = null;
            return;
        }
        this.currentCommand = this.commandStack.get(this.undoRedoPointer);
        this.currentCommand.unexecute();
        this.undoRedoPointer--;
        this.updateDisplayText();
    }

    public void redo() {
        this.operandBuilder = "";
        if (this.undoRedoPointer == this.commandStack.size() - 1) {
            return;
        }
        this.undoRedoPointer++;
        this.currentCommand = this.commandStack.get(this.undoRedoPointer);
        this.currentCommand.execute();
        this.updateDisplayText();
    }

    public void setDecimalSeparator() {
        if (!this.operandBuilder.contains(".")) {
            this.operandBuilder = this.operandBuilder.concat(".");
        }
        this.updateDisplayText();
    }

    private void deleteElementsAfterPointer(int undoRedoPointer) {
        if (this.commandStack.isEmpty()) {
            return;
        }
        if (this.commandStack.size() > undoRedoPointer + 1) {
            this.commandStack.subList(undoRedoPointer + 1, this.commandStack.size()).clear();
        }
    }

    private void updateDisplayText() {
        if (this.currentCommand == null) {
            this.notifyObservers(!this.operandBuilder.isBlank() ? this.operandBuilder : "0");
            return;
        }
        this.notifyObservers(this.currentCommand.getDisplayLabel().concat(this.operandBuilder));
    }

    @Override
    public void subscribe(Observer<String> observer) {
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer<String> observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(String displayText) {
        this.observers.forEach(observer -> observer.update(displayText));
    }
}
