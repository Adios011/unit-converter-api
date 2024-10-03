package com.muhsener98.unit.converter.service.impl;

import com.muhsener98.unit.converter.exception.NoSuchUnitFoundException;
import com.muhsener98.unit.converter.service.UnitConverter;
import com.muhsener98.unit.converter.units.LengthUnits;
import com.muhsener98.unit.converter.units.Unit;
import com.muhsener98.unit.converter.units.WeightUnits;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class WeightUnitConverter implements UnitConverter {

    private static final Map<Unit, Double> conversionRates;

    static {
        conversionRates = new HashMap<>();
        fillConversionRatesMap();
    }

    @Override
    public double convert(double value, Unit fromUnit, Unit toUnit) {
        Double fromRate = conversionRates.get(fromUnit);
        Double toRate = conversionRates.get(toUnit);

        if(fromRate == null || toRate == null)
            throw new UnsupportedOperationException("Unsupported unit conversion!");


        double valueInGram = value * fromRate;
        return valueInGram / toRate;
    }


    private static void fillConversionRatesMap() {
        try (InputStream stream = LengthUnitConverter.class.getResourceAsStream("/application.properties")) {
            if (stream == null)
                throw new RuntimeException("Error occurred when reading file: application.properties");
            Scanner scanner = new Scanner(stream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] keyValue = line.split("=");
                if (!keyValue[0].startsWith("weight"))
                    continue;

                String unitName = keyValue[0].split("\\.")[1];
                Double rate = Double.parseDouble(keyValue[1]);
                conversionRates.put(WeightUnits.fromString(unitName), rate);

            }
        } catch (IOException | NoSuchUnitFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

