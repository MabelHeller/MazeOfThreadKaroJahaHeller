package Domain;


import static Utility.Variables.fieldContent;
import static Utility.Variables.ta;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RunningFast1 extends Player {

    
    /*Subclases*/
    int[] limites = new int[4];
    private Buffer sharedLocation;
    public Double time;
    public String nameThread;

    public RunningFast1(String nameImage,String nameThead, Double time, int x, int y, Block object[][]) throws FileNotFoundException {
        super(nameImage, x, y, object);
        this.nameThread = nameThead;
        this.time = time;
        setSprite();

    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        sprite.add(new Image(new FileInputStream("src/Assets/" + name + ".png")));

    }
    
     public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
        boolean ejecutar = true;
        int x = 40;//330
        int y = 40;//459
        int v;
        int m;
        int direccion = 1;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RunningFast1.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (ejecutar) {
            try {
                v = (this.getX()) / 40;
                m = this.getY() / 40;

                this.limites[0] = object[m + 1][v].getNum();
                this.limites[1] = object[m - 1][v].getNum();
                this.limites[2] = object[m][v + 1].getNum();
                this.limites[3] = object[m][v - 1].getNum();

                if (direccion == 1 && object[m][v + 1].getNum() != 0) {
                    posiciones_posibles = obtener_direcciones(limites);
                    int aleatorio = (int) ((Math.random() * posiciones_posibles.size()) + 0);
                    direccion = posiciones_posibles.get(aleatorio);
                    System.out.println(direccion);

                    if (x == 100 || (x == 200 || x == 300 || x == 400 || x == 500)) {
                        Thread.sleep(100);
                    }

                } else if (direccion == 2 && object[m][v - 1].getNum() != 0) {
                    posiciones_posibles = obtener_direcciones(limites);
                    int aleatorio = (int) ((Math.random() * posiciones_posibles.size()) + 0);
                    direccion = posiciones_posibles.get(aleatorio);
                    if (x == 100 || (x == 200 || x == 300 || x == 400 || x == 500)) {
                        Thread.sleep(100);
                    }
                } else if (direccion == 3 && object[m + 1][v].getNum() != 0) {
                    posiciones_posibles = obtener_direcciones(limites);
                    int aleatorio = (int) ((Math.random() * posiciones_posibles.size()) + 0);
                    direccion = posiciones_posibles.get(aleatorio);
                    if (y == 100 || (y == 200 || y == 300 || y == 400 || y == 500)) {
                        Thread.sleep(100);
                    }
                } else if (direccion == 4 && object[m - 1][v].getNum() != 0) {
                    posiciones_posibles = obtener_direcciones(limites);
                    int aleatorio = (int) ((Math.random() * posiciones_posibles.size()) + 0);
                    direccion = posiciones_posibles.get(aleatorio);

                    if (y == 100 || (y == 200 || y == 300 || y == 400 || y == 500)) {
                        Thread.sleep(100);
                    }
                } else if ((object[m][v + 1].getNum() == 2) || (object[m][v - 1].getNum() == 2) || (object[m + 1][v].getNum() == 2) || (object[m - 1][v].getNum() == 2)) {
                    super.setX(this.getX() + 40);
                    super.setY(this.getY());

                    ejecutar = false;
                    fieldContent.append("Tiempo de " + this.getNameThread() + ": " + Cronometro.recor + "\n");
                    ta.setText(fieldContent.toString());

                }

//             
                switch (direccion) {
                    case 1:
                        super.setImage(sprite.get(0));///DERECHA

                        super.setX(x++);
                        Thread.sleep(5);
                        if (x == 300) {
                            Thread.sleep(5000);
                        }
                        break;
                    case 2:
                        super.setImage(sprite.get(0));

                        super.setX(x--);
                        Thread.sleep(5);

                        break;
                    case 3:
                        super.setImage(sprite.get(0));///////////BAJA PARA ABAJO

                        super.setY(y++);
                        Thread.sleep(5);
                        if (x == 278 && y == 430) {
                            Thread.sleep(5000);
                        }
                        break;

                    case 4:
                        super.setImage(sprite.get(0));//VA PARA  ARRIBA
                        // super.setImage2(sprite2.get(0));
                        super.setY(y--);
                        Thread.sleep(5);

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
        try {

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

        } catch (Exception e) {
            System.out.println("");
        }
        return resultado;
    }

}
