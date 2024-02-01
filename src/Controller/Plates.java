
package Controller;

public class Plates extends ImageObject {

    private String color, shape;
    private boolean isMovingVertically;
  
    public Plates(String color, String shape, int posX, int posY, String path, boolean isMovingVertically) {
        super(posX, posY, path);
        this.color = color;
        this.shape = shape;
        this.isMovingVertically = isMovingVertically;
    }


    public boolean isIsMovingVertically() {
        return isMovingVertically;
    }

    public void setIsMovingVertically(boolean isMovingVertically) {
        this.isMovingVertically = isMovingVertically;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @Override
    public void setY(int mY) {
        if (!this.isMovingVertically) {
            return;
        } else {
            this.y = mY;
        }
    }    
}
