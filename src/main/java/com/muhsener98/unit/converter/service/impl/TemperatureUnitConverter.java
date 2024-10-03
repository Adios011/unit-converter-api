package com.muhsener98.unit.converter.service.impl;

import com.muhsener98.unit.converter.service.Script;
import com.muhsener98.unit.converter.service.ScriptManager;
import com.muhsener98.unit.converter.service.UnitConverter;
import com.muhsener98.unit.converter.units.LengthUnits;
import com.muhsener98.unit.converter.units.TemperatureUnits;
import com.muhsener98.unit.converter.units.Unit;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class TemperatureUnitConverter implements UnitConverter {


    private final ScriptManager scriptManager;

    public TemperatureUnitConverter(ScriptManager scriptManager) {
        this.scriptManager = scriptManager;
        loadScripts();
    }


    @Override
    public double convert(double value, Unit fromUnit, Unit toUnit) {

            // convert fromUnit to celsius
            String fromScriptName = ((TemperatureUnits) fromUnit).getNameInLowerCase() + "To";
            Map<String,Object> argumentMap = new HashMap<>();
            argumentMap.put("value" , value);
            Object valueInCelsius = scriptManager.executeScript(fromScriptName , argumentMap);


        // convert celsiusValue to target unit (toUnit)
        String toScriptName = ((TemperatureUnits) toUnit).getNameInLowerCase()+"From";
        Map<String,Object> argumentMap2 = new HashMap<>();
        argumentMap2.put("value" , valueInCelsius);
        Object targetResult = scriptManager.executeScript(toScriptName , argumentMap2);

        return (double) targetResult;

    }



    private void loadScripts(){
        URL scriptDirectory = this.getClass().getResource("/scripts");
        if(scriptDirectory == null)
            throw new RuntimeException("Error occurred when seeking for directory: /scripts");

        try {
            URI uri = scriptDirectory.toURI();
            Path path = Paths.get(uri);

            Stream<Path> stream = Files.list(path);
            List<Path> files = stream.filter(Files::isRegularFile).toList();

            for(Path file : files){
                String fileName = file.getFileName().toString() ;
                if(fileName.endsWith(".py")){
                    scriptManager.addScript(fileName.replaceFirst(".py$" , "") , new File(file.toUri()));
                }

            }

            stream.close();


        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }


    }
}
