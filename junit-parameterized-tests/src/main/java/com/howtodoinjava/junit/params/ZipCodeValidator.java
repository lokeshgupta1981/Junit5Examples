package com.howtodoinjava.junit.params;

public class ZipCodeValidator {

    public boolean isValid(String zip) {
        return zip != null && zip.strip().matches("\\d{5}");
    }
}
