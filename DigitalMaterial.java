/**
 * A digital study asset (PDF, notes, etc.).
 * Extends MarketItem directly, so it is NOT rentable.
 */
public class DigitalMaterial extends MarketItem {

    private double fileSizeMB;
    private String downloadUrl;

    // Constructor 1: all fields
    public DigitalMaterial(String itemId, String title, double basePrice,
                           double fileSizeMB, String downloadUrl) {
        super(itemId, title, basePrice);
        this.fileSizeMB = fileSizeMB;
        this.downloadUrl = downloadUrl;
    }

    // Constructor 2: file details unknown yet
    public DigitalMaterial(String itemId, String title, double basePrice) {
        super(itemId, title, basePrice);
        this.fileSizeMB = 0.0;
        this.downloadUrl = "not-set";
    }

    public double getFileSizeMB() {
        return fileSizeMB;
    }

    public void setFileSizeMB(double fileSizeMB) {
        this.fileSizeMB = fileSizeMB;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    // Digital items have no extra fees: cost is just the base price
    @Override
    public double calculateTotalCost() {
        return getBasePrice();
    }

    @Override
    public String toString() {
        return "[DIGITAL] " + getTitle() + " | File Size: " + fileSizeMB
                + " MB | URL: " + downloadUrl;
    }
}