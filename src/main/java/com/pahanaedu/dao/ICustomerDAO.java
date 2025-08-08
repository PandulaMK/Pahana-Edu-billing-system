package com.pahanaedu.dao;

import com.pahanaedu.model.Customer;
import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    void insertCustomer(Customer c) throws SQLException;
    void updateCustomer(Customer c) throws SQLException;
    void deleteCustomer(int id) throws SQLException;
    Customer getCustomerById(int id) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
}
