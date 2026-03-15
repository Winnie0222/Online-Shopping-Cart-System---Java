/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.try1;

/**
 *
 * @author kamwi
 */
import java.util.Arrays;
import java.util.Scanner;

public class ShippingHandler {
    private Scanner scanner;
    private String shippingOption;
    private String address;
    private String pickupLocation;
    private double voucherDiscount;

    public ShippingHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getShippingOption() {
        return shippingOption;
    }

    public String getAddress() {
        return address;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public double getVoucherDiscount() {
        return voucherDiscount;
    }

    public double handleShipping() {
    System.out.println("Shipping option:");
    System.out.println("1. Doorstep Delivery (RM 5.00)");
    System.out.println("2. Self Collection (RM 3.00)");

    double shippingPrice = 0.0;
    while (true) {
        try {
            System.out.print("Select a shipping option: ");
            int shippingChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline after the number

            switch (shippingChoice) {
                case 1 -> {
                    shippingPrice = 5.00;
                    shippingOption = "Doorstep Delivery ($" + shippingPrice + ")";
                    handleAddressInput();
                    return shippingPrice;  // Exit the loop and return the price
                }
                case 2 -> {
                    shippingPrice = 3.00;
                    shippingOption = "Self Collection ($" + shippingPrice + ")";
                    processPickupLocation();
                    return shippingPrice;  // Exit the loop and return the price
                }
                default -> System.out.println("Invalid input, please enter 1 or 2.");
            }
        } catch (Exception eX) {
            System.out.println("Invalid input, please enter again.");
            scanner.nextLine();  // Clear the input buffer
        }
    }
}


    private double processPickupLocation() {
        System.out.println("Select a pickup location:");
        System.out.println("1. MBE");
        System.out.println("2. CollectCo");
        System.out.println("3. ParcelHub");

        while (true) {
            try {
                System.out.print("Enter your option: ");
                int pickupOption = scanner.nextInt();
                scanner.nextLine(); // consume newline character

                if (pickupOption == 1 || pickupOption == 2 || pickupOption == 3) {
                    pickupLocation = pickupOption == 1 ? "MBE" : pickupOption == 2 ? "CollectCo" : "ParcelHub";
                    System.out.println("Pickup location selected: " + pickupLocation);
                    return 3.00;
                } else {
                    System.out.println("Invalid input, please choose 1, 2, or 3.");
                }
            } catch (Exception ex) {
                System.out.println("Invalid input, please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private void handleAddressInput() {
        String address1, address2, postcode, state;
        System.out.print("\nPlease enter your shipping address: \n");

        while (true) {
            System.out.print("Address Line 1: ");
            address1 = scanner.nextLine();
            if (address1.length() >= 10) {
                break;
            } else {
                System.out.println("Invalid address, please enter a valid address with at least 10 characters.");
            }
        }

        while (true) {
            System.out.print("Address Line 2: ");
            address2 = scanner.nextLine();
            if (address2.length() >= 5) {
                break;
            } else {
                System.out.println("Invalid address, please enter a valid address with at least 5 characters.");
            }
        }

        while (true) {
            System.out.print("Postcode: ");
            postcode = scanner.nextLine();
            if (postcode.matches("\\d{5}")) {
                break;
            } else {
                System.out.println("Invalid postcode, please enter again.");
            }
        }

        String[] validStates = {"Johor", "Kedah", "Kelantan", "Malacca", "Negeri Sembilan", "Pahang", "Penang", "Perak", "Perlis", "Sabah", "Sarawak", "Selangor", "Terengganu", "Kuala Lumpur"};
        while (true) {
            System.out.print("State: ");
            state = scanner.nextLine().trim();
            if (Arrays.asList(validStates).contains(state)) {
                break;
            } else {
                System.out.println("Invalid state, please enter again.");
            }
        }

        address = address1 + ", " + address2 + ", " + postcode + ", " + state;
        System.out.println("Address entered:\n " + address);
    }

    
}
