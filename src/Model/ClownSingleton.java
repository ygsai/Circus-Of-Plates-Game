package Model;

public class ClownSingleton {
    private static ClownSingleton clown=null;
    
    static int width;
    static int height;
    static String path;
    private  ClownSingleton(int width,int height,String path){
     this.width=width;
     this.height=height;
     this.path=path;
    }
    public synchronized static ClownSingleton getInstance(int width,int height,String path)
    {
    
        if(clown==null){
            clown=new ClownSingleton( width, height, path);
        }
        return clown;
    
    }
    
}
