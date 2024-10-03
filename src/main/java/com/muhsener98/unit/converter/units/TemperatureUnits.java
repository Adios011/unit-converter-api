package com.muhsener98.unit.converter.units;

import com.muhsener98.unit.converter.exception.NoSuchUnitFoundException;

public enum TemperatureUnits implements Unit {

    CELSIUS , FAHRENHEIT,KELVIN;


    /**
     * String value of unit's long name
     * @param str -
     * @return
     * @throws NoSuchUnitFoundException If unknown temperature unit.
     */
    public static TemperatureUnits fromString(String str) throws NoSuchUnitFoundException {
        str = str.toLowerCase();

        return switch (str) {
            case "celsius" -> CELSIUS;
            case "fahrenheit" -> FAHRENHEIT;
            case "kelvin" -> KELVIN;
            default -> throw new NoSuchUnitFoundException("no such unit found: " + str);
        };
    }

    public String getNameInLowerCase(){
        if(this == CELSIUS)
            return "celsius";
        else if(this == FAHRENHEIT)
            return "fahrenheit";
        else if(this == KELVIN)
            return "kelvin";
        else {
            return null;
        }
    }
}
