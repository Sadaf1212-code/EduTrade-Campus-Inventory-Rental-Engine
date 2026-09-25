/**
 * Interface for any class that can produce an audit report.
 */
public interface Auditable {

    // Returns a text report describing the object
    String getAuditReport();
}