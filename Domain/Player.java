package Domain;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Player extends Thread {

    private int x;
    private int y;
    private int imgNum;
    private Image image, image2;
    private ArrayList<Image> sprite, sprite2;
    public Block object[][];
    String position;
    String time;
    public String name;

    /*Clase padre*/
    public Player(String nombre,int x, int y, Block object[][]) {
        this.x = x;
        this.y = y;        
        this.time=time;
        this.name = nombre;
        this.sprite2 = new ArrayList<Image>();
        this.sprite = new ArrayList<Image>();
        this.object = object;
    }

    public Image getImage2() {
        return image2;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    public Player() {
    }

    public Block[][] getObject() {
        return object;
    }

    public void setObject(Block object[][]) {
        this.object = object;
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

    public int getImgNum() {
        return imgNum;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImgNum(int imgNum) {
        this.imgNum = imgNum;
    }

    public ArrayList<Image> getSprite() {
        return sprite;
    }

    public void setSprite(ArrayList<Image> sprite) {
        this.sprite = sprite;
    }

    public ArrayList<Image> getSprite2() {
        return sprite2;
    }

    public void setSprite2(ArrayList<Image> sprite2) {
        this.sprite2 = sprite2;
    }

}
