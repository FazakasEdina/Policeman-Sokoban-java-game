//Fazakas Edina-Szylvia
//Csoport: 522/1
//Azonosito: feim1911

package modelPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block {
    private final int size;
    private int x;
    private int y;
    private BufferedImage img;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        size = 50;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void paintObject(Graphics g){
        g.drawImage(img, getX(), getY(), size, size, null);
    }

    public boolean isLeftAnObject(Block obj) {
        return ( getX() - size == obj.getX() ) &&  ( getY() == obj.getY() );
    }

    public boolean isRightAnObject(Block obj) {
        return ( getX() + size == obj.getX() ) && ( getY() == obj.getY() );
    }

    public boolean isUpAnObject(Block obj) {
        return ( getX() == obj.getX() ) && ( getY() - size == obj.getY() );
    }

    public boolean isDownAnObject(Block obj) {
        return ( getX() == obj.getX() ) && ( getY() + size == obj.getY() );
    }

}
