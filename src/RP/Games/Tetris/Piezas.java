package RP.Games.Tetris;

import java.util.ArrayList;

public class Piezas {

    public Piezas(){}

    public ArrayList<ArrayList<Integer>> getPart(int NoPieza, int NoRotacion){
        return Piezas_.get(NoPieza).get(NoRotacion);
    }

    public Integer CantPiezas(){
        return Piezas_.size();
    }

    public Integer CantRPieza(int NoPieza){
        return Piezas_.get(NoPieza).size();
    }

    public static ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> Piezas_ = new ArrayList<>(){{
        // Cuadrado
        add(new ArrayList<>() {{
            //Rotacion 0
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(1);add(1);}});
                add(new ArrayList<>() {{add(1);add(1);}});
            }});
        }});
        // Z
        add(new ArrayList<>() {{
            //Rotacion 0
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(3);add(3);add(0);}});
                add(new ArrayList<>() {{add(0);add(3);add(3);}});
            }});
            //Rotacion 1
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(3);}});
                add(new ArrayList<>() {{add(3);add(3);}});
                add(new ArrayList<>() {{add(3);add(0);}});
            }});
        }});
        // Z (Invertida)
        add(new ArrayList<>() {{
            //Rotacion 0
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(5);add(5);}});
                add(new ArrayList<>() {{add(5);add(5);add(0);}});
            }});
            //Rotacion 1
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(5);add(0);}});
                add(new ArrayList<>() {{add(5);add(5);}});
                add(new ArrayList<>() {{add(0);add(5);}});
            }});
        }});
        // T Corta
        add(new ArrayList<>() {{
            //Rotacion 0
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(7);add(0);}});
                add(new ArrayList<>() {{add(7);add(7);add(7);}});
            }});
            //Rotacion 1
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(7);add(0);}});
                add(new ArrayList<>() {{add(7);add(7);}});
                add(new ArrayList<>() {{add(7);add(0);}});
            }});
            //Rotacion 2
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(7);add(7);add(7);}});
                add(new ArrayList<>() {{add(0);add(7);add(0);}});
            }});
            //Rotacion 3
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(7);}});
                add(new ArrayList<>() {{add(7);add(7);}});
                add(new ArrayList<>() {{add(0);add(7);}});
            }});
        }});
        // L
        add(new ArrayList<>() {{
            //Rotacion 0
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(9);add(0);}});
                add(new ArrayList<>() {{add(9);add(0);}});
                add(new ArrayList<>() {{add(9);add(9);}});
            }});
            //Rotacion 1
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(9);add(9);add(9);}});
                add(new ArrayList<>() {{add(9);add(0);add(0);}});
            }});
            //Rotacion 2
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(9);add(9);}});
                add(new ArrayList<>() {{add(0);add(9);}});
                add(new ArrayList<>() {{add(0);add(9);}});
            }});
            //Rotacion 3
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(0);add(9);}});
                add(new ArrayList<>() {{add(9);add(9);add(9);}});
            }});
        }});
        // L (Invertida)
        add(new ArrayList<>() {{
            //Rotacion 0
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(11);}});
                add(new ArrayList<>() {{add(0);add(11);}});
                add(new ArrayList<>() {{add(11);add(11);}});
            }});
            //Rotacion 1
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(11);add(0);add(0);}});
                add(new ArrayList<>() {{add(11);add(11);add(11);}});
            }});
            //Rotacion 2
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(11);add(11);}});
                add(new ArrayList<>() {{add(11);add(0);}});
                add(new ArrayList<>() {{add(11);add(0);}});
            }});
            //Rotacion 3
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(11);add(11);add(11);}});
                add(new ArrayList<>() {{add(0);add(0);add(11);}});
            }});
        }});
        // I
        add(new ArrayList<>() {{
            //Rotacion 0
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(13);}});
                add(new ArrayList<>() {{add(13);}});
                add(new ArrayList<>() {{add(13);}});
                add(new ArrayList<>() {{add(13);}});
            }});
            //Rotacion 1
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(13);add(13);add(13);add(13);}});
            }});
        }});
    }};
}