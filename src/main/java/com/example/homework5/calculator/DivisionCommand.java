package com.example.homework5.calculator;

public class DivisionCommand extends Command {

    @Override
    public void execute() {
        this.memento = new Memento();
        this.memento.setState(this.firstOperand, this.secondOperand);
        this.firstOperand = this.firstOperand.divide(secondOperand);
        this.secondOperand = null;
    }
}
