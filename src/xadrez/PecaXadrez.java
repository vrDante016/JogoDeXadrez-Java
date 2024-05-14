package xadrez;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;

public abstract class PecaXadrez extends Peca {
	
	private Cores cores;
	private int contadorMovimentos;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro);
		this.cores = cores;
	}

	public Cores getCores() {
		return cores;
	}
	public int getContadorMovimentos() {
		return contadorMovimentos;
	}
	public void acrescentarMovimentosContador() {
		contadorMovimentos++;
	}
	public void decrementarMovimentos() {
		contadorMovimentos--;
	}
	public PosicaoXadrez getPartidaXadres() {
		return PosicaoXadrez.paraPosicao(posicao);
	}
	
	protected boolean aquiTemUmaPecaInimiga(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().pecas(posicao);
		return p != null && p.getCores() != cores;
	}
	
}
