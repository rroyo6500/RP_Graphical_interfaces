package RP.Games.Tetris;

import java.util.ArrayList;

public class Logica {

    public Logica(){}

    ArrayList<ArrayList<Integer>> Tablero;

    public boolean NewPart(int x, ArrayList<ArrayList<Integer>> Tablero, ArrayList<ArrayList<Integer>> Pieza){
        // Ubicado dentro de un while para que en caso de que la pieza no entre en la posicion seleccionada aleatoriamente 'X' se seleccione una pieza y posicion diferente.

        this.Tablero = Tablero;
        return true;
    }

    public ArrayList<ArrayList<Integer>> UpdateTablero(){
        return Tablero;
    }

}
