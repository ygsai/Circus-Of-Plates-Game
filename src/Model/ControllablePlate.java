package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.List;

public class ControllablePlate implements Plate{

    
    protected List<GameObject> movable;
    protected  List<GameObject> controlable ;
     int i;
    
    public ControllablePlate(List<GameObject> movable, List<GameObject> controlable,int i) {
        this.movable = movable;
        this.controlable = controlable;
        this.i=i;
    }
    
    
    @Override
    public void change() {
      
      this.controlable.add(this.movable.get(i));//State
      this.movable.remove(this.movable.get(i));
        
    }

   
    
}
