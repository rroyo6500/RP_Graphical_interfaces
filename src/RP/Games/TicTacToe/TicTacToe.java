package RP.Games.TicTacToe;

import RP.Var.Var;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TicTacToe {
	Var var = new Var();

	JLabel[] PosicionesTablero = new JLabel[9];
	ArrayList<ArrayList<String>> Tablero = new ArrayList<>(){{
		for (int i = 0; i < 3; i++) {
			add(new ArrayList<>(){{
				for (int j = 0; j < 3; j++) {
					add(" ");
				}
			}});
		}
	}};

	private final JFrame TTT;

	public void Visible(Boolean Visible){
		this.TTT.setVisible(Visible);
		this.TTT.setBounds(0,0, var.Width(500), var.Height(500));
		this.TTT.setLocationRelativeTo(null);
	}

	public TicTacToe(JFrame Panel){
		this.TTT = Panel;
		this.TTT.setLayout(null);

		for (int i = 0; i < PosicionesTablero.length; i++) {
			PosicionesTablero[i] = new JLabel("", SwingConstants.CENTER);
			PosicionesTablero[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
		}
		for (int i = 300, C = 0; i >= 100; i-=100) {
			for (int j = 100; j <= 300; j+=100) {
				PosicionesTablero[C].setBounds(j,i,100,100);
				C++;
			}
		}
		for (JLabel Pos : PosicionesTablero){
			this.TTT.add(Pos);
		}

		this.TTT.setTitle("Tic Tac Toe");
		this.TTT.setResizable(false);
	}

	public void XPlayer(){

	}
	public void YPlayer(){

	}
}
