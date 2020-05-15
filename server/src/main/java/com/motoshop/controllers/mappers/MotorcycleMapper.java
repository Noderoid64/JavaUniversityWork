package com.motoshop.controllers.mappers;

import com.motoshop.model.Motorcycle;
import com.motoshop.model.dto.MotorcycleDto;

import java.util.ArrayList;

public class MotorcycleMapper {
    public MotorcycleDto map (Motorcycle motorcycle) {
        MotorcycleDto dto = new MotorcycleDto();
        dto.title = motorcycle.getTitle();
        dto.type = motorcycle.getType();
        dto.date = motorcycle.getYear();
        dto.type_id = motorcycle.getType_id();
        dto.engineSize = motorcycle.getEngineSize();
        dto.mileage = motorcycle.getMileage();
        dto.price = motorcycle.getPrice();
        return dto;
    }

    public MotorcycleDto[] map (ArrayList<Motorcycle> motorcycleArray) {
        MotorcycleDto[] result = new MotorcycleDto[motorcycleArray.size()];
        int i = 0;
        for(Motorcycle m: motorcycleArray) {
            result[i] = map(m);
            i++;
        }
        return result;
    }
}
