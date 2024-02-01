package Model;

import Controller.Plates;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.List;

public class RemovablePlate implements Plate{
    protected List<GameObject> movable;
    protected  List<GameObject> controlable ;
    Plates temp1 ;
    Plates temp2 ;
    Plates temp3 ;
    public RemovablePlate(List<GameObject> movable, List<GameObject> controlable, Plates temp1, Plates temp2,Plates temp3 ) {
         this.movable = movable;
        this.controlable = controlable;
        this.temp1=temp1;
        this.temp2=temp2;
        this.temp3=temp3;
    }

    
    
    
    
    @Override
    public void change() {
         this.controlable.remove(temp1);
         this.controlable.remove(temp2);
         this.controlable.remove(temp3);
    }
    
}
