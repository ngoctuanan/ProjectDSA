package GameScreen;

import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;


import Manager.ScoreManager;

public class HighScoreScreen extends GameScreen {
    private GameButton back;
    private JLabel scoreLabel;
    public HighScoreScreen(GameButton backButton ){
        super();
        this.back = backButton;
        this.scoreLabel = new JLabel();
        defineScreen();
    }
    @Override
    public void defineScreen() {
        this.setLayout(null);

        this.add(back);
        this.add(scoreLabel);
        

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateFontSize();
                updatePosition();
            }
        });

    }


//---------------------------------------------------

    @Override
    public void updateSize() {
        super.updateSize();
    }
    @Override
    public void updateFontSize() {
        Font font = new Font(back.getFont().getName(),Font.PLAIN,this.getWidth()/20);
        back.setFont(font);

        font = new Font(back.getFont().getName(),Font.PLAIN,this.getWidth()/20);
        scoreLabel.setFont(font);;
    }   

    public void updatePosition(){
        back.setBounds(0, this.getHeight() - back.getPreferredSize().width, back.getPreferredSize().width, back.getPreferredSize().height);
        System.out.println(back.getSize());
        scoreLabel.setBounds(0, 0, this.getWidth(), this.getHeight() - back.getHeight() );

    }

    public void updateContent(){
        
        scoreLabel.setText(ScoreManager.highscoreToString());
            
    }
//-------------------------------------------------
    

//-------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        updateContent();
        System.out.println(scoreLabel.getText());
        
        super.paintComponent(g);
    }
    

    

}