package edu.siam.globalacademy.mu5hu;

import edu.siam.globalacademy.mu5hu.gui.GameFrame;

/**
 * Entry point for the Mu5hu game. Initializes the game frame.
 * 
 * @author Hein Pyae Sone Htet (6704860008)
 * @author San San Yadanar Htun (6704860007)
 * @since 2025-04-03
 * @version v1.0
 */

public class App {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        try {
            // instantiate the main game window (GUI + logic starts from here)
            @SuppressWarnings("unused")
            GameFrame mu5hu = new GameFrame();

        } catch (Exception e) {
            // pinrt any unexpected error during game launch
            e.printStackTrace();
        } finally {
            // log a message after the game ends or crashes.
            System.out.println("Game terminated.");
        }
    }
}
