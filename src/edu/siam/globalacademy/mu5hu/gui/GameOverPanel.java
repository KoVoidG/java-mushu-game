package edu.siam.globalacademy.mu5hu.gui;

import edu.siam.globalacademy.mu5hu.logic.GameLogic;
import java.awt.*;
import javax.swing.*;

/**
 * Panel displaying the game over screen with score and high score.
 * 
 * @author Hein Pyae Sone Htet (6704860008)
 * @author San San Yadanar Htun (6704860007)
 * @since 2025-04-03
 * @version v1.0
 */

public class GameOverPanel extends JPanel {

    private ImageIcon background;
    private int finalScore = 0;

    public GameOverPanel() {
        setPreferredSize(new Dimension(600, 600)); // set the preferred size for the panel
        setFocusable(true);

        // use try-catch-finally to load the image
        try {
            background = new ImageIcon("GameOver 1.png");
        } catch (Exception e) {
            System.out.println("Failed to load gameover background: " + e.getMessage());
        } finally {
            System.out.println("GameOver background loading attempted.");
        }
    }

    /**
     * Sets the final score to be displayed on the game over scree.
     * @param score
     */
    
    public void setFinalScore(int score) {
        this.finalScore = score;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // clear the panel first
        
        if (background != null) {
            background.paintIcon(this, g, 0, 0);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.WHITE);

            // Adjust these positions based on where the "SCORE :" and "HIGH SCORE :" are on your image
            g.drawString(String.valueOf(finalScore), 288, 278);       // score value after "SCORE :"
            g.drawString(String.valueOf(GameLogic.highestScore), 385, 308);   // high score value after "HIGH SCORE" 

        } else {
            // Fallback if background not loaded
            setBackground(Color.BLACK);

            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("GameOver!", 180, 200);

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Score: " + finalScore, 220, 300);
            g.drawString("Press ENTER to restart", 170, 400);
            g.drawString("Press ESCAPE to exit", 170, 440);
        }
    }
}
