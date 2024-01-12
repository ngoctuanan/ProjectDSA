package GameEngine;

import java.util.Stack;

public class Map {
    
     
    private boolean firstCell;
    private Cell map[][];
    private int numOfBoom;
    
    private int numOfOpenCell;
    private int numOfTickedBoom;
    private int numOfFlag ;

    private GameState mapState = GameState.DONOTPLAY;
    private long startTime,endTime; 
    
    public Map(Level level){

        firstCell = true;
        numOfOpenCell =0;;
        numOfTickedBoom = 0;
        switch (level) {
            case EASY:
                createEasyMap();
                break;
            case MEDIUM:
                createMediumMap();
                break;
            case HARD:
                createHardMap();
                break;
            default:
                break;
        }
    }
//------------------------------------------

//------methods use for Create Map-----------------------------------

    public void createEasyMap(){
        numOfBoom = 10;
         createMap(9, 9);
    }
    
    public void createMediumMap(){
        numOfBoom = 40;
        createMap(16, 16);
    }

    public void createHardMap(){
        numOfBoom = 99;
        createMap(30, 16);
    }

    //----------create map method ---------------------------------
    public void createMap(int column, int row){
        map = new Cell[row][column];
        numOfFlag = numOfBoom;
        for(int i= 0; i< row; i++ )
            for(int j = 0; j< column; j++ ){
                map[i][j] = new Cell();
                map[i][j].setRow(i);
                map[i][j].setColumn(j);
            }
        mapState = GameState.PLAYING;
    }
    //--- set up map after first click------------------------------------
    public void setUpMap(int startRow, int startColumn){
        map[startRow][startColumn].setData(-2);
        randomBoom(numOfBoom);
        setNumber();

        printMap();
    }
    //------set boom in map --------------------------------------
    public void randomBoom(int numOfBoom){

        for(int i=0; i< numOfBoom; i++ ){
            int bomRow;
            int bomColumn; 
            do {
                bomRow = (int)(Math.random() * (map.length));
                bomColumn = (int)(Math.random() * (map[0].length));
                //System.out.println(bomRow+" " +bomColumn);
            }while ( !checkValidBoom(bomRow, bomColumn));

            map[bomRow][bomColumn].setData(-1);
            System.out.println(i);
        }
    }

    public boolean checkValidBoom(int bomRow , int bomColumn){
        if(map[bomRow][bomColumn].getData() == -1) return false;
        for(int i = -1; i<= 1; i++ )
            for(int j =-1; j<= 1; j++)
                if(isInMap(bomRow+i, bomColumn+j))            
                    if(map[bomRow + i][bomColumn + j].getData() == -1) {
                        if(countBoomAround(bomRow, bomColumn) >= 7) return false;
                    }else 
                        if(map[bomRow + i][bomColumn + j].getData() == -2) return false;


        return true;
    }
    //-------set number in map----------------------------------------
    public void setNumber(){
        for(int i= 0; i< map.length; i++ )
            for(int j=0; j<map[0].length; j++ ){
                if(map[i][j].getData() == 0)map[i][j].setData(countBoomAround(i, j));
            }
    }
    //----end of set boom in map ----------------------------------------
    public int countBoomAround (int bomRow , int bomColumn){
        int countBoom = 0;
        for(int i = -1 ;i<=1; i++ )
            for(int j = -1; j<=1; j++ )
                if(isInMap(bomRow+i, bomColumn+j))
                    if((i!= 0 || j != 0) && map[bomRow + i ][bomColumn + j].getData() == -1) countBoom ++ ;

        return countBoom;
    }

    public boolean isInMap(int cellRow, int cellColumn){
        if(cellRow >= map.length || cellRow < 0) return false;
        if(cellColumn >= map[0].length || cellColumn < 0) return false;

        return true;
    }
//---------End of setup map ---------------------------------

//----------active with map --------------------------------------------------

    public void tick(Cell cell){
        if(cell.getCellState() != CellState.TICKED) {
            if(numOfFlag == 0) return;
            numOfFlag --;
        }else
            numOfFlag++;
        

        
        
        // if(cell.getData() == -1)
        //     if( cell.getCellState() == CellState.TICKED) numOfTickedBoom --;
        //     else
        //         numOfTickedBoom ++;   
        
        cell.tick();
        

    }

    public void open(Cell cell){

        if(firstCell == true){
            startTime = System.nanoTime();
            setUpMap(cell.getRow(), cell.getColumn());
            firstCell = false;
        }
        if(cell.getCellState()==CellState.DEFAULT){
            cell.open();
            if(cell.getData() == -1){
                System.out.println("LOSE");
                mapState = GameState.LOSE;
                endTime = System.nanoTime();
            }
            else
                if(cell.getData() == 0 || cell.getData() == -2) shine(cell);

            numOfOpenCell ++;

            if(numOfBoom - numOfFlag + numOfOpenCell == map.length * map[0].length && GameState.LOSE != mapState){
                mapState = GameState.WIN;
                endTime = System.nanoTime();
            }
            System.out.println(numOfOpenCell +" " +numOfTickedBoom);
        }   
        
    }
// hello
//hello x2

    public void shine(Cell cell){
        Stack<Cell> cellStack = new Stack<>();
        cellStack.push(cell);

        while (!cellStack.isEmpty()) {
            Cell currenCell = cellStack.peek();
            cellStack.pop();

            for(int i= -1; i<= 1; i++ )
                for(int j = -1; j<=1; j++)
                    if(isInMap(currenCell.getRow()+i, currenCell.getColumn()+j)){
                        int row = currenCell.getRow() +i,
                            column = currenCell.getColumn() + j;

                        if(getMap()[row][column].getCellState() == CellState.DEFAULT ){
                            if( map[row][column].getData() == 0){
                                cellStack.push(map[row][column]);
                                map[row][column].open();
                            }else
                                if(map[row][column].getData() > 0) map[row][column].open();
                            
                            numOfOpenCell ++;
                        }
                    }
                            
        }
        

    }

    public int getMapScore(){
        return numOfOpenCell + numOfTickedBoom ;
    }
//end active with map 

//----------------------------------------------------

    public Cell[][] getMap() {
        return map;
    }

    public GameState getMapState() {
        return mapState;
    }

    public int getNumOfFlag() {
        return numOfFlag;
    }

    public void printMap(){
        for(int i= 0; i<map.length; i++ ){
            for(int j= 0 ; j< map[0].length; j++ )
                System.out.print(map[i][j].getData()+" ");
            System.out.println();
        }
    }
//---------------------------------------------------

    public long getPlayingTime(){
        return endTime - startTime;
    }

    public long getCurrentTIme(){
        if(firstCell) return 0;
        return System.nanoTime() - startTime;
    }

    public void pause(){
        mapState = GameState.PAUSE;
    }

    public void resume(){
        mapState = GameState.PLAYING;
    }
}