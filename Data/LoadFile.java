package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class LoadFile {
    public static ImageIcon loadImage (String path){

        File imageFile = new File(path);
        //System.out.println(imageFile.exists());

        ImageIcon image = null;
        try {
            image = new ImageIcon(ImageIO.read(imageFile));
            //System.out.println("load image path: "+path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    public static LinkedList<Integer> loadInt(String path){
        LinkedList<Integer> numbers = new LinkedList<>();
        File file = new File(path);
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("can not found file : " + e.getMessage());
        }

        return numbers;
    }

    public static void writeInt(LinkedList<Integer> numbers,String path){

        File file = new File(path);

        try (PrintWriter writer = new PrintWriter(file)) {
            for(int i=0; i< numbers.size(); i++ )
                writer.print(numbers.get(i)+" ");
            
        } catch (FileNotFoundException e) {
            System.out.println("Can not write : " + e.getMessage());
        }
    }
}
