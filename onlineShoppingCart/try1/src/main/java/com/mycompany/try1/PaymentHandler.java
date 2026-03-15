/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.try1;

/**
 *
 * @author kamwi
 */

import java.util.Scanner;

public class PaymentHandler {
    private PaymentMethodBase paymentMethod;
    private Scanner scanner;
    
    public PaymentHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public void confirmOrder(Scanner scanner, double totalAmount) {
        System.out.println("Your total amount is RM " + String.format("%.2f", totalAmount));
        selectPaymentMethod(scanner);
    }

    private void selectPaymentMethod(Scanner scanner) {
        int paymentChoice = 0;

        while (true) {
            System.out.println("\nSelect payment method:");
            System.out.println("1. Cash On Delivery (COD)");
            System.out.println("2. Credit/Debit Card");
            System.out.print("Enter your choice (1-2): ");

            try {
                paymentChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (paymentChoice == 1 || paymentChoice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice! Please enter option 1 or 2.\n");
                }
            } catch (Exception ePay) {
                System.out.println("Invalid choice! Please enter a valid option.\n");
                scanner.next(); // Clear invalid input
            }
        }
        

        switch (paymentChoice) {
            case 1 -> {
                paymentMethod = new CashOnDelivery();
                paymentMethod.processPayment();
            }
            case 2 -> {
                paymentMethod = new CardPayment(scanner);
                paymentMethod.processPayment();
            }
        }
    }
    
    public String getSelectedPaymentMethod() {
        return paymentMethod.getPaymentMethod();
    }

}
