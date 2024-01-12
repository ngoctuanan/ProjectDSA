package GameScreen;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Data.FileOperations;
import GameEngine.GameState;
import GameEngine.Time;
import java.awt.Graphics;
import java.awt.Color;

public class ResultScreen extends GameScreen {

    private GameButton ok;
    private JLabel resultLabel;
    private JLabel timeLabel;
    private JLabel stateLabel;

    public ResultScreen(GameButton okButton, int score, long time, GameState state) {
        super();
        this.ok = okButton;
        setupStateLabel(state);
        setResult(score, time);
        defineScreen();

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateFontSize();
                centerText();
            }
        });
        System.out.println(time);
    }

    @Override
    public void defineScreen() {
        this.backGroundImage = FileOperations.loadImage("GameScreen/image/resulf.jpg");
        this.add(stateLabel);
        this.add(resultLabel);
        this.add(timeLabel);
        this.add(ok);
        this.setLayout(new GridLayout(4, 1));

        stateLabel.setForeground(Color.WHITE);  
        resultLabel.setForeground(Color.WHITE);
        timeLabel.setForeground(Color.WHITE);
        ok.setForeground(Color.WHITE);
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backGroundImage != null){
        g.drawImage(this.backGroundImage.getImage(), 0, 0, this.getWidth(),this.getHeight(),this);
        }
    }

    @Override
    public void updateSize() {
        this.setSize(SwingUtilities.getWindowAncestor(this).getSize());
        centerText();
    }
    public void updateFontSize() {
        Font font = new Font(stateLabel.getFont().getFontName(), Font.PLAIN, this.getWidth() / 10);
        stateLabel.setFont(font);
        resultLabel.setFont(font);
        timeLabel.setFont(font);
    
        font = new Font(ok.getFont().getName(), Font.PLAIN, this.getWidth() / 10);
        ok.setFont(font);
        adjustStateLabelHorizontalPosition();
    }
    

    public void centerText() {
        centerSingleText(stateLabel);
        centerSingleText(resultLabel);
        centerSingleText(timeLabel);
        centerSingleText(ok);
    }

    private void centerSingleText(JLabel label) {
        if (label != null) {
            int labelWidth = label.getPreferredSize().width;
            int labelHeight = label.getPreferredSize().height;

            int x = (this.getWidth() - labelWidth) / 2;
            int y = (this.getHeight() - labelHeight) / 2;

            label.setBounds(x , y, labelWidth, labelHeight);
        }
    }

    //----------------------------------------------------------------------
    public void adjustStateLabelHorizontalPosition() {
        if (stateLabel != null) {
            int labelWidth = stateLabel.getPreferredSize().width;
            int screenWidth = this.getWidth();
    
            int x = (screenWidth - labelWidth) / 2;
            stateLabel.setBounds(x, stateLabel.getY(), labelWidth, stateLabel.getHeight());
        }
    }
    public void setupStateLabel(GameState state) {
        if (state == GameState.LOSE)
            setupLoseLabel();
        else
            setupWinLabel();
    }

    public void setupWinLabel() {
        stateLabel = new JLabel();
        stateLabel.setText("YOU WIN");
        stateLabel.setVisible(true);
        adjustStateLabelHorizontalPosition();
    }

    public void setupLoseLabel() {
        stateLabel = new JLabel();
        stateLabel.setText("YOU LOSE");
        setVisible(true);
    }

    public void setResult(int Score, long time) {
        resultLabel = new JLabel();
        resultLabel.setText("SCORE : " + String.valueOf(Score));

        Time timeResult = new Time(time);
        timeLabel = new JLabel();
        timeLabel.setText("TIME : " + timeResult.toString());
    }
    
}
