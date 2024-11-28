package RP.Games;

import RP.Games.Tetris.Tetris;

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

        Tetris Tetris = new Tetris(Games.getFirst() ,Games_);

        JLabel T_Title = new JLabel();
        T_Title.setBounds(10, 80, 100, 20);
        T_Title.setText("Tetris");
        Games_.add(T_Title);

        JButton T_Button = new JButton();
        T_Button.setBounds(120, 65, 40, 40);
        T_Button.setBackground(new Color(255,255,255));
        T_Button.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        T_Button.addActionListener(_ -> {
            Games.getFirst().setVisible(true);
            Tetris.TetrisGameControl();
            Games_.setVisible(false);
        });
        Games_.add(T_Button);

    }
}
