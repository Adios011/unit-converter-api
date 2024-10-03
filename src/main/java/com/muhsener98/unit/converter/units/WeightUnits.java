package com.muhsener98.unit.converter.units;

import com.muhsener98.unit.converter.exception.NoSuchUnitFoundException;

public enum WeightUnits implements Unit{

    MILLIGRAM, GRAM, KILOGRAM, OUNCE, POUND;

    /**
     * String value of unit's long name
     * @param str -
     * @return
     * @throws NoSuchUnitFoundException If unknown weight unit.
     */
    public static WeightUnits fromString(String str) throws NoSuchUnitFoundException {
        str = str.toLowerCase();

        return switch (str) {
            case "milligram" -> MILLIGRAM;
            case "gram" -> GRAM;
            case "kilogram" -> KILOGRAM;
            case "ounce" -> OUNCE;
            case "pound" -> POUND;
            default -> throw new NoSuchUnitFoundException("no such unit found: " + str);
        };
    }

    public String getNameInLowerCase(){
        if(this == MILLIGRAM)
            return "milligram";
        else if(this == KILOGRAM)
            return "kilogram";
        else
            return this.name().toLowerCase();
    }


}
