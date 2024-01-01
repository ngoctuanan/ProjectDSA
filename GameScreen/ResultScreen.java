package GameScreen;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import GameEngine.GameState;
import GameEngine.Time;

public class ResultScreen extends GameScreen {

    private GameButton ok ; 
    private JLabel resultLabel;
    private JLabel timeLabel;
    private JLabel stateLabel;

    public ResultScreen(GameButton okButton,int score,long time,GameState state){
        super();
        this.ok = okButton;
        setupStateLabel(state);
        setResult(score,time);
        defineScreen();
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateFontSize();
            }
        });
        System.out.println(time);
    }
    @Override
    public void defineScreen() {
        this.add(stateLabel);
        this.add(resultLabel);
        this.add(timeLabel);
        this.add(ok);
        this.setLayout(new GridLayout(4,1));
    }

    @Override
    public void updateSize() {
        this.setSize( SwingUtilities.getWindowAncestor(this).getSize());
    }

    public void updateFontSize(){
        Font font = new Font(stateLabel.getFont().getFontName(),Font.PLAIN,this.getWidth()/10);
        stateLabel.setFont(font);
        stateLabel.setFont(font);
        
        font = new Font(resultLabel.getFont().getName(),Font.PLAIN,this.getWidth()/15);
        resultLabel.setFont(font);
        timeLabel.setFont(font);

        font = new Font(ok.getFont().getName(), Font.PLAIN, this.getWidth()/20);
        ok.setFont(font);
    }

//----------------------------------------------------------------------
    public void setupStateLabel(GameState state){
        if(state == GameState.LOSE) setupLoseLabel();
        else
            setupWinLabel();
    }
    public void setupWinLabel(){
        stateLabel = new JLabel();

        stateLabel.setText("YOU WIN");
        this.add(stateLabel);

        stateLabel.setVisible(true);
    }

    public void setupLoseLabel(){
        stateLabel = new JLabel();

        stateLabel.setText("YOU LOSE");
        this.add(stateLabel);
        setVisible(true);
    }
    public void setResult(int Score, long time ){
        resultLabel = new JLabel();
        resultLabel.setText("SCORE : "+String.valueOf(Score));
        
        Time timeResult = new Time(time);
        timeLabel = new JLabel();
        timeLabel.setText("TIME : " + timeResult.toString());
    }
    
}
