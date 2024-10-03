package com.muhsener98.unit.converter.units;

import com.muhsener98.unit.converter.exception.NoSuchUnitFoundException;

public enum ConversionTypes {

    LENGTH, TEMPERATURE , WEIGHT;


    public static ConversionTypes fromString(String str) {
        str = str.toLowerCase();

       return switch (str){
            case "length" -> LENGTH;
            case "temperature" -> TEMPERATURE;
            case "weight" -> WEIGHT;
           default -> throw new UnsupportedOperationException("No such conversion type exists: " + str);
        };
    }
}
