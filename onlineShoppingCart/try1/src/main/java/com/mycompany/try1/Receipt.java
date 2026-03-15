package com.mycompany.try1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;  // <-- Add this line to import HashMap

public class Receipt {

    private Map<Item, Integer> purchasedItems;  // Store the items from the cart
    private String username;
    private String shippingOption;
    private String address;
    private String pickupLocation;
    private double shippingPrice;
    private double voucherDiscount;
    private double totalAmount;
    private String selectedPaymentMethod;

    public Receipt(ShoppingCart cart, String username, String shippingOption, String address,
                   String pickupLocation, double shippingPrice, double voucherDiscount, double totalAmount,
                   String selectedPaymentMethod) {
        this.purchasedItems = new HashMap<>(cart.getSelectedItems());  // Copy items to avoid reference issues
        this.username = username;
        this.shippingOption = shippingOption;
        this.address = address;
        this.pickupLocation = pickupLocation;
        this.shippingPrice = shippingPrice;
        this.voucherDiscount = voucherDiscount;
        this.totalAmount = totalAmount;
        this.selectedPaymentMethod = selectedPaymentMethod;
    }
    
    public int generateInvoiceNumber() {
        Random random = new Random();
        int invoiceNumber = 1000 + random.nextInt(9000); // Generates a random 4-digit number between 1000 and 9999
        return invoiceNumber;
    }

    public void printReceipt() {
    // Print receipt header
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    System.out.println("\n     ------ BELOW IS YOUR RECEIPT ------\n");
    System.out.println("==================================================\n");
    System.out.println("           WELCOME TO URBAN LIVING !      ");
    System.out.println("            Tel : +60 123-456-789         \n");
    System.out.println("Date/Time: " + dtf.format(now));
    System.out.println("Invoice no.: " + generateInvoiceNumber());
    System.out.println("Customer name: " + username);
    System.out.println("==================================================");
    System.out.printf("%-15s %-24s %-17s%n", "Item", "Quantity", "Price");
    System.out.println("--------------------------------------------------");

    // Print items from the purchasedItems (not cart)
    double totalItemCost = 0.0;
    for (Map.Entry<Item, Integer> entry : purchasedItems.entrySet()) {
        Item item = entry.getKey();
        int quantity = entry.getValue();
        double itemTotalPrice = item.getPrice() * quantity;

        System.out.printf("%-18s %-18d RM %-12.2f%n", item.getName(), quantity, itemTotalPrice);
        totalItemCost += itemTotalPrice;
    }

    System.out.println("Total Item Cost: RM " + String.format("%.2f", totalItemCost));
    System.out.println("==================================================");
    System.out.println("Shipping Option: " + shippingOption);
    if (shippingOption.equals("Doorstep Delivery")) {
        System.out.println("Shipping Address: " + address);
    } else {
        System.out.println("Pickup Location: " + pickupLocation);
    }
    System.out.println("Shipping Price: RM " + String.format("%.2f", shippingPrice));
    System.out.println("Voucher Discount: RM " + String.format("%.2f", voucherDiscount));
    System.out.println("=================================================");
    System.out.println("Total Amount: RM " + String.format("%.2f", totalAmount));
    System.out.println("Payment Method: " + selectedPaymentMethod);
    System.out.println("=================================================\n");
    System.out.println("      Thank You For Shopping With Us !      ");
    System.out.println("               See You Again !              \n");
    System.out.println("=================================================\n");
}

}
