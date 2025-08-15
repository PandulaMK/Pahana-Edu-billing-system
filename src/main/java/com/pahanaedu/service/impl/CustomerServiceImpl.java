package com.pahanaedu.service.impl;

import com.pahanaedu.dao.CustomerDao;
import com.pahanaedu.dao.DaoException;
import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void addCustomer(Customer customer) {
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null");
        if (customer.getName() == null || customer.getName().isEmpty())
            throw new IllegalArgumentException("Customer name is required");
        try {
            customerDao.create(customer);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to add customer", e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null");
        try {
            customerDao.update(customer);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to update customer", e);
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        if (customerId <= 0) throw new IllegalArgumentException("Invalid customer id");
        try {
            customerDao.delete(customerId);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to delete customer", e);
        }
    }

    @Override
    public Optional<Customer> getCustomerById(int customerId) {
        try {
            return customerDao.findById(customerId);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to find customer", e);
        }
    }

    @Override
    public Optional<Customer> getCustomerByAccountNo(String accountNo) {
        try {
            return customerDao.findByAccountNo(accountNo);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to find customer", e);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            return customerDao.findAll();
        } catch (DaoException e) {
            throw new RuntimeException("Failed to list customers", e);
        }
    }
}
