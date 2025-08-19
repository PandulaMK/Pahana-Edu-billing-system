package com.pahanaedu.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.pahanaedu.dao.BillDao;
import com.pahanaedu.dao.impl.BillDaoImpl;
import com.pahanaedu.model.Bill;
import com.pahanaedu.util.DBUtil; // used only to seed a customer for FK

public class BillDaoTest {

    // Insert a minimal customer and return its generated ID (so FK is valid)
    private int insertTestCustomer(String accountNo) throws Exception {
        String sql = "INSERT INTO customer(account_no, name, address, phone, units) VALUES (?,?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, accountNo);
            ps.setString(2, "Test Name");
            ps.setString(3, "Test Address");
            ps.setString(4, "0770000000");
            ps.setInt(5, 0);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new IllegalStateException("Failed to insert test customer");
    }

    @Test
    void testCreateFindByNumberAndCustomer() throws Exception {
        BillDao dao = new BillDaoImpl();

        // ensure a valid customer exists (FK)
        int customerId = insertTestCustomer("ACC-BILL-001");

        // create first bill
        Bill b1 = new Bill();
        b1.setBillNumber("BILL_024");
        b1.setCustomerId(customerId);
        b1.setUnitsConsumed(20);
        b1.setRatePerUnit(new BigDecimal("10.00"));
        b1.setTotalAmount(new BigDecimal("200.00"));
        b1.setBillDate(LocalDateTime.now());
        dao.create(b1);
        assertTrue(b1.getBillId() > 0);

        // create second bill
        Bill b2 = new Bill();
        b2.setBillNumber("BILL_025");
        b2.setCustomerId(customerId);
        b2.setUnitsConsumed(35);
        b2.setRatePerUnit(new BigDecimal("10.00"));
        b2.setTotalAmount(new BigDecimal("350.00"));
        b2.setBillDate(LocalDateTime.now());
        dao.create(b2);

        // find by bill number (Optional)
        Optional<Bill> optBill = dao.findByNumber("BILL_024");
        assertTrue(optBill.isPresent());
        assertEquals("BILL_024", optBill.get().getBillNumber());

        // find by customer
        List<Bill> billsByCustomer = dao.findByCustomer(customerId);
        assertTrue(!billsByCustomer.isEmpty());
        assertTrue(billsByCustomer.stream().anyMatch(b -> "BILL_024".equals(b.getBillNumber())));
        assertTrue(billsByCustomer.stream().anyMatch(b -> "BILL_025".equals(b.getBillNumber())));

        // sequence check for current year (should be >= 1 and not decrease)
        int year = LocalDate.now().getYear();
        int seq = dao.nextSequenceForYear(year);
        assertTrue(seq >= 1);
    }
}
