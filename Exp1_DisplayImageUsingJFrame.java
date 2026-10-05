import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Exp1_DisplayImageUsingJFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Exp 1: Display Image using JFrame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 520);
            frame.setLocationRelativeTo(null);

            BufferedImage image = new BufferedImage(620, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            GradientPaint gradient = new GradientPaint(0, 0, new Color(52, 152, 219), 620, 400, new Color(155, 89, 182));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, 620, 400);

            g2d.setColor(new Color(255, 255, 255, 200));
            g2d.fillRoundRect(50, 65, 520, 250, 36, 36);

            g2d.setColor(new Color(44, 62, 80));
            g2d.setFont(new Font("SansSerif", Font.BOLD, 36));
            g2d.drawString("Java Lab Image", 190, 160);

            g2d.setFont(new Font("SansSerif", Font.PLAIN, 24));
            g2d.drawString("Experiment 1", 240, 205);

            g2d.setColor(new Color(231, 76, 60));
            g2d.fillOval(275, 260, 90, 90);
            g2d.setColor(Color.WHITE);
            g2d.drawLine(320, 265, 320, 345);
            g2d.drawLine(280, 305, 360, 305);

            g2d.dispose();

            JLabel imageLabel = new JLabel(new ImageIcon(image));
            frame.add(imageLabel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
