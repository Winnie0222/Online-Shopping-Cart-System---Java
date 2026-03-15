/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.try1;

/**
 *
 * @author kamwi
 */
import java.util.HashMap;
import java.util.Map;

// Base class for both ShoppingCart and Wishlist
public abstract class ItemContainer {
    protected Map<Item, Integer> items;

    public ItemContainer() {
        items = new HashMap<>();
    }

    // Abstract method to add item, with or without quantity depending on the subclass
    public abstract void addItem(Item item);

    // Abstract method to remove item, with or without quantity depending on the subclass
    public abstract void removeItem(Item item);

    // Display the contents of the container
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("The container is empty.");
        } else {
            int index = 1;
            System.out.printf("+----------+-----------------+----------+------------+%n");
            System.out.printf("| %-8s | %-15s | %-8s | %-10s |%n", "Index", "Item", "Quantity", "Price");
            System.out.printf("+----------+-----------------+----------+------------+%n");
            for (Map.Entry<Item, Integer> entry : items.entrySet()) {
                Item item = entry.getKey();
                int quantity = entry.getValue();
                System.out.printf("| %-8s | %-15s | %-8d | $%-9.2f |%n", index, item.getName(), quantity, item.getPrice() * quantity);
                index++;
            }
            System.out.printf("+----------+-----------------+----------+------------+%n");
        }
    }

    public Map<Item, Integer> getSelectedItems() {
        return items;
    }
}
