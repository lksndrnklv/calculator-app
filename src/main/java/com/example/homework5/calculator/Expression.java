package com.example.homework5.calculator;

import java.math.BigDecimal;

public class Expression {
    private BigDecimal firstOperand = new BigDecimal(0);

    private Operation operation;
    private BigDecimal secondOperand;

    public Expression(Operation operation) {
        this.operation = operation;
    }

    public BigDecimal getFirstOperand() {
        return firstOperand;
    }

    public Operation getOperation() {
        return operation;
    }

    public BigDecimal getSecondOperand() {
        return secondOperand;
    }

    public void setFirstOperand(BigDecimal firstOperand) {
        this.firstOperand = firstOperand;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setSecondOperand(BigDecimal secondOperand) {
        this.secondOperand = secondOperand;
    }

    public BigDecimal evaluateExpression() {
        if (this.secondOperand == null) {
            return this.firstOperand;
        }
        return this.operation.terminateOperation(this.firstOperand, this.secondOperand);
    }

    public String getSecondaryDisplayText() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.firstOperand == null ? "x" : this.firstOperand.stripTrailingZeros().toPlainString());
        if (this.operation != null) {
            sb.append(this.operation.getOperationSymbol());
            if (this.secondOperand != null) {
                sb.append(this.secondOperand.stripTrailingZeros().toPlainString());
                if (this.firstOperand != null) {
                    sb.append("=");
                    sb.append(this.evaluateExpression());
                }
            }
        }
        return sb.toString();
    }

    public String getMainDisplayText() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.firstOperand == null ? "x" : this.firstOperand.stripTrailingZeros().toPlainString());
        if (this.operation != null) {
            sb.append(this.operation.getOperationSymbol());
            if (this.secondOperand != null) {
                if (this.firstOperand == null) {
                    sb.append(this.secondOperand.stripTrailingZeros().toPlainString());
                } else {
                    return this.evaluateExpression().stripTrailingZeros().toPlainString();
                }
            }
        }
        return sb.toString();
    }
}
