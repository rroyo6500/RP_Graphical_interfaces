package RP.Tareas;
import RP.Main.Main;
import RP.OBJ.Tar;

import javax.swing.*;
import java.awt.*;

public class Tareas extends JFrame{
    public Tareas(){

        Tar tar = new Tar();

        setLayout(null);

        /* Title & Return to Main Menu */{
            JPanel TTitle = new JPanel();
            TTitle.setBounds(-1,-1,502,36);
            TTitle.setLayout(null);
            TTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            add(TTitle);

            JLabel Title = new JLabel();
            Title.setFont(new Font("Arial", Font.BOLD, 20));
            Title.setText("RP - Tareas");
            Title.setBounds(10, 10, 160, 20);
            TTitle.add(Title);

            JButton MMenu = new JButton();
            MMenu.setBounds(390, 10, 100, 20);
            MMenu.setFont(new Font("Arial", Font.BOLD, 10));
            MMenu.setText("Main Menu");
            MMenu.setBackground(new Color(255,255,255));
            MMenu.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
            MMenu.addActionListener(_ -> {
                System.out.println("RP - Main Menu");
                this.setVisible(false);
                Main.main(null);

            });
            TTitle.add(MMenu);
        }

        // Lista de Tareas
        JTextArea LTareas = new JTextArea();
        JScrollPane SLTareas = new JScrollPane(LTareas);
        SLTareas.setBounds(10,150,480,300);
        SLTareas.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        SLTareas.setBackground(new Color(255,255,255));
        add(SLTareas);

        // Tareas
        JTextField TTarea = new JTextField();
        TTarea.setBounds(10,100,230,20);
        TTarea.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        TTarea.setBackground(new Color(255,255,255));
        add(TTarea);

        JTextField ETarea = new JTextField();
        ETarea.setBounds(260,100,230,20);
        ETarea.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        ETarea.setBackground(new Color(255,255,255));
        add(ETarea);

        JLabel Separador = new JLabel();
        Separador.setBounds(245,98,20,20);
        Separador.setFont(new Font("Arial", Font.BOLD, 20));
        Separador.setText("|");
        add(Separador);

        JButton Add = new JButton();
        Add.setBounds(10,125,480,20);
        Add.setText("Añadir");
        Add.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        Add.setBackground(new Color(80,255,80));
        Add.addActionListener(_ -> {
            LTareas.setText(LTareas.getText() + TTarea.getText() + " | " + tar.CEstado(ETarea.getText()) + "\n");
            TTarea.setText("");
            ETarea.setText("");
        });
        add(Add);

        // Archivo
        JTextField AName = new JTextField();
        AName.setBounds(10,40,480,20);
        AName.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        AName.setBackground(new Color(255,255,255));
        add(AName);

        JButton Save = new JButton();
        Save.setBounds(10,60,100,20);
        Save.setText("Guardar");
        Save.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        Save.setBackground(new Color(80,255,80));
        Save.addActionListener(_ -> {
            tar.setFileName(AName.getText());
            tar.setCant_Tar(LTareas.getLineCount());
            tar.setArrayL(tar.ConvertLT(LTareas.getText()));
            tar.Save(tar.getDirectoryLT());
        });
        add(Save);

        JButton Open = new JButton();
        Open.setBounds(110,60,100,20);
        Open.setText("Abrir");
        Open.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        Open.setBackground(new Color(0, 255, 184));
        Open.addActionListener(_ -> {
            LTareas.setText("");
            tar.setFileName(AName.getText());
            tar.Open(tar.getDirectoryLT());
            try {
                for (int i = 0; i < tar.getCant_Tar(); i++) {
                    LTareas.setText(LTareas.getText() + tar.getTar(i) + "\n");
                }
                tar.ClearTareas();
            } catch (Exception e) {
                System.out.print("");
            }
        });
        add(Open);

        JButton FileList = new JButton();
        FileList.setBounds(210,60,150,20);
        FileList.setText("Lista de Archivos");
        FileList.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        FileList.setBackground(new Color(0, 255, 184));
        FileList.addActionListener(_ -> {
            FileList FL = new FileList();
            FL.setVisible(true);
        });
        add(FileList);

        JButton Remove = new JButton();
        Remove.setBounds(360,60,100,20);
        Remove.setText("Eliminar");
        Remove.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        Remove.setBackground(new Color(255, 80, 80));
        Remove.addActionListener(_ -> {
            tar.setFileName(AName.getText());
            tar.Delete(tar.getDirectoryLT());
            AName.setText("");
            LTareas.setText("");
        });
        add(Remove);

        JButton Clear = new JButton();
        Clear.setBounds(460,60,30,20);
        Clear.setText("C");
        Clear.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        Clear.setBackground(new Color(255, 80, 80));
        Clear.addActionListener(_ -> {
            AName.setText("");
            LTareas.setText("");
        });
        add(Clear);


        setTitle("RP-Tareas");
        setBounds(0,0,500,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}