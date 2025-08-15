package com.pahanaedu.service;

import com.pahanaedu.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
    Optional<Customer> getCustomerById(int customerId);
    Optional<Customer> getCustomerByAccountNo(String accountNo);
    List<Customer> getAllCustomers();
}
