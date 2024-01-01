package GameScreen;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public abstract class GameScreen extends JPanel {
    ImageIcon backGroundImage ; 

    GameScreen(){
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.setVisible(false);
    }

    public abstract void defineScreen();
    public abstract void updateSize();
    
}
