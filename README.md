# EduTrade-Campus-Inventory-Rental-Engine
# EduTrade Campus Inventory & Rental Engine

A Java Swing desktop application that simulates a university resource marketplace where students rent physical textbooks and access digital study materials. Built as an Object-Oriented Programming (OOP) term project to demonstrate encapsulation, inheritance, abstraction, polymorphism, collections, and custom exception handling.

## Objectives

- Apply core OOP principles in one working system: encapsulation, inheritance, abstraction and polymorphism.
- Design a three-level class hierarchy (`MarketItem` → `RentableItem` → `TextbookRental`) with a parallel branch (`DigitalMaterial`) for non-rentable items.
- Use an abstract class and an interface (`Auditable`) to define common rules and reporting behavior.
- Use method overloading, method overriding, and constructor chaining with `super(...)`.
- Manage items with Java Collections (`ArrayList` for ordered storage, `HashMap` for O(1) lookup by item ID).
- Create a custom checked exception, `ListingCapacityExceededException`, and handle errors safely with try-catch and multi-catch.
- Build a user-friendly Swing GUI that shows friendly error dialogs instead of crashing.

## Features

- Add a textbook rental or a digital material item through the GUI
- Search any item instantly by its ID
- Generate a full polymorphic audit report of the catalog
- Enforces a maximum catalog capacity (default: 5) using a custom checked exception
- Graceful error handling with `JOptionPane` alerts — invalid input never crashes the app

## Project Structure

```
EduTrade-Campus-Inventory-Rental-Engine/
├── ListingCapacityExceededException.java   # Custom checked exception
├── Auditable.java                          # Interface for audit reporting
├── MarketItem.java                         # Abstract root class (Level 0)
├── RentableItem.java                       # Extends MarketItem (Level 1)
├── TextbookRental.java                     # Extends RentableItem (Level 2)
├── DigitalMaterial.java                    # Extends MarketItem (parallel branch)
├── InventoryCatalog.java                   # Composition: manages all items
├── EduTradeGUI.java                        # Swing GUI + main()
└── README.md
```

## Class Hierarchy

```
                <<interface>> Auditable
                    ▲              ▲
                    │              │
              MarketItem (abstract)   InventoryCatalog
               ▲            ▲
               │            │
         RentableItem   DigitalMaterial
               ▲
               │
         TextbookRental
```

## Requirements

- JDK 8 or later
- Any IDE (NetBeans, IntelliJ IDEA, Eclipse) or a terminal with `javac`/`java`

## How to Run

Clone the repository and compile all files:

```bash
git clone https://github.com/<Sadaf1212-code>/EduTrade-Campus-Inventory-Rental-Engine.git
cd EduTrade-Campus-Inventory-Rental-Engine
javac *.java
java EduTradeGUI
```

## Usage / Test Cases

| # | Action | Expected Result |
|---|--------|------------------|
| 1 | ID=T101, Title=Java Programming, Price=10, Days=5, Rate=2, Course=CSE201, Edition=3 → **Add Textbook Rental** | Logged as ADDED, Total cost = **35.00** (10 + 5×2 + $15 fee) |
| 2 | ID=D201, Title=OOP Notes, Price=5, File Size=2.5, URL=http://example.com/oop → **Add Digital Material** | Logged as ADDED, Total cost = **5.00** |
| 3 | Enter `T101` → **Search Item by ID** | Displays full item details |
| 4 | Enter an unused ID → **Search Item by ID** | "No item found with ID '...'" |
| 5 | **Generate Audit Report** | Polymorphic report listing all items |
| 6 | Enter `abc` in Base Price → **Add** | Friendly error dialog (no crash) |
| 7 | Add a 6th item after 5 are already stored | `ListingCapacityExceededException` dialog |

## Key OOP Concepts Demonstrated

| Concept | Where |
|---|---|
| Encapsulation | Private fields + getters/setters in all model classes |
| Inheritance | `MarketItem` → `RentableItem` → `TextbookRental` |
| Abstraction | `abstract class MarketItem`, `interface Auditable` |
| Polymorphism / Overriding | `calculateTotalCost()`, `toString()` |
| Overloading | `updatePrice()`, dual constructors on every class |
| Constructor Chaining | `super(...)` / `this(...)` |
| Composition | `InventoryCatalog` holds `ArrayList` + `HashMap` of `MarketItem` |
| Dynamic Binding | `MarketItem item = new TextbookRental(...)` in the GUI |
| Custom Checked Exception | `ListingCapacityExceededException` |
| Multi-catch | `catch (ListingCapacityExceededException | NumberFormatException ex)` |

## License

This project is for academic purposes.

## Author

Add your name and student ID here.
