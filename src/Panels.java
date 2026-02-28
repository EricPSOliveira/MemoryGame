import javax.swing.*;
import java.awt.*;


public class Panels extends ColorRandomizer{
    JPanel panel;
    int wid;
    int hei;

    public Panels() {
        Sizes size = new Sizes();
        this.panel = new JPanel();
        this.wid = size.getWidthCard();
        this.hei = size.getHeightCard();


    }

    public JPanel getPanel(ObjectsOnMemory objectsOnMemory){

        this.panel.setBackground(getColor());
        this.panel.setBounds(0, 0, wid, hei);
        this.panel.putClientProperty("id", objectsOnMemory.getId());
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        return this.panel;
    }

    public int getHeiCard(){
        return this.hei;
    }

    public int getWidCard(){
        return this.wid;
    }


}
