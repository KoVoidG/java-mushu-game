package edu.siam.globalacademy.mu5hu.gui;

import edu.siam.globalacademy.mu5hu.logic.GameLogic;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Handles keyboard input for game controls and state transitions.
 * 
 * @author Hein Pyae Sone Htet (6704860008)
 * @author San San Yadanar Htun (6704860007)
 * @since 2025-04-03
 * @version v1.0
 */

public class GameKeyListener extends KeyAdapter {

    private final Runnable onStart;
    private final GameLogic logic;
    private final Runnable onGameOver;

    public GameKeyListener(Runnable onStart, GameLogic logic, Runnable onGameOver) {
        this.onStart = onStart;
        this.logic = logic;
        this.onGameOver = onGameOver;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //retrieves the code of the key that was pressed
        int key = e.getKeyCode();

        // execute the start game when pressed enter
        if (onStart != null && key == KeyEvent.VK_ENTER) {
            onStart.run();
        }

        // snake movement
        if (logic != null) {
            if (key == KeyEvent.VK_UP && logic.velocityY != 1) {
                logic.velocityX = 0;
                logic.velocityY = -1;
            } else if (key == KeyEvent.VK_DOWN && logic.velocityY != -1) {
                logic.velocityX = 0;
                logic.velocityY = 1;
            } else if (key == KeyEvent.VK_LEFT && logic.velocityX != 1) {
                logic.velocityX = -1;
                logic.velocityY = 0;
            } else if (key == KeyEvent.VK_RIGHT && logic.velocityX != -1) {
                logic.velocityX = 1;
                logic.velocityY = 0;
            }
        }

        // execute game over transition logic when pressed enter
        if (onGameOver != null && key == KeyEvent.VK_ENTER) {
            onGameOver.run();
        }

        // press esc to exit the game
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }
}
