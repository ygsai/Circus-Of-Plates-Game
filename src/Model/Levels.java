package Model;

import Controller.Clown;
import Controller.Plates;
import Model.Observer.GameObserver;
import Model.Observer.Observer;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import javax.swing.JOptionPane;
import view.Audio;
import view.MenuSingleton;
import static view.MenuSingleton.getInstance;

public abstract class Levels implements World {

    protected static int MAX_TIME = 1 * 180 * 1000;
    protected final List<GameObject> constant = new LinkedList<GameObject>();
    protected List<GameObject> movable = new LinkedList<GameObject>();
    protected final List<GameObject> controlable = new LinkedList<GameObject>();
    public final int SPEED = 10;

    public final int CONTROL_SPEED = 20;
    public final int CERTAIN_LIMIT = 10;
    int width, height, score;
    protected long startTime = System.currentTimeMillis();
    public Stack rightCollectedObjects;
    public Stack leftCollectedObjects;
    public List<GameObject> left = new LinkedList<GameObject>();
    public List<GameObject> right = new LinkedList<GameObject>();
    public final String[] SHAPES = {"Circle", "Triangle", "Plate", "Square"};
    public final String[] COLORS = {"Blue", "Green", "Red", "Yellow"};
    Random random;
    String name;
    Clown clown;
    int fallingPlates;
    int gameOverCounter = 0;
    boolean ended = false;
    public List<Observer> observers = new ArrayList<>();
    protected static final GameObserver gameObserver = new GameObserver();

    public void addObserver(Observer observer) {

        observers.add(observer);
    }

    public boolean isPlateEnteredTheRightStack(Plates plate) {

        return (this.clown.getRightCenter() - plate.getX() <= 30 && this.clown.getRightCenter() - plate.getX() >= -30);
    }

    public boolean isPlateEnteredTheLeftStack(Plates plate) {

        return (this.clown.getLeftCenter() - plate.getX() <= 30 && this.clown.getLeftCenter() - plate.getX() >= -30);
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return this.constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return this.movable;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return this.controlable;
    }

