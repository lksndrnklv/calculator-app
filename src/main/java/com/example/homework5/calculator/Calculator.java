package com.example.homework5.calculator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Calculator implements Subject<LabelState> {

    private int undoRedoPointer = -1;
    private final Stack<Command> commandStack = new Stack<>();

    private Command currentCommand = null;

    private String operandBuilder = "";

    private final Set<Observer<LabelState>> observers = new HashSet<>();

    private BigDecimal storedMemory = new BigDecimal(0);

    public void addDigit(int digit) {
        this.operandBuilder = this.operandBuilder.concat(String.valueOf(digit));
        this.notifyObservers();
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
        this.notifyObservers();
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
        this.notifyObservers();
    }

    public void undo() {
        this.operandBuilder = "";
        if (this.undoRedoPointer < 0) {
            this.currentCommand = null;
            this.notifyObservers();
            return;
        }
        this.currentCommand = this.commandStack.get(this.undoRedoPointer);
        this.currentCommand.unexecute();
        this.undoRedoPointer--;
        this.notifyObservers();
    }

    public void redo() {
        this.operandBuilder = "";
        if (this.undoRedoPointer == this.commandStack.size() - 1) {
            return;
        }
        this.undoRedoPointer++;
        this.currentCommand = this.commandStack.get(this.undoRedoPointer);
        this.currentCommand.execute();
        this.notifyObservers();
    }

    public void setDecimalSeparator() {
        if (!this.operandBuilder.contains(".")) {
            this.operandBuilder = this.operandBuilder.concat(".");
        }
        this.notifyObservers();
    }

    private void deleteElementsAfterPointer(int undoRedoPointer) {
        if (this.commandStack.isEmpty()) {
            return;
        }
        if (this.commandStack.size() > undoRedoPointer + 1) {
            this.commandStack.subList(undoRedoPointer + 1, this.commandStack.size()).clear();
        }
    }

    @Override
    public void subscribe(Observer<LabelState> observer) {
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer<LabelState> observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        final String displayText;
        if (this.currentCommand == null) {
            displayText = !this.operandBuilder.isBlank() ? this.operandBuilder : "0";
        } else {
            displayText = this.currentCommand.getDisplayLabel().concat(this.operandBuilder);
        }
        this.observers.forEach(observer -> observer.update(new LabelState(displayText, this.storedMemory.stripTrailingZeros().toPlainString())));
    }

    public void onMemoryStore() {
        if (this.currentCommand != null) {
            this.storedMemory = this.currentCommand.firstOperand;
        } else if (!this.operandBuilder.isBlank()) {
            this.storedMemory = new BigDecimal(this.operandBuilder);
        } else {
            this.storedMemory = new BigDecimal(0);
        }
        this.notifyObservers();
    }

    public void onMemoryAdd() {
        if (this.currentCommand != null) {
            this.storedMemory = this.storedMemory.add(this.currentCommand.firstOperand);
            this.notifyObservers();
        }
    }

    public void onMemorySubtract() {
        if (this.undoRedoPointer >= 0) {
            this.storedMemory = this.storedMemory.subtract(this.currentCommand.firstOperand);
            this.notifyObservers();
        }
    }

    public void onMemoryClear() {
        this.storedMemory = new BigDecimal(0);
        this.notifyObservers();
    }
}
