package Manager;

import java.util.LinkedList;


import Data.FileOperations;
import GameEngine.SortedLinkedList;

public class ScoreManager {
    private static SortedLinkedList highScore ;

    public static void setHighScore(String path){
        LinkedList<Long> linkedList = FileOperations.loadFileInt(path);
        highScore = new SortedLinkedList();
        for(int i =0 ; i< linkedList.size(); i+=2 ){
            long data = linkedList.get(i), time = linkedList.get(i+1);
            highScore.add(data, time);
        }

    }
    public static void storeHighScore(String path){
        FileOperations.storeScore(highScore, path);
    }
    public static void updateScore(long score,long time){
            highScore.add(score, time);
            storeHighScore("Media/HighScore.txt");
    }

    
    public static void main(String args[]){
        ScoreManager.setHighScore("Media/HighScore.txt");
        ScoreManager.displayHighscore();
        System.out.println(highscoreToString());
    }
  
    public static String highscoreToString(){
        return highScore.toString();
    }
    public static void displayHighscore(){
        highScore.display();
    }
}
