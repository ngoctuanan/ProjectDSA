package GameScreen;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class MenuScreen extends GameScreen {
    private JPanel buttonPanel ;
    private GameButton easy,medium,hard,highScore,back;

    public MenuScreen(GameButton easyButton, GameButton mediumButton, GameButton hardButton, GameButton highScoreButton, GameButton backButton){
        super();

        this.easy = easyButton;
        this.medium = mediumButton;
        this.hard = hardButton;
        this.highScore = highScoreButton;
        this.back = backButton;
        defineScreen();

    }

//-------------------------------------------------------------

    @Override
    public void defineScreen() {
       
        setButtonLabel();
        this.add(buttonPanel);

        this.setLayout(null);
        buttonPanel.setBackground(Color.CYAN);
        updateButtonLabelSize();
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                updateButtonLabelSize();
                super.componentResized(e);
            }
        });

         this.setVisible(false);
        
    }
    
    @Override
    public void updateSize() {
        this.setSize( SwingUtilities.getWindowAncestor(this).getSize());
        
    }

//------------------------------------------------------------------
    public void setButtonLabel(){
        buttonPanel = new JPanel();
        //---------------------------------------------------
        buttonPanel.add(easy);
        buttonPanel.add(medium);
        buttonPanel.add(hard);
        buttonPanel.add(highScore);
        buttonPanel.add(back);
        buttonPanel.setLayout(new GridLayout(5,1));
        //-----------------------------------------------------------
    }
//------------------------------------------------------------------------------
    public void updateButtonLabelSize(){
        buttonPanel.setSize(this.getWidth()/2, this.getHeight()/2);
        updateFontSize();
        
    }

    public void updateFontSize(){

        Font font = new Font(easy.getFont().getFontName(),Font.PLAIN,buttonPanel.getWidth()/10);

        easy.setFont(font);
        medium.setFont(font);
        hard.setFont(font);
        highScore.setFont(font);
        back.setFont(font);

    }
    
}
