package RP.Main;

import RP.Calculadora.Calculadora;
import RP.Tareas.Tareas;
import RP.Var.Var;

import javax.swing.*;
import java.awt.*;

public class MainIG extends JFrame {
    public MainIG(){

        Var.Calc_.setBounds(0,0,500,500);
        Var.Calc_.setLayout(null);
        Var.Calc_.setVisible(false);
        add(Var.Calc_);

        Var.Tar_.setBounds(0,0,500,500);
        Var.Tar_.setLayout(null);
        Var.Tar_.setVisible(false);
        add(Var.Tar_);


        setLayout(null);

        JPanel MIG_ = new JPanel();
        MIG_.setBounds(0,0,500,500);
        MIG_.setLayout(null);
        add(MIG_);

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

        /* Calculadora */{
            JLabel LCalc = new JLabel();
            LCalc.setBounds(10, 80, 100, 10);
            LCalc.setText("RP - Calculadora");
            MIG_.add(LCalc);

            JButton BCalc = new JButton();
            BCalc.setBounds(120, 65, 40, 40);
            BCalc.setBackground(new Color(255,255,255));
            BCalc.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
            BCalc.addActionListener(_ -> {
                Calculadora Calc = new Calculadora(Var.Calc_, MIG_);
                Calc.ClearOP();
                System.out.println("RP - Calculadora");
                Var.Calc_.setVisible(true);
                MIG_.setVisible(false);
            });
            MIG_.add(BCalc);
        }

        /* Tareas */{
            JLabel LCalc = new JLabel();
            LCalc.setBounds(10, 120, 100, 10);
            LCalc.setText("RP - Tareas");
            MIG_.add(LCalc);

            JButton BCalc = new JButton();
            BCalc.setBounds(120, 105, 40, 40);
            BCalc.setBackground(new Color(255,255,255));
            BCalc.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
            BCalc.addActionListener(_ -> {
                Tareas Tareas = new Tareas(Var.Tar_, MIG_);
                Tareas.ClearAN();
                System.out.println("RP - Tareas");
                Var.Tar_.setVisible(true);
                MIG_.setVisible(false);
            });
            MIG_.add(BCalc);
        }

        setTitle("RP-Main");
        setBounds(0,0,500,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
