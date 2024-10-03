package com.muhsener98.unit.converter.service;

import com.muhsener98.unit.converter.units.Unit;

public interface UnitConverter {

    double convert(double value, Unit fromUnit, Unit toUnit);
}
