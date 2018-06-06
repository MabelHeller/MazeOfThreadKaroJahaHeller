/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author Heller
 * 
 * 
 */
/*Clase cronometro, para tener un control,  del orden de los personajes*/
public class Cronometro {

    public   Timeline timeline;
    public DoubleProperty timeSeconds = new SimpleDoubleProperty();
    public Duration time = Duration.ZERO;
    public static double recor;

    public void timer(Label timerLabel) {
        Image clockImage = new Image("/Assets/clock.png");
        ImageView clockIV = new ImageView(clockImage);
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.TOMATO);
        recor = timeSeconds.getValue();
        timerLabel.setStyle("-fx-font-size: 5em;");
        if (timeline != null) {
        } else {
            timeline = new Timeline(
                    new KeyFrame(Duration.millis(200),
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            Duration duration = ((KeyFrame) t.getSource()).getTime();
                            time = time.add(duration);
                            timeSeconds.set(time.toSeconds());
                         
                        }
                    })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);//INDEFINITE
            timeline.play();
        }       
  
    }
}
