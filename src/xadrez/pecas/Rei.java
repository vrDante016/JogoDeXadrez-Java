package xadrez.pecas;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	public Rei(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "R";
	}

	public boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().pecas(posicao);
		return p == null || p.getCores() != getCores();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];

		Posicao p = new Posicao(0, 0);

		// acima
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}
		// abaixo
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}
		// esquerda
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}
		// direita
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}
		//
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}

		return mat;
	}

	

}
