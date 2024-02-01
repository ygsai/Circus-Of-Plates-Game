package Model.Observer;

import Controller.Plates;

public class GameObserver implements Observer {

    @Override
    public int update(Plates event) {
        int x = 0, y = 0;
        if (event.getShape().equals("Bomb")) {
             x=-5;
        }
        if (event.getShape().equals("Gift")) {
            y=5;
        } 
        return x+y;
        
    }
}
