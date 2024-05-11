package xadrez;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import tabuleiroJogo.tabuleiroExcecao;
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
	public PecaXadrez pecaComida(PosicaoXadrez originPosicao, PosicaoXadrez alvoPosicao) {
		Posicao origin = originPosicao.toPosition();
		Posicao alvo = alvoPosicao.toPosition();
		validarPosicaoDeOrigin(origin);
		Peca pecaCapturada = fazerMovimento(origin, alvo);
		return (PecaXadrez) pecaCapturada;
	}
	private Peca fazerMovimento(Posicao origin, Posicao alvo) {
		Peca p = tabuleiro.removerPeca(origin);
		Peca pecaCapturada = tabuleiro.removerPeca(alvo);
		tabuleiro.pecaNoLugar(p, alvo);
		return pecaCapturada;
	}
	
	private void validarPosicaoDeOrigin(Posicao posicao) {
		if(!tabuleiro.existeUmaPeca(posicao)) {
			throw new XadrezExcecao("Não existe peça nessa posicao");
		}
		if(!tabuleiro.pecas(posicao).ePossivelMover()) {
			throw new XadrezExcecao("Não é possivel mover!");
		}
	}
	
	private void novoLugaDaPeca(char coluna, int linha, PecaXadrez pecaXadrez) {
		tabuleiro.pecaNoLugar(pecaXadrez, new PosicaoXadrez(coluna, linha).toPosition());
	}
	
	public void iniciarPartida() {
		tabuleiro.pecaNoLugar(new Bispo(tabuleiro, Cores.BRANCO), new Posicao(2,1));
	}
}
