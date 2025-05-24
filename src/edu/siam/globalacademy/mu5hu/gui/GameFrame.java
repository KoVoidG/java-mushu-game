package edu.siam.globalacademy.mu5hu.gui;

import edu.siam.globalacademy.mu5hu.logic.GameLogic;
import edu.siam.globalacademy.mu5hu.logic.GameState;
import java.awt.*;
import javax.swing.*;

/**
 * Main game window managing different game panels using CardLayout.
 * 
 * @author Hein Pyae Sone Htet (6704860008)
 * @author San San Yadanar Htun (6704860007)
 * @since 2025-04-03
 * @version v1.0
 */

public class GameFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final StartupPanel startupPanel;
    private final GameBoardPanel gameBoardPanel;
    private final GameOverPanel gameOverPanel;
    private GameLogic logic;

    public GameFrame() {
        setTitle("Mu5hu Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        cardLayout = new CardLayout(); // switching panels
        cardPanel = new JPanel(cardLayout); // holding panels

        // instantiates all the individual panels
        startupPanel = new StartupPanel();
        gameBoardPanel = new GameBoardPanel();
        gameOverPanel = new GameOverPanel();

        // adds each panel to the CardPanel
        cardPanel.add(startupPanel, GameState.STARTUP.name());
        cardPanel.add(gameBoardPanel, GameState.PLAYING.name());
        cardPanel.add(gameOverPanel, GameState.GAMEOVER.name());

        add(cardPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // adds key listeners to panels
        startupPanel.addKeyListener(new GameKeyListener(this::startGame, null, null));
        gameOverPanel.addKeyListener(new GameKeyListener(this::switchToStartup, null, null));

        // make startup panel ready to receive key input
        startupPanel.requestFocusInWindow();
    }

    /**
     * Initializes game logic and transitions to the playing screen.
     */

    private void startGame() {
        logic = new GameLogic(600, 600, this::switchToGameOver, gameBoardPanel::repaint);
        gameBoardPanel.setGameLogic(logic);
        gameBoardPanel.addKeyListener(new GameKeyListener(null, logic, null));
        switchPanel(GameState.PLAYING);
    }

    /**
     * Switches to the startup screen.
     */

    private void switchToStartup() {
        switchPanel(GameState.STARTUP);
    }


    /**
     * Switches to the game over screen and sets the final score.
     */
    private void switchToGameOver() {
        gameOverPanel.setFinalScore(logic.getScore());
        switchPanel(GameState.GAMEOVER);
    }

    // method for switching panel based on gamestate
    public void switchPanel(GameState state) {
        cardLayout.show(cardPanel, state.name());

        switch (state) {
            case PLAYING ->
                gameBoardPanel.requestFocusInWindow();
            case STARTUP ->
                startupPanel.requestFocusInWindow();
            case GAMEOVER ->
                gameOverPanel.requestFocusInWindow();
        }
    }
}
