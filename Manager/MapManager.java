package Manager;

import GameEngine.*;
public class MapManager {
    public static Map playingMap ;
    public MapManager(){
        
    }
    public static void setPlayingMap(Map map){
        playingMap = map;
    }

    public static GameState getState(){
        return playingMap.getMapState();
    }

    public static int getMapScore(){
        return playingMap.getMapScore();
    }
    public static long getPlayingTime(){
        return playingMap.getPlayingTime();
    }
}
