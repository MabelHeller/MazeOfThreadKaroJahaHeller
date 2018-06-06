/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class Item2 extends Player {

    /*Item 2,  solo se muestrrasn cuando los personajes smart, se muestran,, tiene un movimiento  UP-DOWM*/
    public Item2(String name, int x, int y, Block object[][]) throws FileNotFoundException {
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

        int x = 400;//330 

        int y = 400;//459

        int direccion = 3;
        boolean condition = false;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Item1.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {

            try {
                int v = (this.getX()) / 40;//40
                int m = (this.getY()) / 40;//40

                if (m < object.length - 1 && object[m + 1][v].getNum() == 1) {//DOWM
                    direccion = 4;//4

                    condition = true;
                } else if (condition == true && m > 0 && object[m - 1][v].getNum() == 1) {//UP
                    direccion = 3;///3
                }
                switch (direccion) {

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
