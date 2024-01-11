package GameScreen;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;


public class MenuScreen extends GameScreen {
    private JPanel buttonPanel ;
    private GameButton easy,medium,hard,highScore,back;
    private JScrollPane scrollPane;

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
        scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);

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
        

        int screenWidth = this.getWidth();
        int screenHeight = this.getHeight();
    
        // Cập nhật kích thước và vị trí của scrollPane
        scrollPane.setSize(screenWidth, screenHeight);
        scrollPane.setLocation(0, 0);
    
        // Cập nhật kích thước của buttonPanel và đặt giữa màn hình
        int buttonPanelWidth = screenWidth / 2; // Chỉ sử dụng nửa chiều rộng của màn hình
        int buttonPanelHeight = screenHeight / 2;
        int panelX = (screenWidth - buttonPanelWidth) / 2;
        int panelY = (screenHeight - buttonPanelHeight) / 2;
        
        buttonPanel.setSize(buttonPanelWidth, buttonPanelHeight);
        buttonPanel.setLocation(panelX, panelY);
        buttonPanel.setLayout(new GridLayout(5, 1));
    
        // Cập nhật kích thước phông chữ của các nút
        Font font = new Font(easy.getFont().getFontName(), Font.PLAIN, buttonPanelWidth / 10);  
        easy.setFont(font);
        medium.setFont(font);
        hard.setFont(font);
        highScore.setFont(font);
        back.setFont(font);
    
        // Cập nhật vị trí của các nút để đặt chúng vào giữa buttonPanel
        // Cập nhật vị trí của các nút để đặt chúng vào giữa buttonPanel
        // Cập nhật vị trí của các nút để đặt chúng vào giữa buttonPanel
        int startX = (buttonPanelWidth - easy.getPreferredSize().width) / 2;  // Bắt đầu từ giữa của buttonPanel
        int startY = (buttonPanelHeight - easy.getPreferredSize().height) / 2;  // Bắt đầu từ giữa của buttonPanel

        easy.setBounds(startX, startY, easy.getPreferredSize().width, easy.getPreferredSize().height);

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
