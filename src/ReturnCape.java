import javax.swing.*;
import java.util.List;

public class ReturnCape {
    JPanel firstId;
    JPanel secondId;

    public ReturnCape(){
        this.firstId = null;
        this.secondId = null;
    }

    public ReturnCape(List<JPanel> pares_panel){
        this.firstId = pares_panel.get(0);
        this.secondId = pares_panel.get(1);
    }


    public boolean runReturn(List<JPanel> pares_panel, Verify verify, List<Integer> pares, boolean[] isSecondClick) {

        isVisible();
        clearCape(pares_panel, pares);
        verify.setVerify(false);
        System.out.println("Corretos is Empty " + pares + " " + pares_panel + " " + verify.getVerify());
        return false;
    }

    public void isVisible(){
        this.firstId.setVisible(true);
        this.secondId.setVisible(true);
        this.firstId = null;
        this.secondId = null;
    }

    public void clearCape(List<JPanel> pares_panel, List<Integer> pares){
        pares.clear();
        pares_panel.clear();
    }

}
