package RP.Calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

public class Calc {

    int Space_Num;
    ArrayList<BigDecimal> Numbers = new ArrayList<>();
    ArrayList<String> Operadores = new ArrayList<>();
    String[] OP_D;
    int PosA = 0;
    String Operacion_O;

    BigDecimal Residual;
    BigDecimal Resultado;
    String ResultadoS;


    public Calc(){}

    public Boolean Comp(String Operacion){
        Operacion = Operacion.replace(",", ".");
        Space_Num = Operacion.length() - Operacion.replace(" ", "").length();

        return (
                Space_Num < 2 ||
                        Operacion.matches(".*[a-zA-Z].*") ||
                        (Operacion.contains(".") && (Operacion.matches(".* [.].*") || Operacion.matches(".* [.] .*") || Operacion.matches(".*[.] .*"))) ||
                        Operacion.matches(".*[.]$") || Operacion.matches("^[.].*") ||
                        !Operacion.matches(".*[+\\-*/].*") ||
                        Operacion.matches(".* {2}.*") ||
                        (Operacion.matches(".* $") || Operacion.matches("^ .*")) ||
                        Operacion.matches(".*[+\\-*/]$") || Operacion.matches("^[+*/].*") ||
                        (!Operacion.matches(".*\\d [+\\-*/] -?\\d.*")) ||
                        (Operacion.matches(".*[+\\-*/][.].*") || Operacion.matches(".*[.][+\\-*/].*")) ||
                        Operacion.matches(".*[+*/]\\d.*") ||
                        Operacion.matches(".*[+\\-*/] [+*/].*") ||
                        Operacion.matches(".*- ?[+*/].*") ||
                        Operacion.matches(".*\\d[+\\-*/].*") ||
                        Operacion.matches(".*[+\\-*/]-.*") ||
                        Operacion.matches(".*\\d -\\d.*") ||
                        Operacion.matches(".*\\d[.]\\d[.].*")
        );
    }

    public String Calculate(String Operacion){

        Operacion = Operacion.replace(",", ".");
        Space_Num /= 2;
        OP_D = Operacion.split(" ");
        for (int N = 0; N < (Space_Num + 1); N++) {
            Numbers.add(new BigDecimal(OP_D[PosA]));
            PosA += 2;
        }PosA = 1;
        for (int O = 0; O < Space_Num; O++) {
            Operadores.add(OP_D[PosA]);
            PosA += 2;
        }PosA = 0;

        /*
        System.out.println(Numbers);
        System.out.println(Operadores);
        */

        int L = Operadores.size();
        for (int i = 0; i <= L; i++){
            if ((i + 1) > Operadores.size()){
                i = (L + 1);
            }else {
                if (Operadores.get(PosA).equalsIgnoreCase("*")){
                    Residual = Numbers.get(i).multiply(Numbers.get(i + 1));
                    Numbers.remove(i + 1);
                    Numbers.set(i, Residual);
                    Operadores.remove(PosA);
                    PosA--; i--;
                } else if (Operadores.get(PosA).equalsIgnoreCase("/")) {
                    Residual = Numbers.get(i).divide(Numbers.get(i + 1), 2, RoundingMode.HALF_UP);
                    Numbers.remove(i + 1);
                    Numbers.set(i, Residual);
                    Operadores.remove(PosA);
                    PosA--; i--;
                }
            }PosA++;
        }PosA = 0; L = Operadores.size();
        for (int i = 0; i <= L; i++){
            if ((i + 1) > Operadores.size()){
                i = (L + 1);
            }else {
                if (Operadores.get(PosA).equalsIgnoreCase("+")){
                    Residual = Numbers.get(i).add(Numbers.get(i + 1));
                    Numbers.remove(i + 1);
                    Numbers.set(i, Residual);
                    Operadores.remove(PosA);
                    PosA--; i--;
                } else if (Operadores.get(PosA).equalsIgnoreCase("-")) {
                    Residual = Numbers.get(i).subtract(Numbers.get(i + 1));
                    Numbers.remove(i + 1);
                    Numbers.set(i, Residual);
                    Operadores.remove(PosA);
                    PosA--; i--;
                }
            }PosA++;
        }PosA = 0;

        Resultado = Numbers.getFirst();
        ResultadoS = String.valueOf(Resultado);
        ResultadoS = ResultadoS.replace(".", ",");

        Numbers.clear();
        Operadores.clear();
        return ResultadoS;
    }

