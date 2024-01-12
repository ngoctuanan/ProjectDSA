package GameScreen;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


import Data.FileOperations;


public class MenuScreen extends GameScreen {
    
    private GameButton easy,medium,hard,highScore,back;

    public MenuScreen(GameButton easyButton, GameButton mediumButton, GameButton hardButton, GameButton highScoreButton, GameButton backButton){
        super();

        this.easy = easyButton;
        this.medium = mediumButton;
        this.hard = hardButton;
        this.highScore = highScoreButton;
        this.back = backButton;
        this.defineScreen();
        

    }

//-------------------------------------------------------------

//-------------------------------------------------------------------------------------------------

    @Override
    public void defineScreen() {
       
        

        this.backGroundImage = FileOperations.loadImage("GameScreen/image/menuPlay.jpg");
        this.addButton();
        this.setLayout(null);

        easy.setForeground(Color.WHITE);  
        medium.setForeground(Color.WHITE);
        hard.setForeground(Color.WHITE);
        highScore.setForeground(Color.WHITE);
        back.setForeground(Color.WHITE);
        
        
        updateButtonLabelSize();
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                updateButtonLabelSize();
                updateFontSize();
                super.componentResized(e);
            }
        });    
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backGroundImage != null){
        g.drawImage(this.backGroundImage.getImage(), 0, 0, this.getWidth(),this.getHeight(),this);
        }
    }
    public void addButton(){
        this.add(easy); 
        this.add(medium); 
        this.add(hard); 
        this.add(highScore);
        this.add(back);

       
    }
//------------------------------------------------------------------
   
//------------------------------------------------------------------------------
    public void updateButtonLabelSize(){
        int screenWidth = this.getWidth();
        int screenHeight = this.getHeight();
        
    
        
        int buttonWidth = screenWidth / 2;
        int buttonHeight = screenHeight / 10; 
    
        int panelX = (screenWidth - buttonWidth ) / 2;
        int panelY = (screenHeight - 5 * buttonHeight) / 2; 

        int offset = 35 ;
    
        
        easy.setBounds(panelX + 80 , panelY - 40, buttonWidth, buttonHeight);
        medium.setBounds(panelX + 55, panelY + buttonHeight + offset - 40, buttonWidth, buttonHeight);
        hard.setBounds(panelX + 80, panelY + 2 * (buttonHeight + offset) - 40, buttonWidth, buttonHeight);
        highScore.setBounds(panelX + 20, panelY + 3 * (buttonHeight + offset) -40, buttonWidth, buttonHeight);
        back.setBounds(panelX + 80, panelY + 4 * (buttonHeight + offset) - 40, buttonWidth, buttonHeight);
    }

    public void updateFontSize(){

        Font font = new Font(easy.getFont().getFontName(),Font.PLAIN,this.getWidth()/15);

        easy.setFont(font);
        medium.setFont(font);
        hard.setFont(font);
        highScore.setFont(font);
        back.setFont(font);

    }
    //--------------------------------------------------------------------------------------------------

    
}
