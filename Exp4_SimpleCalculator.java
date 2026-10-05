import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Exp4_SimpleCalculator extends JFrame {
    private final JTextField display = new JTextField();
    private String firstValue = "";
    private String operator = "";
    private boolean startNewNumber = true;

    public Exp4_SimpleCalculator() {
        setTitle("Exp 4: Simple Calculator");
        setSize(420, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        display.setFont(new Font("SansSerif", Font.BOLD, 28));
        display.setPreferredSize(new Dimension(400, 70));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 12, 12));
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "CE", "(", ")"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("SansSerif", Font.BOLD, 26));
            button.addActionListener(this::handleButtonClick);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void handleButtonClick(ActionEvent event) {
        String input = ((JButton) event.getSource()).getText();

        if (input.matches("[0-9]")) {
            if (startNewNumber) {
                display.setText(input);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + input);
            }
            return;
        }

        if (input.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
            startNewNumber = false;
            return;
        }

        if (input.matches("[+\\-*/]")) {
            firstValue = display.getText();
            operator = input;
            startNewNumber = true;
            return;
        }

        if (input.equals("=")) {
            if (firstValue.isEmpty() || operator.isEmpty()) {
                return;
            }
            double a = Double.parseDouble(firstValue);
            double b = Double.parseDouble(display.getText());
            double result = switch (operator) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> (b == 0) ? Double.NaN : a / b;
                default -> 0;
            };

            display.setText(Double.isNaN(result) ? "Error" : String.valueOf(result));
            firstValue = "";
            operator = "";
            startNewNumber = true;
            return;
        }

        if (input.equals("C") || input.equals("CE")) {
            display.setText("");
            firstValue = "";
            operator = "";
            startNewNumber = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Exp4_SimpleCalculator().setVisible(true));
    }
}
