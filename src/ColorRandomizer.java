import java.awt.*;
import java.util.Random;

public class ColorRandomizer {
    int r;
    int g;
    int b;

    public ColorRandomizer(){
        this.r = new Random().nextInt(256);
        this.g = new Random().nextInt(256);
        this.b = new Random().nextInt(256);
    }

    public Color getColor(){
        return new Color(r,g,b);
    }




}
