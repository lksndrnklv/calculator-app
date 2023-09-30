package com.example.homework5;

import com.example.homework5.calculator.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController implements Observer<LabelState> {

    @FXML
    private Label displayLabel;
    @FXML
    private Label memoryLabel;
    private final Calculator calculator = new Calculator();

    public HelloController() {
        this.calculator.subscribe(this);
    }

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

    @FXML
    public void onMemoryStore() {
        this.calculator.onMemoryStore();
    }

    @Override
    public void update(LabelState labelState) {
        this.displayLabel.setText(labelState.getDisplayLabel());
        this.memoryLabel.setText(labelState.getMemoryLabel());
    }
}
