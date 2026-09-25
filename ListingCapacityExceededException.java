/**
 * Custom checked exception thrown when the catalog is already full.
 * Because it extends Exception (not RuntimeException), callers MUST
 * handle it or declare it with "throws".
 */
public class ListingCapacityExceededException extends Exception {

    // Constructor 1: message only
    public ListingCapacityExceededException(String message) {
        super(message);
    }

    // Constructor 2: message plus the original cause
    public ListingCapacityExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "ListingCapacityExceededException: " + getMessage();
    }
}