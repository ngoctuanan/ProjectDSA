package GameEngine;

import javax.swing.ImageIcon;

import Data.FileOperations;

public enum CellImage {
    BRICK("Media/brick.png"),
    BOOM("Media/boom.png"),
    FRAG("Media/redFlag.png"),
    NUMBER("Media/window.png"),
    RED("Media/redBackground.png"),
    ONE("Media/1.png"),
    TWO("Media/2.png"),
    THREE("Media/3.png"),
    FOUR("Media/4.png"),
    FIVE("Media/5.png"),
    SIX("Media/6.png"),
    SEVEN("Media/7.png");

    ImageIcon image;

    CellImage (String path){
        image = FileOperations.loadImage(path);
    }
}
