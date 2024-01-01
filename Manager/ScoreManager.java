package Manager;

import java.util.LinkedList;

import Data.LoadFile;

public class ScoreManager {
    LinkedList<Integer> highScore ;
    public ScoreManager(){
        highScore = LoadFile.loadInt("Media/HighScore.txt");
    }

    public void updateScore(int score){
        
    }

  
}
