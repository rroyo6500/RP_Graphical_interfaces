package RP.MusicPlay;

import javax.swing.*;
import java.awt.*;

public class MusicPlay extends JFrame {

    MP Mp = new MP();

    public MusicPlay(JPanel MP_, JPanel MM){

        /* Title & Return to Main Menu */{
            JPanel MPTitle = new JPanel();
            MPTitle.setBounds(-1,-1,MP_.getWidth()+2,36);
            MPTitle.setLayout(null);
            MPTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            MP_.add(MPTitle);

            JLabel Title = new JLabel();
            Title.setFont(new Font("Arial", Font.BOLD, 20));
            Title.setText("RP - Music Player");
            Title.setBounds(10, 10, 300, 20);
            MPTitle.add(Title);

            JButton MMenu = new JButton();
            MMenu.setBounds(390, 10, 100, 20);
            MMenu.setFont(new Font("Arial", Font.BOLD, 10));
            MMenu.setText("Main Menu");
            MMenu.setBackground(new Color(255,255,255));
            MMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            MMenu.addActionListener(_ -> {
                //ClearOP();
                System.out.println("RP - Main Menu");
                MP_.setVisible(false);
                MM.setVisible(true);
            });
            MPTitle.add(MMenu);
        }

    }
}
