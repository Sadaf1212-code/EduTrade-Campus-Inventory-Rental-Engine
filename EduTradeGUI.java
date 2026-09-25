import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Swing dashboard for the EduTrade system.
 */
public class EduTradeGUI extends JFrame {

    private InventoryCatalog catalog;

    // Input fields
    private JTextField itemIdField;
    private JTextField titleField;
    private JTextField basePriceField;
    private JTextField rentalDaysField;
    private JTextField dailyRateField;
    private JTextField courseCodeField;
    private JTextField editionField;
    private JTextField fileSizeField;
    private JTextField downloadUrlField;

    // Buttons
    private JButton addTextbookButton;
    private JButton addDigitalButton;
    private JButton searchButton;
    private JButton auditButton;

    // Output log
    private JTextArea logArea;

    public EduTradeGUI() {
        super("EduTrade Campus Inventory & Rental Engine");
        catalog = new InventoryCatalog(); // default capacity 5

        setupComponents();
        setupLayout();
        setupListeners();

        setSize(650, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen
    }

    private void setupComponents() {
        itemIdField = new JTextField(15);
        titleField = new JTextField(15);
        basePriceField = new JTextField(15);
        rentalDaysField = new JTextField(15);
        dailyRateField = new JTextField(15);
        courseCodeField = new JTextField(15);
        editionField = new JTextField(15);
        fileSizeField = new JTextField(15);
        downloadUrlField = new JTextField(15);

        addTextbookButton = new JButton("Add Textbook Rental");
        addDigitalButton = new JButton("Add Digital Material");
        searchButton = new JButton("Search Item by ID");
        auditButton = new JButton("Generate Audit Report");

        logArea = new JTextArea(12, 40);
        logArea.setEditable(false);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // Top: labels and text fields
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Item Details"));
        inputPanel.add(new JLabel("Item ID:"));
        inputPanel.add(itemIdField);
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Base Price:"));
        inputPanel.add(basePriceField);
        inputPanel.add(new JLabel("Rental Days (textbook):"));
        inputPanel.add(rentalDaysField);
        inputPanel.add(new JLabel("Daily Rate (textbook):"));
        inputPanel.add(dailyRateField);
        inputPanel.add(new JLabel("Course Code (textbook):"));
        inputPanel.add(courseCodeField);
        inputPanel.add(new JLabel("Edition (textbook):"));
        inputPanel.add(editionField);
        inputPanel.add(new JLabel("File Size MB (digital):"));
        inputPanel.add(fileSizeField);
        inputPanel.add(new JLabel("Download URL (digital):"));
        inputPanel.add(downloadUrlField);

        // Middle: buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        buttonPanel.add(addTextbookButton);
        buttonPanel.add(addDigitalButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(auditButton);

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Bottom: scrolling log
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output Log"));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void setupListeners() {

        // ----- Add Textbook Rental -----
        addTextbookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = itemIdField.getText().trim();
                    String title = titleField.getText().trim();
                    double price = Double.parseDouble(basePriceField.getText().trim());
                    int days = Integer.parseInt(rentalDaysField.getText().trim());
                    double rate = Double.parseDouble(dailyRateField.getText().trim());
                    String course = courseCodeField.getText().trim();
                    int edition = Integer.parseInt(editionField.getText().trim());

                    // Dynamic binding: a MarketItem reference holds a TextbookRental object
                    MarketItem item = new TextbookRental(id, title, price, days, rate, course, edition);
                    catalog.addItem(item);

                    logArea.append("ADDED: " + item.toString() + "\n");
                    logArea.append("   Total cost (dynamic binding): $"
                            + String.format("%.2f", item.calculateTotalCost()) + "\n");
                } catch (ListingCapacityExceededException | NumberFormatException ex) {
                    showError(ex);
                }
            }
        });

        // ----- Add Digital Material -----
        addDigitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = itemIdField.getText().trim();
                    String title = titleField.getText().trim();
                    double price = Double.parseDouble(basePriceField.getText().trim());
                    double size = Double.parseDouble(fileSizeField.getText().trim());
                    String url = downloadUrlField.getText().trim();

                    MarketItem item = new DigitalMaterial(id, title, price, size, url);
                    catalog.addItem(item);

                    logArea.append("ADDED: " + item.toString() + "\n");
                    logArea.append("   Total cost: $"
                            + String.format("%.2f", item.calculateTotalCost()) + "\n");
                } catch (ListingCapacityExceededException | NumberFormatException ex) {
                    showError(ex);
                }
            }
        });

        // ----- Search Item by ID -----
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = itemIdField.getText().trim();
                MarketItem found = catalog.searchItem(id);

                if (found == null) {
                    logArea.append("SEARCH: No item found with ID '" + id + "'\n");
                } else {
                    logArea.append("SEARCH FOUND: " + found.toString() + "\n");
                }
            }
        });

        // ----- Generate Audit Report -----
        auditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calls the interface method on the catalog
                Auditable auditable = catalog;
                logArea.append(auditable.getAuditReport());
            }
        });
    }

    // Shows a friendly error popup and also writes the error to the log
    private void showError(Exception ex) {
        String message;
        if (ex instanceof NumberFormatException) {
            message = "Please enter valid numbers in the numeric fields.\nDetails: "
                    + ex.getMessage();
        } else {
            message = ex.getMessage();
        }
        JOptionPane.showMessageDialog(this, message, "Input Error",
                JOptionPane.ERROR_MESSAGE);
        logArea.append("ERROR: " + message + "\n");
    }

    @Override
    public String toString() {
        return "EduTradeGUI [catalog=" + catalog.toString() + "]";
    }

    // ----- Program entry point -----
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EduTradeGUI gui = new EduTradeGUI();
                gui.setVisible(true);
            }
        });
    }
}