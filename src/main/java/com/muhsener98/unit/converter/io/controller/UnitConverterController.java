package com.muhsener98.unit.converter.io.controller;

import com.muhsener98.unit.converter.exception.NoSuchUnitFoundException;
import com.muhsener98.unit.converter.io.model.ConvertRequest;
import com.muhsener98.unit.converter.io.model.ConvertResponse;
import com.muhsener98.unit.converter.service.UnitConverterContext;
import com.muhsener98.unit.converter.units.ConversionTypes;
import com.muhsener98.unit.converter.units.Unit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitConverterController {

    private final UnitConverterContext unitConverterContext;

    public UnitConverterController(UnitConverterContext unitConverterContext) {
        this.unitConverterContext = unitConverterContext;
    }

    @PostMapping("convert")
    public ConvertResponse  convert(@RequestBody ConvertRequest request) throws NoSuchUnitFoundException {
        double value = request.getValue();
        ConversionTypes type = ConversionTypes.fromString(request.getType());

        Double result = unitConverterContext.convert(value , type , request.getFrom() , request.getTo());

        return new ConvertResponse(String.format("%.2f" , result) + " " + request.getTo());
    }
}
