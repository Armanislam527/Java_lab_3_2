import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Exp1_DisplayImageUsingJFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Exp 1: Display Image using JFrame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 380);
            frame.setLocationRelativeTo(null);

            BufferedImage image = new BufferedImage(430, 260, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            GradientPaint gradient = new GradientPaint(0, 0, new Color(52, 152, 219), 430, 260, new Color(155, 89, 182));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, 430, 260);

            g2d.setColor(new Color(255, 255, 255, 200));
            g2d.fillRoundRect(35, 45, 360, 160, 30, 30);

            g2d.setColor(new Color(44, 62, 80));
            g2d.setFont(new Font("SansSerif", Font.BOLD, 26));
            g2d.drawString("Java Lab Image", 120, 120);

            g2d.setFont(new Font("SansSerif", Font.PLAIN, 16));
            g2d.drawString("Experiment 1", 160, 150);

            g2d.setColor(new Color(231, 76, 60));
            g2d.fillOval(170, 170, 70, 70);
            g2d.setColor(Color.WHITE);
            g2d.drawLine(200, 175, 200, 225);
            g2d.drawLine(175, 200, 225, 200);

            g2d.dispose();

            JLabel imageLabel = new JLabel(new ImageIcon(image));
            frame.add(imageLabel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
