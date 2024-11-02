package RP.Main;
import RP.Calculadora.*;
import RP.Tareas.Tareas;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        new MainIG();
    }
}

class MainIG extends JFrame {
    public MainIG(){
        setLayout(null);

        JLabel Title = new JLabel();
        Title.setFont(new Font("Arial", Font.BOLD, 20));
        Title.setText("RP - Main Menu");
        Title.setBounds(10,10,155,20);
        add(Title);

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
        add(Exit);

        /* Calculadora */{
            JLabel LCalc = new JLabel();
            LCalc.setBounds(10, 80, 100, 10);
            LCalc.setText("RP - Calculadora");
            add(LCalc);

            JButton BCalc = new JButton();
            BCalc.setBounds(120, 65, 40, 40);
            BCalc.setBackground(new Color(255,255,255));
            BCalc.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
            BCalc.addActionListener(_ -> {
                Calculadora Calc = new Calculadora();
                System.out.println("RP - Calculadora");
                Calc.setVisible(true);
                this.setVisible(false);
            });
            add(BCalc);
        }

        /* Tareas */{
            JLabel LCalc = new JLabel();
            LCalc.setBounds(10, 120, 100, 10);
            LCalc.setText("RP - Tareas");
            add(LCalc);

            JButton BCalc = new JButton();
            BCalc.setBounds(120, 105, 40, 40);
            BCalc.setBackground(new Color(255,255,255));
            BCalc.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
            BCalc.addActionListener(_ -> {
                Tareas Tareas = new Tareas();
                System.out.println("RP - Tareas");
                Tareas.setVisible(true);
                this.setVisible(false);
            });
            add(BCalc);
        }

        setTitle("RP-Main");
        setBounds(0,0,500,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}



