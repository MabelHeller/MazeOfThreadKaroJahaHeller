/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Domain.Block;

import static Utility.Variables.fieldContent;
import static Utility.Variables.ta;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class RunningFurious1 extends Player {

    int[] limites = new int[4];
    
    /*Subclases*/
    public Double time;
    public String nameThread;

    public RunningFurious1( String nameImage, String nameThead, Double time, int x, int y, Block object[][]) throws FileNotFoundException {
        super(nameImage, x, y, object);       
        this.nameThread = nameThead;
        this.time = time;
        setSprite();
    

    } 
    
    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        // sprite.add(new Image(new FileInputStream("src/Assets/furioso.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/" + name + ".png")));
    }
    
   
    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getNameThread() {
        return nameThread;
    }

    public void setNameThread(String nameThread) {
        this.nameThread = nameThread;
    }

    @Override
    public void run() {

        ArrayList<Image> sprite = super.getSprite();

        ArrayList<Integer> posiciones_posibles = new ArrayList<Integer>();
        int x = 30;//330
        int y = 40;//459
        int v;
        int m;
        int direccion = 1;
        boolean ejecutar=true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            //  Logger.getLogger(RunningCharacter.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (ejecutar) {
            try {
                v = (this.getX() + 10) / 40;
                m = this.getY() / 40;
                int xx = (this.getX() + 10) / 40;
                int yy = (this.getY()) / 40;

                this.limites[0] = object[m + 1][v].getNum();
                this.limites[1] = object[m - 1][v].getNum();
                this.limites[2] = object[yy][xx + 1].getNum();
                this.limites[3] = object[yy][xx - 1].getNum();

            //    System.out.println("M: " + m + ", V: " + v);
//              

                if (direccion == 1 && object[m][v + 1].getNum() != 0) {
                    posiciones_posibles = obtener_direcciones(limites);
                    int aleatorio = (int) ((Math.random() * posiciones_posibles.size()) + 0);
                    direccion = posiciones_posibles.get(aleatorio);
                    System.out.println(direccion);

                } else if (direccion == 2 && object[m][v - 1].getNum() != 0) {
                    posiciones_posibles = obtener_direcciones(limites);
                    int aleatorio = (int) ((Math.random() * posiciones_posibles.size()) + 0);
                    direccion = posiciones_posibles.get(aleatorio);

                } else if (direccion == 3 && object[m + 1][v].getNum() != 0) {
                    posiciones_posibles = obtener_direcciones(limites);
                    int aleatorio = (int) ((Math.random() * posiciones_posibles.size()) + 0);
                    direccion = posiciones_posibles.get(aleatorio);

                } else if (direccion == 4 && object[m - 1][v].getNum() != 0) {
                    posiciones_posibles = obtener_direcciones(limites);
                    int aleatorio = (int) ((Math.random() * posiciones_posibles.size()) + 0);
                    direccion = posiciones_posibles.get(aleatorio);
                } else if ((object[m][v + 1].getNum() == 2) || (object[m][v - 1].getNum() == 2) || (object[m + 1][v].getNum() == 2) || (object[m - 1][v].getNum() == 2)) {
                    super.setX(this.getX() + 40);
                    super.setY(this.getY());

                    ejecutar = false;
                    fieldContent.append("Tiempo de " + this.getNameThread() + ": " + Cronometro.recor + "\n");
                    ta.setText(fieldContent.toString());   

                }

                switch (direccion) {
                    case 1:
                        super.setImage(sprite.get(0));///DERECHA
                        //  super.setImage2(sprite2.get(0));///DERECHA
                        super.setX(x++);
                        Thread.sleep(50);
                        break;
                    case 2:
                        super.setImage(sprite.get(0));//IZQUIERDA
                        // super.setImage2(sprite2.get(0));
                        super.setX(x--);
                        Thread.sleep(50);

                        break;
                    case 3:
                        super.setImage(sprite.get(0));///////////BAJA PARA ABAJO
                        //super.setImage2(sprite2.get(0));
                        super.setY(y++);
                        Thread.sleep(50);

                        break;

                    case 4:
                        super.setImage(sprite.get(0));//VA PARA  ARRIBA
                        // super.setImage2(sprite2.get(0));
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

    private ArrayList<Integer> obtener_direcciones(int[] limites) {
        ArrayList<Integer> resultado = new ArrayList<Integer>();

        try{
        if (limites[0] == 0) {
            resultado.add(3);
        }
        if (limites[1] == 0) {
            resultado.add(4);
        }
        if (limites[2] == 0) {
            resultado.add(1);
        }
        if (limites[3] == 0) {
            resultado.add(2);
        }

        }catch(Exception e){}
        return resultado;
    }

}
