Project Overview
Urban Living is a console-based online furniture store built using Java and OOP principles. The system allows customers to browse, select, and purchase bedding products conveniently, with features such as a shopping cart, wishlist, voucher application, and secure payment handling.
This project was developed as a team assignment to demonstrate the use of Object-Oriented Programming concepts such as encapsulation, inheritance, polymorphism, and abstraction.
________________________________________
Features
User Management
•	User registration and login with validation (minimum 8-character passwords).
•	OTP-based recovery for forgotten username/password.
•	Limited login attempts to enhance security.
Shopping Cart
•	Add/remove items with quantity specification.
•	Clear cart function.
•	Real-time updates of total cost.
Wishlist
•	Add/remove items for future purchase consideration.
•	No quantity specification; one item per addition.
Product Browsing & Searching
•	Browse products by category.
•	Search by item name or category.
•	Handles invalid input gracefully.
Checkout & Payment
•	Choose shipping option: doorstep delivery or self-collection.
•	Apply vouchers: FREESHIPPING01 for free shipping, PROMOTIONDISCOUNT for 10% discount.
•	Payment methods: Cash on Delivery or Credit/Debit Card.
•	Generates a detailed order receipt.
Order History
•	View all past orders with details: items purchased, quantities, total cost, shipping, and payment method.
________________________________________
Object-Oriented Design
•	Encapsulation:
Classes such as Category, Item, Store, User, and PaymentHandler encapsulate data with private fields and public getters/setters.
•	Inheritance:
o	User extends Try1
o	ShoppingCart and Wishlist extend ItemContainer
o	CardPayment and CashOnDelivery extend PaymentMethodBase
•	Polymorphism:
o	Payment processing methods are implemented differently in CardPayment and CashOnDelivery.
o	Voucher interface implemented by ShippingVoucher and DiscountVoucher.
•	Abstraction:
Abstract classes (ItemContainer, PaymentMethodBase) define behavior that subclasses implement.

Key Learning Outcomes
Applied OOP principles (encapsulation, inheritance, polymorphism, abstraction) in a real-world system.
Developed a modular, maintainable, and user-friendly console application.
Implemented practical features like vouchers, payment methods, and order history.

Authors
Kam Win Ni
Lee Qian Hui
Stephanie Ting Xin Ying
