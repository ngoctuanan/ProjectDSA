package Manager;

import java.awt.Color;

import GameEngine.GameState;
import GameEngine.Level;
import GameEngine.Map;
import GameScreen.*;

public class GameManager {
    private GameScreen introScreen, pauseScreen, endGameScreen, resultScreen, playScreen, menuScreen, currenScreen,highScoreScreen;

    private GameThread gameThread;
    public GameWindow gameWindow;

    public GameManager(GameWindow gameWindow) {
        ScoreManager.setHighScore("Media/HighScore.txt");
        this.gameWindow = gameWindow;
        createIntro();
        createMenu();
        createHighScore();
        currenScreen = introScreen;
        
    }

    public void updateSizeScreen() {
        introScreen.updateSize();
        menuScreen.updateSize();
        highScoreScreen.updateSize();

        if(resultScreen != null) 
            resultScreen.updateSize();
        if (playScreen != null)
            playScreen.updateSize();
    }

    // ------add screen method -----------------------------------------
    public void addScreen(GameWindow gameWindow) {
        gameWindow.add(introScreen);
        gameWindow.add(menuScreen);
        gameWindow.add(highScoreScreen);
    }

// =============intro screen =============================================

    public void createIntro() {
        GameButton start = new GameButton() {

            @Override
            public void clickOn() {
                displayMenu();
            }

        };
        start.setText("START");
        start.setForeground(Color.BLACK);
        GameButton exit = new GameButton() {

            @Override
            public void clickOn() {
                System.exit(0);

            }

        };
        exit.setText("EXIT");
        exit.setForeground(Color.BLACK);

        introScreen = new IntroScreen(start, exit);

    }
    // end intro screen

// ======== Menu Screen==========================================================

    public void createMenu() {
        // ---------set EASY button--------
        GameButton easy = new GameButton() {

            @Override
            public void clickOn() {
                startNewPlay(Level.EASY);
                System.out.println("EASY clicked");
            }

        };
        easy.setText("EASY");
        easy.setForeground(Color.BLACK);

        // --------set MEDIUM button------------
        GameButton medium = new GameButton() {

            @Override
            public void clickOn() {
                startNewPlay(Level.MEDIUM);
                System.out.println("Medium clicked ");
            }

        };
        medium.setText("MEDIUM");
        medium.setForeground(Color.BLACK);
        // ---------set HARD button---------
        GameButton hard = new GameButton() {
            @Override
            public void clickOn() {
                startNewPlay(Level.HARD);
                System.out.println("Hard clicked");
            }
        };
        hard.setText("HARD");
        hard.setForeground(Color.BLACK);
        // --------set HighScore button ------------
        GameButton highScore = new GameButton() {
            @Override
            public void clickOn() {
                displayHighscore();
                System.out.println("High score clicked");
            }
        };
        highScore.setText("High Score");
        highScore.setForeground(Color.BLACK);
        // -------set BACK button-----------------------------------------
        GameButton back = new GameButton() {
            @Override
            public void clickOn() {
                displayIntro();
                System.out.println("back clicked");
            }
        };
        back.setText("BACK");
        back.setForeground(Color.black);
        menuScreen = new MenuScreen(easy, medium, hard, highScore, back);
    }
    // End menuScreen

// ===== PlayScreen ============================================================

    // ----------create new play
    // ---------------------------------------------------------------
    public void startNewPlay(Level level) {
        Map newMap = new Map(level);
        MapManager.setPlayingMap(newMap);
        GameButton quiButton = new GameButton() {

            @Override
            public void clickOn() {
                displayMenu();
                gameWindow.remove(playScreen);
            }

        };
        quiButton.setText("QUIT");
        quiButton.setForeground(Color.black);
        PlayScreen newplayScreen = new PlayScreen(newMap, quiButton);
        playScreen = newplayScreen;
        gameWindow.add(playScreen);
        playScreen.updateSize();
        displayPlayScreen();

        gameThread = new GameThread(this);
        gameThread.start();
    }

    public void endPlay() {
        MapManager.showBomb();
        currenScreen.repaint();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

            System.out.println(e);
        }
        createResult(MapManager.getState(), MapManager.getMapScore());
        gameWindow.remove(playScreen);
        playScreen = null;
    }

    // End play screen

// ==== Result Screen ========================================
    public void createResult(GameState state, int score) {

        GameButton okButton = new GameButton() {
            @Override
            public void clickOn() {
                displayMenu();
                gameWindow.remove(resultScreen);
                resultScreen = null;
                System.out.println("OK clicked");
            }
        };
        okButton.setText("OK");
        okButton.setForeground(Color.BLACK);

        this.resultScreen = new ResultScreen(okButton, score, MapManager.getPlayingTime(), state);
        ScoreManager.updateScore(score, MapManager.getPlayingTime());

        gameWindow.add(resultScreen);
        resultScreen.updateSize();
        displayResult();
    }
    
    //End of Result Screen

//================================================================
    public void createHighScore(){
        GameButton back = new GameButton() {
            @Override
            public void clickOn() {
                displayMenu();
                System.out.println("back clicked");
            }
        };
        back.setText("BACK");
        back.setForeground(Color.black);
        this.highScoreScreen = new HighScoreScreen(back);
    }
    // --------get/set method---------------------------------------------------------------------------------
    public GameScreen getIntroScreen() {
        return introScreen;
    }

    public GameScreen getEndGameScreen() {
        return endGameScreen;
    }

    public GameScreen getPauseScreen() {
        return pauseScreen;
    }

    public GameScreen getPlayScreen() {
        return playScreen;
    }

    public GameScreen getResultScreen() {
        return resultScreen;
    }

    public GameScreen getMenuScreen() {
        return menuScreen;
    }
    // --------End of get/set method---------------------------------------------------------------------

    // -------------displaymethod---------------------------------------------------------------
    public void displayIntro() {
        displayScreen(introScreen);
    }

    public void displayMenu() {
        displayScreen(menuScreen);
    }

    public void displayPlayScreen() {
        displayScreen(playScreen);
    }

    public void displayResult() {
        displayScreen(resultScreen);
    }

    public void displayHighscore(){
        displayScreen(highScoreScreen);
    }

    public void displayScreen(GameScreen screen) {
        currenScreen.setVisible(false);
        screen.requestFocus();
        screen.setVisible(true);
        currenScreen = screen;
    }
}
