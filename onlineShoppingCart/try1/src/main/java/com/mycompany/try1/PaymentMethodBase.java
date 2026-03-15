/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.try1;

/**
 *
 * @author kamwi
 */
public abstract class PaymentMethodBase {
    private String paymentMethod;

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    // Abstract method for processing payment
    public abstract void processPayment();
}
