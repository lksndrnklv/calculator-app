package com.example.homework5;

import com.example.homework5.calculator.Calculator;
import com.example.homework5.calculator.Observer;
import com.example.homework5.calculator.Operation;
import com.example.homework5.calculator.UIState;
import com.example.homework5.calculator.commands.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.*;

import java.util.stream.Collectors;

public class HelloController implements Observer<UIState> {

    private final Calculator calculator = new Calculator();
    @FXML
    public Button decimalSeparatorBtn;
    @FXML
    public Button redoBtn;
    @FXML
    public Button undoBtn;
    @FXML
    public Button equalsBtn;
    @FXML
    private Label mainDisplayLabel;
    @FXML
    private Label secondaryDisplayLabel;
    @FXML
    private Label memoryLabel;
    @FXML
    private Button openMenuButton;
    @FXML
    private Button RCLBtn;
    @FXML
    private ToggleButton PROGModeBtn;

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
        this.calculator.executeCommand(new AddDigitCommand(Integer.parseInt(button.getText())));
    }

    @FXML
    public void onSetDecimalSeparator() {
        this.calculator.executeCommand(new AddDecimalSeparatorCommand());
    }

    @FXML
    public void onAdd() {
        this.calculator.executeCommand(new SetOperationCommand(Operation.ADDITION));
    }

    @FXML
    public void onSubtract() {
        this.calculator.executeCommand(new SetOperationCommand(Operation.SUBTRACTION));
    }

    @FXML
    public void onMultiply() {
        this.calculator.executeCommand(new SetOperationCommand(Operation.MULTIPLICATION));
    }

    @FXML
    public void onDivide() {
        this.calculator.executeCommand(new SetOperationCommand(Operation.DIVISION));
    }

    @FXML
    public void onEquals() {
        this.calculator.executeCommand(new TerminateExpressionCommand());
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
        this.calculator.executeCommand(new MemoryStoreCommand());
    }

    @FXML
    public void onMemoryAdd() {
        this.calculator.executeCommand(new MemoryAddCommand());
    }

    @FXML
    public void onMemorySubtract() {
        this.calculator.executeCommand(new MemorySubtractCommand());
    }

    @FXML
    public void onMemoryClear() {
        this.calculator.executeCommand(new MemoryClearCommand());
    }

    @FXML
    public void onMemoryRead() {
        this.calculator.executeCommand(new MemoryReadCommand());
    }

    @FXML
    public void togglePROGMode() {
        this.calculator.executeCommand(new TogglePROGModeCommand());
    }

    @FXML
    public void executeRecordedProgram() {
        this.calculator.executeCommand(new ExecuteRecordedProgram());
    }

    @Override
    public void update(UIState uiState) {
        this.mainDisplayLabel.setText(uiState.getMainDisplayText());
        this.secondaryDisplayLabel.setText(uiState.getSecondaryDisplayText());
        this.memoryLabel.setText(uiState.getMemoryText());
        this.openMenuButton.getContextMenu().getItems().clear();
        this.openMenuButton.getContextMenu().getItems().addAll(uiState.getExpressionHistory().stream().map(MenuItem::new).collect(Collectors.toList()));
        this.openMenuButton.setDisable(this.openMenuButton.getContextMenu().getItems().isEmpty());
        this.RCLBtn.setDisable(!uiState.isRCLButtonActive());
        this.decimalSeparatorBtn.setDisable(!uiState.isDecimalSeparatorButtonActive());
        this.undoBtn.setDisable(!uiState.isUndoButtonActive());
        this.redoBtn.setDisable(!uiState.isRedoButtonActive());
        this.equalsBtn.setDisable(!uiState.isEqualsButtonActive());
    }
}
