package com.pahanaedu.service;

import com.pahanaedu.dao.CustomerDao;
import com.pahanaedu.dao.DaoException;
import com.pahanaedu.dao.impl.CustomerDaoImpl;
import com.pahanaedu.model.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService() {
        // You can also inject this via a DAOFactory if preferred
        this.customerDao = new CustomerDaoImpl();
    }

    public void addCustomer(Customer customer) throws DaoException {
        customerDao.create(customer);
    }

    public void updateCustomer(Customer customer) throws DaoException {
        customerDao.update(customer);
    }

    public void deleteCustomer(int customerId) throws DaoException {
        customerDao.delete(customerId);
    }

    public Optional<Customer> getCustomerById(int customerId) throws DaoException {
        return customerDao.findById(customerId);
    }

    public Optional<Customer> getCustomerByAccountNo(String accountNo) throws DaoException {
        return customerDao.findByAccountNo(accountNo);
    }

    public List<Customer> getAllCustomers() throws DaoException {
        return customerDao.findAll();
    }
}
