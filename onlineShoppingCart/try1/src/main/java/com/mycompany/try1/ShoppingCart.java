package com.mycompany.try1;

import java.util.Map;
import java.util.Scanner;

public class ShoppingCart extends ItemContainer {

    public double getTotalPrice() {
        double totalCost = 0.0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            totalCost += item.getPrice() * quantity;
        }
        return totalCost;
    }

    @Override
    public void addItem(Item item) {
        Scanner scanner = new Scanner(System.in);
        int quantity = 0;

        // Loop until the user enters a valid positive quantity or 0 to cancel
        while (true) {
            System.out.print("Enter quantity to add (0 to cancel): ");
            quantity = scanner.nextInt();

            if (quantity < 0) {
                System.out.println("Invalid input! Quantity cannot be negative. Please try again.");
            } else if (quantity == 0) {
                System.out.println("Item addition canceled.\n");
                return;  // Exit the method, canceling the addition
            } else {
                break;  // Exit the loop once a valid quantity is entered
            }
        }

        // Add the item with the valid quantity to the cart
        items.put(item, items.getOrDefault(item, 0) + quantity);
        System.out.println(quantity + " x " + item.getName() + " added to your cart.\n");
    }

    @Override
    public void removeItem(Item item) {
        if (items.containsKey(item)) {
            System.out.print("Enter quantity to remove: ");
            Scanner scanner = new Scanner(System.in);
            int quantity = scanner.nextInt();

            int currentQuantity = items.get(item);
            if (quantity >= currentQuantity) {
                items.remove(item);
                System.out.println(item.getName() + " completely removed from your cart.\n");
            } else {
                items.put(item, currentQuantity - quantity);
                System.out.println(quantity + " x " + item.getName() + " removed from your cart.\n");
            }
        } else {
            System.out.println("Item not found in the cart.\n");
        }
    }

    // Method to remove an item by selecting from the displayed list
    public void removeItemBySelection() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect an item to remove:");

        int index = 1;
        for (Item item : items.keySet()) {
            System.out.println(index + ". " + item.getName());
            index++;
        }

        System.out.print("Enter the item number to remove: ");
        int itemNumber = scanner.nextInt();

        if (itemNumber > 0 && itemNumber <= items.size()) {
            Item itemToRemove = (Item) items.keySet().toArray()[itemNumber - 1];
            removeItem(itemToRemove);  // Use the existing removeItem method
        } else {
            System.out.println("Invalid item number. Please try again.");
        }
    }

    // Override displayItems to show total cost as well
    @Override
    public void displayItems() {
        System.out.println("\nShopping Cart:");
        super.displayItems();  // Call the base method to list items
        if (!items.isEmpty()) {
            double totalCost = 0.0;
            for (Map.Entry<Item, Integer> entry : items.entrySet()) {
                Item item = entry.getKey();
                int quantity = entry.getValue();
                totalCost += item.getPrice() * quantity;
            }
            System.out.printf("Total cost: $%.2f%n", totalCost);
        }
    }

    public void clearCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is already empty.\n");
        } else {
            items.clear();
            System.out.println("All items have been removed from your cart.\n");
        }
    }
}
