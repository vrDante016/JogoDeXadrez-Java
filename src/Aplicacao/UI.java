package Aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cores;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;


public class UI {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";	
	
	public static void clearScreen() {
		 System.out.print("\033[H\033[2J");
		 System.out.flush();
		} 
	
	public static PosicaoXadrez lerPosicaoXadrez(Scanner ler) {
		try {
		String s = ler.next();
		char coluna = s.charAt(0);
		int linha = Integer.parseInt(s.substring(1));
		return new PosicaoXadrez(coluna, linha);
		}
		catch(RuntimeException e){
			throw new InputMismatchException("Erro lendo a posicão de xadrez, valores validos de 1 ao 8");
		}
	}
	public static void primeitaPartida(PartidaDeXadrez partida, List<PecaXadrez> capturada) {
		impressaoTabuleiro(partida.getPecas());
		System.out.println();
		pecasCapturadas(capturada);
		System.out.println();
		System.out.println("turno " + partida.getTurno());
		System.out.println("Esperando o jogador com a cor " + partida.getCorJogador());
	}
	
	public static void impressaoTabuleiro(PecaXadrez[][] peca) {
		for(int i = 0; i < peca.length; i++) {
			System.out.print((8 - i ) + " ");
			for(int j = 0; j < peca.length; j++) {
				impressaoPeca(peca[i][j],false);
				
			}
			System.out.println();
		}
		System.out.print("  a b c d i f g h");
	}
	public static void impressaoTabuleiro(PecaXadrez[][] peca, boolean[][] movimentosPossiveis) {
		for(int i = 0; i < peca.length; i++) {
			System.out.print((8 - i ) + " ");
			for(int j = 0; j < peca.length; j++) {
				impressaoPeca(peca[i][j], movimentosPossiveis[i][j]);
				
			}
			System.out.println();
		}
		System.out.print("  a b c d i f g h");
	}
	
	public static void impressaoPeca(PecaXadrez peca, boolean corDeFundo) {
		if(corDeFundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getCores() == Cores.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	private static void pecasCapturadas(List<PecaXadrez> capturada) {
		List<PecaXadrez> white = capturada.stream().filter(x -> x.getCores() == Cores.BRANCO).collect(Collectors.toList());
		List<PecaXadrez> preto = capturada.stream().filter(x -> x.getCores() == Cores.PRETO).collect(Collectors.toList());
		System.out.println("Peça capituradas: ");
		System.out.println("Brancas");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		System.out.println("Pretas");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(preto.toArray()));
	}
}
