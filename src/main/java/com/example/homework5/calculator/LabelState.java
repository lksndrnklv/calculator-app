package com.example.homework5.calculator;

public class LabelState {
    private String mainDisplayText;

    private String secondaryDisplayText;

    private String memoryText;

    public LabelState(String mainDisplayText, String secondaryDisplayText, String memoryText) {
        this.mainDisplayText = mainDisplayText;
        this.secondaryDisplayText = secondaryDisplayText;
        this.memoryText = memoryText;
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
}
