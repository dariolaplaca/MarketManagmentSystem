package com.dlp.mms.Enums;

public enum ProductCategory {
    FRESH("FRESH"),
    DAIRY_AND_EGGS("DAIRY"),
    MEAT_AND_POULTRY("MEAT_AND_POULTRY"),
    BAKERY("BAKERY"),
    FROZEN("FROZEN"),
    BEVERAGES("BEVERAGES"),
    CANNED_AND_PACKAGED("CANNED_AND_PACKAGED"),
    SNACKS("SNACKS"),
    PERSONAL_CARE_AND_HOUSEHOLD("PERSONAL_CARE_AND_HOUSEHOLD"),
    CONDIMENTS_AND_SAUCES("CONDIMENTS_AND_SAUCES");

    private final String name;

    ProductCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
