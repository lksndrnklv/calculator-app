package com.example.homework5;

import com.example.homework5.calculator.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {

    private final Calculator calculator = new Calculator();

    @FXML
    public void onAddDigit(ActionEvent event) {
        Button button = (Button) event.getSource();
        this.calculator.addDigit(Integer.parseInt(button.getText()));
    }

    @FXML
    public void onSetDecimalSeparator() {
        this.calculator.setDecimalSeparator();
    }

    @FXML
    public void onAdd() {
        this.calculator.setNextOperation(new AdditionCommand());
    }

    @FXML
    public void onSubtract() {
        this.calculator.setNextOperation(new SubtractionCommand());
    }

    @FXML
    public void onMultiply() {
        this.calculator.setNextOperation(new MultiplicationCommand());
    }

    @FXML
    public void onDivide() {
        this.calculator.setNextOperation(new DivisionCommand());
    }

    @FXML
    public void onEquals() {
        this.calculator.terminateOperation();
    }

    @FXML
    public void onUndo() {
        this.calculator.undo();
    }

    @FXML
    public void onRedo() {
        this.calculator.redo();
    }

}
