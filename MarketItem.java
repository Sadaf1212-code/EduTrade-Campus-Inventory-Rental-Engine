/**
 * Abstract root class for everything sold or rented in the marketplace.
 * Fields are private, so they are accessed only through getters/setters.
 */
public abstract class MarketItem {

    private String itemId;
    private String title;
    private double basePrice;

    // Constructor 1: all fields
    public MarketItem(String itemId, String title, double basePrice) {
        this.itemId = itemId;
        this.title = title;
        this.basePrice = basePrice;
    }

    // Constructor 2: no price given, so it starts at 0.0
    public MarketItem(String itemId, String title) {
        this(itemId, title, 0.0);
    }

    // ---------- Getters and setters ----------
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    // Every subclass must define its own cost rule
    public abstract double calculateTotalCost();

    // ---------- Overloaded updatePrice methods ----------

    // Version 1: set a new price directly (must be positive)
    public boolean updatePrice(double newPrice) {
        if (newPrice <= 0) {
            return false; // invalid price, nothing changed
        }
        this.basePrice = newPrice;
        return true;
    }

    // Version 2: set a new price and then apply a discount percentage
    public boolean updatePrice(double newPrice, double discountPercentage) {
        if (newPrice <= 0 || discountPercentage < 0 || discountPercentage > 100) {
            return false; // invalid input, nothing changed
        }
        this.basePrice = newPrice - (newPrice * discountPercentage / 100);
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + itemId + " | Title: " + title
                + " | Base Price: $" + String.format("%.2f", basePrice);
    }
}