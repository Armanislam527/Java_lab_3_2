import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Exp2_RestaurantBill extends JFrame {
    private final JTextField itemField = new JTextField();
    private final JTextField quantityField = new JTextField();
    private final JTextField priceField = new JTextField();
    private final JTextArea billArea = new JTextArea(15, 30);
    private final List<String> items = new ArrayList<>();
    private final List<Integer> qtyList = new ArrayList<>();
    private final List<Double> priceList = new ArrayList<>();

    public Exp2_RestaurantBill() {
        setTitle("Exp 2: Restaurant Bill");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Order Details"));

        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(itemField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Price per item:"));
        inputPanel.add(priceField);

        JButton addButton = new JButton("Add Item");
        JButton totalButton = new JButton("Generate Bill");
        inputPanel.add(addButton);
        inputPanel.add(totalButton);

        addButton.addActionListener(this::addItem);
        totalButton.addActionListener(this::generateBill);

        billArea.setEditable(false);
        billArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(billArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addItem(ActionEvent event) {
        try {
            String item = itemField.getText().trim();
            int qty = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (item.isEmpty() || qty <= 0 || price <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter valid item details.");
                return;
            }

            items.add(item);
            qtyList.add(qty);
            priceList.add(price);

            itemField.setText("");
            quantityField.setText("");
            priceField.setText("");
            JOptionPane.showMessageDialog(this, "Item added successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity and price must be numbers.");
        }
    }

    private void generateBill(ActionEvent event) {
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items added yet.");
            return;
        }

        double subtotal = 0;
        StringBuilder bill = new StringBuilder();
        bill.append("====================================\n");
        bill.append("           RESTAURANT BILL\n");
        bill.append("====================================\n");
        bill.append(String.format("%-15s %-8s %-10s %-10s\n", "Item", "Qty", "Rate", "Amount"));

        for (int i = 0; i < items.size(); i++) {
            double amount = qtyList.get(i) * priceList.get(i);
            subtotal += amount;
            bill.append(String.format("%-15s %-8d %-10.2f %-10.2f\n",
                    items.get(i), qtyList.get(i), priceList.get(i), amount));
        }

        double tax = subtotal * 0.05;
        double total = subtotal + tax;

        bill.append("------------------------------------\n");
        bill.append(String.format("Subtotal: %.2f\n", subtotal));
        bill.append(String.format("Tax (5%%): %.2f\n", tax));
        bill.append(String.format("Total: %.2f\n", total));
        bill.append("====================================\n");

        billArea.setText(bill.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Exp2_RestaurantBill().setVisible(true));
    }
}
