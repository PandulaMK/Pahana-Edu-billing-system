// CustomerDao.java
package com.pahanaedu.dao;

import com.pahanaedu.model.Customer;
import java.util.*;

public interface CustomerDao {
    void create(Customer c) throws DaoException;
    void update(Customer c) throws DaoException;
    void delete(int customerId) throws DaoException;
    Optional<Customer> findById(int customerId) throws DaoException;
    Optional<Customer> findByAccountNo(String accountNo) throws DaoException;
    List<Customer> findAll() throws DaoException;
}
