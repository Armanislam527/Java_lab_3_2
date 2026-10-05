import javax.swing.*;
import java.awt.*;

public class Exp3_StudentForm extends JFrame {
    private final JTextField nameField = new JTextField();
    private final JTextField rollField = new JTextField();
    private final JTextField departmentField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JTextField phoneField = new JTextField();
    private final JTextArea outputArea = new JTextArea(8, 25);

    public Exp3_StudentForm() {
        setTitle("Exp 3: Student Form");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Roll Number:"));
        formPanel.add(rollField);
        formPanel.add(new JLabel("Department:"));
        formPanel.add(departmentField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);

        JButton saveButton = new JButton("Save");
        JButton clearButton = new JButton("Clear");
        formPanel.add(saveButton);
        formPanel.add(clearButton);

        outputArea.setEditable(false);
        outputArea.setBackground(new Color(245, 245, 245));

        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            String dept = departmentField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || roll.isEmpty() || dept.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            outputArea.setText("Student Information Saved\n\n"
                    + "Name: " + name + "\n"
                    + "Roll: " + roll + "\n"
                    + "Department: " + dept + "\n"
                    + "Email: " + email + "\n"
                    + "Phone: " + phone);
        });

        clearButton.addActionListener(e -> {
            nameField.setText("");
            rollField.setText("");
            departmentField.setText("");
            emailField.setText("");
            phoneField.setText("");
            outputArea.setText("");
        });

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Exp3_StudentForm().setVisible(true));
    }
}
