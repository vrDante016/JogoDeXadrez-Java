package tabuleiroJogo;

public class Tabuleiro {
	
	private int linha;
	private int coluna;
	private Peca[][] peca;
	
	public Tabuleiro(int linha, int coluna){
		if(linha < 1 && coluna <1) {
			throw new tabuleiroExcecao("Erro ao criar o tabuleiro: é preciso ter uma linha e uma coluna");
		}
		this.linha = linha;
		this.coluna = coluna;
		peca = new Peca[linha][coluna];
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public Peca pecas(int linha, int coluna){
		if(!posicaoExiste(linha, coluna)) {
			throw new tabuleiroExcecao("Posição não esta no tabuleiro");
		}
		return peca[linha][coluna];
	}
	public Peca pecas(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new tabuleiroExcecao("Posição não esta no tabuleiro");
		}
		return peca[posicao.getLinha()][posicao.getColuna()];
	}
	public void pecaNoLugar(Peca pecas, Posicao posicao) {
		if(existeUmaPeca(posicao)) {
			throw new tabuleiroExcecao("Ja tem uma peça nessa posição" + posicao);
		}
		peca[posicao.getLinha()][posicao.getColuna()] = pecas;
		pecas.posicao = posicao;
	}
	public Peca removerPeca(Posicao posicao) {
		if(pecas(posicao) == null) {
			return null;
		}
		Peca aux = pecas(posicao);
		aux.posicao = null;
		peca[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
	private boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha <  this.linha && coluna >= 0 && coluna < this.coluna;
	}
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
		
	}
	public boolean existeUmaPeca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new tabuleiroExcecao("Posição não esta no tabuleiro");
		}
		return pecas(posicao) != null;
	}
}
