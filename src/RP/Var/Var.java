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

    // Window Size

    public Var(){}

    public String getPath(){
        return path;
    }

    public int Width(int Width){
        if (path.matches("[a-zA-Z]:.*")){
            return Width + 20;
        }else {
            return Width;
        }
    }
    public int Height(int Height){
        return Height;
    }

}