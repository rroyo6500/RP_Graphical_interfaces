package RP.Games.TicTacToe;

import RP.Var.Var;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tetris extends JFrame {

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

	public static ArrayList<ArrayList<Integer>> FC_V = new ArrayList<>(){{
		add(new ArrayList<>(){{
			for (int j = 0; j < 15; j++) {
				add(2);
			}
		}});
		add(new ArrayList<>(){{
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

	public void TableroJuego(JFrame TableroJuego) {
		TableroJuego.setLayout(null);
		TableroJuego.setLocationRelativeTo(null);
		TableroJuego.setResizable(false);

		Tablero_.get(15).set(6, 1);
		Tablero_.get(15).set(7, 1);
		Tablero_.get(14).set(6, 1);
		Tablero_.get(14).set(5, 1);


		Tablero_.get(29).set(0, 2);
		Tablero_.get(29).set(1, 2);
		Tablero_.get(29).set(2, 2);
		Tablero_.get(29).set(3, 2);
		Tablero_.get(29).set(4, 2);
		Tablero_.get(29).set(5, 2);
		Tablero_.get(29).set(8, 2);
		Tablero_.get(29).set(9, 2);
		Tablero_.get(29).set(10, 2);
		Tablero_.get(29).set(11, 2);
		Tablero_.get(29).set(12, 2);
		Tablero_.get(29).set(13, 2);
		Tablero_.get(29).set(14, 2);

		for (ArrayList<Integer> Filas : Tablero_) System.out.println(Filas);
		for (ArrayList<Integer> Filas : FC_V) System.out.println(Filas);

		Tablero = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				int x = 10, y = 10;

				for (ArrayList<Integer> integers : Tablero_) {
					for (Integer integer : integers) {
						if (integer == 0) g.setColor(Color.GRAY);
						if (integer == 1) g.setColor(Color.RED);
						if (integer == 2) g.setColor(Color.BLUE);
						g.fillRect(x, y, 32, 32);
						x += 32;
					}
					x = 10;
					y += 32;
				}

			}
		};
		Tablero.setBounds(0, 0, TableroJuego.getWidth(), TableroJuego.getHeight());
		Tablero.setVisible(true);
		TableroJuego.add(Tablero);

		new Thread(() -> {
			while (true){
				for (int i = (Tablero_.size() - 1); i >= 0; i--) {
					for (int j = (Tablero_.getFirst().size() - 1); j >= 0; j--) {
						if (Tablero_.get(i).get(j) == 1) {
							if (!((i + 1) >= Tablero_.size())) {
								if (Tablero_.get((i + 1)).get(j) == 0) {
									Tablero_.get((i + 1)).set(j, 1);
									Tablero_.get(i).set(j, 0);
								} else if (Tablero_.get((i + 1)).get(j) == 2) {
									for (int k = (Tablero_.size() - 1); k >= 0; k--) {
										for (int l = (Tablero_.getFirst().size() - 1); l >= 0; l--) {
											if (Tablero_.get(k).get(l) == 1){
												Tablero_.get(k).set(l, 2);
											}
										}
									}
								}
							} else {
								for (int k = (Tablero_.size() - 1); k >= 0; k--) {
									for (int l = (Tablero_.getFirst().size() - 1); l >= 0; l--) {
										if (Tablero_.get(k).get(l) == 1){
											Tablero_.get(k).set(l, 2);
										}
									}
								}
							}
						}
					}
					if (Tablero_.get(i).equals(FC_V.get(0))) {
						Tablero_.remove(i);
						Tablero_.addFirst(FC_V.get(1));
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				Tablero.repaint();
			}
		}).start();
	}

	public void MoveDown() {
		new Thread(() -> {
			for (int i = (Tablero_.size() - 1); i >= 0; i--) {
				for (int j = (Tablero_.getFirst().size() - 1); j >= 0; j--) {
					if (Tablero_.get(i).get(j) == 1) {
						if (!((i + 1) >= Tablero_.size())) {
							if (Tablero_.get((i + 1)).get(j) == 0) {
								Tablero_.get((i + 1)).set(j, 1);
								Tablero_.get(i).set(j, 0);
							} else if (Tablero_.get((i + 1)).get(j) == 2) {
								for (int k = (Tablero_.size() - 1); k >= 0; k--) {
									for (int l = (Tablero_.getFirst().size() - 1); l >= 0; l--) {
										if (Tablero_.get(k).get(l) == 1){
											Tablero_.get(k).set(l, 2);
										}
									}
								}
							}
						} else {
							for (int k = (Tablero_.size() - 1); k >= 0; k--) {
								for (int l = (Tablero_.getFirst().size() - 1); l >= 0; l--) {
									if (Tablero_.get(k).get(l) == 1){
										Tablero_.get(k).set(l, 2);
									}
								}
							}
						}
					}
				}
			}
			Tablero.repaint();
		}).start();
	}

	public void MoveRight() {
		new Thread(() -> {
			for (int i = (Tablero_.size() - 1); i >= 0; i--) {
				for (int j = (Tablero_.getFirst().size() - 1); j >= 0; j--) {
					if (Tablero_.get(i).get(j) == 1) {
						if (!((j + 1) >= Tablero_.getFirst().size())) {
							if (Tablero_.get(i).get((j+1)) == 0){
								Tablero_.get(i).set((j+1), 1);
								Tablero_.get(i).set(j, 0);
							}
						}
					}
				}
			}
			Tablero.repaint();
		}).start();
	}

	public void MoveLeft() {
		new Thread(() -> {
			for (int i = (Tablero_.size() - 1); i >= 0; i--) {
				for (int j = 0; j < Tablero_.getFirst().size(); j++) {
					if (Tablero_.get(i).get(j) == 1){
						if (!((j-1) < 0)){
							if (Tablero_.get(i).get((j-1)) == 0){
								Tablero_.get(i).set((j-1), 1);
								Tablero_.get(i).set(j, 0);
							}
						}
					}
				}
			}
			Tablero.repaint();
		}).start();
	}

}
