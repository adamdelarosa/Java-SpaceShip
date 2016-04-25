package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SpaceShip {

    Main main;
    public HBox boxForShip = new HBox();
    private static final double W = 500, H = 800;
    private static final String SPACESHIP = "sample/asset/spaceShip.png";
    private Image heroImage;
    private Node hero;
    boolean running, goNorth, goSouth, goEast, goWest;


    public SpaceShip(){
        shipMovment();
        addToScreen();
    }

    public void shipMovment(){
        heroImage = new Image(SPACESHIP);
        hero = new ImageView(heroImage);
        boxForShip.setFocusTraversable(true);
        moveHeroTo(W / 2, H / 2);

        boxForShip.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = true; break;
                case DOWN:  goSouth = true; break;
                case LEFT:  goWest  = true; break;
                case RIGHT: goEast  = true; break;
                case SHIFT: running = true; break;
            }
        });

        boxForShip.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = false; break;
                case DOWN:  goSouth = false; break;
                case LEFT:  goWest  = false; break;
                case RIGHT: goEast  = false; break;
                case SHIFT: running = false; break;
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goNorth) dy -= 1;
                if (goSouth) dy += 1;
                if (goEast)  dx += 1;
                if (goWest)  dx -= 1;
                if (running) { dx *= 3; dy *= 3; }

                moveHeroBy(dx, dy);
            }
        };
        timer.start();
    }
    private void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = boxForShip.getBoundsInLocal().getWidth()  / 2;
        final double cy = boxForShip.getBoundsInLocal().getHeight() / 2;

        double x = cx + boxForShip.getLayoutX() + dx;
        double y = cy + boxForShip.getLayoutY() + dy;

        moveHeroTo(x, y);
    }

    private void moveHeroTo(double x, double y) {
        final double cx = boxForShip.getBoundsInLocal().getWidth()  / 2;
        final double cy = boxForShip.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H) {
            boxForShip.relocate(x - cx, y - cy);
        }

    }
    public void addToScreen(){
        boxForShip.getChildren().addAll(hero);
        main.getRoot().getChildren().add(boxForShip);
    }
}