    public String CorretcOP(String Operacion){
        Operacion = Operacion.replace(",", ".");
        Operacion_O = Operacion;
        Space_Num = Operacion.length() - Operacion.replace(" ", "").length();
        /* Logica de la correccion
        Posiblemmente en un Bucle (While) que compruebe si la operacion es valida o no.
        ** Importante que el bucle no se vuelva infinito
        ** Revisar Espacios
        ** Buscar una forma de corregir bien la operacion.
         */

        if (Space_Num < 2 || !Operacion.matches(".*[+\\-*/].*")){
            Operacion = "";
        }else {
            int Count = 0;
            while (Comp(Operacion)){
                Operacion = Operacion.replaceAll("[a-zA-Z]", "");
                if (Operacion.contains(".")){
                    if (Operacion.matches(".*[+\\-*/][.].*") || Operacion.matches(".*[.][+\\-*/].*")){
                        Operacion = Operacion.replaceAll("[+\\-*/][.]", "¡010");
                        Operacion = ErrorCodes(Operacion, Operacion_O, "010");
                    }else if (Operacion.matches(".* [.].*") || Operacion.matches(".* [.] .*") || Operacion.matches(".*[.] .*")){
                        Operacion = Operacion.replaceAll(" ?[.] ?", ".");
                    }
                    Operacion = Operacion.replaceAll("^[.]", ""); Operacion = Operacion.replaceAll("[.]$", "");
                }
                if (Operacion.matches(".*[+\\-*/]  ?[+*/].*")){
                    Operacion = Operacion.replaceAll(" {2}", " ");
                    Operacion = Operacion.replaceAll("[+\\-*/] [+*/]", "¡131");
                }
                Operacion = Operacion.replaceAll(" {2}", "");
                Operacion = Operacion.replaceAll("^ ", ""); Operacion = Operacion.replaceAll(" $", "");
                Operacion = Operacion.replaceAll("^[+*/]", ""); Operacion = Operacion.replaceAll("[+\\-*/]$", "");
                Operacion = Operacion.replaceAll("- ?[+*/]", "");
                if (Operacion.matches(".*[+*/]\\d.*")){
                    Operacion = Operacion.replaceAll("[+*/]\\d", "¡129");
                    Operacion = ErrorCodes(Operacion, Operacion_O, "129");
                }
                if (Operacion.matches(".*\\d[+\\-*/].*")){
                    Operacion = Operacion.replaceAll("\\d[+\\-*/]", "¡219");
                }
                if (Operacion.matches(".*[+\\-*/]-.*")){
                    Operacion = Operacion.replaceAll("[+\\-*/]-", "¡119");
                }
                if (Operacion.matches(".*\\d -\\d.*")){
                    Operacion = Operacion.replaceAll("\\d -\\d", "¡212");
                }
                if (Operacion.matches(".*\\d[.]\\d[.]\\d.*")){
                    Operacion = Operacion.replaceAll("\\d[.]\\d[.]\\d", "¡020");
                }


                if (Count == 100000){
                    break;
                }Count++;
            }
        }

        Operacion = Operacion.replace(".", ",");
        return Operacion;
    }

    ArrayList<String> Operacion_E = new ArrayList<>();
    ArrayList<String> Operacion_E_O = new ArrayList<>();

    public String ErrorCodes(String Operacion, String Operacion_O, String errorCode){

        boolean pass = false;
        StringBuilder OperacionA = new StringBuilder();
        Operacion_E.clear(); Operacion_E.addAll(Arrays.asList(Operacion.split("")));
        Operacion_E_O.clear(); Operacion_E_O.addAll(Arrays.asList(Operacion_O.split("")));

        switch (errorCode){
            case "010":
                for (int i = 0; i < Operacion_E.size(); i++) {
                    if (Operacion_E.get(i).equals("¡")){
                        if (Operacion_E.get(i + 1).equals("0") && Operacion_E.get(i + 2).equals("1") && Operacion_E.get(i + 3).equals("0")){
                            pass = true;
                            for (int j = 0; j < 3; j++) {
                                Operacion_E.remove(i + 1);
                            }
                        }
                    }
                }
                if (pass){
                    for (String s : Operacion_E_O) {
                        OperacionA.append(s);
                    }
                }
                break;
            case "129":
                for (int i = 0; i < Operacion_E.size(); i++) {
                    if (Operacion_E.get(i).equals("¡")){
                        if (Operacion_E.get(i + 1).equals("1") && Operacion_E.get(i + 2).equals("2") && Operacion_E.get(i + 3).equals("9")){
                            pass = true;
                            for (int j = 0; j < 3; j++) {
                                Operacion_E.remove(i + 1);
                            }
                            Operacion_E_O.add((i + 1), " ");
                        }
                    }
                }
                System.out.println(Operacion_E);
                System.out.println(Operacion_E_O);
                if (pass){
                    for (String s : Operacion_E_O) {
                        OperacionA.append(s);
                    }
                }
                break;
            case "131":
                break;
            case "219":
                break;
            case "119":
                break;
            case "212":
                break;
            case "020":
                break;
        }

        return OperacionA.toString();
    }
}
