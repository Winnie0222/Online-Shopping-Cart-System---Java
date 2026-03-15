/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.try1;

/**
 *
 * @author qhuix
 */

import java.util.List;
import java.util.Scanner;

public class ProductSearch {

    // Method to search for products by name or category
    public static void searchProduct(Store store, Scanner scanner) {
        boolean continueSearching = true;

        while (continueSearching) {
            System.out.println("\n--- Product Search ---");
            System.out.println("Search by: ");
            System.out.println("1. Item Name");
            System.out.println("2. Category");
            System.out.println("3. Exit Search");
            System.out.print("Enter your choice: ");
            int searchChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (searchChoice) {
                case 1:
                    // Search by item name
                    System.out.print("Enter product name to search: ");
                    String productName = scanner.nextLine();

                    Item foundItem = store.searchItemByName(productName);
                    if (foundItem != null) {
                        System.out.println("Product found: " + foundItem.getName() + " ($" + foundItem.getPrice() + ")");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 2:
                    // Search by category
                    List<Category> categories = store.getCategories();  // Ensure this returns List<Category>
                    System.out.println("Available categories:");
                    for (int i = 0; i < categories.size(); i++) {
                        System.out.println((i + 1) + ". " + categories.get(i).getName());
                    }

                    System.out.print("Enter the number of the category to search in: ");
                    int categoryChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (categoryChoice > 0 && categoryChoice <= categories.size()) {
                        Category selectedCategory = categories.get(categoryChoice - 1);
                        List<Item> items = selectedCategory.getItems();

                        System.out.println("Items in " + selectedCategory.getName() + ":");
                        if (items.isEmpty()) {
                            System.out.println("No items available in this category.");
                        } else {
                            for (Item item : items) {
                                System.out.println(item.getName() + " ($" + item.getPrice() + ")");
                            }
                        }
                    } else {
                        System.out.println("Invalid category choice.");
                    }
                    break;

                case 3:
                    // Exit the search loop
                    System.out.println("Exiting product search.");
                    continueSearching = false;
                    break;

                default:
                    System.out.println("Invalid search option.");
                    break;
            }

            // Ask the user if they want to continue searching
            if (continueSearching) {
                System.out.print("\nDo you want to search for another item? (yes/no): ");
                String userChoice = scanner.nextLine().trim().toLowerCase();
                if (!userChoice.equals("yes")) {
                    continueSearching = false;
                    System.out.println("Exiting product search.");
                }
            }
        }
    }
}
