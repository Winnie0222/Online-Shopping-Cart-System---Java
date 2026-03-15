package com.mycompany.try1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;      // Import List
import java.util.ArrayList; // Import ArrayList

public class Store {

    private String username; // Field to store the username
    private Map<String, Category> categories = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    // Define the shipping and payment handlers
    private ShippingHandler shippingHandler;  // Define shippingHandler
    private PaymentHandler paymentHandler;    // Define paymentHandler

    private final ShoppingCart cart;  // Move cart here to persist across runs
    private final Wishlist wishlist;  // Use the passed wishlist
    private final User user;  // Store the User object

    // Constructor that accepts User, Wishlist, and ShoppingCart
    public Store(User user, Wishlist wishlist, ShoppingCart cart) {
        this.user = user;  // Initialize the user
        this.username = user.getLoggedInUser();  // Get the username from the User object
        this.wishlist = wishlist;  // Initialize with user's wishlist
        this.cart = cart;  // Initialize the shopping cart

        // Initialize shipping and payment handlers
        this.shippingHandler = new ShippingHandler(scanner);  // Initialize shippingHandler
        this.paymentHandler = new PaymentHandler(scanner);    // Initialize paymentHandler
    }

    public void addCategory(Category category) {
        categories.put(category.getName(), category);
    }

    public void displayCategories() {
        System.out.println("\nAvailable categories:");
        int categoryIndex = 1;
        for (String categoryName : categories.keySet()) {
            System.out.println(categoryIndex + ". " + categoryName);
            categoryIndex++;
        }
    }

    // Method to search for an item by name across all categories
    public Item searchItemByName(String name) {
        for (Category category : categories.values()) {
            for (Item item : category.getItems()) {
                if (item.getName().equalsIgnoreCase(name)) {
                    return item;
                }
            }
        }
        return null;  // Return null if no item is found
    }

    // Method to retrieve all unique categories
    public List<Category> getCategories() {
        return new ArrayList<>(categories.values());  // Return the categories as a list
    }

