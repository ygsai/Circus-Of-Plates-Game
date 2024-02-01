package Model;

import Controller.ImageObject;
import Controller.Plates;
import Controller.Strategy.SpeedStrategy;
import Model.Observer.Observer;
import java.util.List;
import view.Audio;

public class ClownGameLevelThree extends ClownGameLevelTwo {

    public ClownGameLevelThree(SpeedStrategy s, int width, int height) {
        super(s, width, height);

        this.constant.add(new ImageObject(0, 0, "/Theme3.jpg"));
        for (int i = 0; i < 3; i++) {
            this.getMovableObjects().add(new Plates("Pink", "Gift", (int) (Math.random() * this.width), -1 * (int) (Math.random() * this.height), "/Gift.png", true));
        }
    }

    private int notifyBombCaught(Plates bombPlate, List<Observer> observers) {
        int totalScore = 0;
        for (Observer observer : observers) {
            totalScore += observer.update(bombPlate);
        }
        return totalScore;
    }

    private int notifyGiftCollected(Plates giftPlate, List<Observer> observers) {
        for (Observer observer : observers) {
            return observer.update(giftPlate);
        }
        return 0;
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        this.setInRightPlace();
        for (int i = 0; i < this.movable.size(); i++) {
            this.updateLeftScore();
            this.updateRightScore();
            Plates x = (Plates) this.movable.get(i);
            this.movable.get(i).setY(this.movable.get(i).getY() + 5);
            if (x.getShape().equals("Bomb")) {
                checkBomb(x);
                resizeBomb(x);
            } else if (x.getShape().equals("Gift")) {
                checkGift(x);
                resizeGift(x);
            } else {
                if (!this.setPlateInLeftStack(i, x)) {
                    this.setPlateInRightStack(i, x);
                }
                if (((fallingPlates == 90 || this.getRight().size() == 8 || this.getLeft().size() == 8 || this.getMovableObjects().isEmpty()) && !ended)) {
                    ended = true;
                    gameOverCounter++;
                    gameOver(false);

                    return false;
                }
            }

        }
        if (gameOverCounter == 0 && timeout) {
            ended = true;
            gameOverCounter++;
            gameOver(!timeout && !ended);

        }
        return !timeout;
    }

    public void checkGift(Plates x) {
        if (x.getY() >= this.getHeight()) {
            fallingPlates += 1;
            x.setY(-1 * (int) (Math.random() * getHeight()));
            x.setX((int) (Math.random() * getWidth()));
        }
    }

    public void resizeGift(Plates x) {
        if (this.checkHight(x) && (this.isPlateEnteredTheLeftStack(x) || this.isPlateEnteredTheRightStack(x))) {
            score = score + notifyBombCaught(x, observers);
            Audio.play("Gift.wav", 1);
            this.movable.remove(x);
        }
    }

    @Override
    public void updateLeftScore() {

        try {
            Plates temp1 = (Plates) this.getLeft().get(this.getLeft().size() - 1);
            Plates temp2 = (Plates) this.getLeft().get(this.getLeft().size() - 2);
            Plates temp3 = (Plates) this.getLeft().get(this.getLeft().size() - 3);
            if ((temp1.getColor().equals(temp2.getColor())) && (temp1.getColor().equals(temp3.getColor())) && (temp1.getShape().equals(temp3.getShape())) && (temp1.getShape().equals(temp2.getShape()))) {
                this.score += 5;
                this.getLeft().remove(temp1);
                this.getLeft().remove(temp2);
                this.getLeft().remove(temp3);
                this.getLeftCollectedObjects().pop();
                this.getLeftCollectedObjects().pop();
                this.getLeftCollectedObjects().pop();

                RemovablePlate remove = new RemovablePlate(movable, controlable, temp1, temp2, temp3);

                plate.setPlate(remove);
                plate.change();
            }

        } catch (IndexOutOfBoundsException e) {

        }
    }

    @Override
    public void updateRightScore() {

        try {
            Plates temp1 = (Plates) this.getRight().get(this.getRight().size() - 1);
            Plates temp2 = (Plates) this.getRight().get(this.getRight().size() - 2);
            Plates temp3 = (Plates) this.getRight().get(this.getRight().size() - 3);
            if ((temp1.getColor().equals(temp2.getColor())) && (temp1.getColor().equals(temp3.getColor())) && (temp1.getShape().equals(temp3.getShape())) && (temp1.getShape().equals(temp2.getShape()))) {

                this.score += 5;
                this.getRight().remove(temp1);
                this.getRight().remove(temp2);
                this.getRight().remove(temp3);
                this.getRightCollectedObjects().pop();
                this.getRightCollectedObjects().pop();
                this.getRightCollectedObjects().pop();

                RemovablePlate remove = new RemovablePlate(movable, controlable, temp1, temp2, temp3);

                plate.setPlate(remove);
                plate.change();
            }

        } catch (IndexOutOfBoundsException e) {

        }
    }

    public int getSpeed() {
        s.getSpeed();
        return -1;
    }
}
