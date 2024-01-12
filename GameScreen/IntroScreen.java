package GameScreen;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


import Data.FileOperations;


public class IntroScreen extends GameScreen {

    private GameButton start,exit;
    public IntroScreen(GameButton starButton, GameButton exitButton){
        super();
        this.start = starButton;
        this.exit = exitButton;

        this.defineScreen();
        
        
    }
//-----------------------------------------------------------------------------------------------
    @Override
    public void defineScreen() {
        
        this.backGroundImage = FileOperations.loadImage("GameScreen/image/intro_background.jpg");

        this.addButton();
        this.setLayout(null);
        
        updatePosition();
        updateFontSize();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                
                updateFontSize();
                updatePosition();
                super.componentResized(e);
            }
        });
    }
//--------------------------------------------------------------------------------------------------
        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(this.backGroundImage.getImage(), 0, 0, this.getWidth(),this.getHeight(),this);
        
    }
//-------------------------------------------------------------------------------------------------
    // @Override
    // public void updateSize() {
        
    //     this.setSize( SwingUtilities.getWindowAncestor(this).getSize());
    //     //System.out.println("xx"+this.getSize());
    // }

//-------------------------------------------------------------------------------------------------------

    public void addButton(){
        this.add(start);
        this.add(exit);
    }
//--------------------------------------------------------------------------------------------------------
    public void updateFontSize(){
        Font font = new Font(start.getFont().getFontName(),Font.PLAIN,this.getWidth()/10);
        start.setFont(font);
        exit.setFont(font);
    }

    public void updatePosition(){
        int screenWidth = this.getWidth();
        int screenHeight = this.getHeight();
    
        int startWidth = start.getPreferredSize().width;
        int startHeight = start.getPreferredSize().height;
    
        int exitWidth = exit.getPreferredSize().width;
        int exitHeight = exit.getPreferredSize().height;
    
        
        int startX = (screenWidth - startWidth) / 2;
        int startY = (screenHeight - startHeight - exitHeight - 1) / 2;
    
        
        int exitX = (screenWidth - exitWidth) / 2;
        int exitY = startY + startHeight + 1;
    
        start.setBounds(startX, startY, startWidth, startHeight);
        exit.setBounds(exitX, exitY, exitWidth, exitHeight);
    }
//--------------------------------------------------------------------------------------------------------
}
