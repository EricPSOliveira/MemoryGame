// Importações necessárias para a interface gráfica e manipulação de eventos

import isConclued.Conclued;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;

// Classe principal
public class Main {
    public static void main(String[] args) {
        // Criação da janela principal do jogo
        JFrame tela = new JFrame("MemoryGame");

        // Objeto para controle de verificação de pares
        Verify verify = new Verify(false);

        // Cor de fundo e dimensões da janela e dos cartões
        String colorOne = "#e3cab3";
        Panels size = new Panels();


        int widthWindow = 1600;
        int heightWindow = 1100;
        int widthCard = size.getWidCard();
        int heightCard = size.getHeiCard();

        // Configuração da janela
        tela.setSize(widthWindow, heightWindow);
        tela.setResizable(false);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setBackground(Color.decode(colorOne));

        // Painel que vai conter todos os cartões (3 linhas x 6 colunas = 18 cartas)
        JPanel outerPanel = new JPanel(new GridLayout(3, 6, 10, 10));
        outerPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Margem
        outerPanel.setBackground(Color.decode(colorOne));


        JPanel restartWindow = new JPanel(new GridBagLayout());

        JLabel label = new JLabel("Você ganhou o jogo");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        JButton playAgain = new JButton("Jogar Novamente");


        playAgain.setBackground(Color.BLACK);
        playAgain.setForeground(Color.WHITE);
        playAgain.setBorderPainted(false);
        playAgain.setFocusPainted(false);


        restartWindow.setBackground(new Color(0, 0, 0));
        restartWindow.setSize(widthWindow, heightWindow);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(200, 0, 20, 0); // empurra pra baixo: top, left, bottom, right
        restartWindow.add(label, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 150, 0); // botão um pouco mais abaixo
        restartWindow.add(playAgain, gbc);

        // Lista com os objetos que vão representar os pares do jogo da memória
        List<ObjectsOnMemory> objectsOnMemories = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            String object = "image" + i;
            String path = String.format("/assets/memorySystemImages/image%d.png", i);

            // Adiciona dois objetos com o mesmo ID e imagem (pares)
            objectsOnMemories.add(new ObjectsOnMemory(i, object + "_a", path));
            objectsOnMemories.add(new ObjectsOnMemory(i, object + "_b", path));
        }

        ReturnCape clearCape = new ReturnCape();

        List<Integer> pares = new ArrayList<>();
        List<JPanel> pares_panel = new ArrayList<>();

        List<Integer> corretos = new ArrayList<>();
        List<JPanel> corretos_panel = new ArrayList<>();

        boolean[] isSecondClick = {false};
        Conclued isConclued = new Conclued(false);

        // Embaralha os pares
        Collections.shuffle(objectsOnMemories);




        // Para cada objeto da memória, cria um cartão

        RandomGameSystem randomGameSystem = new RandomGameSystem(false, objectsOnMemories, outerPanel, isConclued, isSecondClick, pares_panel, pares, corretos_panel, corretos, verify, clearCape, restartWindow);
        randomGameSystem.RandomGameSystemInitializer(objectsOnMemories);

        tela.add(restartWindow);
        tela.add(outerPanel);
        tela.setVisible(true);


        playAgain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                boolean finalist = randomGameSystem.getIsFinal();
                if (finalist) {
                    System.out.println("oi");
                }

                Restart restart = new Restart(true);
                restart.reshuffle(objectsOnMemories, corretos, corretos_panel);
                restartWindow.setVisible(false);
                outerPanel.setVisible(true);

            }
        });



        // Adiciona o painel com as cartas na janela e mostra tudo

    }
}
