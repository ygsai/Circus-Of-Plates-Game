package Controller;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.Random;

public class ImageObjectFactory {

    public static final String[] SHAPES = {"Circle", "Triangle", "Plate", "Square"};
    public static final String[] COLORS = {"Blue", "Green", "Red", "Yellow"};

    public static GameObject createImageObject(String type, int x, int y) {
        if ("Clown".equalsIgnoreCase(type)) {
            return new Clown(x, y, "/Clown.png");
        } else if ("Plates".equalsIgnoreCase(type)) {
            int colorIndex = new Random().nextInt(4);
            int shapeIndex = new Random().nextInt(4);
            String color = COLORS[colorIndex];
            String shape = SHAPES[shapeIndex];
            String name = "/" + color + shape + ".png";
            return new Plates(color, shape, x, y, name, true);
        }
        return null;
    }

    public static GameObject createImageObject(String type, int x, int y, String color, String shape, String imageName) {
        if ("Clown".equalsIgnoreCase(type)) {
            return new Clown(x, y, "/Clown.png");
        } else if ("Plates".equalsIgnoreCase(type)) {
            return new Plates(color, shape, x, y, imageName, true);
        }
        return null;

    }

}
