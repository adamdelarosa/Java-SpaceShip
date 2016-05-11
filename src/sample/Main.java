package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public int windowHeight = 500;
    public int windowWeight = 800;

    public Scene scene = new Scene(root,windowHeight,windowWeight);
    public Stage globalPrimartStage;
    public static Group root = new Group();

    public static Group getRoot(){
        return root;
    }

    public Scene getScene (){
        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene.setFill(Color.BLACK);
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.show();
        MainStarter();
        globalPrimartStage = primaryStage;
    }

    public void MainStarter() {
       // new BackGroundSlide(this,this);
        new SpaceShip();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
