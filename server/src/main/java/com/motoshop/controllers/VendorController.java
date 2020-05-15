package com.motoshop.controllers;

import com.motoshop.model.Vendor;
import com.motoshop.persistance.dao.VendorsDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class VendorController {

    private VendorsDAO vendorsDAO = new VendorsDAO();

    @GetMapping("/vendor/all")
    public ArrayList<Vendor> getMotorcycles() {
        return vendorsDAO.getAllVendors();
    }
}
