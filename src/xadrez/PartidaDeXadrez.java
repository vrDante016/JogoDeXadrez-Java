package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peoes;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

@SuppressWarnings({ "unused", "unused", "unused" })
public class PartidaDeXadrez {

	private int turno;
	private Cores corJogadores;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;

	private List<Peca> pecasDoTabuleiro = new ArrayList();
	private List<Peca> pecasCapturadas = new ArrayList();

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

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
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

	public boolean[][] movimentosPossiveis(PosicaoXadrez originPosicao) {
		Posicao posicao = originPosicao.toPosition();
		validarPosicaoDeOrigin(posicao);
		return tabuleiro.pecas(posicao).movimentosPossiveis();
	}

	public PecaXadrez iniciarJogoDeXadrez(PosicaoXadrez originPosicao, PosicaoXadrez alvoPosicao) {
		Posicao origin = originPosicao.toPosition();
		Posicao alvo = alvoPosicao.toPosition();
		validarPosicaoDeOrigin(origin);
		validarPosicaoDoAlvo(origin, alvo);
		Peca pecaCapturada = fazerMovimento(origin, alvo);
		if (testCheck(corJogadores)) {
			desfazerMovimento(origin, alvo, pecaCapturada);
			throw new XadrezExcecao("Voce não pode se colocar me check");

		}
		check = (testCheck(oponente(corJogadores))) ? true : false;
		if (testCheckMate(oponente(corJogadores))) {
			checkMate = true;
		} else {
			turnoSeguinte();
		}

		return (PecaXadrez) pecaCapturada;
	}

