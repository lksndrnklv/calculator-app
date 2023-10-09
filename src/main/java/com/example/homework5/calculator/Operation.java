package com.example.homework5.calculator;

import java.math.BigDecimal;

public enum Operation {
    ADDITION {
        @Override
        public BigDecimal terminateOperation(BigDecimal firstOperand, BigDecimal secondOperand) {
            return firstOperand.add(secondOperand);
        }

        @Override
        public String getOperationSymbol() {
            return "+";
        }
    },
    SUBTRACTION {
        @Override
        public BigDecimal terminateOperation(BigDecimal firstOperand, BigDecimal secondOperand) {
            return firstOperand.subtract(secondOperand);
        }

        @Override
        public String getOperationSymbol() {
            return "-";
        }
    },
    MULTIPLICATION {
        @Override
        public BigDecimal terminateOperation(BigDecimal firstOperand, BigDecimal secondOperand) {
            return firstOperand.multiply(secondOperand);
        }

        @Override
        public String getOperationSymbol() {
            return "×";
        }
    },
    DIVISION {
        @Override
        public BigDecimal terminateOperation(BigDecimal firstOperand, BigDecimal secondOperand) {
            return firstOperand.divide(secondOperand);
        }

        @Override
        public String getOperationSymbol() {
            return "÷";
        }
    };

    public abstract BigDecimal terminateOperation(BigDecimal firstOperand, BigDecimal secondOperand);

    public abstract String getOperationSymbol();
}
