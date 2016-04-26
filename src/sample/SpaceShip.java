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
    private Node nodeSpaceShip;
    boolean running, goNorth, goSouth, goEast, goWest;

    public SpaceShip(){
        shipMovment();
        addToScreen();
        //Place for space ship
        setSpaceShipPosition(W / 2, H / 2);
    }

    public void shipMovment(){
        heroImage = new Image(SPACESHIP);
        nodeSpaceShip = new ImageView(heroImage);
        boxForShip.setFocusTraversable(true);

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

                if (goNorth) dy -= 8;
                if (goSouth) dy += 8;
                if (goEast)  dx += 8;
                if (goWest)  dx -= 8;
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

        setSpaceShipPosition(x, y);
    }

    private void setSpaceShipPosition(double x, double y) {
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
        boxForShip.getChildren().addAll(nodeSpaceShip);
        main.getRoot().getChildren().add(boxForShip);
    }
}
