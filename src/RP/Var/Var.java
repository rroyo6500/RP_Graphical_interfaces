package RP.Var;

import java.nio.file.FileSystems;
import java.nio.file.Path;
//import java.util.Scanner;

public class Var {

    //public static Scanner in = new Scanner(System.in);

    static Path currentDirectoryPath = FileSystems.getDefault().getPath("");
    public static String path = currentDirectoryPath.toAbsolutePath().toString();

    public Var(){}

    public String getPath(){
        return path;
    }
}
