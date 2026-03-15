package com.mycompany.try1;

public class DiscountVoucher implements Voucher {
    private final String discountCode = "PROMOTIONDISCOUNT";
    private final double discountRate = 0.1; // 10% discount
    private double totalDiscount = 0.0;
    private boolean isValid = false;

    @Override
    public String getVoucherCode() {
        return discountCode;
    }

    @Override
    public double calculateVoucher(String code, double totalAmount) {
        if (code.equalsIgnoreCase(getVoucherCode())) {
            totalDiscount = totalAmount * discountRate;
            isValid = true;
            return totalAmount - totalDiscount; // Apply 10% discount
        } else {
            isValid = false;
            return totalAmount; // Return the original amount (no discount)
        }
    }

    // Method to get a message about voucher validity
    public String getVoucherMessage() {
        if (isValid) {
            return "Voucher applied successfully!";
        } else {
            return "Invalid voucher code.";
        }
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }
}
