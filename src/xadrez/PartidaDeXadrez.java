package xadrez;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import tabuleiroJogo.tabuleiroExcecao;
import xadrez.pecas.Bispo;

@SuppressWarnings({ "unused", "unused", "unused" })
public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		iniciarPartida();
	}

	public PecaXadrez[][] getPecas() {

		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinha()][tabuleiro.getColuna()];
		for (int i = 0; i < tabuleiro.getLinha(); i++) {
			for (int j = 0; j < tabuleiro.getColuna(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.pecas(i, j);
			}
		}
		return mat;
	}

	public PecaXadrez iniciarJogoDeXadrez(PosicaoXadrez originPosicao, PosicaoXadrez alvoPosicao) {
		Posicao origin = originPosicao.toPosition();
		Posicao alvo = alvoPosicao.toPosition();
		validarPosicaoDeOrigin(origin);
		validarPosicaoDoAlvo(origin, alvo);
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
		if (!tabuleiro.existeUmaPeca(posicao)) {
			throw new XadrezExcecao("Não existe peça nessa posicao");
		}
		if (!tabuleiro.pecas(posicao).ePossivelMover()) {
			throw new XadrezExcecao("Não é possivel mover!");
		}
	}
	private void validarPosicaoDoAlvo(Posicao origin, Posicao alvo) {
		if(!tabuleiro.pecas(origin).movimentosPossiveis(alvo)) {
			throw new XadrezExcecao("A pessa escolhida não pode ser movida para o lugar escolhido!");
		}
	}

	private void novoLugaDaPeca(char coluna, int linha, PecaXadrez pecaXadrez) {
		tabuleiro.pecaNoLugar(pecaXadrez, new PosicaoXadrez(coluna, linha).toPosition());
	}

	public void iniciarPartida() {
		tabuleiro.pecaNoLugar(new Bispo(tabuleiro, Cores.BRANCO), new Posicao(2, 1));
	}
}
