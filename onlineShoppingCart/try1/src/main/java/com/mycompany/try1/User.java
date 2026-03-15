package com.mycompany.try1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class User extends Try1 {

    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private int usernameAttempts = 0;
    private int passwordAttempts = 0;
    private boolean loggedIn = false;  // Indicates whether a user is currently logged in
    private String loggedInUser = "";  // Stores the user who is currently logged in
    private static boolean registered = false;  // Flag to track whether any user is registered
    private Wishlist wishlist = new Wishlist();  // Keep Wishlist as an instance variable
    private ShoppingCart cart = new ShoppingCart(); 

    private List<Receipt> orderHistory = new ArrayList<>();

    public void addReceiptToHistory(Receipt receipt) {
        orderHistory.add(receipt); // Add each order's receipt to the list
    }

    public List<Receipt> getOrderHistory() {
        return orderHistory;
    }

    // Method to view the order history
    static void viewOrderHistory(User obj) {
        if (obj.getOrderHistory().isEmpty()) {
            System.out.println("\n--- No previous orders found ---");
            return;
        }

        System.out.println("\n--- Order History ---\n");
        for (int i = 0; i < obj.getOrderHistory().size(); i++) {
            Receipt receipt = obj.getOrderHistory().get(i);
            System.out.println("Order " + (i + 1) + ":");
            receipt.printReceipt(); // Display the details of each receipt
        }
    }

    // Other methods remain the same
    // Method to handle Browse Menu option
static void browseMenu(User obj, ShoppingCart cart) {
    // Create categories and items
    Category mattress = new Category("Mattress");
    mattress.addItem(new Item("Getha", 499.99, "Mattress"));
    mattress.addItem(new Item("SweetDream", 599.99, "Mattress"));
    mattress.addItem(new Item("Neckpro", 1299.99, "Mattress"));

    Category bedframe = new Category("Bedframe");
    bedframe.addItem(new Item("Harvey Norman", 199.99, "Bedframe"));
    bedframe.addItem(new Item("Ruma", 299.99, "Bedframe"));
    bedframe.addItem(new Item("Ikea", 99.99, "Bedframe"));

    Category pillow = new Category("Pillow");
    pillow.addItem(new Item("Forbes", 49.99, "Pillow"));
    pillow.addItem(new Item("Goodnite", 59.99, "Pillow"));
    pillow.addItem(new Item("Gaias", 99.99, "Pillow"));

    Category comforter = new Category("Comforter");
    comforter.addItem(new Item("Buffy Cloud", 59.99, "Comforter"));
    comforter.addItem(new Item("Rest Evercool", 49.99, "Comforter"));
    comforter.addItem(new Item("Crane & Canopy", 39.99, "Comforter"));

    // Create a store and pass the User object and Wishlist
    Store store = new Store(obj, obj.getWishlist(), cart);
    store.addCategory(mattress);
    store.addCategory(bedframe);
    store.addCategory(pillow);
    store.addCategory(comforter);

    // Run the store
    store.run();
}

// Method to handle Products Searching option
static void productSearch(User obj, ShoppingCart cart) {
    Store store = new Store(obj, obj.getWishlist(), cart); // Pass the User object, not loggedInUser

    // Create and populate the categories with items
    Category mattress = new Category("Mattress");
    mattress.addItem(new Item("Getha", 499.99, "Mattress"));
    mattress.addItem(new Item("SweetDream", 599.99, "Mattress"));
    mattress.addItem(new Item("Neckpro", 1299.99, "Mattress"));

    Category bedframe = new Category("Bedframe");
    bedframe.addItem(new Item("Harvey Norman", 199.99, "Bedframe"));
    bedframe.addItem(new Item("Ruma", 299.99, "Bedframe"));
    bedframe.addItem(new Item("Ikea", 99.99, "Bedframe"));

    Category pillow = new Category("Pillow");
    pillow.addItem(new Item("Forbes", 49.99, "Pillow"));
    pillow.addItem(new Item("Goodnite", 59.99, "Pillow"));
    pillow.addItem(new Item("Gaias", 99.99, "Pillow"));

    Category comforter = new Category("Comforter");
    comforter.addItem(new Item("Buffy Cloud", 59.99, "Comforter"));
    comforter.addItem(new Item("Rest Evercool", 49.99, "Comforter"));
    comforter.addItem(new Item("Crane & Canopy", 39.99, "Comforter"));

    // Add all categories to the store
    store.addCategory(mattress);
    store.addCategory(bedframe);
    store.addCategory(pillow);
    store.addCategory(comforter);

    // Call the ProductSearch method to start the search
    ProductSearch.searchProduct(store, new Scanner(System.in));
}


    // Method to handle WishList option
    static void wishList(User obj) {
        System.out.println("\n--- WishList ---");
        Wishlist userWishlist = obj.getWishlist();  // Access the wishlist of the logged-in user
        userWishlist.displayItems();  // Display items in the wishlist
    }

    // Other existing methods like signUp, login, etc.

    public Wishlist getWishlist() {
        return wishlist;
    }

    // Method to handle Sign Up option
    static void signUp(User obj) {
        String enteredUser;
        do {
            System.out.print("\nEnter your User Name: ");
            enteredUser = sc.next();
            if (isUsernameExists(enteredUser)) {
                System.out.println("Username already exists! Please try a different username.");
            }
        } while (isUsernameExists(enteredUser));
        obj.setusername(enteredUser);  // Set the user name using the setusername method
        addUsername(enteredUser);  // Add the username to the set

        String enteredPassword;
        do {
            System.out.print("Enter your User Password (at least 8 characters): ");
            enteredPassword = sc.next();
            if (enteredPassword.length() < 8) {
                System.out.println("Password must be at least 8 characters long! Please try again.");
            }
        } while (enteredPassword.length() < 8);
        obj.setpassword(enteredPassword);  // Set the password using the setpassword method

        String enteredGmail;
        do {
            System.out.print("Enter your Gmail: ");
            enteredGmail = sc.next();
            if (!isValidEmailFormat(enteredGmail)) {
                System.out.println("Invalid email format! Please enter a valid email.");
            } else if (isGmailExists(enteredGmail)) {
                System.out.println("Gmail already exists! Please try a different Gmail.");
            }
        } while (!isValidEmailFormat(enteredGmail) || isGmailExists(enteredGmail));
        obj.setGmail(enteredGmail);  // Set the Gmail address using the setGmail method
        addGmail(enteredGmail);  // Add the Gmail to the set

        String enteredGmailPass;
        do {
            System.out.print("Enter your Gmail Password (at least 8 characters): ");
            enteredGmailPass = sc.next();
            if (enteredGmailPass.length() < 8) {
                System.out.println("Gmail password must be at least 8 characters long! Please try again.");
            }
        } while (enteredGmailPass.length() < 8);
        obj.setGmailPass(enteredGmailPass);  // Set the Gmail password using the setGmailPass method

        delaySeconds(2);  // Adding a 2-second delay
        registered = true;  // Set the registered flag to true
        System.out.println("\n--- Registration Successful! --- ");
    }

    // Method to handle Login option
    static void login(User obj) {
        if (!registered) {
            System.out.println("\n--- You are not registered! Please sign up first --- ");
            return;
        }

        if (obj.usernameAttempts < MAX_LOGIN_ATTEMPTS) {
            System.out.print("\nEnter your User Name: ");
            String enteredUser = sc.next();

            if (obj.getusername().equals(enteredUser)) {
                System.out.print("Enter your User Password: ");
                String enteredPassword = sc.next();

                if (obj.getpassword().equals(enteredPassword)) {
                    obj.loggedIn = true;
                    obj.loggedInUser = enteredUser;
                    delaySeconds(3);  // Adding a 3-second delay for displaying "Login Success!" message
                    System.out.println("\n--- Login Success! --- ");
                } else {
                    obj.passwordAttempts++;
                    int remainingAttempts = MAX_LOGIN_ATTEMPTS - obj.passwordAttempts;
                    if (remainingAttempts > 0) {
                        System.out.println("\n--- Invalid Password! You have " + remainingAttempts + " more attempts --- ");
                        login(obj); // Recursive call to retry entering the password immediately
                    } else {
                        System.out.println("\n--- You've exceeded the maximum number of login attempts for the password! --- ");
                        resetPassword(obj);  // Call the resetPassword method if max login attempts for password are reached
                    }
                }
            } else {
                obj.usernameAttempts++;
                int remainingAttempts = MAX_LOGIN_ATTEMPTS - obj.usernameAttempts;
                if (remainingAttempts > 0) {
                    System.out.println("\n--- Invalid User Name! You have " + remainingAttempts + " more attempts --- ");
                    login(obj); // Recursive call to retry entering the username immediately
                } else {
                    System.out.println("\n--- You've exceeded the maximum number of login attempts for the username! --- ");
                    resetUsername(obj);  // Call the resetUsername method if max login attempts for username are reached
                }
            }
        } else {
            System.out.println("\n--- You've exceeded the maximum number of login attempts for both username and password! Please sign up again --- ");
            obj.usernameAttempts = 0;
            obj.passwordAttempts = 0;
        }
    }

    static void resetUsername(User obj) {
        boolean otpSent = false;
        int remainingGmailAttempts = 3;

        while (remainingGmailAttempts > 0) {
            System.out.print("\nEnter your Gmail: ");
            String enteredGmail = sc.next();

            if (obj.getGmail().equals(enteredGmail)) {
                System.out.print("Enter your Gmail Password: ");
                String enteredGmailPassword = sc.next();

                if (obj.getGmailPassword().equals(enteredGmailPassword)) {
                    String otp = generateOTP();  // Generate OTP
                    System.out.println("Your OTP for username reset is: " + otp);
                    otpSent = true;

                    while (true) {
                        System.out.print("Enter OTP to confirm username reset (or 'R' to resend OTP): ");
                        String enteredOTP = sc.next();

                        if (otp.equals(enteredOTP)) {
                            delaySeconds(2);  // Adding a 2-second delay after OTP matches
                            System.out.print("\nEnter your new User Name: ");
                            String newUsername = sc.next();
                            obj.setusername(newUsername);  // Set the new user name
                            System.out.println("\n--- Username reset successful! --- ");
                            obj.usernameAttempts = 0;
                            return;
                        } else if (enteredOTP.equalsIgnoreCase("R")) {
                            otp = generateOTP();  // Resend OTP
                            System.out.println("\nResending OTP: " + otp);
                        } else {
                            System.out.println("\n--- Invalid OTP! Username reset failed --- ");
                        }
                    }
                } else {
                    System.out.println("\n--- Invalid Gmail Password! You have " + remainingGmailAttempts + " more attempts --- ");
                    remainingGmailAttempts--;
                }
            } else {
                System.out.println("\n--- Invalid Gmail! You have " + remainingGmailAttempts + " more attempts --- ");
                remainingGmailAttempts--;
            }
        }
        if (!otpSent) {
            System.out.println("\n--- Failed to send OTP! Username reset failed --- ");
        }
    }

    static void resetPassword(User obj) {
        boolean otpSent = false;
        int remainingGmailAttempts = 3;

        while (remainingGmailAttempts > 0) {
            System.out.print("\nEnter your Gmail: ");
            String enteredGmail = sc.next();

            if (obj.getGmail().equals(enteredGmail)) {
                System.out.print("Enter your Gmail Password: ");
                String enteredGmailPassword = sc.next();

                if (obj.getGmailPassword().equals(enteredGmailPassword)) {
                    String otp = generateOTP();  // Generate OTP
                    System.out.println("\nYour OTP for password reset is: " + otp);
                    otpSent = true;

                    while (true) {
                        System.out.print("Enter OTP to confirm password reset (or 'R' to resend OTP): ");
                        String enteredOTP = sc.next();

                        if (otp.equals(enteredOTP)) {
                            delaySeconds(2);  // Adding a 2-second delay after OTP matches
                            String newPassword;
                            do {
                                System.out.print("\nEnter your new User Password (at least 8 characters): ");
                                newPassword = sc.next();
                                if (newPassword.length() < 8) {
                                    System.out.println("Password must be at least 8 characters long! Please try again.");
                                }
                            } while (newPassword.length() < 8);
                            obj.setpassword(newPassword);  // Set the new password
                            System.out.println("\n--- Password reset successful! ---");
                            obj.passwordAttempts = 0;
                            return;
                        } else if (enteredOTP.equalsIgnoreCase("R")) {
                            otp = generateOTP();  // Resend OTP
                            System.out.println("\nResending OTP: " + otp);
                        } else {
                            System.out.println("\n--- Invalid OTP! Password reset failed --- ");
                        }
                    }
                } else {
                    System.out.println("\n--- Invalid Gmail Password! You have " + remainingGmailAttempts + " more attempts --- ");
                    remainingGmailAttempts--;
                }
            } else {
                System.out.println("\n--- Invalid Gmail! You have " + remainingGmailAttempts + " more attempts --- ");
                remainingGmailAttempts--;
            }
        }
        if (!otpSent) {
            System.out.println("\n--- Failed to send OTP! Password reset failed --- ");
        }
    }

    // Method to handle Logout confirmation
    static boolean logOutConfirmation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Are you sure you want to log out? (yes/no): ");
        String response = sc.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    // Method to handle Logout functionality
    void logOut() {
        loggedIn = false;
        loggedInUser = "";
        System.out.println("Logged out successfully.");
    }

    static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);  // Generate a 6-digit OTP
        delaySeconds(2);  // Adding a 2-second delay
        return Integer.toString(otp);
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

}
