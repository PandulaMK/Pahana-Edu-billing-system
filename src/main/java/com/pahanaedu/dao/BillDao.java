package com.pahanaedu.dao;

import com.pahanaedu.model.Bill;

import java.util.List;
import java.util.Optional;

public interface BillDao {
    void create(Bill bill) throws DaoException;

    Optional<Bill> findById(int billId) throws DaoException;

    Optional<Bill> findByNumber(String billNumber) throws DaoException;

    List<Bill> findByCustomer(int customerId) throws DaoException;

    List<Bill> findAll() throws DaoException;

    /**
     * Returns the next sequence number for the given year to build a bill number like BILL-YYYY-0001.
     * You can implement it using COUNT(*) or MAX(...). Service will left-pad it to 4 digits.
     */
    int nextSequenceForYear(int year) throws DaoException;
}
