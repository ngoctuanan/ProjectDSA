package GameScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;

import GameEngine.Cell;
import GameEngine.Map;
import GameEngine.Time;
import Manager.MapManager;

public class PlayScreen extends GameScreen {
    private JPanel playPanel ;
    private JPanel menuPanel ;
    private JLabel timeLabel,flagLabel;
    private GameButton quit ;


    private Map map ;
    public PlayScreen(Map map,GameButton quiButton ){
        super();
        this.quit = quiButton;
        this.map = map;
        defineScreen();  
    }

    @Override
    public void defineScreen() {
       
        setPlayPanel();
        setMenuPanel();
        playPanel.setBackground(Color.WHITE);
        menuPanel.setBackground(Color.CYAN);
        this.add(menuPanel);
        this.add(playPanel);
        this.setLayout(null);
        this.setVisible(false);

    }

    @Override
    public void updateSize() {
        this.setSize( SwingUtilities.getWindowAncestor(this).getSize());
        playPanel.setSize(this.getWidth()-20,this.getHeight()*4/6);
        menuPanel.setSize(this.getWidth()-20,this.getHeight() *1/6);

        menuPanel.setBounds(0,0,this.getWidth()-20,this.getHeight() * 1/6);
        playPanel.setBounds(0, menuPanel.getHeight(), this.getWidth()-20, this.getHeight()*4/6);

        //System.out.println("Play panel" + this.getSize()+"\n\t"+menuPanel.getSize()+"\n\t"+playPanel.getSize());
    }

    @Override
    protected void paintComponent(Graphics g) {
        updateMentuContent(map.getCurrentTIme(), map.getNumOfFlag());
        super.paintComponent(g);
    }

    public void updatePlayPanel(){
        Map map = MapManager.playingMap;
        
        for(int i=0; i< map.getMap().length; i++ ) 
            for(int j=0; j<map.getMap()[0].length; j++)
                this.add(map.getMap()[i][j]);

    }
//-----------set up playPanel--------------
    public void setPlayPanel(){
        playPanel = new JPanel();
        
        for(int i=0; i< map.getMap().length; i++ ) 
            for(int j=0; j<map.getMap()[0].length; j++){
                playPanel.add(map.getMap()[i][j]);
                Cell cell = map.getMap()[i][j];
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        
                    }
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(e.getButton() == MouseEvent.BUTTON3){
                            map.tick(cell);
                            //System.out.println("Tiked " + cell.getRow()+" "+cell.getColumn());
                        }
                        if(e.getButton() == MouseEvent.BUTTON1) {
                            map.open(cell);
                            //System.out.println("Open     " + cell.getRow()+" "+cell.getColumn());
                            
                        }
                    }
                });
            }

        playPanel.setLayout(new GridLayout(map.getMap().length,map.getMap()[0].length));

        playPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                for(int i=0; i< map.getMap().length; i++ ) 
                    for(int j=0; j<map.getMap()[0].length; j++)
                        map.getMap()[i][j].setSize(playPanel.getWidth()/map.getMap()[0].length,playPanel.getWidth()/map.getMap().length);

                //System.out.println(map.getMap()[0][0].getSize());
            }
        });
    } 

    
    

//----------set up menuPanel----------------
    public void setMenuPanel(){
        this.menuPanel = new JPanel();
        timeLabel = new JLabel();
        flagLabel = new JLabel();
        
        menuPanel.add(quit);
        menuPanel.add(timeLabel);
        menuPanel.add(flagLabel);

        timeLabel.setForeground(Color.YELLOW);
        flagLabel.setForeground(Color.RED);

        menuPanel.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e) {
                Font font = new Font(timeLabel.getFont().getName(),Font.PLAIN,menuPanel.getWidth()/20);

                timeLabel.setFont(font);
                menuPanel.setFont(font);
                quit.setFont(font);

            }
        });
    
    }


    public void updateMentuContent(long time, int numFlag){
        timeLabel.setText("TIME: " + new Time(time));
        flagLabel.setText("FLAG: " + String.valueOf(numFlag));
    }
//------------------------------------------

    @Override
    public void updateFontSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFontSize'");
    }
    
}
