package com.example.homework5.calculator;

import java.util.List;

public class LabelState {
    private String mainDisplayText;

    private String secondaryDisplayText;

    private String memoryText;

    private List<String> expressionHistory;

    public LabelState(String mainDisplayText, String secondaryDisplayText, String memoryText, List<String> expressionHistory) {
        this.mainDisplayText = mainDisplayText;
        this.secondaryDisplayText = secondaryDisplayText;
        this.memoryText = memoryText;
        this.expressionHistory = expressionHistory;
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
}
