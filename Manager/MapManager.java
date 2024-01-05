package Manager;

import GameEngine.*;

public class MapManager {
    public static Map playingMap;

    public MapManager() {

    }

    public static void setPlayingMap(Map map) {
        playingMap = map;
    }

    public static GameState getState() {
        return playingMap.getMapState();
    }

    public static int getMapScore() {
        return playingMap.getMapScore();
    }

    public static long getPlayingTime() {
        return playingMap.getPlayingTime();
    }

    public static void showBomb() {
        for (int i = 0; i < playingMap.getMap().length; i++) {
            for (int j = 0; j < playingMap.getMap()[0].length; j++) {
                if (playingMap.getMap()[i][j].getData() == -1) {
                    playingMap.getMap()[i][j].changeState(CellState.OPEN);
                }
            }

        }

    }
}
