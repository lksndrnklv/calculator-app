package com.example.homework5.calculator.state;

import com.example.homework5.calculator.Expression;
import com.example.homework5.calculator.Operation;
import com.example.homework5.calculator.UIState;

import java.math.BigDecimal;

public class CalculatorState implements Cloneable {
    private Expression currentExpression = null;
    private String operandBuilder = "";
    private BigDecimal storedMemory = new BigDecimal(0);
    private boolean isProgModeActive = false;
    private Program recordedProgram = new Program();

    public CalculatorState() {
    }

    public CalculatorState(Expression currentExpression, String operandBuilder, BigDecimal storedMemory, boolean isProgModeActive, Program recordedProgram) {
        this.currentExpression = currentExpression;
        this.operandBuilder = operandBuilder;
        this.storedMemory = storedMemory;
        this.isProgModeActive = isProgModeActive;
        this.recordedProgram = recordedProgram;
    }

    public void addDigit(int digit) {
        if (this.operandBuilder.length() == 1 && this.operandBuilder.equals("0")) {
            this.operandBuilder = "";
        }
        this.operandBuilder = this.operandBuilder.concat(String.valueOf(digit));
    }

    public void addDecimalSeparator() {
        if (this.operandBuilder.isBlank()) {
            this.operandBuilder = this.operandBuilder.concat("0.");
        } else if (!this.operandBuilder.contains(".")) {
            this.operandBuilder = this.operandBuilder.concat(".");
        }
    }

    public void terminateExpression() {
        this.currentExpression.setSecondOperand(new BigDecimal(this.operandBuilder));
        this.operandBuilder = "";
    }

    public void memoryClear() {
        this.storedMemory = new BigDecimal(0);
    }

    public void memoryStore() {
        if (this.currentExpression != null) {
            this.storedMemory = this.currentExpression.evaluateExpression();
        } else if (!this.operandBuilder.isBlank()) {
            this.storedMemory = new BigDecimal(this.operandBuilder);
        } else {
            this.storedMemory = new BigDecimal(0);
        }
    }

    public void memoryAdd() {
        if (this.currentExpression != null) {
            this.storedMemory = this.storedMemory.add(this.currentExpression.evaluateExpression());
        } else if (!this.operandBuilder.isBlank()) {
            this.storedMemory = this.storedMemory.add(new BigDecimal(this.operandBuilder));
        }
    }

    public void memorySubtract() {
        if (this.currentExpression != null) {
            this.storedMemory = this.storedMemory.subtract(this.currentExpression.evaluateExpression());
        } else if (!this.operandBuilder.isBlank()) {
            this.storedMemory = this.storedMemory.subtract(new BigDecimal(this.operandBuilder));
        }
    }

    public void memoryRead() {
        if (this.currentExpression.getSecondOperand() == null
                && this.currentExpression.getOperation() != null) {
            this.operandBuilder = this.storedMemory.stripTrailingZeros().toPlainString();
        } else {
            this.currentExpression.setFirstOperand(this.storedMemory);
            this.currentExpression.setOperation(null);
            this.currentExpression.setSecondOperand(null);
        }
    }

    public void togglePROGMode() {
        this.isProgModeActive = !this.isProgModeActive;
        this.operandBuilder = "";
        if (this.isProgModeActive) {
            this.recordedProgram.clearProgram();
        }
    }

    public void setNextOperation(Operation operation) {
        Expression expression = new Expression();
        expression.setOperation(operation);
        if (isProgModeActive) {
            if (!this.operandBuilder.isBlank()) {
                this.recordedProgram.setSecondOperand(new BigDecimal(this.operandBuilder));
                this.operandBuilder = "";
            }
            this.recordedProgram.addExpression(expression);
        } else if (this.currentExpression == null) {
            this.currentExpression = expression;
            if (!this.operandBuilder.isBlank()) {
                expression.setFirstOperand(new BigDecimal(this.operandBuilder));
                this.operandBuilder = "";
            }
        } else {
            this.currentExpression.setFirstOperand(this.currentExpression.evaluateExpression());
            this.currentExpression.setOperation(operation);
            this.currentExpression.setSecondOperand(null);
        }
    }

    @Override
    public CalculatorState clone() {
        return new CalculatorState(this.currentExpression != null ? this.currentExpression.clone() : null, this.operandBuilder, this.storedMemory, this.isProgModeActive, this.recordedProgram);
    }

    public UIState getUIState() {
        UIState uiState = new UIState();
        final String mainDisplayText;
        if (this.isProgModeActive) {
            mainDisplayText = this.recordedProgram.getMainDisplayText().concat(this.operandBuilder);
        } else if (this.currentExpression != null) {
            mainDisplayText = this.currentExpression.getMainDisplayText().concat(this.operandBuilder);
        } else {
            mainDisplayText = !this.operandBuilder.isBlank() ? this.operandBuilder : "0";
        }
        final String secondaryDisplayText;
        if (!isProgModeActive) {
            secondaryDisplayText = this.currentExpression != null ? this.currentExpression.getSecondaryDisplayText() : "";
        } else {
            secondaryDisplayText = this.recordedProgram.getSecondaryDisplayText();
        }
        uiState.setMainDisplayText(mainDisplayText);
        uiState.setSecondaryDisplayText(secondaryDisplayText);
        uiState.setProgMode(this.isProgModeActive);
        uiState.setMemoryText(this.storedMemory.stripTrailingZeros().toPlainString());
        uiState.setRCLButtonActive(!this.isProgModeActive && this.recordedProgram.hasRecordedProgram());
        uiState.setDecimalSeparatorButtonActive(!this.operandBuilder.contains("."));
        uiState.setEqualsButtonActive(!this.isProgModeActive);
        return uiState;
    }

    public void executeRecordedProgram() {
        BigDecimal bigDecimal = this.currentExpression != null ? this.currentExpression.evaluateExpression() : !this.operandBuilder.isBlank() ? new BigDecimal(this.operandBuilder) : new BigDecimal(0);
        this.currentExpression = this.recordedProgram.executeProgram(bigDecimal);
        this.operandBuilder = "";
    }

    public boolean isNotTerminated() {
        return !this.isProgModeActive && this.currentExpression != null && this.currentExpression.getOperation() != null && this.currentExpression.getSecondOperand() == null;
    }
}
