package edu.siam.globalacademy.mu5hu.gui;

import java.awt.*;
import javax.swing.*;

/**
 * Panel displaying the startup screen with background image or fallback text.
 * 
 * @author Hein Pyae Sone Htet (6704860008)
 * @author San San Yadanar Htun (6704860007)
 * @since 2025-04-03
 * @version v1.0
 */

public class StartupPanel extends JPanel {

    private ImageIcon background;

    public StartupPanel() {
        setPreferredSize(new Dimension(600, 600));
        setFocusable(true);

        // try-catch-finally blocks to load the image.
        try {
            background = new ImageIcon("StartPanel.png");
        } catch (Exception e) {
            System.out.println("Failed to load startup background: " + e.getMessage());
        } finally {
            System.out.println("Startup background loading attempted.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // clear the panel first
        
        if (background != null) {
            background.paintIcon(this, g, 0, 0);

        } else {
            // Fallback if background not loaded
            setBackground(Color.BLACK);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Welcome to Mu5hu Game!", 100, 100);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Press ENTER to start", 120, 150);
        }
    }
}
