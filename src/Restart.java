import javax.swing.*;
import java.util.Collections;
import java.util.List;


public class Restart{
    boolean isRestart;


    public Restart(boolean isRestart){
        this.isRestart = isRestart;
    }


    public void reshuffle(List<ObjectsOnMemory> shuffer, List<Integer> integers, List<JPanel> panels){
        Collections.shuffle(shuffer);
        integers.clear();
        panels.clear();
    }

    public boolean isRestarting(){
        return this.isRestart;
    }



}
