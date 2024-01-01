package Manager;

import javax.swing.JFrame;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.awt.*;
public class GameWindow extends JFrame{

    GameManager screenManager; 

    public GameWindow(){
        
        this.setName("MineSweeper");
        this.setTitle("MineSweeper");
        this.setBackground(Color.WHITE);
        this.setMinimumSize(new Dimension(600,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        System.out.println("Window"+getSize());
        
        screenManager = new GameManager(this);
        screenManager.addScreen(this);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                screenManager.updateSizeScreen();
                super.componentResized(e);    
            }

        });
        
        screenManager.displayIntro();

        this.setVisible(true);
    }

    public void startNewPlay(){

    }

}