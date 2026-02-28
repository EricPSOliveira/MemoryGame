import javax.swing.*;
import java.awt.*;
import java.sql.Array;
import java.util.List;

public class ObjectsOnMemory{
    public int id;
    public String object;
    public ImageIcon image_path;


    public ObjectsOnMemory(int id, String object, String imag_path) {
        this.id = id;
        this.object = object;
        this.image_path = new ImageIcon(getClass().getResource(imag_path));
    }

    public String getObject() {
        return object;
    }

    public ImageIcon getImage_path() {
        return image_path;
    }

    public int getId(){
        return id;
    }


}
