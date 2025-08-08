package com.pahanaedu.service;

import com.pahanaedu.dao.ICustomerDAO;
import com.pahanaedu.factory.DAOFactory;
import com.pahanaedu.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private final ICustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = DAOFactory.getCustomerDAO();
    }

    public void addCustomer(Customer customer) throws SQLException {
        customerDAO.insertCustomer(customer);
    }

    public void updateCustomer(Customer customer) throws SQLException {
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int id) throws SQLException {
        customerDAO.deleteCustomer(id);
    }

    public Customer getCustomerById(int id) throws SQLException {
        return customerDAO.getCustomerById(id);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }
}
