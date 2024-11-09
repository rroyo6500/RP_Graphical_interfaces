package RP.Calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Calc {

    int Space_Num;
    ArrayList<BigDecimal> Numbers = new ArrayList<>();
    ArrayList<String> Operadores = new ArrayList<>();
    String[] OP_D;
    int PosA = 0;

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
        /* Logica de la correccion
        Posiblemmente en un Bucle (While) que compruebe si la operacion es valida o no.
        ** Importante que el bucle no se vuelva infinito
        ** Revisar Espacios
        ** Buscar una forma de corregir bien la operacion.
         */

        Operacion = Operacion.replace(".", ",");
        return Operacion;
    }

}
