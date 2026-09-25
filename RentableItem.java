/**
 * An item that can be rented for a number of days at a daily rate.
 * Extends MarketItem and implements Auditable.
 */
public class RentableItem extends MarketItem implements Auditable {

    private int rentalDays;
    private double dailyRate;

    // Constructor 1: all fields
    public RentableItem(String itemId, String title, double basePrice,
                        int rentalDays, double dailyRate) {
        super(itemId, title, basePrice); // constructor chaining
        this.rentalDays = rentalDays;
        this.dailyRate = dailyRate;
    }

    // Constructor 2: default rental of 1 day at $1.00 per day
    public RentableItem(String itemId, String title, double basePrice) {
        super(itemId, title, basePrice); // constructor chaining
        this.rentalDays = 1;
        this.dailyRate = 1.0;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    // Total = base price + (days * daily rate)
    @Override
    public double calculateTotalCost() {
        return getBasePrice() + (rentalDays * dailyRate);
    }

    @Override
    public String getAuditReport() {
        return "[RENTABLE] " + getItemId() + " - " + getTitle()
                + " | Days: " + rentalDays
                + " | Total Cost: $" + String.format("%.2f", calculateTotalCost());
    }

    @Override
    public String toString() {
        return super.toString() + " | Rental Days: " + rentalDays
                + " | Daily Rate: $" + String.format("%.2f", dailyRate);
    }
}