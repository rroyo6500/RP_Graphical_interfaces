package RP.Calculadora;

import RP.Main.Main;
import RP.OBJ.Calc;

import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame{

    Calc Calc = new Calc();
    Boolean Comp;

    public Calculadora(){
        setLayout(null);

        /* Title & Return to Main Menu */{
            JPanel CTitle = new JPanel();
            CTitle.setBounds(-1,-1,502,36);
            CTitle.setLayout(null);
            CTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            add(CTitle);

            JLabel Title = new JLabel();
            Title.setFont(new Font("Arial", Font.BOLD, 20));
            Title.setText("RP - Calculadora");
            Title.setBounds(10, 10, 160, 20);
            CTitle.add(Title);

            JButton MMenu = new JButton();
            MMenu.setBounds(390, 10, 100, 20);
            MMenu.setFont(new Font("Arial", Font.BOLD, 10));
            MMenu.setText("Main Menu");
            MMenu.setBackground(new Color(255,255,255));
            MMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            MMenu.addActionListener(_ -> {
                System.out.println("RP - Main Menu");
                this.setVisible(false);
                Main.main(null);

            });
            CTitle.add(MMenu);
        }

        /* Linea de tiempo Operaciones + Resultado */
        JTextArea LOp = new JTextArea();
        LOp.setEditable(false);
        JScrollPane SLOp = new JScrollPane(LOp);
        SLOp.setBounds(220,100,270,180);
        SLOp.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        SLOp.setBackground(new Color(255,255,255));
        add(SLOp);

        /* Calculadora */
        JLabel Resultado = new JLabel();
        Resultado.setBounds(255,60,200,20);
        //Resultado.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        add(Resultado);

        JLabel ResultT = new JLabel();
        ResultT.setBounds(185, 60, 100, 20);
        ResultT.setText("Resultado:");
        add(ResultT);

        JTextField Operacion = new JTextField();
        Operacion.setBounds(10, 40, 480, 20);
        Operacion.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        Operacion.setBackground(new Color(255,255,255));
        add(Operacion);

        JButton Calculate = new JButton();
        Calculate.setBounds(10,60,90,20);
        Calculate.setText("Calcular");
        Calculate.setBackground(new Color(80,255,80));
        Calculate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Calculate.addActionListener(_ -> {
            String OP = Operacion.getText();
            Comp = Calc.Comp(OP);
            //Resultado.setText(String.valueOf(Comp));
            if (Comp){
                Error error = new Error();
                error.setVisible(true);
                Operacion.setBackground(new Color(255,150,150));
            }else {
                Operacion.setBackground(new Color(150, 255, 150));
                Resultado.setText(Calc.Calculate(OP));

                LOp.setText(Operacion.getText() + "\n    - Resultado: " + Resultado.getText() + "\n" + LOp.getText());
            }
        });
        add(Calculate);

        JButton Clear = new JButton();
        Clear.setBounds(100,60,80,20);
        Clear.setText("Clear");
        Clear.setBackground(new Color(255, 80, 80));
        Clear.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Clear.addActionListener(_ -> {
            if (!Operacion.getText().equalsIgnoreCase("")) {
                Operacion.setBackground(new Color(255, 255, 255));
                Operacion.setText("");
                Resultado.setText("");
            }
        });
        add(Clear);

        JButton ClearTL = new JButton();
        ClearTL.setBounds(220,280,270,20);
        ClearTL.setText("Clear");
        ClearTL.setBackground(new Color(255, 80, 80));
        ClearTL.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        ClearTL.addActionListener(_ -> LOp.setText(""));
        add(ClearTL);

        /* Botones Numero / Operador */

        JButton N7 = new JButton();
        N7.setText("7");
        N7.setBounds(10,100, 50,50);
        N7.setBackground(new Color(255,255,255));
        N7.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        N7.addActionListener(_ -> Operacion.setText(Operacion.getText() + "7"));
        add(N7);

        JButton N8 = new JButton();
        N8.setText("8");
        N8.setBounds(60,100, 50,50);
        N8.setBackground(new Color(255,255,255));
        N8.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        N8.addActionListener(_ -> Operacion.setText(Operacion.getText() + "8"));
        add(N8);

        JButton N9 = new JButton();
        N9.setText("9");
        N9.setBounds(110,100, 50,50);
        N9.setBackground(new Color(255,255,255));
        N9.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        N9.addActionListener(_ -> Operacion.setText(Operacion.getText() + "9"));
        add(N9);

        JButton N4 = new JButton();
        N4.setText("4");
        N4.setBounds(10,150, 50,50);
        N4.setBackground(new Color(255,255,255));
        N4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        N4.addActionListener(_ -> Operacion.setText(Operacion.getText() + "4"));
        add(N4);

        JButton N5 = new JButton();
        N5.setText("5");
        N5.setBounds(60,150, 50,50);
        N5.setBackground(new Color(255,255,255));
        N5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        N5.addActionListener(_ -> Operacion.setText(Operacion.getText() + "5"));
        add(N5);

        JButton N6 = new JButton();
        N6.setText("6");
        N6.setBounds(110,150, 50,50);
        N6.setBackground(new Color(255,255,255));
        N6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        N6.addActionListener(_ -> Operacion.setText(Operacion.getText() + "6"));
        add(N6);

        JButton N1 = new JButton();
        N1.setText("1");
        N1.setBounds(10,200, 50,50);
        N1.setBackground(new Color(255,255,255));
        N1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        N1.addActionListener(_ -> Operacion.setText(Operacion.getText() + "1"));
        add(N1);

        JButton N2 = new JButton();
        N2.setText("2");
        N2.setBounds(60,200, 50,50);
        N2.setBackground(new Color(255,255,255));
        N2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        N2.addActionListener(_ -> Operacion.setText(Operacion.getText() + "2"));
        add(N2);

        JButton N3 = new JButton();
        N3.setText("3");
        N3.setBounds(110,200, 50,50);
        N3.setBackground(new Color(255,255,255));
        N3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        N3.addActionListener(_ -> Operacion.setText(Operacion.getText() + "3"));
        add(N3);

        JButton N0 = new JButton();
        N0.setText("0");
        N0.setBounds(10,250, 100,50);
        N0.setBackground(new Color(255,255,255));
        N0.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        N0.addActionListener(_ -> Operacion.setText(Operacion.getText() + "0"));
        add(N0);

        JButton Coma = new JButton();
        Coma.setText(",");
        Coma.setBounds(110,250, 50,50);
        Coma.setBackground(new Color(255,255,255));
        Coma.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Coma.addActionListener(_ -> Operacion.setText(Operacion.getText() + ","));
        add(Coma);

        JButton Division = new JButton();
        Division.setText("/");
        Division.setBounds(160,100, 50,50);
        Division.setBackground(new Color(255,255,255));
        Division.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Division.addActionListener(_ -> Operacion.setText(Operacion.getText() + " / "));
        add(Division);

        JButton Multiplicacion = new JButton();
        Multiplicacion.setText("*");
        Multiplicacion.setBounds(160,150, 50,50);
        Multiplicacion.setBackground(new Color(255,255,255));
        Multiplicacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Multiplicacion.addActionListener(_ -> Operacion.setText(Operacion.getText() + " * "));
        add(Multiplicacion);

        JButton Resta = new JButton();
        Resta.setText("-");
        Resta.setBounds(160,200, 50,50);
        Resta.setBackground(new Color(255,255,255));
        Resta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Resta.addActionListener(_ -> {
            if (Operacion.getText().matches(".*[+\\-*/] $") || Operacion.getText().matches("^$")){
                Operacion.setText(Operacion.getText() + "-");
            }else {
                Operacion.setText(Operacion.getText() + " - ");
            }
        });
        add(Resta);

        JButton Suma = new JButton();
        Suma.setText("+");
        Suma.setBounds(160,250, 50,50);
        Suma.setBackground(new Color(255,255,255));
        Suma.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Suma.addActionListener(_ -> Operacion.setText(Operacion.getText() + " + "));
        add(Suma);

        JButton BackSpace = new JButton();
        BackSpace.setText("<-");
        BackSpace.setBounds(10,300, 100,50);
        BackSpace.setBackground(new Color(255,255,255));
        BackSpace.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        BackSpace.addActionListener(_ -> {
            if (Operacion.getText().matches(".*-?\\d$")){
                Operacion.setText(Operacion.getText().replaceAll("-?\\d$", ""));
            } else if (Operacion.getText().matches(".* [+\\-*/] ?$")) {
                Operacion.setText(Operacion.getText().replaceAll(" [+\\-*/] ?$", ""));
            } else if (Operacion.getText().matches(".*,$")) {
                Operacion.setText(Operacion.getText().replaceAll(",$", ""));
            } else if (Operacion.getText().matches(".*<ERROR>.*")) {
                Operacion.setText(Operacion.getText().replaceAll(" <ERROR> ", ""));
            }
        });
        add(BackSpace);

        JButton Correct = new JButton();
        Correct.setText("Corregir");
        Correct.setBounds(110,300, 100,50);
        Correct.setBackground(new Color(255,255,255));
        Correct.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Correct.addActionListener(_ -> Operacion.setText(Calc.CorretcOP(Operacion.getText())));
        add(Correct);


        setTitle("RP-Calculadora");
        setBounds(0,0,500,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
