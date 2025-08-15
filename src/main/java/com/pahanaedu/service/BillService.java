package com.pahanaedu.service;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;

import java.util.List;
import java.util.Optional;

public interface BillService {
    Bill generateBill(String accountNo, int unitsConsumed, String generatedBy);
    // ✅ NEW: use this when you already have the customer id
    Bill generateBillByCustomerId(int customerId, int unitsConsumed, String generatedBy);

    Optional<Bill> findBillByBillNo(String billNo);
    List<Bill> getBillsForCustomer(String accountNo);
    Optional<Customer> findCustomer(String accountNo);
    List<Bill> getAllBills(); // (if you added this earlier)
}
