package RP.Games.Tetris;

import java.util.ArrayList;

public class Logica {

    Piezas piezas = new Piezas();

    public Logica(){}

    int Repeticiones = 0;

    /*
        Rotar Piezas:
            Idea:
                Localizar las partes de las piezas que se pueden mover y comparar con la pieza para conseguir la esquena superior izquierda.
                Una vez conseguida se eliminan las partes le pa pieza en movimiento y se reescriben con la nueva rotacion.
            IMPORTANTE:
                Compobar si la posicion es un bloque estatico o no (unicamente si el bloque ovservado en la nueva rotacion != 0)

                        if (NoRotacion == piezas.CantRPieza(NoPieza)) NoPieza = 0;

                        ArrayList<ArrayList<Integer>> NewPartRotation = piezas.getPart(NoPieza, NoRotacion);
    */
    public int RotatePart(ArrayList<ArrayList<Integer>> Tablero, int NoPieza, int NoRotacion){
        boolean Pass = true;

        int RotacionRes;

        int[] PosPart = new int[2]; // 0 -> Y | 1 -> X
        int SFFB = 0; //SpacesForFirstBlock

        for (int i = 0; i < Tablero.size(); i++) {
            for (int j = 0; j < Tablero.getFirst().size(); j++) {
                if ((Tablero.get(i).get(j) % 2) == 1){
                    PosPart[0] = i;
                    PosPart[1] = j;
                    i = Tablero.size();
                    break;
                }
            }
        }
        for (int i = 0; i < piezas.getPart(NoPieza, NoRotacion).size(); i++) {
            for (int j = 0; j < piezas.getPart(NoPieza, NoRotacion).getFirst().size(); j++) {
                if (piezas.getPart(NoPieza, NoRotacion).get(i).get(j) == 0) SFFB++;
                else {
                    i = piezas.getPart(NoPieza, NoRotacion).size();
                    break;
                }
            }
        }PosPart[1] -= SFFB;

        RotacionRes = NoRotacion;
        if (NoRotacion >= (piezas.CantRPieza(NoPieza) - 1)) NoRotacion = 0;
        else NoRotacion++;

        for (int i = 0; i < piezas.getPart(NoPieza, NoRotacion).size(); i++) {
            for (int j = 0; j < piezas.getPart(NoPieza, NoRotacion).getFirst().size(); j++) {
                try {
                    if ((Tablero.get((i + PosPart[0])).get((j + PosPart[1])) % 2) == 0 && Tablero.get((i + PosPart[0])).get((j + PosPart[1])) != 0) {
                        Pass = false;
                        i = piezas.getPart(NoPieza, NoRotacion).size();
                        NoRotacion = RotacionRes;
                        break;
                    }
                } catch (Exception e) {
                    Pass = false;
                    i = piezas.getPart(NoPieza, NoRotacion).size();
                    NoRotacion = RotacionRes;
                    break;
                }
            }
        }

        if (Pass){
            for (int i = (Tablero.size() - 1); i >= 0; i--) {
                for (int j = (Tablero.getFirst().size() - 1); j >= 0; j--) {
                    if ((Tablero.get(i).get(j) % 2) == 1) {
                        Tablero.get(i).set(j, 0);
                    }
                }
            }
            for (int i = 0; i < piezas.getPart(NoPieza, NoRotacion).size(); i++) {
                for (int j = 0; j < piezas.getPart(NoPieza, NoRotacion).getFirst().size(); j++) {
                    if (piezas.getPart(NoPieza, NoRotacion).get(i).get(j) != 0){
                        Tablero.get((i+PosPart[0])).set((j+PosPart[1]), piezas.getPart(NoPieza, NoRotacion).get(i).get(j));
                    }
                }
            }
        }

        return NoRotacion;
    }

    public boolean NewPart(ArrayList<ArrayList<Integer>> Tablero, int x, ArrayList<ArrayList<Integer>> Pieza){
        boolean Return = false;
        int C_0 = 0;
        if (CompFilas(Tablero)){
            if ((Tablero.getFirst().size() - x) < Pieza.getFirst().size()) Return = true;
            else {
                for (int i = 0; i < Pieza.size(); i++) {
                    for (int j = 0; j < Pieza.getFirst().size(); j++) {
                        if (Tablero.get(i).get(j + x) != 0) {
                            C_0++;
                        }
                    }
                }
                if (C_0 > 0) {
                    if (Repeticiones > 25){
                        Tablero_Restart(Tablero);
                    }else {
                        Return = true;
                        Repeticiones++;
                    }
                }
                else {
                    for (int i = 0; i < Pieza.size(); i++) {
                        for (int j = 0; j < Pieza.getFirst().size(); j++) {
                            Tablero.get(i).set((j + x), Pieza.get(i).get(j));
                        }
                    }
                }
            }
        }
        return Return;
    }

