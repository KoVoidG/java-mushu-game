package edu.siam.globalacademy.mu5hu.gui;

import edu.siam.globalacademy.mu5hu.logic.GameLogic;
import edu.siam.globalacademy.mu5hu.logic.GameLogic.Tile;
import java.awt.*;
import javax.swing.*;

/**
 * Panel responsible for drawing the game board, including the snake and food.
 * 
 * @author Hein Pyae Sone Htet (6704860008)
 * @author San San Yadanar Htun (6704860007)
 * @since 2025-04-03
 * @version v1.0
 */
public class GameBoardPanel extends JPanel {

    private GameLogic logic;

    public GameBoardPanel() {
        setFocusable(true);
    }

    /**
     * Injects the GameLogic instance and sets up board properties.
     * @param logic
     */

    // This will be called after logic is created in GameFrame
    public void setGameLogic(GameLogic logic) {
        this.logic = logic;
        setPreferredSize(new Dimension(logic.boardWidth, logic.boardHeight)); // panel size
        setBackground(new Color(176, 224, 230)); // panel background light blue
        removeKeyListener(getKeyListeners().length > 0 ? getKeyListeners()[0] : null); // reset
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // clear old drawings.
        draw(g);
    }

    private void draw(Graphics g) {

        if (logic == null) {
            return; // safety check
        }
        // draw the food
        g.setColor(Color.orange);
        g.fill3DRect(logic.food.x * logic.tileSize, logic.food.y * logic.tileSize, logic.tileSize, logic.tileSize, true);

        // draw the snake head
        g.setColor(Color.red);
        g.fill3DRect(logic.snakeHead.x * logic.tileSize, logic.snakeHead.y * logic.tileSize, logic.tileSize, logic.tileSize, true);

        // draw snake body
        for (int i = 0; i < logic.snakeBody.size(); i++) {
            Tile snakePart = logic.snakeBody.get(i);
            g.setColor(Color.blue);
            g.fill3DRect(snakePart.x * logic.tileSize, snakePart.y * logic.tileSize, logic.tileSize, logic.tileSize, true);
        }

        // draw ingame score
        if (!logic.gameOver) {
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.setColor(Color.black);
            g.drawString("Score: " + String.valueOf(logic.getScore()), logic.tileSize - 16, logic.tileSize);
        }
    }
}
