package xadrez.pecas;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];

		Posicao p = new Posicao(0, 0);

	
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}

		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}

		
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}
	
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() -1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() - 1,  p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}

		
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() - 1, p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}

		
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}
		
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() + 1, p.getColuna() - 1);;
		}
		if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;

		}

		return mat;
	}

}
