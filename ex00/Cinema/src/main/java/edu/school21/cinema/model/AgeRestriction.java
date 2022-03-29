package edu.school21.cinema.model;

public enum AgeRestriction {
    ZERO("0+"),
    SIX("6+"),
    TWELVE("12+"),
    SIXTEEN("16+"),
    EIGHTEEN("18+");

    private String explanation;

    AgeRestriction(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }
}
