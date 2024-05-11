package Aplicacao;

import xadrez.PecaXadrez;

public class UI {
	
	
	public static void impressaoTabuleiro(PecaXadrez[][] peca) {
		for(int i = 0; i < peca.length; i++) {
			System.out.print((8 - i ) + " ");
			for(int j = 0; j < peca.length; j++) {
				impressaoPeca(peca[i][j]);
				
			}
			System.out.println();
		}
		System.out.print("  a b c d i f g h");
	}
	
	public static void impressaoPeca(PecaXadrez peca) {
		if(peca == null) {
			System.out.print("-");
		}
		else {
			System.out.print(peca);
		}
		System.out.print(" ");
	}
}
