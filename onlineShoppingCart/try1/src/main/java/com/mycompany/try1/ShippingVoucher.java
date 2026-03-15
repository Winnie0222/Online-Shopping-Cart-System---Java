/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.try1;

/**
 *
 * @author kamwi
 */
public class ShippingVoucher implements Voucher {
    private final String voucherCode = "FREESHIPPING01";

    @Override
    public String getVoucherCode() {
        return voucherCode;
    }

    @Override
    public double calculateVoucher(String code, double shippingFee) {
        if (code.equalsIgnoreCase(getVoucherCode())) {
            shippingFee = 0.0;
            return shippingFee; // Apply free shipping
        }
        return shippingFee; // No discount applied
    }
}
