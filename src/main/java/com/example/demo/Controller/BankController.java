package com.example.demo.Controller;

import com.example.demo.Api.ApiResponse;
import com.example.demo.Model.Customer;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class BankController {
    ArrayList<Customer> customers = new ArrayList<>();

      @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return new ApiResponse("Customer added successfully");
    }

    @GetMapping("/get")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer) {
        if (index >= 0 && index < customers.size()) {
            customers.set(index, customer);
            return new ApiResponse("Customer updated successfully");
        }
        return new ApiResponse("Index out of bounds");
    }

    @DeleteMapping("/delete/{index}")
    public String deleteCustomer(@PathVariable int index) {
        if (index >= 0 && index < customers.size()) {
            customers.remove(index);
            return "Customer deleted successfully";
        }
        return "Index out of bounds";
    }

    @PutMapping("/deposit/{index}")
    public ApiResponse depositMoney(@PathVariable int index, @RequestParam double amount) {
        if (index >= 0 && index < customers.size()) {
            Customer customer = customers.get(index);
            customer.setBalance(customer.getBalance() + amount);
            return new ApiResponse("Deposited " + amount + " to customer " + index + ". New balance: " + customer.getBalance());
        }
        return new ApiResponse("Index out of bounds");
    }

    @PutMapping("/withdraw/{index}")
    public ApiResponse withdrawMoney(@PathVariable int index, @RequestParam double amount) {
        if (index >= 0 && index < customers.size()) {
            Customer customer = customers.get(index);
            if (customer.getBalance() >= amount) {
                customer.setBalance(customer.getBalance() - amount);
                return new ApiResponse("Withdrew " + amount + " from customer " + index + ". New balance: " + customer.getBalance());
            } else {
                return new ApiResponse("Insufficient balance for customer " + index);
            }
        }
        return new ApiResponse("Index out of bounds");
    }

    @GetMapping("/search")
    public ArrayList<Customer> searchCustomer(@RequestParam String username) {
        ArrayList<Customer> foundCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getUsername().equalsIgnoreCase(username)) {
                foundCustomers.add(customer);
            }
        }
        return foundCustomers;
    }
}

