package com.example.homework5.calculator;

import java.util.List;

public class UIState {
    private String mainDisplayText;

    private String secondaryDisplayText;

    private String memoryText;

    private List<String> expressionHistory;

    private boolean RCLButtonActive;

    public UIState(String mainDisplayText, String secondaryDisplayText, String memoryText, List<String> expressionHistory, boolean RCLButtonActive) {
        this.mainDisplayText = mainDisplayText;
        this.secondaryDisplayText = secondaryDisplayText;
        this.memoryText = memoryText;
        this.expressionHistory = expressionHistory;
        this.RCLButtonActive = RCLButtonActive;
    }

    public String getMainDisplayText() {
        return mainDisplayText;
    }

    public String getSecondaryDisplayText() {
        return secondaryDisplayText;
    }

    public String getMemoryText() {
        return memoryText;
    }

    public List<String> getExpressionHistory() {
        return expressionHistory;
    }

    public boolean isRCLButtonActive() {
        return RCLButtonActive;
    }
}
