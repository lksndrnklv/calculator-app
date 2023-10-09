package com.example.homework5.calculator.state;

import com.example.homework5.calculator.Expression;

import java.math.BigDecimal;
import java.util.Stack;

public class Program {

    private final Stack<Expression> expressionStack = new Stack<>();

    public void clearProgram() {
        this.expressionStack.clear();
    }

    public void setSecondOperand(BigDecimal bigDecimal) {
        this.expressionStack.peek().setSecondOperand(bigDecimal);
    }

    public void addExpression(Expression expression) {
        expression.setFirstOperand(null);
        this.expressionStack.push(expression);
    }

    public String getMainDisplayText() {
        return !this.expressionStack.isEmpty() ? this.expressionStack.peek().getMainDisplayText() : "x";
    }

    public String getSecondaryDisplayText() {
        return this.expressionStack.isEmpty() ? "" : this.expressionStack.firstElement().getSecondaryDisplayText();
    }

    public Expression executeProgram(BigDecimal bigDecimal) {
        for (Expression expression : expressionStack) {
            expression.setFirstOperand(bigDecimal);
            bigDecimal = expression.evaluateExpression();
        }
        Expression expressionResult = new Expression(this.expressionStack.lastElement().getFirstOperand(), this.expressionStack.lastElement().getOperation(), this.expressionStack.firstElement().getSecondOperand());
        this.expressionStack.forEach(x -> x.setFirstOperand(null));
        return expressionResult;
    }

    public boolean hasRecordedProgram() {
        return !this.expressionStack.isEmpty();
    }
}
