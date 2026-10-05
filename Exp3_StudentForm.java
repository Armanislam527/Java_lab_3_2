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
        setSize(680, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        Font controlFont = new Font("SansSerif", Font.PLAIN, 25);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(controlFont);
        formPanel.add(nameLabel);
        nameField.setFont(controlFont);
        formPanel.add(nameField);
        JLabel rollLabel = new JLabel("Roll Number:");
        rollLabel.setFont(controlFont);
        formPanel.add(rollLabel);
        rollField.setFont(controlFont);
        formPanel.add(rollField);
        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setFont(controlFont);
        formPanel.add(departmentLabel);
        departmentField.setFont(controlFont);
        formPanel.add(departmentField);
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(controlFont);
        formPanel.add(emailLabel);
        emailField.setFont(controlFont);
        formPanel.add(emailField);
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(controlFont);
        formPanel.add(phoneLabel);
        phoneField.setFont(controlFont);
        formPanel.add(phoneField);

        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        submitButton.setFont(controlFont);
        clearButton.setFont(controlFont);
        formPanel.add(submitButton);
        formPanel.add(clearButton);

        outputArea.setEditable(false);
        outputArea.setBackground(new Color(245, 245, 245));
        outputArea.setFont(new Font("SansSerif", Font.PLAIN, 25));

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            String dept = departmentField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || roll.isEmpty() || dept.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            outputArea.setText("Student Information Submitted\n\n"
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
