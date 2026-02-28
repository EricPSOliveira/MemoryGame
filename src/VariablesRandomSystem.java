import isConclued.Conclued;

import javax.swing.*;
import java.util.List;


public class VariablesRandomSystem extends Panels{
    List<ObjectsOnMemory> objectsOnMemory;
    JPanel outerPanel;
    Conclued isConclued;
    boolean[] isSecondClick;
    List<JPanel> pares_panel;
    List<Integer> pares;
    List<JPanel> corretos_panel;
    List<Integer> corretos;
    Verify verify;
    ReturnCape clearCape;
    JPanel restartWindow;


    public VariablesRandomSystem(List<ObjectsOnMemory> objectsOnMemory, JPanel outerPanel, Conclued isConclued, boolean[] isSecondClick, List<JPanel> pares_panel, List<Integer> pares, List<JPanel> corretos_panel, List<Integer> corretos, Verify verify, ReturnCape clearCape, JPanel restartWindow) {
        this.objectsOnMemory = objectsOnMemory;
        this.outerPanel = outerPanel;
        this.isConclued = isConclued;
        this.isSecondClick = isSecondClick;
        this.pares_panel = pares_panel;
        this.pares = pares;
        this.corretos_panel = corretos_panel;
        this.corretos = corretos;
        this.verify = verify;
        this.clearCape = clearCape;
        this.restartWindow = restartWindow;
    }

}
