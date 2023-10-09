package com.example.homework5.calculator;

import java.util.List;

public class UIState {
    boolean isProgMode;
    boolean isRCLButtonActive;
    boolean isDecimalSeparatorButtonActive;
    boolean isUndoButtonActive;
    boolean isRedoButtonActive;

    boolean isEqualsButtonActive;

    private String mainDisplayText;
    private String secondaryDisplayText;
    private String memoryText;
    private List<String> expressionHistory;

    public String getMainDisplayText() {
        return mainDisplayText;
    }

    public void setMainDisplayText(String mainDisplayText) {
        this.mainDisplayText = mainDisplayText;
    }

    public String getSecondaryDisplayText() {
        return secondaryDisplayText;
    }

    public void setSecondaryDisplayText(String secondaryDisplayText) {
        this.secondaryDisplayText = secondaryDisplayText;
    }

    public String getMemoryText() {
        return memoryText;
    }

    public void setMemoryText(String memoryText) {
        this.memoryText = memoryText;
    }

    public List<String> getExpressionHistory() {
        return expressionHistory;
    }

    public void setExpressionHistory(List<String> expressionHistory) {
        this.expressionHistory = expressionHistory;
    }

    public void setProgMode(boolean progMode) {
        isProgMode = progMode;
    }

    public boolean isRCLButtonActive() {
        return isRCLButtonActive;
    }

    public void setRCLButtonActive(boolean RCLButtonActive) {
        isRCLButtonActive = RCLButtonActive;
    }

    public boolean isDecimalSeparatorButtonActive() {
        return isDecimalSeparatorButtonActive;
    }

    public void setDecimalSeparatorButtonActive(boolean decimalSeparatorButtonActive) {
        isDecimalSeparatorButtonActive = decimalSeparatorButtonActive;
    }

    public boolean isUndoButtonActive() {
        return isUndoButtonActive;
    }

    public void setUndoButtonActive(boolean undoButtonActive) {
        isUndoButtonActive = undoButtonActive;
    }

    public boolean isRedoButtonActive() {
        return isRedoButtonActive;
    }

    public void setRedoButtonActive(boolean redoButtonActive) {
        isRedoButtonActive = redoButtonActive;
    }

    public boolean isEqualsButtonActive() {
        return isEqualsButtonActive;
    }

    public void setEqualsButtonActive(boolean equalsButtonActive) {
        isEqualsButtonActive = equalsButtonActive;
    }
}