    public List<GameObject> getRight() {
        return right;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    public Stack getRightCollectedObjects() {
        return rightCollectedObjects;
    }

    public Stack getLeftCollectedObjects() {
        return leftCollectedObjects;
    }

    public List<GameObject> getLeft() {
        return left;
    }

    public int getLeftHeight() {
        int height1 = 0;
        for (int i = 0; i < this.left.size(); i++) {
            Plates plate = (Plates) this.left.get(i);
            height1 -= 42;

        }
        return 295 + height1;
    }

    public int getRightHeight() {
        int height1 = 0;
        for (int i = 0; i < this.right.size(); i++) {
            Plates plate = (Plates) this.right.get(i);

            height1 -= 47;
        }
        return 295 + height1;
    }

    public boolean checkHight(Plates plate) {     //<<-- problem with checking the height
        if (this.right.isEmpty() || this.left.isEmpty()) {

            if ((plate.getY() - (295) <= 2 && plate.getY() - (295) >= -2)) {
                return true;
            }

        } else {
            if (this.isPlateEnteredTheLeftStack(plate)) {

                if ((plate.getY() - (this.getLeftHeight()) <= 2) && (plate.getY() - (this.getLeftHeight()) >= -2)) {
                    return true;
                }

            } else if (this.isPlateEnteredTheRightStack(plate)) {

                if ((plate.getY() - (this.getRightHeight()) <= 2) && (plate.getY() - (this.getRightHeight()) >= -2)) {
                    return true;
                }

            }
        }
        return false;
    }

    public void updateLeftScore() {

        try {
            Plates temp1 = (Plates) this.left.get(this.left.size() - 1);
            Plates temp2 = (Plates) this.left.get(this.left.size() - 2);
            Plates temp3 = (Plates) this.left.get(this.left.size() - 3);
            if ((temp1.getColor().equals(temp2.getColor())) && (temp1.getColor().equals(temp3.getColor()))) {
                this.score += 5;
                this.left.remove(temp1);
                this.left.remove(temp2);
                this.left.remove(temp3);
                this.leftCollectedObjects.pop();
                this.leftCollectedObjects.pop();
                this.leftCollectedObjects.pop();
                this.controlable.remove(temp1);
                this.controlable.remove(temp2);
                this.controlable.remove(temp3);
            }

        } catch (IndexOutOfBoundsException e) {

        }
    }

    public void updateRightScore() {

        try {
            Plates temp1 = (Plates) this.right.get(this.right.size() - 1);
            Plates temp2 = (Plates) this.right.get(this.right.size() - 2);
            Plates temp3 = (Plates) this.right.get(this.right.size() - 3);
            if ((temp1.getColor().equals(temp2.getColor())) && (temp1.getColor().equals(temp3.getColor()))) {

                this.score += 5;
                System.out.println(temp1.getColor() + temp2.getColor()  +temp3.getColor());//checking
                this.right.remove(temp1);
                this.right.remove(temp2);
                this.right.remove(temp3);
                this.rightCollectedObjects.pop();
                this.rightCollectedObjects.pop();
                this.rightCollectedObjects.pop();
                this.controlable.remove(temp1);
                this.controlable.remove(temp2);
                this.controlable.remove(temp3);
            }

        } catch (IndexOutOfBoundsException e) {

        }
    }

    public boolean setPlateInLeftStack(int i, Plates x) {
        if (this.isPlateEnteredTheLeftStack((Plates) this.movable.get(i)) && this.checkHight((Plates) this.movable.get(i))) {

            Audio.play("PlateCatched.wav", 1);
          
            if (this.left.isEmpty()) {

                this.movable.get(i).setY(295);

            } else {
                this.movable.get(i).setY(this.getLeftHeight());
            }

            this.movable.get(i).setX(this.clown.getLeftCenter());
            this.left.add(this.movable.get(i));
            this.leftCollectedObjects.push(this.movable.get(i));
            //this.controlable.add(this.movable.get(i));
            this.controlable.add(this.movable.get(i));
            this.movable.remove(this.movable.get(i));
            x.setIsMovingVertically(false);
            return true;
        }
        return false;
    }

    public boolean setPlateInRightStack(int i, Plates x) {
        if (this.isPlateEnteredTheRightStack((Plates) this.movable.get(i)) && this.checkHight((Plates) this.movable.get(i))) {

            Audio.play("PlateCatched.wav", 1);
            if (this.right.isEmpty()) {

                this.movable.get(i).setY(295);

            } else {
                this.movable.get(i).setY(this.getRightHeight());

            }
            this.movable.get(i).setX(this.clown.getRightCenter());
            this.right.add(this.movable.get(i));
            this.rightCollectedObjects.push(this.movable.get(i));
            this.controlable.add(this.movable.get(i));
            this.movable.remove(this.movable.get(i));
            x.setIsMovingVertically(false);
        } else if (this.movable.get(i).getY() >= this.getHeight()) {
            fallingPlates += 1;
            this.movable.get(i).setY(-1 * (int) (Math.random() * getHeight()));
            this.movable.get(i).setX((int) (Math.random() * getWidth()));
            return true;
        }
        return false;
    }

    public void setInRightPlace() {

        try {

            if (right.get(0).getX() >= 850) {
                for (int i = 0; i < this.right.size(); i++) {

                    right.get(i).setX(857);

                }
                for (int i = 0; i < this.left.size(); i++) {
                    left.get(i).setX(857 - (clown.getRightCenter() - clown.getLeftCenter()));
                }
            }

        } catch (IndexOutOfBoundsException e) {

        }

        try {
            if (left.get(0).getX() <= 50) {
                for (int i = 0; i < this.left.size(); i++) {

                    left.get(i).setX(50);

                }
                for (int i = 0; i < this.right.size(); i++) {
                    right.get(i).setX(50 + (clown.getRightCenter() - clown.getLeftCenter()));
                }
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            if (right.get(0).getX() <= 50 + (clown.getRightCenter() - clown.getLeftCenter())) {
                for (int i = 0; i < this.right.size(); i++) {

                    right.get(i).setX(50 + (clown.getRightCenter() - clown.getLeftCenter()));

                }

            }
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            if (left.get(0).getX() >= 850 - (clown.getRightCenter() - clown.getLeftCenter())) {
                for (int i = 0; i < this.left.size(); i++) {

                    left.get(i).setX(857 - (clown.getRightCenter() - clown.getLeftCenter()));

                }

            }
        } catch (IndexOutOfBoundsException e) {

        }
    }

    @Override
    public abstract boolean refresh();

    public void gameOver(boolean ended) {
        if (ended == false) {

            Audio.play("GameOver.wav", 1);
            JOptionPane.showMessageDialog(null, "Game Over:(!");
            MenuSingleton menu = getInstance();
            menu.setIsVisible(true);
        }
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);
    }

    @Override
    public int getSpeed() {
        return SPEED;
    }

    @Override
    public int getControlSpeed() {
        return CONTROL_SPEED;
    }

    public static int getMAX_TIME() {
        return MAX_TIME;
    }

    public static void setMAX_TIME(int MAX_TIME) {
        Levels.MAX_TIME = MAX_TIME;
    }

    public List<GameObject> getMovable() {
        return movable;
    }

    public void setMovable(List<GameObject> movable) {
        this.movable = movable;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clown getClown() {
        return clown;
    }

    public void setClown(Clown clown) {
        this.clown = clown;
    }

    public int getFallingPlates() {
        return fallingPlates;
    }

    public void setFallingPlates(int fallingPlates) {
        this.fallingPlates = fallingPlates;
    }

    public int getGameOverCounter() {
        return gameOverCounter;
    }

    public void setGameOverCounter(int gameOverCounter) {
        this.gameOverCounter = gameOverCounter;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public String[] getSHAPES() {
        return SHAPES;
    }

    public String[] getCOLORS() {
        return COLORS;
    }

}
