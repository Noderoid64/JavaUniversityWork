package com.motoshop.controllers;

import com.motoshop.model.Lease;
import com.motoshop.persistance.dao.LeaseDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LeaseController {

    private LeaseDAO leaseDAO = new LeaseDAO();

    @GetMapping("/lease/all")
    public ArrayList<Lease> getAllLease() {
        return leaseDAO.getAllLease();
    }
}
