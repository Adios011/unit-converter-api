package com.muhsener98.unit.converter.service.impl;

import com.muhsener98.unit.converter.exception.NoSuchUnitFoundException;
import com.muhsener98.unit.converter.service.UnitConverter;
import com.muhsener98.unit.converter.units.LengthUnits;
import com.muhsener98.unit.converter.units.Unit;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.script.AbstractScriptEngine;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class LengthUnitConverter implements UnitConverter {

    private static final  Map<Unit,Double> CONVERSION_RATES = new HashMap<>();

    static {
        fillConversionRateMap();
    }

    public LengthUnitConverter(){

    }




    @Override
    public double convert(double value, Unit fromUnit, Unit toUnit) {
        Double fromRate = CONVERSION_RATES.get(fromUnit);
        Double toRate = CONVERSION_RATES.get(toUnit);

        if(fromRate == null || toRate == null)
            throw new UnsupportedOperationException("Unsupported unit conversion!");


        double valueInMeter = value * fromRate;
        return valueInMeter / toRate;
    }






    private static void fillConversionRateMap(){

        try(InputStream stream = LengthUnitConverter.class.getResourceAsStream("/application.properties")){
            if(stream == null )
                throw new RuntimeException("Error occurred when reading file: application.properties");
            Scanner scanner = new Scanner(stream);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] keyValue = line.split("=");
                if(!keyValue[0].startsWith("length"))
                    continue;

                String unitName = keyValue[0].split("\\.")[1];
                Double rate = Double.parseDouble(keyValue[1]);
                CONVERSION_RATES.put(LengthUnits.fromString(unitName),  rate   );

            }


        } catch (IOException | NoSuchUnitFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
