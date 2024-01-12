package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import GameEngine.Node;
import GameEngine.SortedLinkedList;
import javax.imageio.ImageIO;


public class FileOperations {
//------load imgae method---------------------------------------------
    public static ImageIcon loadImage (String path){

        File imageFile = createFile(path);

        ImageIcon image = null;
        try {
            image = new ImageIcon(ImageIO.read(imageFile));
            //System.out.println("load image path: "+path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }//end of load image method
//------load integer number method ------------------------------------------
    public static LinkedList<Long> loadFileInt(String path){
        LinkedList<Long> numbers = new LinkedList<>();
        File file = createFile(path);
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLong  ()) {
                numbers.add(scanner.nextLong());
            }
        } catch (FileNotFoundException e) {
            System.out.println("can not found file : " + e.getMessage());
        }

        return numbers;
    }

    public static Long loadLong(Scanner scanner){
        long number=0 ;
        
        try {

            if(scanner.hasNextLong()) number = scanner.nextLong(); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return number;
    }
//---------------------------------------------------------------
    public static void writeString(String string,FileWriter file){
        try {
            file.write(string+"\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeScore(SortedLinkedList list , String path){
        FileWriter file;
        
        
        try {
            file = new FileWriter(createFile(path));
            Node current = list.getHead();
            int i =0;
            while (current!= null && i < 100) {
                writeString(current.toString(), file);
                current = current.getNextNode();
                i++;
            }
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }
//------------------------------------------------------------------
    public static File createFile(String path){
        File file = new File(path);
        
        if(!file.exists()) System.out.println(path + " does not exist");

        return file;
    }

}
