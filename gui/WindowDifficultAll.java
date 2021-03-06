package gui;

import Domain.RunningSmart1;
import Domain.Item1;
import Domain.Item2;
import Domain.Block;
import Domain.Cronometro;
import Domain.RunningFast1;
import Domain.Item1;
import Domain.Item2;
import Domain.Player;
import Domain.RunningSmart1;
import Utility.Variables;
import Domain.RunningFurious1;
import Domain.SynchronizedBuffer;
import static Utility.Variables.ta;
import static gui.ControlPanel.globalSpinner1;
import static gui.ControlPanel.globalSpinner2;
import static gui.ControlPanel.globalSpinner3;
import static gui.ControlPanel.globalSpinner4;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class WindowDifficultAll extends Application implements Runnable {

    /*Esta ventana, simula el nivel dificil, en el cual,  los personajes correrán, por toda la matriz, la cual quedará guardada en un Objeto
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
    private Item1 it;
    private Item2 it2;

    ArrayList<Player> arrayIrem, arrayLeft;

    private RunningFast1 fast1;
    private RunningFurious1 furious1;
    private RunningSmart1 smart1;
    Cronometro c = new Cronometro();
    private final Label timerLabel = new Label();
    ArrayList<Player> fastArray, furiousArray, smartArray;

    GraphicsContext gc;
    public static int ejeX;
    public static int ejeY;

    /*tamano de la matriz*/
    private Block object[][] = new Block[17][17];
    public static int numeros[][] = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
        {1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 2},
        {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            primaryStage.setTitle("Graphics and Threads");
            initComponents(primaryStage);
            gc = canvas.getGraphicsContext2D();
            VBox vb = new VBox(10);
            vb.getChildren().addAll(timerLabel);
            vb.relocate(710, 300);
            pane.getChildren().addAll(vb);
            this.c.timer(timerLabel);
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

            arrayIrem = new ArrayList<>();
            for (int i = 1; i < 3; i++) {
                this.it = new Item1("E1", 200, 300, this.object);
                arrayIrem.add(it);
                it.start();

            }

            for (int j = 1; j < arrayIrem.size(); j++) {
                // System.out.println("d" + arrayIrem.size());
                arrayIrem.get(j);
/////
            }

            arrayLeft = new ArrayList<>();
            for (int i = 1; i < 3; i++) {
                this.it2 = new Item2("E1", 80, 200, this.object);
                arrayLeft.add(it2);
                it2.start();

            }
            for (int j = 1; j < arrayLeft.size(); j++) {
                //  System.out.println("d" + arrayLeft.size());
                arrayLeft.get(j);
/////
            }

            fastArray = new ArrayList<>();
            for (int i = 1; i < globalSpinner1; i++) {
                this.fast1 = new RunningFast1("a1", "Thread" + i, 0.0, 40, 40, this.object);
                fastArray.add(fast1);
                fast1.start();
            }

            for (int j = 1; j < fastArray.size(); j++) {
                System.out.println("d" + fastArray.size());
                fastArray.get(j);

            }

            furiousArray = new ArrayList<>();
            for (int i = 1; i < globalSpinner2; i++) {
                this.furious1 = new RunningFurious1("furioso", "", 0.0, 60, 40, this.object);
                furiousArray.add(furious1);
                furious1.start();
            }

            for (int j = 1; j < furiousArray.size(); j++) {
                furiousArray.get(j);

            }

            smartArray = new ArrayList<>();
            for (int i = 1; i < globalSpinner3; i++) {
                this.smart1 = new RunningSmart1("E1", "Thread" + i, 0.0, 40, 40, this.object);
                smartArray.add(smart1);
                smart1.start();

            }

            for (int j = 1; j < smartArray.size(); j++) {

                smartArray.get(j);

            }

            matriz(gc);
            suspendeAndReanudeThreat();

            this.thread = new Thread(this);
            this.thread.start();
        } catch (BufferOverflowException ex) {
        } catch (FileNotFoundException ex) {
//         
        }
    }

    public void matriz(GraphicsContext gc) {
        try {
            for (int x = 0; x < numeros.length; x++) {
                for (int y = 0; y < numeros[x].length; y++) {
                    if (numeros[x][y] == 1) {
                        gc.setFill(Color.BLACK);
                        gc.fillRect(y * 40, x * 40, 40, 40);
                        object[x][y] = new Block(y * 40, x * 40, 40, 40, 1);
                        fast1.setObject(object);
                        it.setObject(object);
                        it2.setObject(object);
                        furious1.setObject(object);

                        smart1.setObject(object);

                    } else if (numeros[x][y] == 0) {
                        gc.setFill(Color.WHITE);
                        gc.fillRect(y * 40, x * 40, 40, 40);
                        object[x][y] = new Block(y * 40, x * 40, 40, 40, 0);
                        fast1.setObject(object);
                        it.setObject(object);
                        it2.setObject(object);

                        furious1.setObject(object);

                        smart1.setObject(object);

                    } else if (numeros[x][y] == 2) {
                        gc.setFill(Color.RED);
                        gc.fillRect(y * 40, x * 40, 40, 40);
                        object[x][y] = new Block(y * 40, x * 40, 40, 40, 2);
                        it.setObject(object);
                        it2.setObject(object);
                        fast1.setObject(object);

                        furious1.setObject(object);

                        smart1.setObject(object);

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
                            probar(gc, canvas);
                        } catch (FileNotFoundException ex) {
//                        Logger.getLogger(WindowEasy.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (button == MouseButton.SECONDARY) {

                    } else if (button == MouseButton.MIDDLE) {

                    }
                }
            });
        } catch (Exception e) {
        }
    }

    /*Este metodo lo que hace es editar la matriz original, si hay "piso", se puede editar*/
    public void probar(GraphicsContext gc, Canvas canvas) throws FileNotFoundException {
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

                        } else {
                            gc = canvas.getGraphicsContext2D();
                            gc.setFill(Color.WHITE);
                            gc.fillRect(object[i][j].getX(), object[i][j].getY(), 40, 40);

//                        object[i][j] = new Block(j * 40, i * 40, 40, 40, 0);
                            object[i][j].setNum(0);

                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /*Dibuja el personaje sobre la matriz*/
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

            for (int i = 1; i < fastArray.size(); i++) {
                gc.drawImage(fastArray.get(i).getImage(), fastArray.get(i).getX(), fastArray.get(i).getY());
                gc.fillText("Player" + i, fastArray.get(i).getX(), fastArray.get(i).getY(), 15);

            }
            for (int i = 1; i < smartArray.size(); i++) {
                gc.drawImage(smartArray.get(i).getImage(), smartArray.get(i).getX(), smartArray.get(i).getY());
                // gc.fillText("Player"+i, smartArray.get(i).getX(),  smartArray.get(i).getY(),15);
            }
            for (int i = 1; i < furiousArray.size(); i++) {
                gc.drawImage(furiousArray.get(i).getImage(), furiousArray.get(i).getX(), furiousArray.get(i).getY());

            }

            for (int i = 1; i < arrayIrem.size(); i++) {
                gc.drawImage(arrayIrem.get(i).getImage(), arrayIrem.get(i).getX(), arrayIrem.get(i).getY());

            }
            for (int i = 1; i < arrayLeft.size(); i++) {
                gc.drawImage(arrayLeft.get(i).getImage(), arrayLeft.get(i).getX(), arrayLeft.get(i).getY());

            }
        } catch (Exception e) {
        }

    }

}
