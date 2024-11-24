package RP.Games;

import javax.swing.*;
import java.awt.*;

public class Games extends JFrame{
    public Games(JPanel Games_, JPanel MM){

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

    }
}
