/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.try1;

/**
 *
 * @author kamwi
 */
public class CashOnDelivery extends PaymentMethodBase {

    //@Override
    public void processPayment() {
        setPaymentMethod("Cash On Delivery");
        System.out.println("You selected COD! Please have the exact amount ready on delivery.\nYour order is successfully placed!");
    }
}