    public boolean CompFilas(ArrayList<ArrayList<Integer>> Tablero){
        boolean Return = true;
        ArrayList<Integer> Residual = new ArrayList<>();

        for (int i = (Tablero.size() - 1); i >= 0 ; i--) {
            for (int j = 0; j < Tablero.get(i).size(); j++) {
                if ((Tablero.get(i).get(j) % 2) == 0 && Tablero.get(i).get(j) != 0) Residual.add(0);
                else break;
            }
            if (Residual.size() == CompFTablero.size()){
                for (int j = i; j >= 0; j--) {
                    for (int k = 0; k < Tablero.getFirst().size(); k++) {
                        try {
                            Tablero.get(j).set(k, Tablero.get((j-1)).get(k));
                        } catch (Exception e) {
                            Tablero.get(j).set(k, 0);
                        }
                    }
                }
                i++;
                Return = false;
            }
            Residual.clear();
        }
        return Return;
    }

    public void Tablero_Restart(ArrayList<ArrayList<Integer>> Tablero){
        for (int i = 0; i < Tablero.size(); i++) {
            for (int j = 0; j < Tablero.getFirst().size(); j++) {
                Tablero.get(i).set(j, 0);
            }
        }
    }

    public void PartDown(ArrayList<ArrayList<Integer>> Tablero){
        int Residual;
        if (CompDownR(Tablero) || CompDownL(Tablero)) {
            for (int i = (Tablero.size() - 1); i >= 0; i--) {
                for (int j = (Tablero.getFirst().size() - 1); j >= 0; j--) {
                    if ((Tablero.get(i).get(j) % 2) == 1) {
                        Tablero.get(i).set(j, (Tablero.get(i).get(j) + 1));
                    }
                }
            }
        } else {
            for (int i = (Tablero.size() - 1); i >= 0; i--) {
                for (int j = (Tablero.getFirst().size() - 1); j >= 0; j--) {
                    if ((Tablero.get(i).get(j) % 2) == 1) {
                        if (Tablero.get((i+1)).get(j) == 0){
                            Residual = Tablero.get(i).get(j);
                            Tablero.get(i).set(j, 0);
                            Tablero.get((i + 1)).set(j, Residual);
                        }
                    }
                }
            }
        }
    }

    public void PartRight(ArrayList<ArrayList<Integer>> Tablero){
        if (CompRightD(Tablero) && CompRightU(Tablero)){
            for (int i = (Tablero.size() - 1); i >= 0 ; i--) {
                for (int j = (Tablero.getFirst().size() - 1); j >= 0 ; j--) {
                    if ((Tablero.get(i).get(j) % 2) == 1){
                        if (((Tablero.get(i).get((j + 1))) == 0)){
                            Tablero.get(i).set((j + 1), Tablero.get(i).get(j));
                            Tablero.get(i).set(j, 0);
                        }
                    }
                }
            }
        }
    }

    public void PartLeft(ArrayList<ArrayList<Integer>> Tablero){
        if (CompLeftD(Tablero) && CompLeftU(Tablero)){
            for (int i = (Tablero.size() - 1); i >= 0 ; i--) {
                for (int j = 0; j < Tablero.getFirst().size() ; j++) {
                    if ((Tablero.get(i).get(j) % 2) == 1){
                        if (((Tablero.get(i).get((j - 1))) == 0)){
                            Tablero.get(i).set((j - 1), Tablero.get(i).get(j));
                            Tablero.get(i).set(j, 0);
                        }
                    }
                }
            }
        }
    }

    public boolean CompDownL(ArrayList<ArrayList<Integer>> Tablero){
        boolean Return = false;

        for (int i = (Tablero.size() - 1); i >= 0 ; i--) {
            for (int j = 0; j < Tablero.getFirst().size() ; j++) {
                if ((Tablero.get(i).get(j) % 2) == 1){
                    try {
                        if ((Tablero.get((i+1)).get(j) % 2) == 0 && Tablero.get((i+1)).get(j) != 0){
                            Return = true;
                        }
                    } catch (Exception e) {
                        Return = true;
                    }
                }
            }
        }

        return Return;
    }

