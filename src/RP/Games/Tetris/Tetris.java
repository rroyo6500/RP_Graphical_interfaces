package RP.Games.Tetris;

import RP.Var.Var;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends JFrame{

    int Velocidad = 0;        // +5
    int C = 0;
    int C_Time = 0;
    boolean Ex = true;

    int NoPieza;
    int NoRotacion;

    Piezas piezas = new Piezas();
    Logica logica = new Logica();
    Var var = new Var();

    Timer Tetris_Principal;
    Timer Tetris_Draw;

    JPanel Tetoris, GamesMenu;
    JFrame TableroJuego = new JFrame() {{
        setBounds(0, 0, var.Width(500), var.Height(1020));
    }};

    public static JPanel Tablero;

    public Tetris(JPanel GamePanel, JPanel GamesMenu) {
        this.Tetoris = GamePanel;
        this.GamesMenu = GamesMenu;
    }

    public void TetrisControler(){
        Tetoris.setFocusable(true);
        Tetoris.requestFocusInWindow();
        /* Title & Return to Main Menu */
        {
            JPanel Tetoris_Title = new JPanel();
            Tetoris_Title.setBounds(-1, -1, Tetoris.getWidth() + 2, 36);
            Tetoris_Title.setLayout(null);
            Tetoris_Title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            Tetoris.add(Tetoris_Title);

            JLabel Title = new JLabel();
            Title.setFont(new Font("Arial", Font.BOLD, 20));
            Title.setText("Tetris");
            Title.setBounds(10, 10, 300, 20);
            Tetoris_Title.add(Title);

            JButton MMenu = new JButton();
            MMenu.setBounds(390, 10, 100, 20);
            MMenu.setFont(new Font("Arial", Font.BOLD, 10));
            MMenu.setText("Games Menu");
            MMenu.setBackground(new Color(255, 255, 255));
            MMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            MMenu.addActionListener(_ -> {
                logica.Tablero_Restart(Tablero_);
                if (Tetris_Principal != null){
                    Tetris_Principal.cancel();
                }
                Tetoris.setVisible(false);
                GamesMenu.setVisible(true);
                TableroJuego.setVisible(false);
            });
            Tetoris_Title.add(MMenu);
        }

        JButton Down = new JButton();
        Down.setBounds(200, 200, 100, 50);
        Down.setText("↓");
        Down.addActionListener(_ -> logica.PartDown(Tablero_));
        Tetoris.add(Down);

        JButton Right = new JButton();
        Right.setBounds(300, 200, 100, 50);
        Right.setText("→");
        Right.addActionListener(_ -> logica.PartRight(Tablero_));
        Tetoris.add(Right);

        JButton Left = new JButton();
        Left.setBounds(100, 200, 100, 50);
        Left.setText("←");
        Left.addActionListener(_ -> logica.PartLeft(Tablero_));
        Tetoris.add(Left);

        JButton Rotate = new JButton();
        Rotate.setBounds(200, 150, 100, 50);
        Rotate.setText("↻");
        Rotate.addActionListener(_ -> NoRotacion = logica.RotatePart(Tablero_, NoPieza, NoRotacion));
        Tetoris.add(Rotate);

        new Thread(() -> TableroJuego(TableroJuego)).start();
        TableroJuego.setVisible(true);

        Tetoris.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == 38) NoRotacion = logica.RotatePart(Tablero_, NoPieza, NoRotacion);
                else if (e.getKeyCode() == 37) logica.PartLeft(Tablero_);
                else if (e.getKeyCode() == 40) logica.PartDown(Tablero_);
                else if (e.getKeyCode() == 39) logica.PartRight(Tablero_);

            }
        });
    }

    public void TableroJuego(JFrame TableroJuego){
        TableroJuego.setLayout(null);
        TableroJuego.setLocationRelativeTo(null);
        TableroJuego.setResizable(false);

        Tablero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                int x = 10, y = 10;

                //if (Tablero_.size() == 30) TableroResidual = (ArrayList<ArrayList<Integer>>) Tablero_.clone();

                try {
                    for (ArrayList<Integer> Filas : Tablero_) {
                        for (Integer Col : Filas) {
                            if (Col == 0) g.setColor(Color.BLACK);
                            if (Col == 1 || Col == 2) g.setColor(Color.RED);
                            if (Col == 3 || Col == 4) g.setColor(Color.BLUE);
                            if (Col == 5 || Col == 6) g.setColor(Color.GREEN);
                            if (Col == 7 || Col == 8) g.setColor(Color.MAGENTA);
                            if (Col == 9 || Col == 10) g.setColor(Color.YELLOW);
                            if (Col == 11 || Col == 12) g.setColor(Color.CYAN);
                            if (Col == 13 || Col == 14) g.setColor(Color.WHITE);
                            if (Col == 15 || Col == 16) g.setColor(Color.ORANGE);
                            if (Col == 17 || Col == 18) g.setColor(Color.GRAY);

                            g.fillRect(x, y, 32, 32);
                            x += 32;
                        }
                        x = 10;
                        y += 32;
                    }
                } catch (Exception e) {
                    System.out.println("Error-Tablero");
                }
                //Tablero.repaint();
            }
        };
        Tablero.setFocusable(true);
        Tablero.requestFocusInWindow();
        Tablero.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == 38) NoRotacion = logica.RotatePart(Tablero_, NoPieza, NoRotacion);
                else if (e.getKeyCode() == 37) logica.PartLeft(Tablero_);
                else if (e.getKeyCode() == 40) logica.PartDown(Tablero_);
                else if (e.getKeyCode() == 39) logica.PartRight(Tablero_);

            }
        });

        IniciarTablero();
        IniciarJuego();

        Tablero.setBounds(0, 0, TableroJuego.getWidth(), TableroJuego.getHeight());
        Tablero.setVisible(true);
        TableroJuego.add(Tablero);
    }

    public void IniciarTablero(){
        Tetris_Draw = new Timer();
        Tetris_Draw.schedule(new TimerTask() {
            @Override
            public void run() {
                Tablero.repaint();
            }
        }, 0, 10);
    }

    public void IniciarJuego(){
        Tetris_Principal = new Timer();
        Tetris_Principal.schedule(new TimerTask() {
            @Override
            public void run() {
                if (Ex){
                    Ex = false;
                    NewPart();
                }
            }
        }, 0, Velocidad + 5);
    }

    public void NewPart(){
        if (!Tablero_.isEmpty()){

            /*int NoPieza = (int) (Math.random()*2);
            if (NoPieza == 1) {
                NoPieza = 6;
            }*/

            int CountMParts = 0;

            for (int i = (Tablero_.size() - 1); i >= 0; i--) {
                for (int j = 0; j < Tablero_.getFirst().size(); j++) {
                    if ((Tablero_.get(i).get(j) % 2) == 1) {
                        CountMParts++;
                    }
                }
            }

            if (CountMParts == 0) {
                NoPieza = (int) (Math.random() * piezas.CantPiezas());
                NoRotacion = (int) (Math.random() * piezas.CantRPieza(NoPieza));
                int PosAparicion = (int) (Math.random() * Tablero_.getFirst().size());
                C_Time+=1;
                while (logica.NewPart(Tablero_, PosAparicion, piezas.getPart(NoPieza, NoRotacion))) {
                    PosAparicion = (int) (Math.random() * Tablero_.getFirst().size());
                }
            }

            if (C_Time == 0){
                logica.PartDown(Tablero_);
            }else C_Time--;

            Ex = true;

        }else {
            Tablero_ = new ArrayList<>(){{
                for (int i = 0; i < 30; i++) {
                    add(new ArrayList<>() {{
                        for (int j = 0; j < 15; j++) {
                            add(0);
                        }
                    }});
                }
            }};
        }
    }

    public void Restart(){
        C = 0;
        Tablero_ = new ArrayList<>(){{
            for (int i = 0; i < 30; i++) {
                add(new ArrayList<>() {{
                    for (int j = 0; j < 15; j++) {
                        add(0);
                    }
                }});
            }
        }};
    }

    // Arrays [Tablero_, CompFTablero]
    public static ArrayList<ArrayList<Integer>> Tablero_;
}
