package xadrez.pecas;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cores;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;

public class Peoes extends PecaXadrez {

	private PartidaDeXadrez partidaXadrez;

	public Peoes(Tabuleiro tabuleiro, Cores cores, PartidaDeXadrez partidaXadrez) {
		super(tabuleiro, cores);
		this.partidaXadrez = partidaXadrez;

	}

	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];

		Posicao p = new Posicao(0, 0);

		if (getCores() == Cores.BRANCO) {
			p.setValues(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;

			}
			p.setValues(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p) && getTabuleiro().posicaoExiste(p2)
					&& !getTabuleiro().existeUmaPeca(p2) && getContadorMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;

			}
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;

			}
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;

			}
			// movimento especial en passat nas peças brancas
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && aquiTemUmaPecaInimiga(esquerda)
						&& getTabuleiro().pecas(esquerda) == partidaXadrez.getEnPassatVulnerable())
					;
				{
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}

				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && aquiTemUmaPecaInimiga(direita)
						&& getTabuleiro().pecas(direita) == partidaXadrez.getEnPassatVulnerable())
					;
				{
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}
		}
		if (getCores() == Cores.PRETO) {
			p.setValues(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;

			}
			p.setValues(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p) && getTabuleiro().posicaoExiste(p2)
					&& !getTabuleiro().existeUmaPeca(p2) && getContadorMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;

			}
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;

			}
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;

			}
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && aquiTemUmaPecaInimiga(esquerda)
						&& getTabuleiro().pecas(esquerda) == partidaXadrez.getEnPassatVulnerable())
					;
				{
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}

				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && aquiTemUmaPecaInimiga(direita)
						&& getTabuleiro().pecas(direita) == partidaXadrez.getEnPassatVulnerable())
					;
				{
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}

		}
		return mat;
	}

}