	private Peca fazerMovimento(Posicao origin, Posicao alvo) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origin);
		p.acrescentarMovimentosContador();
		Peca pecaCapturada = tabuleiro.removerPeca(alvo);
		tabuleiro.pecaNoLugar(p, alvo);
		if (pecaCapturada != null) {
			pecasDoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		// movimento especial roque lado do rei
		if (p instanceof Rei && alvo.getColuna() == origin.getColuna() + 2) {
			Posicao originT = new Posicao(origin.getLinha(), origin.getColuna() + 3);
			Posicao alvoT = new Posicao(origin.getLinha(), origin.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(originT);
			tabuleiro.pecaNoLugar(torre, alvoT);
			torre.acrescentarMovimentosContador();
		}
		// movimento especial roque lado da rainha
		if (p instanceof Rei && alvo.getColuna() == origin.getColuna() - 2) {
			Posicao originT = new Posicao(origin.getLinha(), origin.getColuna() - 4);
			Posicao alvoT = new Posicao(origin.getLinha(), origin.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(originT);
			tabuleiro.pecaNoLugar(torre, alvoT);
			torre.acrescentarMovimentosContador();
		}
		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao origin, Posicao alvo, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(alvo);
		p.decrementarMovimentos();
		tabuleiro.pecaNoLugar(p, origin);

		if (pecaCapturada != null) {
			tabuleiro.pecaNoLugar(pecaCapturada, alvo);
			pecasCapturadas.remove(pecaCapturada);
			pecasDoTabuleiro.add(pecaCapturada);
		}

		// movimento especial roque lado do rei
		if (p instanceof Rei && alvo.getColuna() == origin.getColuna() + 2) {
			Posicao originT = new Posicao(origin.getColuna(), origin.getColuna() + 3);
			Posicao alvoT = new Posicao(origin.getColuna(), origin.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(alvoT);
			tabuleiro.pecaNoLugar(torre, originT);
			torre.decrementarMovimentos();
		}
		// movimento especial roque lado da rainha
		if (p instanceof Rei && alvo.getColuna() == origin.getColuna() - 2) {
			Posicao originT = new Posicao(origin.getColuna(), origin.getColuna() - 4);
			Posicao alvoT = new Posicao(origin.getColuna(), origin.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(alvoT);
			tabuleiro.pecaNoLugar(torre, originT);
			torre.decrementarMovimentos();
		}
	}

	private void validarPosicaoDeOrigin(Posicao posicao) {
		if (!tabuleiro.existeUmaPeca(posicao)) {
			throw new XadrezExcecao("Não existe peça nessa posicao");
		}
		if (corJogadores != ((PecaXadrez) tabuleiro.pecas(posicao)).getCores()) {
			throw new XadrezExcecao("A peça escolhida não é sua");
		}
		if (!tabuleiro.pecas(posicao).ePossivelMover()) {
			throw new XadrezExcecao("Não é possivel mover!");
		}
	}

	private void validarPosicaoDoAlvo(Posicao origin, Posicao alvo) {
		if (!tabuleiro.pecas(origin).movimentosPossiveis(alvo)) {
			throw new XadrezExcecao("A pessa escolhida não pode ser movida para o lugar escolhido!");
		}
	}

	private void turnoSeguinte() {
		turno++;
		corJogadores = (corJogadores == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
	}

	private Cores oponente(Cores cor) {
		return (cor == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
	}

	private PecaXadrez king(Cores cor) {
		List<Peca> list = pecasDoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCores() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Aqui não ha rei da cor" + cor);
	}

	private boolean testCheckMate(Cores cores) {
		if (!testCheck(cores)) {
			return false;
		}
		List<Peca> list = pecasDoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCores() == cores)
				.collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinha(); i++) {
				for (int j = 0; j < tabuleiro.getColuna(); j++) {
					if (mat[i][j]) {
						Posicao origin = ((PecaXadrez) p).getPartidaXadres().toPosition();
						Posicao alvo = new Posicao(i, j);
						Peca pecaCapturada = fazerMovimento(origin, alvo);
						boolean testCheck = testCheck(cores);
						desfazerMovimento(origin, alvo, pecaCapturada);
						if (!testCheck) {
							return false;
						}

					}
				}
			}
		}
		return true;
	}

	private boolean testCheck(Cores cores) {
		Posicao posicaoDoRei = king(cores).getPartidaXadres().toPosition();
		List<Peca> pecasOponentes = pecasDoTabuleiro.stream()
				.filter(x -> ((PecaXadrez) x).getCores() == oponente(cores)).collect(Collectors.toList());
		for (Peca p : pecasOponentes) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private void novoLugaDaPeca(char coluna, int linha, PecaXadrez pecaXadrez) {
		tabuleiro.pecaNoLugar(pecaXadrez, new PosicaoXadrez(coluna, linha).toPosition());
		pecasDoTabuleiro.add(pecaXadrez);
	}

	public void iniciarPartida() {
		novoLugaDaPeca('a', 1, new Torre(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('h', 1, new Torre(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('d', 1, new Rainha(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('b', 1, new Cavalo(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('g', 1, new Cavalo(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('c', 1, new Bispo(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('f', 1, new Bispo(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('a', 2, new Peoes(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('b', 2, new Peoes(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('c', 2, new Peoes(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('d', 2, new Peoes(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('e', 2, new Peoes(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('f', 2, new Peoes(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('g', 2, new Peoes(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('h', 2, new Peoes(tabuleiro, Cores.BRANCO));
		novoLugaDaPeca('e', 1, new Rei(tabuleiro, Cores.BRANCO, this));

		novoLugaDaPeca('a', 8, new Torre(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('h', 8, new Torre(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('d', 8, new Rainha(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('b', 8, new Cavalo(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('g', 8, new Cavalo(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('c', 8, new Bispo(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('f', 8, new Bispo(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('a', 7, new Peoes(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('b', 7, new Peoes(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('c', 7, new Peoes(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('d', 7, new Peoes(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('e', 7, new Peoes(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('f', 7, new Peoes(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('g', 7, new Peoes(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('h', 7, new Peoes(tabuleiro, Cores.PRETO));
		novoLugaDaPeca('e', 8, new Rei(tabuleiro, Cores.PRETO, this));

	}
}
