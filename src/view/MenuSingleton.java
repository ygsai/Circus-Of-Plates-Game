
package view;


public class MenuSingleton {

    private GameMenu fm;

    private static MenuSingleton obj = null;

    private MenuSingleton() {
        this.fm = new GameMenu();
    }

    public synchronized static MenuSingleton getInstance() {
        if (obj == null) {
            obj = new MenuSingleton();
        }
        return obj;
    }

    public void setIsVisible(boolean isVisible) {

       fm.setIsVisible(isVisible);
    }
}
