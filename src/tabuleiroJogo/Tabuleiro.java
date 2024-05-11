package tabuleiroJogo;

public class Tabuleiro {
	
	private Integer linha;
	private Integer coluna;
	private Peca[][] peca;
	
	public Tabuleiro(Integer linha, Integer coluna) {
		this.linha = linha;
		this.coluna = coluna;
		peca = new Peca[linha][coluna];
	}

	protected Integer getLinha() {
		return linha;
	}

	protected void setLinha(Integer linha) {
		this.linha = linha;
	}

	protected Integer getColuna() {
		return coluna;
	}

	protected void setColuna(Integer coluna) {
		this.coluna = coluna;
	}
	
	
}
