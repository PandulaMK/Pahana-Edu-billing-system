package com.pahanaedu.service;

import com.pahanaedu.dao.BillDao;
import com.pahanaedu.dao.impl.BillDaoImpl;
import com.pahanaedu.model.Bill;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BillService {

    private final BillDao billDao = new BillDaoImpl();

    public Bill createBill(int customerId, int units, BigDecimal ratePerUnit) throws Exception {
        BigDecimal total = ratePerUnit.multiply(BigDecimal.valueOf(units));

        Bill bill = new Bill();
        bill.setCustomerId(customerId);
        bill.setUnitsConsumed(units);
        bill.setRatePerUnit(ratePerUnit);
        bill.setTotalAmount(total);
        bill.setBillDate(LocalDateTime.now()); // correct type

        // Generate bill number
        int year = LocalDate.now().getYear();
        int seq = billDao.nextSequenceForYear(year);
        String billNumber = String.format("BILL-%d-%04d", year, seq);
        bill.setBillNumber(billNumber);

        billDao.create(bill);
        return bill;
    }


    public List<Bill> getAllBills() throws Exception {
        return billDao.findAll();
    }
}
