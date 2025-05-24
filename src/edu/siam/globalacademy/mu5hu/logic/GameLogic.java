package edu.siam.globalacademy.mu5hu.logic;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

/**
 * Core game logic handling movement, collisions, scoring and persistence.
 * 
 * @author Hein Pyae Sone Htet (6704860008)
 * @author San San Yadanar Htun (6704860007)
 * @since 2025-04-03
 * @version v1.0
 */

public class GameLogic{

    // a static variable that stores the highest score in the game
    public static int highestScore = 0;

    /**
     * Represents a coordinate tile used by the snake and food.
     */

    public class Tile {
        public int x, y;

        public Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // size of each tile in pixels
    public final int tileSize = 25;

    // game board dimensions
    public int boardWidth, boardHeight;

    // snake's head and body segments
    public Tile snakeHead;
    public ArrayList<Tile> snakeBody;

    // food object
    public Tile food;
    private final Random random;

    // main game timer loop
    private Timer gameLoop;

    //snake's movement direction
    public int velocityX;
    public int velocityY;

    // game status
    public boolean gameOver = false;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public GameLogic(int boardWidth, int boardHeight, Runnable onGameOver, Runnable onUpdate) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        highestScore = loadHighestScore();

        // initialize the snake head at coordinates(5, 5)
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<>();

        // initialize food position and randomizer
        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        // initial no movement.
        velocityX = 0;
        velocityY = 0;

        // start the game loop (runs every 100ms)
        gameLoop = new Timer(100, (@SuppressWarnings("unused") ActionEvent e) -> {
            if (!gameOver) {
                move();
                onUpdate.run(); // redraw screen
            } else {
                updateHighestScore();
                gameLoop.stop();
                onGameOver.run(); // trigger game over screen
            }
        });
        gameLoop.start();
    }

    /**
     * Updates and saves the highest score if the current score is higher.
     */

    private void updateHighestScore(){
        if(getScore() > highestScore) {
            highestScore = getScore();
            saveHighestScore(highestScore);
        }
    }
        
    /**
     * Loads the highest score from a file.
     * @return
     */

    private int loadHighestScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader("score.dat"))) {
            return Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Saves the highest score to a file.
     * @param score
     */

    @SuppressWarnings("CallToPrintStackTrace")
    private void saveHighestScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("score.dat"))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates the snake's position, checks for collisions and handles food consumption.
     */

    public void move() {
        
        // Check if snake eats food
        if (snakeHead.x == food.x && snakeHead.y == food.y) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        // Move the body
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile part = snakeBody.get(i);
            if (i == 0) {
                part.x = snakeHead.x;
                part.y = snakeHead.y;
            } else {
                Tile prev = snakeBody.get(i - 1);
                part.x = prev.x;
                part.y = prev.y;
            }
        }

        // Move the head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // Collision with body
        for (Tile part : snakeBody) {
            if (snakeHead.x == part.x && snakeHead.y == part.y) {
                gameOver = true;
            }
        }

        // Collision with walls
        if (snakeHead.x < 0 || snakeHead.x * tileSize >= boardWidth ||
            snakeHead.y < 0 || snakeHead.y * tileSize >= boardHeight) {
            gameOver = true;
        }
    }

    /**
     * Places food at a random position on the board.
     */

    public void placeFood() {
        food.x = random.nextInt(boardWidth / tileSize);
        food.y = random.nextInt(boardHeight / tileSize);
    }

    /**
     * Returns the current score based on the length of the snake's body.
     * @return
     */

    public int getScore() {
        return snakeBody.size();
    }
}
