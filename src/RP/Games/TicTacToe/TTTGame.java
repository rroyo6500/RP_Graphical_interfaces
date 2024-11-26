package RP.Games.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TTTGame extends JFrame {

    JPanel TTT, GamesMenu;
    JFrame Player = new JFrame();

    public TTTGame(JPanel GamePanel, JPanel GamesMenu){
        this.TTT = GamePanel;
        this.GamesMenu = GamesMenu;
        Player.setBounds(0,0,GamePanel.getWidth(), GamePanel.getHeight());
    }

    public void TicTacToeGameStart(){
        /* Title & Return to Main Menu */{
            JPanel TTT_Title = new JPanel();
            TTT_Title.setBounds(-1,-1,TTT.getWidth()+2,36);
            TTT_Title.setLayout(null);
            TTT_Title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            TTT.add(TTT_Title);

            JLabel Title = new JLabel();
            Title.setFont(new Font("Arial", Font.BOLD, 20));
            Title.setText("Tic Tac Toe");
            Title.setBounds(10, 10, 300, 20);
            TTT_Title.add(Title);

            JButton MMenu = new JButton();
            MMenu.setBounds(390, 10, 100, 20);
            MMenu.setFont(new Font("Arial", Font.BOLD, 10));
            MMenu.setText("Games Menu");
            MMenu.setBackground(new Color(255,255,255));
            MMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            MMenu.addActionListener(_ -> {
                //ClearOP();
                TTT.setVisible(false);
                GamesMenu.setVisible(true);
                Player.setVisible(false);
            });
            TTT_Title.add(MMenu);
        }

        ArrayList<JLabel> Casillas = new ArrayList<>(){{
            for (int i = 0; i < 9; i++) {
                add(new JLabel("", SwingConstants.CENTER));
            }
        }};

        for (int i = 300, C = 0; i >= 100 ; i-=100) {
            for (int j = 100; j <= 300; j+=100) {
                Casillas.get(C).setBounds(j,i,100,100);
                Casillas.get(C).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
                C++;
            }
        }
        for (JLabel Pos : Casillas){
            TTT.add(Pos);
        }

        Player(Player);
        Player.setVisible(true);
    }

    public void Player(JFrame Player){
        Player.setLayout(null);

        Player.setLocationRelativeTo(null);
        Player.setResizable(false);
        new Thread(() -> {

        });
    }
}
