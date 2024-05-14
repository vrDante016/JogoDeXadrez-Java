package xadrez.pecas;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaXadrez;

public class Peoes extends PecaXadrez{

	public Peoes(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
		
	}
	public String toString() {
		return "P";
	}
	@Override
	public boolean[][] movimentosPossiveis() {
	boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];
		
		Posicao p = new Posicao(0,0);
		
		if(getCores() == Cores.BRANCO) {
			p.setValues(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			p.setValues(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)&& getTabuleiro().posicaoExiste(p2) && !getTabuleiro().existeUmaPeca(p2) && getContadorMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if(getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if(getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
		}
		if(getCores() == Cores.PRETO) {
			p.setValues(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			p.setValues(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().existeUmaPeca(p)&& getTabuleiro().posicaoExiste(p2) && !getTabuleiro().existeUmaPeca(p2) && getContadorMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if(getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if(getTabuleiro().posicaoExiste(p) && aquiTemUmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				
			}
		}
		return mat;
	}

}
