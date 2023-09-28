package com.example.homework5.calculator;

import java.math.BigDecimal;
import java.util.Stack;

public class Calculator {

    private int undoRedoPointer = -1;
    private final Stack<Command> commandStack = new Stack<>();

    private Command currentCommand = new AdditionCommand();

    private String operandBuilder = "";

    public void addDigit(int digit) {
        this.operandBuilder = this.operandBuilder.concat(String.valueOf(digit));
    }

    public void setNextOperation(Command command) {
        if (this.commandStack.isEmpty() && !this.operandBuilder.isEmpty()) {
            command.setFirstOperand(new BigDecimal(this.operandBuilder));
            this.operandBuilder = "";
        } else if (!this.commandStack.isEmpty()) {
            command.setFirstOperand(this.currentCommand.firstOperand);
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

        System.out.println(this.commandStack.peek().getFirstOperand());
    }

    public void undo() {
        if (this.undoRedoPointer < 0) {
            return;
        }
        this.currentCommand = this.commandStack.get(this.undoRedoPointer);
        this.currentCommand.unexecute();
        this.undoRedoPointer--;
        System.out.println(this.commandStack.get(this.undoRedoPointer + 1).getFirstOperand());
    }

    public void redo() {
        if (this.undoRedoPointer == this.commandStack.size() - 1) {
            return;
        }
        this.undoRedoPointer++;
        this.currentCommand = this.commandStack.get(this.undoRedoPointer);
        this.currentCommand.execute();
        System.out.println(this.commandStack.get(this.undoRedoPointer).getFirstOperand());
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
}
