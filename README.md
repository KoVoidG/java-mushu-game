# Mu5hu - The Dragon Game

## Description
"Mu5hu" is a dynamic grid-based game where the player controls a dragon that moves around the screen to collect flames. The game follows a classic snake-game-like mechanic, where the dragon grows as it consumes flames, and the objective is to grow as large as possible without colliding with itself or the screen borders. Players will experience a smooth, interactive game loop with colorful graphics, intuitive controls, and an engaging gameplay cycle, including a restart option after the game ends.

## Requirements

To run the "Mu5hu - The Dragon Game" project, you will need the following:

- [**Java Development Kit**](https://www.oracle.com/th/java/technologies/downloads/) (JDK) version 8 or higher
- [**Visual Studio Code**](https://code.visualstudio.com/download) with Java extensions installed (used for code editing and running the project).
- **Swing and AWT libraries** (which are included in the JDK).

### Functional Requirement

- Playable game with dragon instead of a snake
- Collect flames (food) to grow
- Score tracking and high score saving
- Game ends when dragon hits itself or the wall.
- Key controls (`arrow, esc, enter`) for the game.

### Design Requirements

- 600x600 game board, 25px tiles
- Board color
- Snake Head, Body, and flames color
- Custom Startup Panel
- Custom GameOver Panel

## How to run

Follow the steps below to run play the game:

1) Download the delivered zip file in your perferred location. (e.g. `C:\Downloads`)

2) It is important to extract the zip file to folder first.

3) Open **Visual Studio Code**

4) In the VS Code, 
`File > Open Folder > choose the extracted folder > Select Folder`

5) Navigate the java package (`src\edu\siam\globalacademy\mu5hu`)

6) Go to `App.java` and on the navigation bar click "Run" and "Start Debugging" or use the shortcut "f5" on your keyboard.

**Enjoy the game!**

## UML Class Diagram

```
classDiagram
    %% ENTRY POINT
    class App {
        +main(String[]) void
    }

    %% MAIN FRAME
    class GameFrame {
        -CardLayout CardLayout
        -JPanel cardPanel
        -StartupPanel startupPanel
        -GameBoardPanel gameBoardPanel
        -GameOverPanel gameOverPanel
        -GameLogic logic

        +GameFrame() void
        -startGame() void
        -switchToStartup() void
        -switchToGameOver() void
        +switchPanel(GameState) void
    }

    %% PANELS
    class StartupPanel {
        -ImageIcon background

        +StartupPanel() void
        #paintComponent(Graphics) void
    }

    class GameBoardPanel {
        -GameLogic logic

        +GameBoardPanel() void
        +setGameLogic(GameLogic) void
        #paintComponent(Graphics) void
        -draw(Graphic) void
    }

    class GameOverPanel {
        -ImageIcon background
        -int finalScore

        +GameOverPanel() void
        +setFinalScore(int) void
        #paintComponent(Graphics) void
    }

    %% LOGIC
    class GameLogic {
        +tileSize
        + boardWidth, boardHeight
        + Tile snakeHead
        + ArrayList<Tile> snakeBody
        + Tile food
        - Random random
        -Timer gameLoop
        + int velocityX, velocityY
        +boolean gameOver
        
        +GameLogic(int, int, Runnable, Runnable) void
        -updateHighestScore() void
        -loadHighestScore() void
        -saveHighestScore() void
        +move() void
        +placeFood() void
        +getScore() int
    }

    class GameKeyListener {
        -Runnable onStart
        -GameLogic logic
        -Runnable onGameOver

        +GameKeyListener(Runnable, GameLogic, Runnable)
        +keyPressed(KeyEvent) void
    }

    %% DATA STRUCTURES
    class Tile {
        +int x
        +int y
        +Tile(int, int)
    }

    class GameState {
        <<enum>>
        STARTUP
        PLAYING
        GAMEOVER
    }

    %% RELATIONSHIPS 
    App --> GameFrame : create

    
    GameFrame o-- StartupPanel : contain
    GameFrame o-- GameBoardPanel : contain
    GameFrame o-- GameOverPanel : contain
    GameFrame --|> JFrame : extends

    StartupPanel --|> JPanel : extends
    GameOverPanel --|> JPanel : extends
    GameBoardPanel --|> JPanel : extends
    
    GameBoardPanel *-- GameLogic : contain

    GameOverPanel --> GameFrame : use
    StartupPanel --> GameFrame : use

    GameLogic --> Tile : use
    GameLogic --> GameState : use
    GameLogic --> Timer : use
    GameLogic --> File : use

    GameKeyListener --> GameLogic : use
    GameKeyListener --> GameFrame : use
    GameKeyListener --|> KeyAdapter : extends
```
## Known Issues (if any)

Occasionally, when the dragon becomes long enough, it may overlap the flames with its body causing the player to lose track of the flame from time to time.

