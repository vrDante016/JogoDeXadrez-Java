package xadrez;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Tabuleiro;

public class PecaXadrez extends Peca {
	
	private Cores cores;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro);
		this.cores = cores;
	}

	protected Cores getCores() {
		return cores;
	}

	
}
