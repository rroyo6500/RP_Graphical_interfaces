package RP.Var;

import javax.swing.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
//import java.util.Scanner;

public class Var extends JFrame{

    //public static Scanner in = new Scanner(System.in);

    static Path currentDirectoryPath = FileSystems.getDefault().getPath("");
    public static String path = currentDirectoryPath.toAbsolutePath().toString();

    // Paneles
    public static JPanel Calc_ = new JPanel();
    public static JPanel Tar_ = new JPanel();

    public Var(){}

    public String getPath(){
        return path;
    }
}
