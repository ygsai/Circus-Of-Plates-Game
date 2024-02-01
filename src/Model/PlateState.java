
package Model;

public class PlateState implements Plate {

    private Plate plate;
    public Plate getPlate()
    {
        return plate;
    }
    
    public void setPlate(Plate plate)
    {
        this.plate=plate;
    }
    @Override
    public void change() {
       plate.change();
    }
    
}


 
