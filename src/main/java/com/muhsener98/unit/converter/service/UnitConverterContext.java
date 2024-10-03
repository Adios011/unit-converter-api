package com.muhsener98.unit.converter.service;

import com.muhsener98.unit.converter.exception.NoSuchUnitFoundException;
import com.muhsener98.unit.converter.service.impl.LengthUnitConverter;
import com.muhsener98.unit.converter.service.impl.TemperatureUnitConverter;
import com.muhsener98.unit.converter.service.impl.WeightUnitConverter;
import com.muhsener98.unit.converter.units.*;
import org.springframework.stereotype.Component;

@Component
public class UnitConverterContext {

    private final TemperatureUnitConverter temperatureUnitConverter;
    private final LengthUnitConverter lengthUnitConverter;
    private final WeightUnitConverter weightUnitConverter   ;

    public UnitConverterContext(TemperatureUnitConverter temperatureUnitConverter,
                                LengthUnitConverter lengthUnitConverter,
                                WeightUnitConverter weightUnitConverter) {
        this.temperatureUnitConverter = temperatureUnitConverter;
        this.lengthUnitConverter = lengthUnitConverter;
        this.weightUnitConverter = weightUnitConverter;
    }

    public Double convert(double value, ConversionTypes type , String fromUnit, String toUnit ) throws NoSuchUnitFoundException {
        if(type.equals(ConversionTypes.LENGTH))
            return lengthUnitConverter.convert(value , LengthUnits.fromString(fromUnit), LengthUnits.fromString(toUnit));
        else if(type.equals(ConversionTypes.WEIGHT))
            return weightUnitConverter.convert(value , WeightUnits.fromString(fromUnit), WeightUnits.fromString(toUnit));
        else if(type.equals(ConversionTypes.TEMPERATURE))
            return temperatureUnitConverter.convert(value, TemperatureUnits.fromString(fromUnit),TemperatureUnits.fromString(toUnit));
        else
            throw new UnsupportedOperationException("Unsupported type conversion: " + type.toString());
    }
}
