package com.example.homework5.calculator;

public class LabelState {
    private String displayLabel;

    private String memoryLabel;

    public LabelState(String displayLabel, String memoryLabel) {
        this.displayLabel = displayLabel;
        this.memoryLabel = memoryLabel;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }

    public String getMemoryLabel() {
        return memoryLabel;
    }
}
