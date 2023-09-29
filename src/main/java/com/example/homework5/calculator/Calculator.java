package com.example.homework5.calculator;

import java.math.BigDecimal;
import java.util.Stack;

public class Calculator {

    private int undoRedoPointer = -1;
    private final Stack<Command> commandStack = new Stack<>();

    private Command currentCommand = null;

    private String operandBuilder = "";

    public void addDigit(int digit) {
        this.operandBuilder = this.operandBuilder.concat(String.valueOf(digit));
    }

    public void setNextOperation(Command command) {
        if (this.undoRedoPointer < 0 && !this.operandBuilder.isEmpty()) {
            command.setFirstOperand(new BigDecimal(this.operandBuilder));
            this.operandBuilder = "";
        } else if (this.undoRedoPointer < 0) {
            command.setFirstOperand(this.commandStack.get(0).getFirstOperand());
        } else if (!this.commandStack.isEmpty()) {
            command.setFirstOperand(this.commandStack.get(this.undoRedoPointer).firstOperand);
        }
        this.currentCommand = command;
    }

    public void terminateOperation() {
        this.currentCommand.setSecondOperand(new BigDecimal(this.operandBuilder));
        this.operandBuilder = "";

        deleteElementsAfterPointer(this.undoRedoPointer);
        this.currentCommand.execute();
        this.commandStack.push(this.currentCommand);
        this.undoRedoPointer++;
    }

    public void undo() {
        if (this.undoRedoPointer < 0) {
            return;
        }
        this.currentCommand = this.commandStack.get(this.undoRedoPointer);
        this.currentCommand.unexecute();
        this.undoRedoPointer--;
    }

    public void redo() {
        if (this.undoRedoPointer == this.commandStack.size() - 1) {
            return;
        }
        this.undoRedoPointer++;
        this.currentCommand = this.commandStack.get(this.undoRedoPointer);
        this.currentCommand.execute();
    }

    public void setDecimalSeparator() {
        if (!this.operandBuilder.contains(".")) {
            this.operandBuilder = this.operandBuilder.concat(".");
        }
    }

    private void deleteElementsAfterPointer(int undoRedoPointer) {
        if (this.commandStack.isEmpty()) {
            return;
        }
        if (this.commandStack.size() > undoRedoPointer + 1) {
            this.commandStack.subList(undoRedoPointer + 1, this.commandStack.size()).clear();
        }
    }

    public String getDisplayLabel() {
        if (this.currentCommand == null) {
            return this.operandBuilder;
        }
        return this.currentCommand.getDisplayLabel().concat(this.operandBuilder);
    }
}
