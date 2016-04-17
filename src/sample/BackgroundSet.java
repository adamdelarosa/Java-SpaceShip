package sample;

import javafx.scene.layout.HBox;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import java.util.Random;

public class BackgroundSet {

    private Main main;
    private HBox boxOne = new HBox();
    private Random rndmStarX = new Random();
    private Thread backgroundScrollRunner = new Thread();
    private Rectangle starMain = new Rectangle(1,2,Color.BLANCHEDALMOND);
    private int xPoz = rndmStarX.nextInt(450);


    BackgroundSet(){
        paintComponent();
        changeBackground();
    }

    public void paintComponent() {
        backgroundScrollRunner = new Thread(() -> {
                for (int yPoz = 0; true; yPoz += 2) {
                    try {


                        boxOne.setLayoutX(xPoz);
                        boxOne.setLayoutY(yPoz);
                        Thread.sleep(150);
                    } catch (InterruptedException ie) {}
                }

        });
        backgroundScrollRunner.start();
    }

    public void changeBackground() {
        boxOne.getChildren().add(starMain);
        main.getRoot().getChildren().add(boxOne);
    }

}