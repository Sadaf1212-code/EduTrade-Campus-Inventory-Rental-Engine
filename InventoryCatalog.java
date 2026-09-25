import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores all market items. Uses composition: the catalog HAS-A list and
 * HAS-A map of MarketItem objects.
 */
public class InventoryCatalog implements Auditable {

    private ArrayList<MarketItem> itemList;
    private HashMap<String, MarketItem> itemMap;
    private int maxCapacity;

    // Constructor 1: default capacity of 5
    public InventoryCatalog() {
        this(5);
    }

    // Constructor 2: custom capacity
    public InventoryCatalog(int maxCapacity) {
        this.itemList = new ArrayList<MarketItem>();
        this.itemMap = new HashMap<String, MarketItem>();
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getItemCount() {
        return itemList.size();
    }

    // Adds an item to both collections, or throws if the catalog is full
    public void addItem(MarketItem item) throws ListingCapacityExceededException {
        if (itemList.size() >= maxCapacity) {
            throw new ListingCapacityExceededException(
                    "Catalog is full! Maximum capacity is " + maxCapacity + " items.");
        }
        itemList.add(item);
        itemMap.put(item.getItemId(), item);
    }

    // Fast lookup by ID using the HashMap (returns null if not found)
    public MarketItem searchItem(String itemId) {
        return itemMap.get(itemId);
    }

    // Removes an item from both collections
    public boolean removeItem(String itemId) {
        MarketItem item = itemMap.get(itemId);
        if (item == null) {
            return false; // nothing to remove
        }
        itemList.remove(item);
        itemMap.remove(itemId);
        return true;
    }

    // Builds the report with a for-each loop
    @Override
    public String getAuditReport() {
        String report = "===== INVENTORY AUDIT REPORT =====\n";
        report += "Items: " + itemList.size() + " / " + maxCapacity + "\n";

        for (MarketItem item : itemList) {
            // Polymorphism: the correct version runs depending on the real type
            if (item instanceof Auditable) {
                Auditable auditableItem = (Auditable) item;
                report += auditableItem.getAuditReport() + "\n";
            } else {
                report += item.toString() + "\n";
            }
        }

        report += "==================================\n";
        return report;
    }

    @Override
    public String toString() {
        return "InventoryCatalog [items=" + itemList.size()
                + ", maxCapacity=" + maxCapacity + "]";
    }
}