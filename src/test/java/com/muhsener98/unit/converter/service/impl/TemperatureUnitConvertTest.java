package com.muhsener98.unit.converter.service.impl;

import com.muhsener98.unit.converter.service.Script;
import com.muhsener98.unit.converter.service.ScriptManager;
import com.muhsener98.unit.converter.units.TemperatureUnits;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemperatureUnitConvertTest {

    private TemperatureUnitConverter converter ;
    private ScriptManager scriptManager ;

    @BeforeAll
    public void setup(){
         scriptManager = new ScriptManager();
        converter = new TemperatureUnitConverter(scriptManager);
    }

    @Test
    public void testConvertFromCelsiusToFahrenheit(){
        double celsius = 0 ;

        double fahrenheit = converter.convert(celsius , TemperatureUnits.CELSIUS , TemperatureUnits.FAHRENHEIT);

        assertEquals(fahrenheit , 32.0);
    }


    @Test
    public void testConvertFromFahrenheitToKelvin(){
        double fahrenheit = 32 ;
        double kelvin = converter.convert(fahrenheit , TemperatureUnits.FAHRENHEIT , TemperatureUnits.KELVIN);

        assertEquals(kelvin , -273.15);
    }

    @Test
    public void testConvertFromKelvinToFahrenheit(){
        double kelvin = -273.15;
        double fahrenheit = converter.convert(kelvin , TemperatureUnits.KELVIN , TemperatureUnits.FAHRENHEIT    );

        assertEquals(fahrenheit , 32);
    }
}
