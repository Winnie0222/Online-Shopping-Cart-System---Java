package com.mycompany.try1;

import java.util.Scanner;

public class Wishlist extends ItemContainer {

    @Override
    public void addItem(Item item) {
        // Always add the item with quantity 1
        items.put(item, 1);  // Ignore existing quantity, always 1 for wishlist
        System.out.println(item.getName() + " added to your wishlist.\n");
    }

    @Override
    public void removeItem(Item item) {
        // Always remove the item completely in wishlist, no need to specify quantity
        if (items.containsKey(item)) {
            items.remove(item);
            System.out.println(item.getName() + " removed from your wishlist.\n");
        } else {
            System.out.println("Item not found in the wishlist.");
        }
    }

    // Method to remove an item by selecting from the displayed list
    public void removeItemBySelection() {
        if (items.isEmpty()) {
            System.out.println("Your wishlist is empty.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect an item to remove from your wishlist:");

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

    // Wishlist-specific display
    @Override
    public void displayItems() {
        System.out.println("\nWishlist:");
        super.displayItems();
    }
}
