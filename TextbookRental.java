/**
 * A high-value physical textbook that is rented for a course.
 * Adds a flat technology fee to the rental cost.
 */
public class TextbookRental extends RentableItem {

    private static final double COURSE_TECH_FEE = 15.0; // flat fee in dollars

    private String courseCode;
    private int edition;

    // Constructor 1: all fields
    public TextbookRental(String itemId, String title, double basePrice,
                          int rentalDays, double dailyRate,
                          String courseCode, int edition) {
        super(itemId, title, basePrice, rentalDays, dailyRate); // chaining
        this.courseCode = courseCode;
        this.edition = edition;
    }

    // Constructor 2: only course code given, defaults for the rest
    public TextbookRental(String itemId, String title, double basePrice,
                          String courseCode) {
        super(itemId, title, basePrice); // chaining
        this.courseCode = courseCode;
        this.edition = 1;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    // Parent cost + flat technology fee
    @Override
    public double calculateTotalCost() {
        return super.calculateTotalCost() + COURSE_TECH_FEE;
    }

    @Override
    public String toString() {
        return super.toString() + " | Course: " + courseCode
                + " | Edition: " + edition;
    }
}