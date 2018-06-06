package Domain;

import Domain.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import Domain.Block;
import java.util.concurrent.ThreadLocalRandom;

public class Item1 extends Player {
    
 /*Item 2,  solo se muestrrasn cuando los personajes smart, se muestran,, tiene un movimiento  rigth-left*/

    public Item1(String name, int x, int y, Block object[][]) throws FileNotFoundException {
        super(name, x, y, object);

        setSprite2();
    }

    public void setSprite2() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite2();
        sprite.add(new Image(new FileInputStream("src/Assets/vida1.png")));
    }

    @Override

    public void run() {
        ArrayList<Image> sprite2 = super.getSprite2();

        int x = 100;//330 

        int y = 500;//459

        int direccion = 1;
        boolean condition = false;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Item1.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {

            try {
                int v = (this.getX() - 10) / 40;//40
                int m = (this.getY()) / 40;//40

                int yy = (this.getY()) / 40;//40
                int xx = (this.getX() + 25) / 40;//4

                if (v < object.length && object[m][v + 1].getNum() == 1) {
                    condition = true;
                    direccion = 2;//2
                }
                if (v > 0 && object[m][v - 1].getNum() == 1) {
                    direccion = 1;//1
                }
                switch (direccion) {
                    case 1:
                        super.setImage(sprite2.get(0));
                        super.setX(x++);
                        Thread.sleep(10);
                        break;
                    case 2:
                        super.setImage(sprite2.get(0));//IZQUIERDA
                        super.setX(x--);
                        Thread.sleep(10);

                        break;
                    case 3:
                        super.setImage(sprite2.get(0));///////////BAJA PARA ABAJO
                        super.setY(y++);
                        Thread.sleep(10);

                        break;

                    case 4:
                        super.setImage(sprite2.get(0));//VA PARA  ARRIBA
                        super.setY(y--);
                        Thread.sleep(50);
                        break;

                    default:
                        break;
                }
            } catch (InterruptedException ex) {

            }
        }
    }

}
