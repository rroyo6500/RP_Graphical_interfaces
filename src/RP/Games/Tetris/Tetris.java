package RP.Games.Tetris;

import RP.Var.Var;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tetris extends JFrame{

    Piezas piezas = new Piezas();
    Var var = new Var();

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
                //ClearOP();
                Tetoris.setVisible(false);
                GamesMenu.setVisible(true);
                TableroJuego.setVisible(false);
            });
            Tetoris_Title.add(MMenu);
        }

        TableroJuego(TableroJuego);
        TableroJuego.setVisible(true);
    }

    public void TableroJuego(JFrame TableroJuego){
        TableroJuego.setLayout(null);
        TableroJuego.setLocationRelativeTo(null);
        TableroJuego.setResizable(false);

        for (ArrayList<Integer> Fila : piezas.getPart(7, 3)){
            System.out.println(Fila);
        }

        Tablero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                int x = 10, y = 10;

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
                            if (Col == 15 || Col == 16) g.setColor(Color.ORANGE);

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
    }

    // Arrays [Tablero_, CompFTablero]
    public static ArrayList<ArrayList<Integer>> Tablero_ = new ArrayList<>(){{
        for (int i = 0; i < 30; i++) {
            add(new ArrayList<>() {{
                for (int j = 0; j < 15; j++) {
                    add(0);
                }
            }});
        }
    }};
    public static ArrayList<ArrayList<Integer>> CompFTablero = new ArrayList<>() {{
        add(new ArrayList<>() {{
            for (int i = 0; i < 15; i++) {
                add(0);
            }
        }});
    }};
}
