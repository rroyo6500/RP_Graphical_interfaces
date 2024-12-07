package RP.Games.Tetris;

import RP.Var.Var;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends JFrame{

    Piezas piezas = new Piezas();
    Logica logica = new Logica();
    Var var = new Var();

    final BufferedImage YellowBlock, BlueBlock, CyanBLock, GreenBlock, OrangeBlock, PurpleBlock, RedBlock, BlackBlock;

    {
        try {
            YellowBlock = ImageIO.read(new File(logica.getPath(var.getPath()) + "TetrisYellow.png"));
            BlueBlock = ImageIO.read(new File(logica.getPath(var.getPath()) + "TetrisBlue.png"));
            CyanBLock = ImageIO.read(new File(logica.getPath(var.getPath()) + "TetrisCyan.png"));
            GreenBlock = ImageIO.read(new File(logica.getPath(var.getPath()) + "TetrisGreen.png"));
            OrangeBlock = ImageIO.read(new File(logica.getPath(var.getPath()) + "TetrisOrange.png"));
            PurpleBlock = ImageIO.read(new File(logica.getPath(var.getPath()) + "TetrisPurple.png"));
            RedBlock = ImageIO.read(new File(logica.getPath(var.getPath()) + "TetrisRed.png"));
            BlackBlock = ImageIO.read(new File(logica.getPath(var.getPath()) + "TetrisGhost.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    int Velocidad = 500;        // +5
    int C = 0;
    int C_Time = 0;
    boolean Ex = true;

    int NoPieza;
    int NoRotacion;
    int NoProxPieza;
    int NoProxRotacion;

    int IPuntuacion = 15;
    int PuntuacionFinal = 0;

    boolean Ex_ = true;

    Timer Tetris_Principal;
    Timer Tetris_Draw;

    JLabel Puntuacion;

    JPanel Tetoris, GamesMenu;
    JFrame TableroJuego = new JFrame() {{
        setBounds(0, 0, var.Width(500), var.Height(1020));
    }};

    public static JPanel Tablero;
    public static JPanel Panel_ProxPieza;

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
                logica.Tablero_Restart(Tablero_);
                if (Tetris_Principal != null){
                    Tetris_Principal.cancel();
                    Tetris_Draw.cancel();
                }
                Ex_ = false;
                Tetoris.setVisible(false);
                GamesMenu.setVisible(true);
                TableroJuego.setVisible(false);
            });
            Tetoris_Title.add(MMenu);

            JButton BCommandPrompt = new JButton();
            BCommandPrompt.setBounds(290, 10, 100, 20);
            BCommandPrompt.setFont(new Font("Arial", Font.BOLD, 10));
            BCommandPrompt.setText("Command Prompt");
            BCommandPrompt.setBackground(new Color(255, 255, 255));
            BCommandPrompt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
            BCommandPrompt.addActionListener(_ -> new Thread(this::CommandPrompt).start());
            Tetoris_Title.add(BCommandPrompt);
        }

        JButton Down = new JButton();
        Down.setBounds(200, 350, 100, 50);
        Down.setText("↓");
        Down.setBackground(new Color(255,255,255));
        Down.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Down.addActionListener(_ -> logica.PartDown(Tablero_));
        Tetoris.add(Down);

        JButton Right = new JButton();
        Right.setBounds(300, 350, 100, 50);
        Right.setText("→");
        Right.setBackground(new Color(255,255,255));
        Right.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Right.addActionListener(_ -> logica.PartRight(Tablero_));
        Tetoris.add(Right);

        JButton Left = new JButton();
        Left.setBounds(100, 350, 100, 50);
        Left.setText("←");
        Left.setBackground(new Color(255,255,255));
        Left.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Left.addActionListener(_ -> logica.PartLeft(Tablero_));
        Tetoris.add(Left);

        JButton Rotate = new JButton();
        Rotate.setBounds(200, 300, 100, 50);
        Rotate.setText("↻");
        Rotate.setBackground(new Color(255,255,255));
        Rotate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        Rotate.addActionListener(_ -> NoRotacion = logica.RotatePart(Tablero_, NoPieza, NoRotacion));
        Tetoris.add(Rotate);

        Puntuacion = new JLabel();
        Puntuacion.setBounds(175, 400, 300, 50);
        Puntuacion.setFont(new Font("Arial", Font.BOLD, 25));
        new Thread(() -> {
            Ex_ = true;
            while (Ex_){
                Puntuacion.setText("Puntuacion: " + (PuntuacionFinal + (IPuntuacion * logica.getLineasCompletas())));
                if (logica.getLineasSeguidas() >= 4) {
                    PuntuacionFinal += (10 * logica.getLineasSeguidas());
                    logica.resetLineasSeguidas();
                }
            }
            PuntuacionFinal = 0;
            logica.reserLineasCompletas();
            Puntuacion.setText("");
        }).start();
        Tetoris.add(Puntuacion);

        Panel_ProxPieza = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                NoProxPieza = logica.getNoProxPieza();
                NoProxRotacion = logica.getNoProxRotacion();

                if (NoProxRotacion > (piezas.CantRPieza(NoProxPieza) - 1)) NoProxRotacion = 0;

                int x = 0;
                if (piezas.getPart(NoProxPieza, NoProxRotacion).getFirst().size() == 1) x = 140;
                else if (piezas.getPart(NoProxPieza, NoProxRotacion).getFirst().size() == 2) x = 130;
                else if (piezas.getPart(NoProxPieza, NoProxRotacion).getFirst().size() == 3) x = 120;
                else if (piezas.getPart(NoProxPieza, NoProxRotacion).getFirst().size() == 4) x = 110;
                int x_res = x;

                int y = 0;
                if (piezas.getPart(NoProxPieza, NoProxRotacion).size() == 1) y = 90;
                else if (piezas.getPart(NoProxPieza, NoProxRotacion).size() == 2) y = 80;
                else if (piezas.getPart(NoProxPieza, NoProxRotacion).size() == 3) y = 70;
                else if (piezas.getPart(NoProxPieza, NoProxRotacion).size() == 4) y = 60;

                try {
                    for (ArrayList<Integer> Filas : piezas.getPart(NoProxPieza, NoProxRotacion)) {
                        for (Integer Col : Filas) {
                            if (Col == 0) {
                                g.setColor(Color.BLACK);
                                g.fillRect(x, y, 20, 20);
                            }
                            if (Col == 1 || Col == 2) g.drawImage( YellowBlock, x, y, 20, 20, null);
                            if (Col == 3 || Col == 4) g.drawImage( GreenBlock, x, y, 20, 20, null);
                            if (Col == 5 || Col == 6) g.drawImage( RedBlock, x, y, 20, 20, null);
                            if (Col == 7 || Col == 8) g.drawImage( PurpleBlock, x, y, 20, 20, null);
                            if (Col == 9 || Col == 10) g.drawImage( OrangeBlock, x, y, 20, 20, null);
                            if (Col == 11 || Col == 12) g.drawImage( BlueBlock, x, y, 20, 20, null);
                            if (Col == 13 || Col == 14) g.drawImage( CyanBLock, x, y, 20, 20, null);

                            x += 20;
                        }
                        x = x_res;
                        y += 20;
                    }
                } catch (Exception e) {
                    System.out.println("Error-Tablero");
                }
                Panel_ProxPieza.repaint();
            }
        };
        Panel_ProxPieza.setBackground(Color.BLACK);
        Panel_ProxPieza.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        Panel_ProxPieza.setBounds(100, 75, 300, 200);
        Tetoris.add(Panel_ProxPieza);

        Panel_ProxPieza.repaint();

        new Thread(() -> TableroJuego(TableroJuego)).start();
        TableroJuego.setVisible(true);

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

                            if (Col == 0) {
                                g.setColor(Color.BLACK);
                                g.fillRect(x, y, 32, 32);
                                g.drawImage( BlackBlock, x, y, 32, 32, null);
                            }
                            if (Col == 1 || Col == 2) g.drawImage( YellowBlock, x, y, 32, 32, null);
                            if (Col == 3 || Col == 4) g.drawImage( GreenBlock, x, y, 32, 32, null);
                            if (Col == 5 || Col == 6) g.drawImage( RedBlock, x, y, 32, 32, null);
                            if (Col == 7 || Col == 8) g.drawImage( PurpleBlock, x, y, 32, 32, null);
                            if (Col == 9 || Col == 10) g.drawImage( OrangeBlock, x, y, 32, 32, null);
                            if (Col == 11 || Col == 12) g.drawImage( BlueBlock, x, y, 32, 32, null);
                            if (Col == 13 || Col == 14) g.drawImage( CyanBLock, x, y, 32, 32, null);
                            if (Col == 100) {
                                g.setColor(Color.WHITE);
                                g.fillRect(x, y, 32, 32);
                                g.drawImage( BlackBlock, x, y, 32, 32, null);
                            }

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
        Tablero.setBackground(Color.GRAY);
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

            int CountMParts = 0;

            for (int i = (Tablero_.size() - 1); i >= 0; i--) {
                for (int j = 0; j < Tablero_.getFirst().size(); j++) {
                    if ((Tablero_.get(i).get(j) % 2) == 1) {
                        CountMParts++;
                    }
                }
            }

            if (CountMParts == 0) {
                NoPieza = logica.getNoProxPieza();
                NoRotacion = logica.getNoProxRotacion();

                int PosAparicion = (int) (Math.random() * Tablero_.getFirst().size());
                C_Time+=1;
                while (logica.NewPart(Tablero_, PosAparicion, piezas.getPart(NoPieza, NoRotacion))) {
                    PosAparicion = (int) (Math.random() * Tablero_.getFirst().size());
                }
            }

            if (C_Time == 0){
                logica.PartDown(Tablero_);
            }else C_Time--;

            if (logica.getLineasSeguidas_10() == 10){
                Velocidad -= 10;
                Tetris_Principal.cancel();
                IniciarJuego();
                logica.resetLineasSeguidas_10();
            }

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

    public void CommandPrompt(){
        String Command;
        String[] Command_S;
        while (Ex_){
            System.out.print("    - ");
            Command = var.in().nextLine();

            if (Command.matches("^¡v = .*")){
                Command = Command.replaceAll(" = ", " ");
                Command_S = Command.split(" ");
                Velocidad = Integer.parseInt(Command_S[1]);
                Tetris_Principal.cancel();
                IniciarJuego();
            } else if (Command.matches("^¡p = .*")) {
                Command = Command.replaceAll(" = ", " ");
                Command_S = Command.split(" ");
                logica.setNoProxPieza(Integer.parseInt(Command_S[1]));
                logica.setNoProxRotacion(0);
            } else if (Command.matches("^¡r = .*")) {
                Command = Command.replaceAll(" = ", " ");
                Command_S = Command.split(" ");
                logica.setNoProxRotacion(Integer.parseInt(Command_S[1]));
            } else if (Command.matches("^¡compf = .*")) {
                Command = Command.replaceAll(" = ", " ");
                Command_S = Command.split(" ");
                for (int i = 0; i < Tablero_.getFirst().size(); i++) {
                    Tablero_.get(Integer.parseInt(Command_S[1])).set(i, 100);
                }
            } else if (Command.matches("^¡compaf = .*")) {
                Command = Command.replaceAll(" = ", " ");
                Command_S = Command.split(" ");
                logica.RecorrerTablero(Tablero_, (Tablero_.size() - 1),Integer.parseInt(Command_S[1]), (Tablero_.getFirst().size() - 1), 0, false, 100, "N");
            } else if (Command.matches("^¡score = .*")) {
                Command = Command.replaceAll(" = ", " ");
                Command_S = Command.split(" ");
                PuntuacionFinal = Integer.parseInt(Command_S[1]);
            } else if (Command.matches("^¡freze.*")) {
                logica.RecorrerTablero(Tablero_, (Tablero_.size() - 1), 0, (Tablero_.getFirst().size() - 1), 0, true, 1, "A");
            } else if (Command.matches("^¡clear.*")) {
                logica.Tablero_Restart(Tablero_);
            } else if (Command.matches("^¡exit")) Ex_ = false;
        }
        Ex_ = true;
    }
}
