/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.try1;

/**
 *
 * @author kamwi
 */
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CardPayment extends PaymentMethodBase {
    private Scanner scanner;

    public CardPayment(Scanner scanner) {
        this.scanner = scanner;
    }

    //@Override
    @Override
    public void processPayment() {
        setPaymentMethod("Credit/Debit Card");
        validateCard();
    }

    // Card validation method
    private void validateCard() {
        System.out.println("You selected Credit/Debit Card.\n");

        String cardNumber, cvv, expiryDate, cardHolderName;
        boolean isValid = false;

        // Validate card number
        while (true) {
            System.out.print("Enter card number (16 digits): ");
            cardNumber = scanner.nextLine();
            if (cardNumber.matches("\\d{16}")) {
                break;
            } else {
                System.out.println("Invalid card number! Please enter a 16-digit card number.\n");
            }
        }

        // Validate card holder name
        while (true) {
            System.out.print("Enter card holder name (at least 3 words): ");
            cardHolderName = scanner.nextLine();
            if (cardHolderName.matches("[a-zA-Z\\s]+") && cardHolderName.trim().split("\\s+").length >= 3) {
                break;
            } else {
                System.out.println("Invalid name. Please enter a name with only alphabets and at least 3 words.\n");
            }
        }

        // Validate CVV
        while (true) {
            System.out.print("Enter CVV (3 digits): ");
            cvv = scanner.nextLine();
            if (cvv.matches("\\d{3}")) {
                break;
            } else {
                System.out.println("Invalid CVV. Please enter a 3-digit CVV.\n");
            }
        }

        // Validate expiry date
        while (true) {
            System.out.print("Enter expiry date (MM/YY): ");
            expiryDate = scanner.nextLine();
            try {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/yy");
                YearMonth expiry = YearMonth.parse(expiryDate, dateFormat);
                if (expiry.isAfter(YearMonth.now())) {
                    isValid = true;
                    break;
                } else {
                    System.out.println("Invalid expiry date. The expiry date cannot be in the past.\n");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter the date in MM/YY format.\n");
            }
        }

        if (isValid) {
            System.out.println("\nPayment by credit/debit card was successful!\nYour order is successfully placed!");
        }
    }
}
