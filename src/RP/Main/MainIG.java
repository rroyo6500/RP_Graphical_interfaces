package RP.Main;

import RP.Calculadora.Calculadora;
import RP.Games.Games;
import RP.MusicPlay.MusicPlay;
import RP.Tareas.Tareas;
import RP.Var.Var;

import javax.swing.*;
import java.awt.*;

public class MainIG extends JFrame {

    Var var = new Var();

    public MainIG(){

        this.setLayout(null);

        Var.Calc_.setBounds(0,0, var.Width(500), var.Height(500));
        Var.Calc_.setLayout(null);
        Var.Calc_.setVisible(false);
        add(Var.Calc_);

        Var.Tar_.setBounds(0,0, var.Width(500), var.Height(500));
        Var.Tar_.setLayout(null);
        Var.Tar_.setVisible(false);
        add(Var.Tar_);

        Var.MP_.setBounds(0,0, var.Width(500), var.Height(500));
        Var.MP_.setLayout(null);
        Var.MP_.setVisible(false);
        add(Var.MP_);

        Var.Games_.setBounds(0,0, var.Width(500), var.Height(500));
        Var.Games_.setLayout(null);
        Var.Games_.setVisible(false);
        add(Var.Games_);

        JPanel MIG_ = new JPanel();
        MIG_.setBounds(0,0, var.Width(500), var.Height(500));
        MIG_.setLayout(null);
        add(MIG_);

        for (JPanel Game : Var.GamesPanel){
            Game.setBounds(0,0, var.Width(500), var.Height(500));
            Game.setLayout(null);
            Game.setVisible(false);
            add(Game);
        }

        JLabel Title = new JLabel();
        Title.setFont(new Font("Arial", Font.BOLD, 20));
        Title.setText("RP - Main Menu");
        Title.setBounds(10,10,155,20);
        MIG_.add(Title);

        JButton Exit = new JButton();
        Exit.setBounds(390, 10, 100, 20);
        Exit.setFont(new Font("Arial", Font.BOLD, 10));
        Exit.setText("Exit");
        Exit.setBackground(new Color(255,80,80));
        Exit.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        Exit.addActionListener(_ -> {
            System.out.println("EXIT");
            this.setVisible(false);
            System.exit(0);
        });
        MIG_.add(Exit);

        Calculadora Calc = new Calculadora(Var.Calc_, MIG_);
        Tareas Tareas = new Tareas(Var.Tar_, MIG_);
        MusicPlay MusicPlay = new MusicPlay(Var.MP_, MIG_);
        Games Games = new Games(Var.Games_, MIG_, Var.GamesPanel);

        // Calculadora
        JLabel LCalc = new JLabel();
        LCalc.setBounds(10, 80, 100, 20);
        LCalc.setText("RP - Calculadora");
        MIG_.add(LCalc);

        JButton BCalc = new JButton();
        BCalc.setBounds(120, 65, 40, 40);
        BCalc.setBackground(new Color(255,255,255));
        BCalc.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        BCalc.addActionListener(_ -> {
            Calc.ClearOP();
            System.out.println("RP - Calculadora");
            Var.Calc_.setVisible(true);
            MIG_.setVisible(false);
        });
        MIG_.add(BCalc);

        // Tareas
        JLabel LTar = new JLabel();
        LTar.setBounds(10, 120, 100, 20);
        LTar.setText("RP - Tareas");
        MIG_.add(LTar);

        JButton BTar = new JButton();
        BTar.setBounds(120, 105, 40, 40);
        BTar.setBackground(new Color(255,255,255));
        BTar.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        BTar.addActionListener(_ -> {
            Tareas.ClearAN();
            System.out.println("RP - Tareas");
            Var.Tar_.setVisible(true);
            MIG_.setVisible(false);
        });
        MIG_.add(BTar);

        // MusicPlay
        JLabel LMusicPlay = new JLabel();
        LMusicPlay.setBounds(10, 160, 100, 20);
        LMusicPlay.setText("RP - Music Play");
        MIG_.add(LMusicPlay);

        JButton BMusicPlay = new JButton();
        BMusicPlay.setBounds(120, 145, 40, 40);
        BMusicPlay.setBackground(new Color(255,255,255));
        BMusicPlay.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        BMusicPlay.addActionListener(_ -> {
            System.out.println("RP - MusicPlay");
            Var.MP_.setVisible(true);
            MIG_.setVisible(false);
        });
        MIG_.add(BMusicPlay);

        // Games
        JLabel LGames = new JLabel();
        LGames.setBounds(10, 200, 100, 20);
        LGames.setText("RP - Games");
        MIG_.add(LGames);

        JButton BGames = new JButton();
        BGames.setBounds(120, 185, 40, 40);
        BGames.setBackground(new Color(255,255,255));
        BGames.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        BGames.addActionListener(_ -> {
            System.out.println("RP - Games");
            Var.Games_.setVisible(true);
            MIG_.setVisible(false);
        });
        MIG_.add(BGames);

        this.setTitle("RP-Main");
        this.setBounds(0,0, var.Width(500), var.Height(500));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
