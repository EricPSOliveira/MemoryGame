import isConclued.Conclued;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RandomGameSystem extends VariablesRandomSystem {
    boolean isFinal;



    public RandomGameSystem(boolean isFinal, List<ObjectsOnMemory> objectsOnMemory, JPanel outerPanel, Conclued isConclued, boolean[] isSecondClick, List<JPanel> pares_panel, List<Integer> pares, List<JPanel> corretos_panel, List<Integer> corretos, Verify verify, ReturnCape clearCape, JPanel restartWindow) {
        super(
                objectsOnMemory,
                outerPanel,
                isConclued,
                isSecondClick,
                pares_panel,
                pares,
                corretos_panel,
                corretos,
                verify,
                clearCape,
                restartWindow
        );
        this.isFinal = isFinal;
    }


    public void RandomGameSystemInitializer(List<ObjectsOnMemory> objectsOnMemories) {

        for (ObjectsOnMemory objectsOnMemory : objectsOnMemories) {
            // Cores aleatórias para os cartões
            ColorRandomizer colorRandomizer = new ColorRandomizer();
            Color randomColor = colorRandomizer.getColor();

            // Painel em camadas para empilhar imagem e "capa"
            JLayeredPane card = new JLayeredPane();
            card.setPreferredSize(new Dimension(wid, hei));
            card.setLayout(null); // Permite usar coordenadas absolutas

            // Painel interno que contém a imagem da carta
            Panels insidePanel = new Panels();
            JPanel innerPanel = insidePanel.getPanel(objectsOnMemory);
            // image
            JLabel imageLabel = new JLabel(objectsOnMemory.getImage_path());
            innerPanel.add(imageLabel);

            // Painel superior (capa da carta, que será removida ao clicar)
            Panels sidePanel = new Panels();
            JPanel outSidePanel = sidePanel.getPanel(objectsOnMemory);

            // Adiciona os painéis em camadas: imagem embaixo e capa por cima
            card.add(innerPanel, Integer.valueOf(0));
            card.add(outSidePanel, Integer.valueOf(1));

            // Lista e mapa para armazenar os pares clicados e os corretos
            outerPanel.setVisible(false);


            // Evento de clique na capa da carta
            outSidePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    Boolean isFinish = isConclued.getConn();

                    if (isFinish == true) {
                        return;
                    }

                    if (isSecondClick[0] == true) {
                        return;
                    }


                    // Mostra a imagem (remove a capa)
                    outSidePanel.setVisible(false);

                    // Adiciona o ID do cartão na lista de pares selecionados
                    int idValue = (Integer) outSidePanel.getClientProperty("id");
                    pares_panel.add(outSidePanel);
                    pares.add(idValue);
//                    System.out.println("aqui é o valor de pares: " + pares);
                    // Verifica se é o segundo clique
                    if (verify.getVerify() == true) {
                        isSecondClick[0] = true;

                        int idOne = pares.get(0);
                        int idtwo = pares.get(1);
                        JPanel panelOne = pares_panel.get(0);
                        JPanel panelTwo = pares_panel.get(1);


                        // Compara os dois IDs selecionados
                        if (idOne == idtwo) {
                            // Se forem iguais, adiciona ao mapa de acertos
                            corretos.add(idOne);
                            corretos.add(idtwo);
                            corretos_panel.add(panelOne);
                            corretos_panel.add(panelTwo);
                            clearCape.clearCape(pares_panel, pares);
                            System.out.println(pares_panel + " " + pares);
                            verify.setVerify(false);
//                            System.out.println("aqui é o valor de corretos: " + corretos);
                            isSecondClick[0] = false;
                            if (corretos.size() == 18) {
                                isConclued.setConclued(true);
                                isFinal = true;


                                outerPanel.setVisible(false);
                                restartWindow.setVisible(true);


                            }


                        } else {
                            // Se forem diferentes, limpa e mostra a capa de novo
                            if (corretos.isEmpty()) {
                                new java.util.Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        ReturnCape returnCape = new ReturnCape(pares_panel);
                                        isSecondClick[0] = returnCape.runReturn(pares_panel, verify, pares, isSecondClick);
                                    }
                                }, 1000);

                            } else {
                                for (int i = 0; i < objectsOnMemories.size(); i++) {
                                    int value = corretos.get(i);
                                    if (idValue == value) {
                                    } else {
                                        new Timer().schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                ReturnCape returnCape = new ReturnCape(pares_panel);
                                                isSecondClick[0] = returnCape.runReturn(pares_panel, verify, pares, isSecondClick);

                                            }
                                        }, 1000);
                                    }
                                }
                                clearCape.clearCape(pares_panel, pares);
                                verify.setVerify(false);
                            }

                        }

                    } else {
                        // Primeiro clique: marca que está aguardando o segundo
                        verify.setVerify(true);
                    }
                }
            });

            // Evento para clicar na imagem e cobrir de novo (usado pra teste aqui)
            innerPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    Boolean isFinish = isConclued.getConn();
                    if (isFinish == true) {
                        return;
                    }

                    clearCape.clearCape(pares_panel, pares);
                    verify.setVerify(false);
                    outSidePanel.setVisible(true);
                    isSecondClick[0] = false;// Cobre novamente
                }
            });

            // Adiciona o cartão ao painel principal
            outerPanel.add(card);
        }
    }

    public boolean getIsFinal(){
         return this.isFinal;
    }

    public void resetComponents(){

    }


}
