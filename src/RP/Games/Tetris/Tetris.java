package RP.Games.Tetris;

import RP.Var.Var;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tetris extends JFrame {

    Var var = new Var();

    int Velocidad = 0;

    public static int NoPieza;
    public static int NoRotacion;

    JPanel Tetoris, GamesMenu;
    JFrame TableroJuego = new JFrame() {{
        setBounds(0, 0, var.Width(500), var.Height(1020));
    }};

    public static JPanel Tablero;

    public Tetris(JPanel GamePanel, JPanel GamesMenu) {
        this.Tetoris = GamePanel;
        this.GamesMenu = GamesMenu;
    }

    public void TetrisGameControl() {
        /* Title & Return to Main Menu */
        {
            JPanel Tetoris_Title = new JPanel();
            Tetoris_Title.setBounds(-1, -1, Tetoris.getWidth() + 2, 36);
            Tetoris_Title.setLayout(null);
            Tetoris_Title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            Tetoris.add(Tetoris_Title);

            JLabel Title = new JLabel();
            Title.setFont(new Font("Arial", Font.BOLD, 20));
            Title.setText("Tic Tac Toe");
            Title.setBounds(10, 10, 300, 20);
            Tetoris_Title.add(Title);

            JButton MMenu = new JButton();
            MMenu.setBounds(390, 10, 100, 20);
            MMenu.setFont(new Font("Arial", Font.BOLD, 10));
            MMenu.setText("Games Menu");
            MMenu.setBackground(new Color(255, 255, 255));
            MMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            MMenu.addActionListener(_ -> {
                //ClearOP();
                Tetoris.setVisible(false);
                GamesMenu.setVisible(true);
                TableroJuego.setVisible(false);
            });
            Tetoris_Title.add(MMenu);
        }

        JButton Down = new JButton();
        Down.setBounds(100, 100, 100, 50);
        Down.setText("Down");
        Down.addActionListener(_ -> MoveDown());
        Tetoris.add(Down);

        JButton Right = new JButton();
        Right.setBounds(200, 100, 100, 50);
        Right.setText("Right");
        Right.addActionListener(_ -> MoveRight());
        Tetoris.add(Right);

        JButton Left = new JButton();
        Left.setBounds(300, 100, 100, 50);
        Left.setText("Left");
        Left.addActionListener(_ -> MoveLeft());
        Tetoris.add(Left);

        TableroJuego(TableroJuego);
        TableroJuego.setVisible(true);
    }

    public static ArrayList<ArrayList<Integer>> FC_V = new ArrayList<>() {{
        add(new ArrayList<>() {{
            for (int j = 0; j < 15; j++) {
                add(2);
            }
        }});
        add(new ArrayList<>() {{
            for (int j = 0; j < 15; j++) {
                add(0);
            }
        }});
    }};
    public static ArrayList<ArrayList<Integer>> Tablero_ = new ArrayList<>() {{
        for (int i = 0; i < 30; i++) {
            add(new ArrayList<>() {{
                for (int j = 0; j < 15; j++) {
                    add(0);
                }
            }});
        }
    }};

    public static ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> Piezas = new ArrayList<>() {{
        // Cuadrado
        add(new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(1);add(1);}});
                add(new ArrayList<>() {{add(1);add(1);}});
            }});
        }});
        // Z
        add(new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(3);add(3);add(0);}});
                add(new ArrayList<>() {{add(0);add(3);add(3);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(3);}});
                add(new ArrayList<>() {{add(3);add(3);}});
                add(new ArrayList<>() {{add(3);add(0);}});
            }});
        }});
        // Z (Invertida)
        add(new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(5);add(5);}});
                add(new ArrayList<>() {{add(5);add(5);add(0);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(5);add(0);}});
                add(new ArrayList<>() {{add(5);add(5);}});
                add(new ArrayList<>() {{add(0);add(5);}});
            }});
        }});
        // T (Invertida)
        add(new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(7);add(0);}});
                add(new ArrayList<>() {{add(7);add(7);add(7);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(7);add(0);}});
                add(new ArrayList<>() {{add(7);add(7);}});
                add(new ArrayList<>() {{add(7);add(0);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(7);add(7);add(7);}});
                add(new ArrayList<>() {{add(0);add(7);add(0);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(7);}});
                add(new ArrayList<>() {{add(7);add(7);}});
                add(new ArrayList<>() {{add(0);add(7);}});
            }});
        }});
        // L
        add(new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(9);add(0);}});
                add(new ArrayList<>() {{add(9);add(0);}});
                add(new ArrayList<>() {{add(9);add(9);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(9);add(9);add(9);}});
                add(new ArrayList<>() {{add(9);add(0);add(0);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(9);add(9);}});
                add(new ArrayList<>() {{add(0);add(9);}});
                add(new ArrayList<>() {{add(0);add(9);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(0);add(9);}});
                add(new ArrayList<>() {{add(9);add(9);add(9);}});
            }});
        }});
        // L (Invertida)
        add(new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(0);add(11);}});
                add(new ArrayList<>() {{add(0);add(11);}});
                add(new ArrayList<>() {{add(11);add(11);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(11);add(0);add(0);}});
                add(new ArrayList<>() {{add(11);add(11);add(11);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(11);add(11);}});
                add(new ArrayList<>() {{add(11);add(0);}});
                add(new ArrayList<>() {{add(11);add(0);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(11);add(11);add(11);}});
                add(new ArrayList<>() {{add(0);add(0);add(11);}});
            }});
        }});
        // I
        add(new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(13);}});
                add(new ArrayList<>() {{add(13);}});
                add(new ArrayList<>() {{add(13);}});
                add(new ArrayList<>() {{add(13);}});
            }});
            add(new ArrayList<>() {{
                add(new ArrayList<>() {{add(13);add(13);add(13);add(13);}});
            }});
        }});
    }};

    public void TableroJuego(JFrame TableroJuego) {
        TableroJuego.setLayout(null);
        TableroJuego.setLocationRelativeTo(null);
        TableroJuego.setResizable(false);

        for (ArrayList<Integer> Filas : Tablero_){
            System.out.println(Filas);
        }

        Tablero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int x = 10, y = 10;

                if (Tablero_.size() > 1){
                    for (int i = (Tablero_.size() - 1); i >= 0; i--) {
                        int CountEquals = 0;
                        for (int j = 0; j < Tablero_.getFirst().size(); j++) {
                            if ((Tablero_.get(i).get(j) % 2) == 0 && Tablero_.get(i).get(j) != 0){
                                CountEquals++;
                            }
                        }
                        if (CountEquals >= Tablero_.getFirst().size()){
                            Tablero_.remove(i);
                            Tablero_.addFirst(FC_V.get(1));
                        }
                    }
                }

                int Count_1 = 0;
                for (int i = 0; i < Tablero_.size(); i++) {
                    for (int j = 0; j < Tablero_.getFirst().size(); j++) {
                        if (((Tablero_.get(i).get(j) % 2) == 1)) {
                            Count_1++;
                        }
                    }
                }
                if (Count_1 < 1) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Ficha();
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                try {
                    for (ArrayList<Integer> Filas : Tablero_) {
                        for (Integer Col : Filas) {
                            if (Col == 0) g.setColor(Color.GRAY);
                            if (Col == 1 || Col == 2) g.setColor(Color.RED);
                            if (Col == 3 || Col == 4) g.setColor(Color.BLUE);
                            if (Col == 5 || Col == 6) g.setColor(Color.GREEN);
                            if (Col == 7 || Col == 8) g.setColor(Color.MAGENTA);
                            if (Col == 9 || Col == 10) g.setColor(Color.YELLOW);
                            if (Col == 11 || Col == 12) g.setColor(Color.CYAN);
                            if (Col == 13 || Col == 14) g.setColor(Color.BLACK);

                            g.fillRect(x, y, 32, 32);
                            x += 32;
                        }
                        x = 10;
                        y += 32;
                    }
                } catch (Exception e) {
                    System.out.println("Error-Tablero");
                }
                Tablero.repaint();
            }
        };
        Tablero.setBounds(0, 0, TableroJuego.getWidth(), TableroJuego.getHeight());
        Tablero.setVisible(true);
        TableroJuego.add(Tablero);

        new Thread(() -> {
            while (true) {
                if (CompDownR() || CompDownL()){
                    for (int i = (Tablero_.size() - 1); i >= 0 ; i--) {
                        for (int j = (Tablero_.getFirst().size() - 1); j >= 0 ; j--) {
                            if ((Tablero_.get(i).get(j) % 2) == 1){
                                Tablero_.get(i).set(j, (Tablero_.get(i).get(j) + 1));
                            }
                        }
                    }
                }else {
                    for (int i = (Tablero_.size() - 1); i >= 0 ; i--) {
                        for (int j = (Tablero_.getFirst().size() - 1); j >= 0 ; j--) {
                            if ((Tablero_.get(i).get(j) % 2) == 1){
                                Tablero_.get((i+1)).set(j, Tablero_.get(i).get(j));
                                Tablero_.get(i).set(j, 0);
                            }
                        }
                    }
                    /*new Thread(() -> {
                        int Count_1 = 0;
                        for (int i = 0; i < Tablero_.size(); i++) {
                            for (int j = 0; j < Tablero_.getFirst().size(); j++) {
                                if ((Tablero_.get(i).get(j) == 1)) {
                                    Count_1++;
                                }
                            }
                        }
                        if (Count_1 < 1) {
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            Ficha();
                        }
                    }).start();*/
                }
                try {
                    Thread.sleep(Velocidad + 10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void Ficha() {
        NoPieza = (int) (Math.random() * Piezas.size());
        NoRotacion = (int) (Math.random() * Piezas.get(NoPieza).size());
        int PosAparicion = (int) (Math.random() * 15);

        while (true) {
            if ((Tablero_.getFirst().size() - PosAparicion) < Piezas.get(NoPieza).getFirst().getFirst().size()) {
                PosAparicion = (int) (Math.random() * 15);
            } else if ((PosAparicion + Piezas.get(NoPieza).getFirst().size()) >= Tablero_.getFirst().size()) {
                PosAparicion = (int) (Math.random() * 15);
            } else break;
        }

        for (int i = 0; i < Piezas.get(NoPieza).get(NoRotacion).size(); i++) {
            for (int j = 0; j < Piezas.get(NoPieza).get(NoRotacion).getFirst().size(); j++) {
                if (Tablero_.get(i).get((PosAparicion + j)) == 0) {
                    Tablero_.get(i).set((PosAparicion + j), Piezas.get(NoPieza).get(NoRotacion).get(i).get(j));
                }else {
                    Tablero_.clear();
                    Tablero_ = new ArrayList<>() {{
                        for (int i = 0; i < 30; i++) {
                            add(new ArrayList<>() {{
                                for (int j = 0; j < 15; j++) {
                                    add(0);
                                }
                            }});
                        }
                    }};
                    /*int Count_1 = 0;
                    for (int k = 0; k < Tablero_.size(); k++) {
                        for (int l = 0; l < Tablero_.getFirst().size(); l++) {
                            if ((Tablero_.get(k).get(l) == 1)) {
                                Count_1++;
                            }
                        }
                    }
                    if (Count_1 < 1) {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        Ficha();
                    }*/
                }
            }
        }
    }

	/*public void Rotar(){

	}*/

    public boolean CompLeftU(){
        boolean Return = false;

        for (int i = 0; i < Tablero_.size() ; i++) {
            for (int j = 0; j < Tablero_.getFirst().size() ; j++) {
                if ((Tablero_.get(i).get(j) % 2) == 1){
                    try {
                        if (Tablero_.get(i).get((j-1)) == 0){
                            Return = true;
                        }
                    } catch (Exception e) {
                        Return = false;
                    }
                }
            }
        }

        return Return;
    }

    public boolean CompLeftD(){
        boolean Return = false;

        for (int i = (Tablero_.size() - 1); i >= 0 ; i--) {
            for (int j = 0; j < Tablero_.getFirst().size() ; j++) {
                if ((Tablero_.get(i).get(j) % 2) == 1){
                    try {
                        if (Tablero_.get(i).get((j-1)) == 0){
                            Return = true;
                        }
                    } catch (Exception e) {
                        Return = false;
                    }
                }
            }
        }

        return Return;
    }

    public boolean CompRightU(){
        boolean Return = false;

        for (int i = 0; i < Tablero_.size() ; i++) {
            for (int j = (Tablero_.getFirst().size() - 1); j >= 0 ; j--) {
                if ((Tablero_.get(i).get(j) % 2) == 1){
                    try {
                        if (Tablero_.get(i).get((j+1)) == 0){
                            Return = true;
                        }
                    } catch (Exception e) {
                        Return = false;
                    }
                }
            }
        }

        return Return;
    }

    public boolean CompRightD(){
        boolean Return = false;

        for (int i = (Tablero_.size() - 1); i >= 0 ; i--) {
            for (int j = (Tablero_.getFirst().size() - 1); j >= 0 ; j--) {
                if ((Tablero_.get(i).get(j) % 2) == 1){
                    try {
                        if (Tablero_.get(i).get((j+1)) == 0){
                            Return = true;
                        }
                    } catch (Exception e) {
                        Return = false;
                    }
                }
            }
        }

        return Return;
    }

    public boolean CompDownL(){
        boolean Return = false;

        for (int i = (Tablero_.size() - 1); i >= 0 ; i--) {
            for (int j = 0; j < Tablero_.getFirst().size() ; j++) {
                if ((Tablero_.get(i).get(j) % 2) == 1){
                    try {
                        if ((Tablero_.get((i+1)).get(j) % 2) == 0 && Tablero_.get((i+1)).get(j) != 0){
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

    public boolean CompDownR(){
        boolean Return = false;

        for (int i = (Tablero_.size() - 1); i >= 0 ; i--) {
            for (int j = (Tablero_.getFirst().size() - 1); j >= 0 ; j--) {
                if ((Tablero_.get(i).get(j) % 2) == 1){
                    try {
                        if ((Tablero_.get((i+1)).get(j) % 2) == 0 && Tablero_.get((i+1)).get(j) != 0){
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

    public void MoveDown(){
        if (CompDownR() || CompDownL()){
            for (int i = (Tablero_.size() - 1); i >= 0 ; i--) {
                for (int j = (Tablero_.getFirst().size() - 1); j >= 0 ; j--) {
                    if ((Tablero_.get(i).get(j) % 2) == 1){
                        Tablero_.get(i).set(j, (Tablero_.get(i).get(j) + 1));
                    }
                }
            }
        }else {
            for (int i = (Tablero_.size() - 1); i >= 0 ; i--) {
                for (int j = (Tablero_.getFirst().size() - 1); j >= 0 ; j--) {
                    if ((Tablero_.get(i).get(j) % 2) == 1){
                        Tablero_.get((i+1)).set(j, Tablero_.get(i).get(j));
                        Tablero_.get(i).set(j, 0);
                    }
                }
            }
        }
    }

    public void MoveRight(){
        if (CompRightD() && CompRightU()){
            for (int i = (Tablero_.size() - 1); i >= 0 ; i--) {
                for (int j = (Tablero_.getFirst().size() - 1); j >= 0 ; j--) {
                    if ((Tablero_.get(i).get(j) % 2) == 1){
                        Tablero_.get(i).set((j+1), Tablero_.get(i).get(j));
                        Tablero_.get(i).set(j, 0);
                    }
                }
            }
        }
    }

    public void MoveLeft(){
        if (CompLeftD() && CompLeftU()){
            for (int i = (Tablero_.size() - 1); i >= 0 ; i--) {
                for (int j = 0; j < Tablero_.getFirst().size() ; j++) {
                    if ((Tablero_.get(i).get(j) % 2) == 1){
                        Tablero_.get(i).set((j-1), Tablero_.get(i).get(j));
                        Tablero_.get(i).set(j, 0);
                    }
                }
            }
        }
    }
}