    public boolean CompDownR(ArrayList<ArrayList<Integer>> Tablero){
        boolean Return = false;

        for (int i = (Tablero.size() - 1); i >= 0 ; i--) {
            for (int j = (Tablero.getFirst().size() - 1); j >= 0 ; j--) {
                if ((Tablero.get(i).get(j) % 2) == 1){
                    try {
                        if ((Tablero.get((i+1)).get(j) % 2) == 0 && Tablero.get((i+1)).get(j) != 0){
                            Return = true;
                        }
                    } catch (Exception e) {
                        Return = true;
                    }
                }
            }
        }

        return Return;
    }

    public boolean CompLeftU(ArrayList<ArrayList<Integer>> Tablero){
        boolean Return = false;

        for (int i = 0; i < Tablero.size() ; i++) {
            for (int j = 0; j < Tablero.getFirst().size() ; j++) {
                if ((Tablero.get(i).get(j) % 2) == 1){
                    try {
                        if ((Tablero.get(i).get((j-1)) % 2) == 0 && Tablero.get(i).get((j-1)) != 0){
                            Return = false;
                            i = Tablero.size();
                            j = Tablero.getFirst().size();
                        }else {
                            Return = (Tablero.get(i).get((j-1)) == 0 || (Tablero.get(i).get((j-1)) % 2) == 1);
                        }
                    } catch (Exception e) {
                        Return = false;
                        i = Tablero.size();
                        j = Tablero.getFirst().size();
                    }
                }
            }
        }

        return Return;
    }

    public boolean CompLeftD(ArrayList<ArrayList<Integer>> Tablero){
        boolean Return = false;

        for (int i = (Tablero.size() - 1); i >= 0 ; i--) {
            for (int j = 0; j < Tablero.getFirst().size() ; j++) {
                if ((Tablero.get(i).get(j) % 2) == 1){
                    try {
                        if ((Tablero.get(i).get((j-1)) % 2) == 0 && Tablero.get(i).get((j-1)) != 0){
                            Return = false;
                            i = -1;
                            j = Tablero.getFirst().size();
                        }else {
                            Return = (Tablero.get(i).get((j-1)) == 0 || (Tablero.get(i).get((j-1)) % 2) == 1);
                        }
                    } catch (Exception e) {
                        Return = false;
                        i = -1;
                        j = Tablero.getFirst().size();
                    }
                }
            }
        }

        return Return;
    }

    public boolean CompRightU(ArrayList<ArrayList<Integer>> Tablero){
        boolean Return = false;

        for (int i = 0; i < Tablero.size() ; i++) {
            for (int j = (Tablero.getFirst().size() - 1); j >= 0 ; j--) {
                if ((Tablero.get(i).get(j) % 2) == 1){
                    try {
                        if ((Tablero.get(i).get((j+1)) % 2) == 0 && Tablero.get(i).get((j+1)) != 0){
                            Return = false;
                            i = Tablero.size();
                            j = -1;
                        }else {
                            Return = (Tablero.get(i).get((j+1)) == 0 || (Tablero.get(i).get((j+1)) % 2) == 1);
                        }
                    } catch (Exception e) {
                        Return = false;
                        i = Tablero.size();
                        j = -1;
                    }
                }
            }
        }

        return Return;
    }

    public boolean CompRightD(ArrayList<ArrayList<Integer>> Tablero){
        boolean Return = false;

        for (int i = (Tablero.size() - 1); i >= 0 ; i--) {
            for (int j = (Tablero.getFirst().size() - 1); j >= 0 ; j--) {
                if ((Tablero.get(i).get(j) % 2) == 1){
                    try {
                        if ((Tablero.get(i).get((j+1)) % 2) == 0 && Tablero.get(i).get((j+1)) != 0){
                            Return = false;
                            i = -1;
                            j = -1;
                        }else {
                            Return = (Tablero.get(i).get((j+1)) == 0 || (Tablero.get(i).get((j+1)) % 2) == 1);
                        }
                    } catch (Exception e) {
                        Return = false;
                        i = -1;
                        j = -1;
                    }
                }
            }
        }

        return Return;
    }

    public static ArrayList<Integer> CompFTablero = new ArrayList<>() {{
        for (int i = 0; i < 15; i++) {
            add(0);
        }
    }};
}
