package com.example.homework5.calculator;

public class AdditionCommand extends Command {

    @Override
    public void execute() {
        this.memento = new Memento();
        this.memento.setState(this.firstOperand, this.secondOperand);
        this.firstOperand = this.firstOperand.add(secondOperand);
        this.secondOperand = null;
    }

    @Override
    public String getDisplayLabel() {
        if (this.memento == null) {
            return this.firstOperand.stripTrailingZeros().toPlainString().concat("+");
        }
        return this.firstOperand.stripTrailingZeros().toPlainString();
    }
}
