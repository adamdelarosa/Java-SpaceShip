package sample;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class SpaceShip {

    Main main;
    final ImageView selectedImage = new ImageView();
    public Image assetSpaceShip = new Image("sample/asset/spaceShip.png");
    HBox boxForShip = new HBox();




    public SpaceShip(){
        shipMovment();
        addToScreen();
    }


    public void shipMovment(){
        selectedImage.setImage(assetSpaceShip);
        boxForShip.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        System.out.println("UP yours"); break;

                }
            }
        });




    }

    public void addToScreen(){
        boxForShip.setLayoutX(250);
        boxForShip.setLayoutY(700);
        boxForShip.getChildren().addAll(selectedImage);
        main.getRoot().getChildren().add(boxForShip);
    }

}
