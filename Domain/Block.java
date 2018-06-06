
package Domain;

public class Block {

    int x;
    int y;
    int heigth;
    int width;
    int num;

    
    /*Encapsulamiento, almacena la matriz quemada*/
    public Block(int x, int y, int heigth, int width, int num) {
        this.x = x;
        this.y = y;
        this.heigth = heigth;
        this.width = width;
        this.num = num;
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

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Object{" + "x=" + x + ", y=" + y + ", heigth=" + heigth + ", width=" + width + ", num=" + num + '}';
    }

}
