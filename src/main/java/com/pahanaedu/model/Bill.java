package com.pahanaedu.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bill {
    private int billId;              // AUTO_INCREMENT primary key
    private String billNumber;       // Unique bill number (e.g., BILL-2025-0001)
    private int customerId;          // FK to Customer
    private int unitsConsumed;       // Number of units consumed
    private BigDecimal ratePerUnit;  // Rate per unit
    private BigDecimal totalAmount;  // Computed total
    private LocalDateTime billDate;  // Date/time of bill generation

    public Bill() {
    }

    public Bill(int billId, String billNumber, int customerId,
                int unitsConsumed, BigDecimal ratePerUnit,
                BigDecimal totalAmount, LocalDateTime billDate) {
        this.billId = billId;
        this.billNumber = billNumber;
        this.customerId = customerId;
        this.unitsConsumed = unitsConsumed;
        this.ratePerUnit = ratePerUnit;
        this.totalAmount = totalAmount;
        this.billDate = billDate;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(int unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public BigDecimal getRatePerUnit() {
        return ratePerUnit;
    }

    public void setRatePerUnit(BigDecimal ratePerUnit) {
        this.ratePerUnit = ratePerUnit;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }
}
