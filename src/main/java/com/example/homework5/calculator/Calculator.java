package com.example.homework5.calculator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator implements Subject<LabelState> {

    private int undoRedoPointer = -1;
    private final Stack<Expression> expressionStack = new Stack<>();
    private final Stack<Expression> PROGExpressionStack = new Stack<>();

    private Expression currentExpression = null;

    private String operandBuilder = "";

    private final Set<Observer<LabelState>> observers = new HashSet<>();

    private BigDecimal storedMemory = new BigDecimal(0);
    private boolean isMemoryReadState = false;
    private boolean isProgModeActive = false;

    public void addDigit(int digit) {
        if (this.expressionStack.contains(this.currentExpression)) {
            return;
        }
        if (this.operandBuilder.length() == 1 && this.operandBuilder.equals("0")) {
            return;
        }
        this.operandBuilder = this.operandBuilder.concat(String.valueOf(digit));
        this.notifyObservers();
    }

    public void setNextOperation(Expression expression) {
        if (this.isMemoryReadState) {
            expression.setFirstOperand(new BigDecimal(this.operandBuilder));
            this.operandBuilder = "";
            this.isMemoryReadState = false;
        } else if (this.undoRedoPointer < 0 && this.currentExpression == null) {
            expression.setFirstOperand(new BigDecimal(0));
            if (!this.operandBuilder.isBlank()) {
                expression.setFirstOperand(new BigDecimal(this.operandBuilder));
            }
            this.operandBuilder = "";
        } else if (this.currentExpression != null
                && !this.expressionStack.contains(this.currentExpression)) {
            this.terminateExpression();
            this.setNextOperation(expression);
        } else if (!this.expressionStack.isEmpty() && this.undoRedoPointer >= 0) {
            expression.setFirstOperand(this.expressionStack.get(this.undoRedoPointer).evaluateExpression());
        }
        this.currentExpression = expression;
        this.notifyObservers();
    }

    public void terminateExpression() {
        if (this.operandBuilder.isBlank()) {
            return;
        }
        this.currentExpression.setSecondOperand(new BigDecimal(this.operandBuilder));
        this.operandBuilder = "";

        deleteElementsAfterPointer(this.undoRedoPointer);
        this.expressionStack.push(this.currentExpression);
        this.undoRedoPointer++;
        this.notifyObservers();
    }

    public void undo() {
        this.operandBuilder = "";
        if (this.isMemoryReadState) {
            this.isMemoryReadState = false;
            this.operandBuilder = "";
        } else if (this.undoRedoPointer <= 0) {
            this.currentExpression = null;
            this.undoRedoPointer = -1;
        } else {
            this.undoRedoPointer--;
            this.currentExpression = this.expressionStack.get(this.undoRedoPointer);
        }
        this.notifyObservers();
    }

    public void redo() {
        this.operandBuilder = "";
        if (this.undoRedoPointer == this.expressionStack.size() - 1) {
            return;
        }
        this.undoRedoPointer++;
        this.currentExpression = this.expressionStack.get(this.undoRedoPointer);
        this.notifyObservers();
    }

    public void setDecimalSeparator() {
        if (!this.operandBuilder.contains(".")) {
            this.operandBuilder = this.operandBuilder.concat(".");
        } else if(this.operandBuilder.isBlank()) {
            this.operandBuilder = this.operandBuilder.concat("0.");
        }
        this.notifyObservers();
    }

    private void deleteElementsAfterPointer(int undoRedoPointer) {
        if (this.expressionStack.isEmpty()) {
            return;
        }
        if (this.expressionStack.size() > undoRedoPointer + 1) {
            this.expressionStack.subList(undoRedoPointer + 1, this.expressionStack.size()).clear();
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
        final String mainDisplayText;
        if (this.isMemoryReadState) {
            mainDisplayText = this.storedMemory.stripTrailingZeros().toPlainString();
        } else if (this.currentExpression != null) {
            mainDisplayText = this.currentExpression.getMainDisplayText().concat(this.operandBuilder);
        } else {
            mainDisplayText = !this.operandBuilder.isBlank() ? this.operandBuilder : "0";
        }
        final String secondaryDisplayText;
        if (this.undoRedoPointer >= 0) {
            secondaryDisplayText = this.expressionStack.get(this.undoRedoPointer).getSecondaryDisplayText();
        } else {
            secondaryDisplayText = "";
        }
        List<String> expressionHistory = this.expressionStack.stream().map(Expression::getSecondaryDisplayText).collect(Collectors.toList());
        this.observers.forEach(observer -> observer.update(new LabelState(mainDisplayText, secondaryDisplayText, this.storedMemory.stripTrailingZeros().toPlainString(), expressionHistory)));
    }

    public void memoryStore() {
        if (this.currentExpression != null) {
            this.storedMemory = this.currentExpression.evaluateExpression();
        } else if (!this.operandBuilder.isBlank()) {
            this.storedMemory = new BigDecimal(this.operandBuilder);
        } else {
            this.storedMemory = new BigDecimal(0);
        }
        this.notifyObservers();
    }

    public void memoryAdd() {
        if (this.currentExpression != null) {
            this.storedMemory = this.storedMemory.add(this.currentExpression.evaluateExpression());
        } else if (!this.operandBuilder.isBlank()) {
            this.storedMemory = this.storedMemory.add(new BigDecimal(this.operandBuilder));
        }
        this.notifyObservers();
    }

    public void memorySubtract() {
        if (this.currentExpression != null) {
            this.storedMemory = this.storedMemory.subtract(this.currentExpression.evaluateExpression());
        } else if (!this.operandBuilder.isBlank()) {
            this.storedMemory = this.storedMemory.subtract(new BigDecimal(this.operandBuilder));
        }
        this.notifyObservers();
    }

    public void memoryClear() {
        this.storedMemory = new BigDecimal(0);
        this.notifyObservers();
    }

    public void memoryRead() {
        this.isMemoryReadState = true;
        this.operandBuilder = this.storedMemory.stripTrailingZeros().toPlainString();
        this.notifyObservers();
    }

    public void setPROGMode(boolean isProgModeActive) {
        this.isProgModeActive = isProgModeActive;
    }
}
