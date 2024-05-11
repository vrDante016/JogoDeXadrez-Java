package Aplicacao;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		
		while(true) {
			UI.impressaoTabuleiro(partida.getPecas());
			System.out.println("");
			System.out.print("Origin");
			PosicaoXadrez origin = UI.lerPosicaoXadrez(ler);
			
			System.out.println();
			System.out.print("Alvo");
			PosicaoXadrez alvo = UI.lerPosicaoXadrez(ler);
			
			PecaXadrez pecaCapturada = partida.pecaComida(origin, alvo);
		}
	}

}
