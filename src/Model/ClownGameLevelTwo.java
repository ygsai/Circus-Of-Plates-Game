package Model;

import Controller.ImageObject;
import Controller.Plates;
import Controller.Strategy.SpeedStrategy;
import Model.Observer.Observer;
import java.util.List;
import view.Audio;

public class ClownGameLevelTwo extends ClownGameLevelOne {

    public ClownGameLevelTwo(SpeedStrategy s, int width, int height) {
        super(s, width, height);

        this.constant.add(new ImageObject(0, 0, "/Theme2.jpg"));
        for (int i = 0; i < 5; i++) {
            this.getMovableObjects().add(new Plates("Black", "Bomb", (int) (Math.random() * this.width), -1 * (int) (Math.random() * this.height), "/Bomb.png", true));
        }
    }

    private int notifyBombCaught(Plates bombPlate, List<Observer> observers) {
        int totalScore = 0;
        for (int i = 0; i < observers.size(); i++) {
            totalScore += observers.get(i).update(bombPlate);
        }
        return totalScore;
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        this.setInRightPlace();
        for (int i = 0; i < this.movable.size(); i++) {
            this.updateLeftScore();
            this.updateRightScore();
            Plates x = (Plates) this.movable.get(i);
            this.movable.get(i).setY(this.movable.get(i).getY() + 4);

            if (!x.getShape().equals("Bomb")) {
                if (!this.setPlateInLeftStack(i, x)) {
                    this.setPlateInRightStack(i, x);
                }
                if (((fallingPlates == 90 || this.getRight().size() == 8 || this.getLeft().size() == 8 || this.getMovableObjects().isEmpty()) && !ended)) {
                    ended = true;
                    gameOverCounter++;
                    gameOver(false);
                    return false;
                }
            } else {
                resizeBomb(x);
                checkBomb(x);

            }

        }

        if (gameOverCounter == 0 && timeout) {
            ended = true;
            gameOverCounter++;
            gameOver(!timeout && !ended);

        }
        return !timeout;
    }

    public void resizeBomb(Plates x) {
        if (x.getY() >= this.getHeight()) {
            fallingPlates += 1;
            x.setY(-1 * (int) (Math.random() * getHeight()));
            x.setX((int) (Math.random() * getWidth()));
        }
    }

    public void checkBomb(Plates x) {
        if (this.checkHight(x) && (this.isPlateEnteredTheLeftStack(x) || this.isPlateEnteredTheRightStack(x))) {
            this.score = this.score + notifyBombCaught(x, observers);
            Audio.play("Bomb.wav", 1);
            this.movable.remove(x);
        }
    }

    public int getSpeed() {
        s.getSpeed();
        return -1;

    }
}
