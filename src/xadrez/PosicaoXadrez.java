package xadrez;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.tabuleiroExcecao;

public class PosicaoXadrez {
	
	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if(coluna <  'a' || coluna > 'h' || linha < 1 || linha > 8) {
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
	protected Posicao toPosition() { 
		return new Posicao(8 - linha, coluna - 'a');
	}
	protected static PosicaoXadrez paraPosicao(Posicao posicao) {
		return new PosicaoXadrez((char)('a' - posicao.getColuna()), 8 - posicao.getLinha());
	}
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
	
}

