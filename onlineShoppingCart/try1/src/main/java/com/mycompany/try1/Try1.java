/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.try1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Try1 {
    static Scanner sc = new Scanner(System.in);  // Create a static Scanner for user input
    private final static Set<String> usernames = new HashSet<>(); // store username
    private final static Set<String> gmails = new HashSet<>(); // store gmail address
    private String user = "Alex";  // Default user name
    private String password = "Alex@123";  // Default password
    private String gmail;
    private String gmailPassword;
    private static boolean registered = false; // Track registration status

    // Shared ShoppingCart instance
    private static ShoppingCart cart = new ShoppingCart();  // Shared cart instance

    // Method to set the user name
    void setusername(String user) {
        this.user = user;
    }

    // Method to set the password
    void setpassword(String password) {
        this.password = password;
    }

    // Method to get the user name
    String getusername() {
        return user;
    }

    // Method to get the password
    String getpassword() {
        return password;
    }

    // Method to set the Gmail address
    void setGmail(String gmail) {
        this.gmail = gmail;
    }

    // Method to set the Gmail password
    void setGmailPass(String gmailPassword) {
        this.gmailPassword = gmailPassword;
    }

    // Method to get the Gmail address
    String getGmail() {
        return gmail;
    }

    // Method to get the Gmail password
    String getGmailPassword() {
        return gmailPassword;
    }

    // Method to prevent redundancy of username
    static boolean isUsernameExists(String username) {
        return usernames.contains(username);
    }

    static void addUsername(String username) {
        usernames.add(username);
    }

    // Method to prevent redundancy of gmail address
    static boolean isGmailExists(String gmail) {
        return gmails.contains(gmail);
    }

    static void addGmail(String gmail) {
        gmails.add(gmail);
    }

    // Method to validate the email format
    static boolean isValidEmailFormat(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Main method, the entry point of the program
    public static void main(String[] args) {
        User obj = new User();  // Create an instance of the User class

        System.out.println("-----------------------------------------------------------");
        System.out.println("""
                            _   _       _                 _     _       _        __ _ 
                           | | | | _ _ | |__  __ _  _ _  | |   (_)__ __(_) _ _  / _` |
                           | |_| || '_||  _ \\/ _` || ' \\ | |__ | |\\ V /| || ' \\ \\__. |
                            \\___/ |_|  |____/\\__/_||_||_||____||_| \\_/ |_||_||_||___/ 
                           """);
        System.out.println("-----------------------------------------------------------");
        System.out.println("               WELCOME TO URBANLIVING                             ");
        System.out.println("-----------------------------------------------------------");

        while (true) {
            if (obj.isLoggedIn()) {  // If a user is logged in
                System.out.println("\nLogged in as: " + obj.getLoggedInUser());
                System.out.println("\nMain menu:");
                System.out.println("1. Browse Menu");
                System.out.println("2. Products Searching");
                System.out.println("3. WishList");
                System.out.println("4. View Order History");
                System.out.println("5. View Cart");  // <-- New option for viewing the cart
                System.out.println("6. Log Out");
                System.out.print("Enter your choice: ");
            } else {
                System.out.println("\n1. Sign Up");
                System.out.println("2. Log In");
                System.out.println("3. Forget Username");
                System.out.println("4. Forget Password");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
            }

            try {
                int choice = sc.nextInt();  // Read the user's choice
                if (obj.isLoggedIn()) {
                    // Handle the choices available for logged-in users
                    switch (choice) {
                        case 1 -> User.browseMenu(obj, cart);  // Pass cart to browseMenu
                        case 2 -> User.productSearch(obj, cart);  // Pass cart to productSearch
                        case 3 -> User.wishList(obj);  // Display Wishlist
                        case 4 -> User.viewOrderHistory(obj);  // View Order History
                        case 5 -> cart.displayItems();  // Display the contents of the cart
                        case 6 -> {
                            if (User.logOutConfirmation()) {
                                obj.logOut();  // Log Out
                                delaySeconds(3);  // Adding a 3-second delay after displaying "Logged out successfully"
                            } else {
                                System.out.println("\n--- Logout canceled ! --- ");
                            }
                        }
                        default -> System.out.println("\n--- Invalid choice! Please enter between 1 to 6 --- ");
                    }
                } else {
                    // Handle the choices available before login
                    switch (choice) {
                        case 1 -> {
                            User.signUp(obj);  // Sign Up
                            registered = true;  // Set registered flag to true
                        }
                        case 2 -> {
                            if (!obj.isLoggedIn()) {
                                User.login(obj);  // Log In
                            } else {
                                System.out.println("\nYou are already logged in as: " + obj.getLoggedInUser());
                            }
                        }
                        case 3 -> {
                            if (registered) {
                                User.resetUsername(obj);  // Forget Username
                            } else {
                                System.out.println("\n--- You are not registered! Please sign up first --- ");
                            }
                        }
                        case 4 -> {
                            if (registered) {
                                User.resetPassword(obj);  // Forget Password
                            } else {
                                System.out.println("\n--- You are not registered! Please sign up first --- ");
                            }
                        }
                        case 5 -> {
                            System.out.println("\n-> -> Exiting the program <- <-");
                            System.exit(0);  // Exit
                        }
                        default -> System.out.println("\n--- Invalid choice! Please enter between 1 to 5 --- ");
                    }
                }
            } catch (Exception e) {
                System.out.println("\n--- Invalid choice! Please enter a valid number --- ");
                sc.next(); // Clear invalid input
            }
        }
    }

    // Method to introduce a delay in seconds
    static void delaySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
