package Manager;

import GameEngine.GameState;

public class GameThread extends Thread  {
    int FPS = 60;

    GameManager screenManager;
    public GameThread(GameManager playScreen){
        this.screenManager = playScreen;
    }
    @Override
    public void run() {
        
        double drawInterval = 1e9 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime ;
        while(!this.isInterrupted()){
            //System.out.println("thread");
            if(MapManager.getState() != GameState.PLAYING) {
                screenManager.endPlay();
                System.out.println("Stop the game thread ");
                this.interrupt();
                
            }
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1){
                delta -= 1;
                screenManager.getPlayScreen().repaint();
            }
        }
    }
    
}
