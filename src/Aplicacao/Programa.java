package Aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezExcecao;

public class Programa {

	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		List<PecaXadrez> pecasCapturadas = new ArrayList();
		
		while(!partida.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.primeitaPartida(partida, pecasCapturadas);
				System.out.println("");
				System.out.print("Origin");
				PosicaoXadrez origin = UI.lerPosicaoXadrez(ler);
				
				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origin);
				UI.clearScreen();
				UI.impressaoTabuleiro(partida.getPecas(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("Alvo");
				PosicaoXadrez alvo = UI.lerPosicaoXadrez(ler);
				
				
				PecaXadrez pecaCapturada = partida.iniciarJogoDeXadrez(origin, alvo);
				if(pecasCapturadas != null) {
					pecasCapturadas.add(pecaCapturada);
				}
				}
				catch(XadrezExcecao e){
					System.out.println(e.getMessage());
					ler.next();
					
				}
				catch(InputMismatchException e) {
					System.out.println(e.getMessage());
					ler.next();
				}
				
		}
		UI.clearScreen();
		UI.primeitaPartida(partida, pecasCapturadas);
	}

}
