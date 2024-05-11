package Aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezExcecao;

public class Programa {

	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		
		while(true) {
			try {
				UI.clearScreen();
				UI.impressaoTabuleiro(partida.getPecas());
				System.out.println("");
				System.out.print("Origin");
				PosicaoXadrez origin = UI.lerPosicaoXadrez(ler);
				
				System.out.println();
				System.out.print("Alvo");
				PosicaoXadrez alvo = UI.lerPosicaoXadrez(ler);
				
				PecaXadrez pecaCapturada = partida.iniciarJogoDeXadrez(origin, alvo);
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
	}

}
