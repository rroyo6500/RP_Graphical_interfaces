package RP.Tareas;

import RP.Var.Var;

import javax.swing.*;
import java.awt.*;

public class FileList extends JFrame{

    Tar tar = new Tar();
    Var var = new Var();

    public FileList(){
        this.setLayout(null);

        JTextArea Files = new JTextArea();
        Files.setFont(new Font("Arial", Font.BOLD, 15));
        JScrollPane SFiles = new JScrollPane(Files);
        SFiles.setBounds(10,10,480,380);
        SFiles.setBackground(new Color(255,255,255));
        SFiles.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        // No se como pero funciona .-.
        Files.setText(tar.getDirectoryFiles(tar.getDirectoryLT(), 0));
        for (int i = 1; i <= tar.getCant_Files(tar.getDirectoryLT()); i++){
            Files.setText(Files.getText() + " - " + tar.getDirectoryFiles(tar.getDirectoryLT(), i) + "\n");
        }
        tar.ClearFiles();
        Files.setEditable(false);
        add(SFiles);

        JButton Accept = new JButton();
        Accept.setBounds(190,410,120,40);
        Accept.setFont(new Font("Arial", Font.BOLD, 15));
        Accept.setText("Aceptar");
        Accept.setBackground(new Color(255,255,255));
        Accept.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        Accept.addActionListener(_ -> this.setVisible(false));
        add(Accept);


        JButton Update = new JButton();
        Update.setBounds(320,410,40,40);
        Update.setFont(new Font("Arial", Font.BOLD, 15));
        Update.setBackground(new Color(255,255,255));
        Update.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        Update.setText("\uD83D\uDDD8");
        Update.addActionListener(_ -> {
            Files.setText("");
            Files.setText(tar.getDirectoryFiles(tar.getDirectoryLT(), 0));
            for (int i = 1; i <= tar.getCant_Files(tar.getDirectoryLT()); i++){
                Files.setText(Files.getText() + " - " + tar.getDirectoryFiles(tar.getDirectoryLT(), i) + "\n");
            }
            tar.ClearFiles();
        });
        add(Update);

        this.setTitle("RP-Tareas - FileList");
        this.setBounds(0,0,var.Width(500),var.Height(500));
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
