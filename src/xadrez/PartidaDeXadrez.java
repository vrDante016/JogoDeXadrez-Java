package xadrez;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

@SuppressWarnings({ "unused", "unused", "unused" })
public class PartidaDeXadrez {
	
	private int turno;
	private Cores corJogadores;
	private Tabuleiro tabuleiro;

	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		corJogadores = Cores.BRANCO;
		iniciarPartida();
		
	}
	public int getTurno() {
		return turno;
	}
	public Cores getCorJogador() {
		return corJogadores;
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
	public boolean[][] movimentosPossiveis(PosicaoXadrez originPosicao){
		Posicao posicao =  originPosicao.toPosition();
		validarPosicaoDeOrigin(posicao);
		return tabuleiro.pecas(posicao).movimentosPossiveis();
	}

	public PecaXadrez iniciarJogoDeXadrez(PosicaoXadrez originPosicao, PosicaoXadrez alvoPosicao) {
		Posicao origin = originPosicao.toPosition();
		Posicao alvo = alvoPosicao.toPosition();
		validarPosicaoDeOrigin(origin);
		validarPosicaoDoAlvo(origin, alvo);
		Peca pecaCapturada = fazerMovimento(origin, alvo);
		turnoSeguinte();
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
		if(corJogadores != ((PecaXadrez)tabuleiro.pecas(posicao)).getCores()) {
			throw new XadrezExcecao("A peça escolhida não é sua");
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
	private void turnoSeguinte() {
		turno++;
		corJogadores  = (corJogadores == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
	}

	private void novoLugaDaPeca(char coluna, int linha, PecaXadrez pecaXadrez) {
		tabuleiro.pecaNoLugar(pecaXadrez, new PosicaoXadrez(coluna, linha).toPosition());
	}

	public void iniciarPartida() {
		novoLugaDaPeca('d',1, new Rei(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('d',2, new Torre(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('b',2, new Torre(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('b',1, new Torre(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('c',1, new Torre(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('c',2, new Torre(tabuleiro, Cores.BRANCO));
		
	}
}
