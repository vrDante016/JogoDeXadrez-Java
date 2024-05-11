package xadrez;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.pecas.Bispo;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		iniciarPartida();
	}
	
	public PecaXadrez[][] getPecas(){
		
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinha()][tabuleiro.getColuna()];
		for(int i = 0; i < tabuleiro.getLinha(); i ++) {
			for(int j = 0; j < tabuleiro.getColuna(); j++) {
				mat[i][j] = (PecaXadrez)tabuleiro.pecas(i, j);
			}
		}
		return mat;
	}
	private void novoLugaDaPeca(char coluna, int linha, PecaXadrez pecaXadrez) {
		tabuleiro.pecaNoLugar(pecaXadrez, new PosicaoXadrez(coluna, linha).toPosition());
	}
	
	public void iniciarPartida() {
		tabuleiro.pecaNoLugar(new Bispo(tabuleiro, Cores.BRANCO), new Posicao(2,1));
	}
}
