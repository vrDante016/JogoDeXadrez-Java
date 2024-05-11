package xadrez;

import tabuleiroJogo.tabuleiroExcecao;

public class PosicaoXadrez {
	
	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if(coluna <  'a' || coluna > 'h' && linha < 1 || linha > 8) {
			throw new tabuleiroExcecao("Fora da posição do tabuleiro!");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	protected char getColuna() {
		return coluna;
	}

	protected int getLinha() {
		return linha;
	}
	
	
}

