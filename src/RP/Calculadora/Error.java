package RP.Calculadora;

import RP.Var.Var;

import javax.swing.*;
import java.awt.*;

public class Error extends JFrame {

    Var var = new Var();

    public Error(){
        setLayout(null);

        JLabel L1 = new JLabel(), L2 = new JLabel(), L3 = new JLabel(), L4 = new JLabel(), L5 = new JLabel(), L6 = new JLabel(), L7 = new JLabel(), L8 = new JLabel(), L9 = new JLabel(), L10 = new JLabel(), L11 = new JLabel();

        L1.setBounds(10,10,480,15);
        L1.setText("ERROR: La operacion debe cumplir las siguientes condiciones");
        add(L1);

        L2.setBounds(10,25,480,15);
        L2.setText("    > Debe tener espacios entre Numero y Operador");
        add(L2);

        L3.setBounds(10,40,480,15);
        L3.setText("        ✓: 2 + 2");
        add(L3);

        L4.setBounds(10,55,480,15);
        L4.setText("        x: 2+2 | 2 +2");
        add(L4);

        L5.setBounds(10,70,480,15);
        L5.setText("    > Debe contener almenos 2 numeros");
        add(L5);

        L6.setBounds(10,85,480,15);
        L6.setText("        ✓: 2 + 2");
        add(L6);

        L7.setBounds(10,100,480,15);
        L7.setText("    > Debe contener los alguno de los siguientes sumbolos: + | - | * | /");
        add(L7);

        L8.setBounds(10,115,600,15);
        L8.setText("    > No puede contener caracteres que no sean '+, -, *, /' o Numeros (Decimmales con ' . ' o ' , ')");
        add(L8);

        L9.setBounds(10,130,480,15);
        L9.setText("    > Los ' . ' o ' , ' (Separadores de decimales) no pueden estar 'Solos'");
        add(L9);

        L10.setBounds(10,145,600,15);
        L10.setText("    > ERROR: La operacion no puede terminar con un operador (+, -, *, /) o con un espacio");
        add(L10);

        L11.setBounds(10,160,480,15);
        L11.setText("    > No puede tener espacios de mas");
        add(L11);

        JButton Accept = new JButton();
        Accept.setBounds(240,200,120,40);
        Accept.setFont(new Font("Arial", Font.BOLD, 15));
        Accept.setText("Aceptar");
        Accept.setBackground(new Color(255,255,255));
        Accept.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        Accept.addActionListener(_ -> this.setVisible(false));
        add(Accept);

        setTitle("RP-Calculadora (ERROR)");
        setBounds(0,0, var.Width(600), var.Height(300));
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
