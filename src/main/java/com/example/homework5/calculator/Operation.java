package com.example.homework5.calculator;

import java.math.BigDecimal;

public enum Operation {
    ADDITION {
        @Override
        BigDecimal terminateOperation(BigDecimal firstOperand, BigDecimal secondOperand) {
            return firstOperand.add(secondOperand);
        }

        @Override
        String getOperationSymbol() {
            return "+";
        }
    },
    SUBTRACTION {
        @Override
        BigDecimal terminateOperation(BigDecimal firstOperand, BigDecimal secondOperand) {
            return firstOperand.subtract(secondOperand);
        }

        @Override
        String getOperationSymbol() {
            return "-";
        }
    },
    MULTIPLICATION {
        @Override
        BigDecimal terminateOperation(BigDecimal firstOperand, BigDecimal secondOperand) {
            return firstOperand.multiply(secondOperand);
        }

        @Override
        String getOperationSymbol() {
            return "×";
        }
    },
    DIVISION {
        @Override
        BigDecimal terminateOperation(BigDecimal firstOperand, BigDecimal secondOperand) {
            return firstOperand.divide(secondOperand);
        }

        @Override
        String getOperationSymbol() {
            return "÷";
        }
    };

    abstract BigDecimal terminateOperation(BigDecimal firstOperand, BigDecimal secondOperand);
    abstract String getOperationSymbol();
}
