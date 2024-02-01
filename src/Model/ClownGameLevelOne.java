package Model;

import Controller.Clown;
import Controller.ImageObject;
import Controller.ImageObjectFactory;
import Controller.Plates;
import Controller.Strategy.SpeedStrategy;
import eg.edu.alexu.csd.oop.game.World;
import java.util.Random;
import java.util.Stack;

public class ClownGameLevelOne extends Levels implements World {

    public SpeedStrategy s;

    PlateState plate = new PlateState();

    public ClownGameLevelOne(SpeedStrategy s, int width, int height) {
        this.addObserver(gameObserver);
        this.s = s;
        fallingPlates = 0;
        this.random = new Random();
        this.width = width;
        this.height = height;
        this.score = 0;
        this.rightCollectedObjects = new Stack();
        this.leftCollectedObjects = new Stack();
        int x, y;
        this.constant.add(new ImageObject(0, 0, "/Theme1.jpg"));
        clown = (Clown) ImageObjectFactory.createImageObject("Clown", this.width / 3 - 50, (int) (this.height * 0.8) - 270);
        controlable.add(clown);

        for (int i = 0; i < 20; i++) {
            x = random.nextInt(4);
            y = random.nextInt(4);
            String name = "/" + this.COLORS[x] + this.SHAPES[y] + ".png";
            movable.add((Plates) ImageObjectFactory.createImageObject("Plates", (int) (Math.random() * this.width), -1 * (int) (Math.random() * this.height), this.COLORS[x], this.SHAPES[y], name));
        }
    }

    @Override
    public boolean refresh() {

        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        this.setInRightPlace();
        for (int i = 0; i < this.movable.size(); i++) {
            this.updateLeftScore();
            this.updateRightScore();
            Plates x = (Plates) this.movable.get(i);

            this.movable.get(i).setY(this.movable.get(i).getY() + 1 + random.nextInt(4));
            if (!this.setPlateInLeftStack(i, x)) {
                this.setPlateInRightStack(i, x);
            }

            if (((fallingPlates == 90 || this.getRight().size() == 8 || this.getLeft().size() == 8 || this.getMovableObjects().isEmpty()) && !ended)) {
                ended = true;
                gameOverCounter++;
                System.out.println("you missed");//checking
                gameOver(false);

                return false;
            }
        }
        if (gameOverCounter == 0 && timeout) {
            ended = true;
            gameOverCounter++;
            gameOver(!timeout && !ended);

        }

        return !timeout;

    }
        public int getSpeed() {
        s.getSpeed();
        return -1;
    }
}
