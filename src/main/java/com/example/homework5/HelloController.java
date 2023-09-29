package com.example.homework5;

import com.example.homework5.calculator.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label label;
    private final Calculator calculator = new Calculator();

    @FXML
    public void onAddDigit(ActionEvent event) {
        Button button = (Button) event.getSource();
        this.calculator.addDigit(Integer.parseInt(button.getText()));
        this.refreshLabel();
    }

    @FXML
    public void onSetDecimalSeparator() {
        this.calculator.setDecimalSeparator();
        this.refreshLabel();
    }

    @FXML
    public void onAdd() {
        this.calculator.setNextOperation(new AdditionCommand());
        this.refreshLabel();
    }

    @FXML
    public void onSubtract() {
        this.calculator.setNextOperation(new SubtractionCommand());
        this.refreshLabel();
    }

    @FXML
    public void onMultiply() {
        this.calculator.setNextOperation(new MultiplicationCommand());
        this.refreshLabel();
    }

    @FXML
    public void onDivide() {
        this.calculator.setNextOperation(new DivisionCommand());
        this.refreshLabel();
    }

    @FXML
    public void onEquals() {
        this.calculator.terminateOperation();
        this.refreshLabel();
    }

    @FXML
    public void onUndo() {
        this.calculator.undo();
        this.refreshLabel();
    }

    @FXML
    public void onRedo() {
        this.calculator.redo();
        this.refreshLabel();
    }

    private void refreshLabel() {
        this.label.setText(this.calculator.getDisplayLabel());
    }
}
