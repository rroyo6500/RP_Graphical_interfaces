package RP.Tareas;

import RP.Var.Var;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Tar {

    private final Var var = new Var();

    String FileName;
    int Cant_Tar;
    ArrayList<String> Tareas = new ArrayList<>();
    ArrayList<String> ALF = new ArrayList<>();
    String[] AF;
    String[] ATareas;

    public Tar(){}

    public void setFileName(String FileName){
        this.FileName = FileName + ".txt";
    }

    public void setCant_Tar(int Cant_Tar){
        this.Cant_Tar = (Cant_Tar - 1);
    }

    public int getCant_Tar(){
        return Cant_Tar;
    }

    public String CEstado(String Estado){
        if (Estado.equalsIgnoreCase("p")){
            Estado = "Pendiente";
        }else if (Estado.equalsIgnoreCase("ep")){
            Estado = "En Proceso";
        } else if (Estado.equalsIgnoreCase("f")) {
            Estado = "Finalizado";
        }
        return Estado;
    }

    /*
    public String getPath(){
        return var.getPath();
    }
    */

    public String getDirectoryLT(){
        if (var.getPath().matches("^[a-zA-Z]:")){
            return var.getPath() + "\\Files\\ListasDeTareas\\";
        }else {
            return var.getPath() + "/Files/ListasDeTareas/";
        }
    }

    public String ConvertLT(String LTar){
        //LTar = LTar.replace(" | ", "|");
        LTar = LTar.replace("\n", "¡");
        //LTar = LTar.replace(" ", "_");
        LTar = LTar.replaceAll("¡$", "");
        return LTar;
    }

    public void setArrayL(String LTar){
        this.ATareas = LTar.split("¡");
        this.Tareas.addAll(Arrays.asList(ATareas));
    }

    public void Save(String path){
        try {
            File LTareas = new File(path + FileName);
            if (LTareas.createNewFile()){
                System.out.println("File Created: " + FileName);
            }else {
                System.out.println("File " + FileName + " are over writed");
            }
            FileWriter GuardaroT = new FileWriter(path + FileName);
            GuardaroT.write("CTar = " + Cant_Tar + "\n");
            for (int i = 0; i < Cant_Tar; i++){
                GuardaroT.write(Tareas.get(i) + "\n");
            }
            GuardaroT.close();
            Tareas.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Open(String path){
        try {
            FileReader Leer = new FileReader(path + FileName);
            BufferedReader L = new BufferedReader(Leer);
            Cant_Tar = Integer.parseInt(L.readLine().replace("CTar = ", ""));
            for (int i = 0; i < Cant_Tar; i++){
                Tareas.add(L.readLine());
            }
            Leer.close();
        } catch (IOException e) {
            System.out.println("File dosen't exist");
        }
    }

    public String getTar(int Pos){
        return Tareas.get(Pos);
    }

    public void ClearTareas(){
        Tareas.clear();
    }

    public void Delete(String path){
        File file = new File(path + FileName);
        if (file.delete()) {
            System.out.println("Deleted the file: " + FileName);
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    // No se como pero funciona .-.
    public String getDirectoryFiles(String path, int Pos){
        File file = new File(path);
        String[] SFile = file.list();
        StringBuilder files = new StringBuilder();
        for (String s : Objects.requireNonNull(SFile)) {
            files.append("¡").append(s);
        }
        files = new StringBuilder(files.toString().replaceAll("[.]txt$", ""));
        files = new StringBuilder(files.toString().replaceAll("[.]txt¡", "¡"));
        AF = files.toString().split("¡");
        ALF.addAll(Arrays.asList(AF));
        return ALF.get(Pos);
    }

    public int getCant_Files(String path){
        File file = new File(path);
        return Objects.requireNonNull(file.list()).length;
    }

    public void ClearFiles(){
        ALF.clear();
    }

}
