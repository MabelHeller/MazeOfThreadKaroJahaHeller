package gui;

import Domain.Block;
import Domain.Cronometro;
import Domain.Player;
import Domain.RunningFast1;
import Domain.SynchronizedBuffer;
import Utility.Variables;
import static Utility.Variables.ta;
import static gui.ControlPanel.globalSpinner1;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class WindowMediumFast1 extends Application implements Runnable {

    /*Esta ventana, simula el nivel medio, en el cual,  los personajes correrán, por toda la matriz, la cual quedará guardada en un Objeto
    la matriz es estatica, es decir; tiene valores definidosentre 0,1,2, 0 significa un bloque color blanco, en el cual el personaje  se moverá
    los bloques color negro, simulan las paredes de un laberinto, y finalmente, el bloque numero 2, color rojo, simula la salida del personaje.
    
    Para la creación, en cuanto a la cantidad de estudiantes, lo que implementamos fue un ArrayList, con un tamaño establecido por el usuario
    este tamaño se guarda en el ArrayList, y dependiendo ese tamano creamos la cantidad de personajes, sugerido por el usuario
    
    
     */
    private Thread thread;
    private Scene scene;
    private Pane pane;
    private Canvas canvas;
    private Image image;
    Cronometro c = new Cronometro();

    private final Label timerLabel = new Label();

    private RunningFast1 fast1;

    GraphicsContext gc;
    public static int ejeX;
    public static int ejeY;
    private Block object[][] = new Block[17][17];
    ArrayList<Player> fast;

    public static int numeros[][] = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    @Override
    public void run() {
        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000 / fps;

        while (true) {

            try {

                start = System.nanoTime();
                elapsed = System.nanoTime() - start;
                wait = time - elapsed / 10000000;
                Thread.sleep(wait);
                GraphicsContext gc = this.canvas.getGraphicsContext2D();
                draw(gc);

            } catch (InterruptedException ex) {
            }

        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            primaryStage.setTitle("Graphics and Threads");
            initComponents(primaryStage);
            suspendeAndReanudeThreat();
            VBox vb = new VBox(10);
            vb.getChildren().addAll(timerLabel);//, splitTimerLabel);
            vb.relocate(710, 300);
            pane.getChildren().addAll(vb);//VB
            this.c.timer(timerLabel);
            gc = canvas.getGraphicsContext2D();

            primaryStage.show();
            Variables.fieldContent = new StringBuilder();
            ta = new TextArea();
            ta.relocate(750, 500);
            ta.setPrefSize(400, 150);
            pane.getChildren().add(ta);
        } catch (Exception e) {
        }
    }

    public void suspendeAndReanudeThreat() {
        try {
            javafx.scene.image.Image clockImage = new javafx.scene.image.Image("/Assets/clock.png");
            ImageView clockIV = new ImageView(clockImage);

            Image pauseImage = new Image("/Assets/pause2.png");
            ImageView pauseIV = new ImageView(pauseImage);

            Image playImage = new Image("/Assets/play1.png");
            ImageView playIV = new ImageView(playImage);

            Button button = new Button("Pause", pauseIV);

            Button buttonStart = new Button("Start", playIV);

            button.relocate(700, 10);
            buttonStart.relocate(800, 10);
            button.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {

                    thread.suspend();
                }
            });

            buttonStart.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {

                    thread.resume();

                }
            });

            pane.getChildren().addAll(button, pauseIV);
            pane.getChildren().addAll(buttonStart, playIV);

        } catch (Exception e) {
        }
    }

    private void initComponents(Stage primaryStage) {

        try {
            this.pane = new Pane();
            this.scene = new Scene(this.pane, Variables.WIDTH, Variables.HEIGHT);
            this.canvas = new Canvas(Variables.WIDTH, Variables.HEIGHT);
            gc = canvas.getGraphicsContext2D();
            evento(gc, this.canvas);

            this.image = new Image(new FileInputStream("src/Assets/door1.png"));//
            gc.setFill(Color.BURLYWOOD);
            this.pane.getChildren().add(this.canvas);
            primaryStage.setScene(this.scene);

            fast = new ArrayList<>();
            for (int i = 1; i < globalSpinner1; i++) {
                this.fast1 = new RunningFast1("a1", "Thread" + i, 0.0, 40, 40, this.object);
                fast.add(fast1);
                fast1.start();

                System.out.println("SIDE" + globalSpinner1);
            }

            for (int j = 1; j < fast.size(); j++) {
                System.out.println("d" + fast.size());
                fast.get(j);

            }

            matriz(gc);

            this.thread = new Thread(this);
            this.thread.start();

        } catch (BufferOverflowException ex) {
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WindowMediumFast1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*Este metodo, pinta  cada bloque de la matriz, y los guarda en un objecto */
    public void matriz(GraphicsContext gc) {
        try {
            for (int x = 0; x < numeros.length; x++) {
                for (int y = 0; y < numeros[x].length; y++) {
                    if (numeros[x][y] == 1) {
                        gc.setFill(Color.BLACK);
                        gc.fillRect(y * 40, x * 40, 40, 40);
                        object[x][y] = new Block(y * 40, x * 40, 40, 40, 1);
                        fast1.setObject(object);

                    } else if (numeros[x][y] == 0) {
                        gc.setFill(Color.WHITE);
                        gc.fillRect(y * 40, x * 40, 40, 40);
                        object[x][y] = new Block(y * 40, x * 40, 40, 40, 0);
                        fast1.setObject(object);

                    } else if (numeros[x][y] == 2) {
                        gc.setFill(Color.RED);
                        gc.fillRect(y * 40, x * 40, 40, 40);
                        object[x][y] = new Block(y * 40, x * 40, 40, 40, 2);
                        fast1.setObject(object);

                    } else {
                        gc.setFill(Color.WHITE);
                        gc.fillRect(y * 40, x * 40, 40, 40);
                        object[x][y] = new Block(y * 40, x * 40, 40, 40, 3);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /*Obtener posiciones del eje X y Y*/
    public void evento(GraphicsContext gc, Canvas canvas) {
        try {
            canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    MouseButton button = event.getButton();
                    ejeY = (int) event.getY();
                    ejeX = (int) event.getX();
                    if (button == MouseButton.PRIMARY) {
                        try {
                            cambiarcuadro(gc, canvas);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(WindowMediumFast1.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (button == MouseButton.SECONDARY) {

                    } else if (button == MouseButton.MIDDLE) {

                    }
                }

            });
        } catch (Exception e) {
        }
    }

    /*la funcion de este método, es editar la matriz, es decir, si hay piso, podemos editarlo a que haya pared, y viceversa, la editado se guarda en nuestro objeto*/
    public void cambiarcuadro(GraphicsContext gc, Canvas canvas) throws FileNotFoundException {
        try {
            for (int i = 0; i < object.length; i++) {
                for (int j = 0; j < object[i].length; j++) {
                    if ((ejeX >= object[i][j].getX() && ejeX <= object[i][j].getX() + object[i][j].getWidth())
                            && (ejeY >= object[i][j].getY() && ejeY <= object[i][j].getY() + object[i][j].getHeigth())) {
                        if (object[i][j].getNum() == 0) {
                            gc = canvas.getGraphicsContext2D();
                            gc.setFill(Color.BLACK);

                            gc.fillRect(object[i][j].getX(), object[i][j].getY(), 40, 40);

                            object[i][j].setNum(1);

                        } else if (object[i][j].getNum() == 1) {
                            gc = canvas.getGraphicsContext2D();
                            gc.setFill(Color.WHITE);

                            gc.fillRect(object[i][j].getX(), object[i][j].getY(), 40, 40);

                            object[i][j].setNum(0);
                            ;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /*Este metodo dibuja los personajes simultaneamente,*///
    private void draw(GraphicsContext gc) {
        try {
            gc.clearRect(0, 0, Variables.WIDTH, Variables.HEIGHT);
            for (int x = 0; x < object.length; x++) {
                for (int y = 0; y < object[x].length; y++) {
                    if (object[x][y].getNum() == 1) {
                        gc.setFill(Color.BLACK);
                        gc.fillRect(y * 40, x * 40, 40, 40);
                    } else if (object[x][y].getNum() == 0) {
                        gc.setFill(Color.WHITE);
                        gc.fillRect(y * 40, x * 40, 40, 40);
                    } else if (object[x][y].getNum() == 2) {
                        gc.setFill(Color.RED);
                        gc.fillRect(y * 40, x * 40, 40, 40);
                        gc.drawImage(image, y * 40, x * 40);
                    } else {
                        gc.setFill(Color.WHITE);
                        gc.fillRect(y * 40, x * 40, 40, 40);
                    }
                }
            }
            for (int i = 1; i < fast.size(); i++) {
                gc.drawImage(fast.get(i).getImage(), fast.get(i).getX(), fast.get(i).getY());
                gc.fillText("Player " + i, fast.get(i).getX(), fast.get(i).getY(), 15);
            }
        } catch (Exception e) {
        }

    }

}
