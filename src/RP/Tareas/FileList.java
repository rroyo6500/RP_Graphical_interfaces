package RP.Tareas;

import RP.OBJ.Tar;

import javax.swing.*;
import java.awt.*;

public class FileList extends JFrame{

    Tar tar = new Tar();

    public FileList(){
        setLayout(null);

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

        setTitle("RP-Tareas - FileList");
        setBounds(0,0,500,500);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }

}
