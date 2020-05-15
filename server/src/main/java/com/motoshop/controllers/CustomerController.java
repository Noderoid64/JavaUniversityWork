package com.motoshop.controllers;

import com.motoshop.model.Customer;
import com.motoshop.persistance.dao.CustomersDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CustomerController {

    private CustomersDAO customersDAO = new CustomersDAO();

    @GetMapping("/customer/all")
    public ArrayList<Customer> getMotorcycles() {
        return customersDAO.getAllCustomers();
    }
}
