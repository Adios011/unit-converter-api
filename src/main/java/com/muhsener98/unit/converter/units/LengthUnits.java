package com.muhsener98.unit.converter.units;

import com.muhsener98.unit.converter.exception.NoSuchUnitFoundException;

public enum LengthUnits implements Unit {

    MILLIMETER, CENTIMETER, METER, KILOMETER, INCH , FOOT, YARD, MILE;

    /**
     * String value of unit's long name
     * @param str -
     * @return
     * @throws NoSuchUnitFoundException If unknown length unit.
     */
    public static LengthUnits fromString(String str) throws NoSuchUnitFoundException {
        str = str.toLowerCase();

        return switch (str) {
            case "millimeter" -> MILLIMETER;
            case "centimeter" -> CENTIMETER;
            case "meter" -> METER;
            case "kilometer" -> KILOMETER;
            case "inch" -> INCH;
            case "foot" -> FOOT;
            case "yard" -> YARD;
            case "mile" -> MILE;
            default -> throw new NoSuchUnitFoundException("no such unit found: " + str);
        };
    }


    public  String getNameInLowerCase(){
        if(this == MILLIMETER)
            return "millimeter";
        else if(this ==CENTIMETER)
            return "centimeter";
        else if(this == KILOMETER)
            return "kilometer";
        else if(this == INCH)
            return "inch";
        else if(this == MILE)
            return "mile";
        else
            return this.name().toLowerCase();
    }
}
