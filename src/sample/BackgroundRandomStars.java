package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class BackgroundRandomStars {

    //

    public Main main;

    Image imageOne = new Image("sample/asset/splash.jpg");
    Image imageTwo = new Image("sample/asset/splash.jpg");


    ImageView iv1 = new ImageView();
    ImageView iv2 = new ImageView();

    private Thread backgroundScrollRunner = new Thread();
    Rectangle2D viewportRectOne = new Rectangle2D(500, 500, 500, 500);
    Rectangle2D viewportRectTwo = new Rectangle2D(500, 500, 500, 500);



    BackgroundRandomStars(){
        paintComponent();
    }
//
    public void paintComponent() {
        iv1.setImage(imageOne);
        iv1.setViewport(viewportRectOne);
        iv2.setImage(imageTwo);
        iv2.setViewport(viewportRectTwo);

        //For now:
        iv2.setTranslateY(0);
        iv1.setTranslateY(0);

        //Background movement:
        backgroundScrollRunner = new Thread(() -> {
            int positionYTwo = -1;
            int positionYOne = -1;

            while(true) {
                positionYOne+=10;
                try {
                    if(true){
                        positionYTwo+=10;
                        iv2.setTranslateY(positionYTwo);
                        System.out.println("Two started" + positionYTwo);
                    }else{
                         positionYTwo = -1;
                    }

                    if(iv2.getTranslateY() >= 500 || iv1.getTranslateY() >= 1000){
                        iv1.setTranslateY(0);

                        positionYOne+=10;
                        positionYOne = 0;
                        System.out.println("One started" + positionYOne);

                    }

                    iv1.setTranslateY(positionYOne);
                    System.out.println(iv1.getTranslateY());

                    backgroundScrollRunner.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        backgroundScrollRunner.start();

        //Start Adding to main screen:
        changeBackground();
    }

    public void changeBackground() {
        HBox boxOne = new HBox();
        HBox boxTwo = new HBox();
        boxOne.getChildren().add(iv1);
        boxTwo.getChildren().add(iv2);
        main.getRoot().getChildren().add(boxTwo);
        main.getRoot().getChildren().add(boxOne);
    }

}

