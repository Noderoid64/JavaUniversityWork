package com.motoshop.controllers;

import com.motoshop.controllers.mappers.MotorcycleMapper;
import com.motoshop.model.Motorcycle;
import com.motoshop.persistance.dao.MotorcycleDAO;
import com.motoshop.model.dto.MotorcycleDto;
import com.motoshop.persistance.dao.TypeDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Map;

@RestController
public class MotorcycleController {

    private MotorcycleDAO motorcycleDAO = new MotorcycleDAO();
    private TypeDAO typeDAO = new TypeDAO();
    private MotorcycleMapper motorcycleMapper = new MotorcycleMapper();


    @GetMapping("motorcycle/all")
    public MotorcycleDto[] getMotorcycles() {
        ArrayList<Motorcycle> moto = motorcycleDAO.getAllMotorcycles();
        return motorcycleMapper.map(moto);
    }

    @PostMapping("motorcycle/new")
    public boolean getFilteredMotorcycles(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "type", required = false) int type,
            @RequestParam(name = "year", required = false) java.util.Date year,
            @RequestParam(name = "engineSize", required = false) int engineSize,
            @RequestParam(name = "mileage", required = false) int mileage,
            @RequestParam(name = "price", required = false) Double price
    ) {
        Motorcycle moto = new Motorcycle();
        moto.setTitle(title);
        moto.setType_id(type);
        moto.setEngineSize(engineSize);
        moto.setMileage(engineSize);
        moto.setYear(new Date(year.getTime()));
        moto.setMileage(mileage);
        moto.setPrice(price);
        return motorcycleDAO.addNewRecord(moto);
    }

    @GetMapping("motorcycle/types")
    public Map<Integer, String> getTypes() {
        return typeDAO.getTypes();
    }
}
