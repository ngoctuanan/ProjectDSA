package GameEngine;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;

import javax.swing.JPanel;



public class Cell extends JPanel  {
    private int data;
    private CellState cellState = CellState.DEFAULT;
    private ImageIcon dataImage;
    //private JLabel dataLabel;
    private int row,column;
    private static CellImage number[] = new CellImage[]{CellImage.NUMBER,CellImage.ONE,CellImage.TWO,CellImage.THREE,CellImage.FOUR,CellImage.FIVE,CellImage.SIX,CellImage.SEVEN};
    public Cell(){
        cellState = CellState.DEFAULT;
        setData(0);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                
            }
        });
        // addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         if(e.getButton() == MouseEvent.BUTTON3) changeState(CellState.TICKED);
        //         if(e.getButton() == MouseEvent.BUTTON1) System.out.println("some");
        //     }
        // });

        
    }
//-----------------------------------
    public void setData(int data) {
        

        this.data = data;
        if(data == -1 ) this.dataImage = CellImage.BOOM.image;
        else
            this.dataImage = CellImage.NUMBER.image;


    }


    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }
    public int getData() {
        return data;
    }

    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public CellState getCellState() {
        return cellState;
    }
//-----------------------------------------
    @Override
    public void paintComponent(Graphics g) {

        
        ImageIcon image;
        if(CellState.OPEN == this.cellState) {
            image = dataImage;
        }
        else    
            image = CellImage.BRICK.image;

        g.drawImage(image.getImage(), 0, 0, this.getWidth(),this.getHeight(),this);
        if(CellState.TICKED == this.cellState)g.drawImage(CellImage.FRAG.image.getImage(),0 , 0, this.getWidth(), this.getHeight(), this);

        if(data > 0 && CellState.OPEN == this.cellState)  g.drawImage(number[data].image.getImage(),0 , 0, this.getWidth(), this.getHeight(), this);
        
       
        

    }
    //----------------------------------------------------------------------------------------------------------
    public void changeState(CellState state){
        this.cellState = state;
    }
    public void open(){
        if(this.cellState != CellState.TICKED ) changeState(CellState.OPEN);
    }
    public void tick(){
        if(this.cellState != CellState.OPEN)
            if(this.cellState == CellState.TICKED ) changeState(CellState.DEFAULT);
            else
                changeState(CellState.TICKED);
    }
    //------------------------------------------------------------------
}