    // Main loop of the store
    public void run() {
        String choice;

        while (true) {
            displayCategories();

            System.out.print("Enter the number of the category you want to choose (or type '0' to exit): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
                continue;
            }

            int categoryChoice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            if (categoryChoice == 0) {
                System.out.println("Exiting program.");
                break;
            }

            if (categoryChoice > 0 && categoryChoice <= categories.size()) {
                String chosenCategory = (String) categories.keySet().toArray()[categoryChoice - 1];
                Category category = categories.get(chosenCategory);

                category.displayItems();

                while (true) {
                    System.out.print("Enter the number of the item you want to choose (or type '0' to finish this category): ");
                    choice = scanner.nextLine();

                    if (choice.equals("0")) {
                        break;
                    }

                    try {
                        int itemNumber = Integer.parseInt(choice);
                        if (itemNumber > 0 && itemNumber <= category.getItems().size()) {
                            Item selectedItem = category.getItems().get(itemNumber - 1);

                            // Ask the user if they want to add to the cart or wishlist
                            System.out.print("Would you like to add this item to the cart or wishlist? (cart/wishlist): ");
                            String addItemChoice = scanner.nextLine();

                            if (addItemChoice.equalsIgnoreCase("cart")) {
                                cart.addItem(selectedItem);

                            } else if (addItemChoice.equalsIgnoreCase("wishlist")) {
                                wishlist.addItem(selectedItem);

                            } else {
                                System.out.println("Invalid choice. Please enter 'cart' or 'wishlist'.");
                            }
                        } else {
                            System.out.println("Invalid item number. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                }
            } else {
                System.out.println("Invalid category number. Please try again.");
            }

            // Ask what the user wants to do next
            while (true) {
                System.out.println("\nWhat would you like to do next? ");
                System.out.println("> View my shopping cart          [view-cart] ");
                System.out.println("> View my wishlist               [view-wishlist] ");
                System.out.println("> Remove item from my cart       [remove-cart] ");
                System.out.println("> Remove item from my wishlist   [remove-wishlist] ");
                System.out.println("> Clear my shopping cart         [clear-cart] "); // Added option to clear the cart
                System.out.println("> Choose another category        [continue] ");
                System.out.println("> Proceed to checkout            [checkout] ");
                System.out.println("> Back to main menu              [cancel] ");
                System.out.print("Enter your option: ");
                String actionChoice = scanner.nextLine();

                if (actionChoice.equalsIgnoreCase("view-cart")) {
                    cart.displayItems();
                } else if (actionChoice.equalsIgnoreCase("view-wishlist")) {
                    wishlist.displayItems();
                } else if (actionChoice.equalsIgnoreCase("remove-cart")) {
                    cart.displayItems();
                    cart.removeItemBySelection();
                    // Handle cart removal
                } else if (actionChoice.equalsIgnoreCase("remove-wishlist")) {
                    wishlist.displayItems();
                    wishlist.removeItemBySelection();
                    // Handle wishlist removal
                } else if (actionChoice.equalsIgnoreCase("clear-cart")) {  // New option to clear the cart
                    cart.clearCart();  // Call the clearCart() function
                } else if (actionChoice.equalsIgnoreCase("continue")) {
                    break;
                } else if (actionChoice.equalsIgnoreCase("checkout")) {
                    if (cart.getSelectedItems().isEmpty()) {
                        System.out.println("\nYour cart is empty\n");
                    } else {
                        System.out.println("\nProceeding to checkout...");
                        double shippingPrice = shippingHandler.handleShipping();
                        cart.displayItems();

                        System.out.println("Shipping option: " + shippingHandler.getShippingOption());
                        if (shippingHandler.getShippingOption().equals("Doorstep Delivery")) {
                            System.out.println("Shipping address: " + shippingHandler.getAddress());
                        } else {
                            System.out.println("Pickup location: " + shippingHandler.getPickupLocation());
                        }

                        double totalAmount = cart.getTotalPrice();
                        System.out.print("\nDo you want to apply a voucher? (Y/N): ");
                        char voucherOp = scanner.next().charAt(0);
                        scanner.nextLine(); // consume newline character

                        double finalAmount = totalAmount;
                        double totalDiscount = 0;

                        if (voucherOp == 'Y' || voucherOp == 'y') {
                            System.out.print("Enter voucher code: ");
                            String voucherCode = scanner.nextLine();
                            DiscountVoucher discountVoucher = new DiscountVoucher();
                            ShippingVoucher shippingVoucher = new ShippingVoucher();
                            if (voucherCode.equals("PROMOTIONDISCOUNT")) {
                                finalAmount = discountVoucher.calculateVoucher(voucherCode, totalAmount);
                                totalDiscount = discountVoucher.getTotalDiscount();  // Get the total discount applied
                            } else if (voucherCode.equals("FREESHIPPING01")) {
                                shippingPrice = shippingVoucher.calculateVoucher(voucherCode, shippingPrice);
                            }

                            // Inform the user if voucher application was successful or not
                            System.out.println(discountVoucher.getVoucherMessage());
                        }

                        double finalTotalAmount = finalAmount + shippingPrice;

                        System.out.println("\nShipping price: RM " + String.format("%.2f", shippingPrice));
                        System.out.println("Total amount after shipping and discount: RM " + String.format("%.2f", finalTotalAmount));

                        // Ask for payment confirmation
                        paymentHandler.confirmOrder(scanner, finalTotalAmount);

                        // Create the receipt before clearing the cart
                        Receipt receipt = new Receipt(cart, username, shippingHandler.getShippingOption(),
                                shippingHandler.getAddress(), shippingHandler.getPickupLocation(),
                                shippingPrice, totalDiscount, finalTotalAmount, paymentHandler.getSelectedPaymentMethod());

                        // Print the receipt immediately after purchase
                        receipt.printReceipt();

                        // Add the receipt to the user's order history
                        user.addReceiptToHistory(receipt);  // Save the receipt to order history

                        // Clear the cart after receipt is printed and stored
                        cart.clearCart();
                        System.out.println("Thank you for your purchase! Your cart has been cleared.");
                        return;
                    }
                } else if (actionChoice.equalsIgnoreCase("cancel")) {
                    System.out.println("Returning back to main menu...");
                    return;  // This brings you back to the main menu without resetting the wishlist
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

}
