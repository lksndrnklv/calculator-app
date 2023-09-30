package com.example.homework5;

import com.example.homework5.calculator.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.*;

import java.util.stream.Collectors;

public class HelloController implements Observer<LabelState> {

    @FXML
    private Label mainDisplayLabel;
    @FXML
    private Label secondaryDisplayLabel;
    @FXML
    private Label memoryLabel;
    @FXML
    private Button openMenuButton;
    @FXML
    private ToggleButton PROGModeBtn;
    private final Calculator calculator = new Calculator();

    public HelloController() {
        this.calculator.subscribe(this);
    }

    @FXML
    private void initialize() {
        ContextMenu contextMenu = new ContextMenu();
        this.openMenuButton.setContextMenu(contextMenu);
        this.openMenuButton.setDisable(true);
    }

    @FXML
    private void openMenuAction() {
        this.openMenuButton.getContextMenu().show(openMenuButton, Side.BOTTOM, 0, 0);
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
        this.calculator.setNextOperation(new Expression(Operation.ADDITION));
    }

    @FXML
    public void onSubtract() {
        this.calculator.setNextOperation(new Expression(Operation.SUBTRACTION));
    }

    @FXML
    public void onMultiply() {
        this.calculator.setNextOperation(new Expression(Operation.MULTIPLICATION));
    }

    @FXML
    public void onDivide() {
        this.calculator.setNextOperation(new Expression(Operation.DIVISION));
    }

    @FXML
    public void onEquals() {
        this.calculator.terminateExpression();
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
        this.calculator.memoryStore();
    }

    @FXML
    public void onMemoryAdd() {
        this.calculator.memoryAdd();
    }

    @FXML
    public void onMemorySubtract() {
        this.calculator.memorySubtract();
    }

    @FXML
    public void onMemoryClear() {
        this.calculator.memoryClear();
    }

    @FXML
    public void onMemoryRead() {
        this.calculator.memoryRead();
    }

    @FXML
    public void togglePROGMode() {
        this.calculator.setPROGMode(this.PROGModeBtn.isSelected());
    }

    @Override
    public void update(LabelState labelState) {
        this.mainDisplayLabel.setText(labelState.getMainDisplayText());
        this.secondaryDisplayLabel.setText(labelState.getSecondaryDisplayText());
        this.memoryLabel.setText(labelState.getMemoryText());
        this.openMenuButton.getContextMenu().getItems().clear();
        this.openMenuButton.getContextMenu().getItems().addAll(labelState.getExpressionHistory().stream().map(MenuItem::new).collect(Collectors.toList()));
        this.openMenuButton.setDisable(this.openMenuButton.getContextMenu().getItems().isEmpty());
    }
}
