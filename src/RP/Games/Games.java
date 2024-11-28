package RP.Games;

import RP.Games.TicTacToe.Tetris;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Games extends JFrame{
    public Games(JPanel Games_, JPanel MM, ArrayList<JPanel> Games){

        /* Title & Return to Main Menu */{
            JPanel GTitle = new JPanel();
            GTitle.setBounds(-1,-1,Games_.getWidth()+2,36);
            GTitle.setLayout(null);
            GTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            Games_.add(GTitle);

            JLabel Title = new JLabel();
            Title.setFont(new Font("Arial", Font.BOLD, 20));
            Title.setText("RP - Games");
            Title.setBounds(10, 10, 300, 20);
            GTitle.add(Title);

            JButton MMenu = new JButton();
            MMenu.setBounds(390, 10, 100, 20);
            MMenu.setFont(new Font("Arial", Font.BOLD, 10));
            MMenu.setText("Main Menu");
            MMenu.setBackground(new Color(255,255,255));
            MMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            MMenu.addActionListener(_ -> {
                //ClearOP();
                System.out.println("RP - Main Menu");
                Games_.setVisible(false);
                MM.setVisible(true);
            });
            GTitle.add(MMenu);
        }

        Tetris TicTacToe = new Tetris(Games.getFirst() ,Games_);

        JLabel TTT_3Title = new JLabel();
        TTT_3Title.setBounds(10, 80, 100, 20);
        TTT_3Title.setText("Tic Tac Toe");
        Games_.add(TTT_3Title);

        JButton TTT_3Button = new JButton();
        TTT_3Button.setBounds(120, 65, 40, 40);
        TTT_3Button.setBackground(new Color(255,255,255));
        TTT_3Button.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        TTT_3Button.addActionListener(_ -> {
            Games.getFirst().setVisible(true);
            TicTacToe.TetrisGameControl();
            Games_.setVisible(false);
        });
        Games_.add(TTT_3Button);

    }
}
