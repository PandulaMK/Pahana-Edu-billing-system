package com.pahanaedu.service.impl;

import com.pahanaedu.dao.BillDao;
import com.pahanaedu.dao.CustomerDao;
import com.pahanaedu.dao.DaoException;
import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;
import com.pahanaedu.service.BillService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BillServiceImpl implements BillService {

    private final BillDao billDao;
    private final CustomerDao customerDao;

    public BillServiceImpl(BillDao billDao, CustomerDao customerDao) {
        this.billDao = billDao;
        this.customerDao = customerDao;
    }

    @Override
    public Bill generateBill(String accountNo, int unitsConsumed, String generatedBy) {
        if (accountNo == null || accountNo.isEmpty()) throw new IllegalArgumentException("Account number is required");
        if (unitsConsumed < 0) throw new IllegalArgumentException("Units consumed cannot be negative");

        try {
            Optional<Customer> customerOpt = customerDao.findByAccountNo(accountNo);
            if (customerOpt.isPresent()) throw new IllegalArgumentException("Customer does not exist");

            Customer customer = customerOpt.get();

            // Your current rule: fixed rate and sequential bill number per year using DAO
            BigDecimal ratePerUnit = new BigDecimal("10");
            BigDecimal total = ratePerUnit.multiply(BigDecimal.valueOf(unitsConsumed));

            Bill bill = new Bill();
            bill.setCustomerId(customer.getCustomerId());
            bill.setUnitsConsumed(unitsConsumed);
            bill.setRatePerUnit(ratePerUnit);
            bill.setTotalAmount(total);
            bill.setBillDate(LocalDateTime.now());

            int year = LocalDate.now().getYear();
            int seq = billDao.nextSequenceForYear(year);
            bill.setBillNumber(String.format("BILL-%d-%04d", year, seq));

            billDao.create(bill);

            // Optional: persist updated units for the customer if your domain requires it
            customer.setUnits(customer.getUnits() + unitsConsumed);
            customerDao.update(customer);

            return bill;
        } catch (DaoException e) {
            throw new RuntimeException("Failed to generate bill", e);
        }
    }

    @Override
    public Optional<Bill> findBillByBillNo(String billNo) {
        if (billNo == null || billNo.isEmpty()) return Optional.empty();
        try {
            return billDao.findByNumber(billNo);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to find bill", e);
        }
    }

    @Override
    public List<Bill> getBillsForCustomer(String accountNo) {
        if (accountNo == null || accountNo.isEmpty())
            throw new IllegalArgumentException("Account number required");
        try {
            Optional<Customer> c = customerDao.findByAccountNo(accountNo);
            if (c.isPresent()) throw new IllegalArgumentException("Customer not found");
            return billDao.findByCustomer(c.get().getCustomerId());
        } catch (DaoException e) {
            throw new RuntimeException("Failed to list bills for customer", e);
        }
    }

    @Override
    public Optional<Customer> findCustomer(String accountNo) {
        if (accountNo == null || accountNo.isEmpty()) return Optional.empty();
        try {
            return customerDao.findByAccountNo(accountNo);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to find customer", e);
        }
    }
    
    @Override
    public List<Bill> getAllBills() {
        try {
           
            return billDao.findAll();
        } catch (DaoException e) {
            throw new RuntimeException("Failed to load bills", e);
        }
    }
    @Override
    public Bill generateBillByCustomerId(int customerId, int unitsConsumed, String generatedBy) {
        if (customerId <= 0) throw new IllegalArgumentException("Customer id is required");
        if (unitsConsumed < 0) throw new IllegalArgumentException("Units consumed cannot be negative");

        try {
            java.util.Optional<Customer> customerOpt = customerDao.findById(customerId);
            if (!customerOpt.isPresent()) throw new IllegalArgumentException("Customer does not exist");

            Customer customer = customerOpt.get();

            java.math.BigDecimal ratePerUnit = new java.math.BigDecimal("10");
            java.math.BigDecimal total = ratePerUnit.multiply(java.math.BigDecimal.valueOf(unitsConsumed));

            Bill bill = new Bill();
            bill.setCustomerId(customer.getCustomerId());
            bill.setUnitsConsumed(unitsConsumed);
            bill.setRatePerUnit(ratePerUnit);
            bill.setTotalAmount(total);
            bill.setBillDate(java.time.LocalDateTime.now());

            int year = java.time.LocalDate.now().getYear();
            int seq = billDao.nextSequenceForYear(year);
            bill.setBillNumber(String.format("BILL-%d-%04d", year, seq));

            billDao.create(bill);

            // ✅ Do NOT mutate customer units here (unless your workflow requires clearing them)
            // If you want to clear after billing, uncomment:
            // customer.setUnits(0);
            // customerDao.update(customer);

            return bill;
        } catch (DaoException e) {
            throw new RuntimeException("Failed to generate bill", e);
        }
    }


}
