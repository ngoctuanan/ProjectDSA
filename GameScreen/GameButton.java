package GameScreen;


import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JLabel;

public abstract class GameButton extends JLabel implements MouseListener {
    
    public GameButton(){
        this.addMouseListener(this);
    }

        @Override
        public void mouseClicked(MouseEvent e) {
            clickOn();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //System.out.println("right");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            focusing();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            stopFocus();
        }

        public void focusing(){
            this.setForeground(Color.GRAY);
        }
        public void stopFocus(){
            this.setForeground(Color.BLACK);
        }
        public abstract void clickOn();
    
}

