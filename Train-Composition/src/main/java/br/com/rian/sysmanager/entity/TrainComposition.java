package br.com.rian.sysmanager.entity;

/**
 * 
 * @author rian
 *
 */
public class TrainComposition implements Identificavel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4693033425469354071L;

	private Long codigo;

	private String nome;

	private Integer vagoes[];

	private Integer novoVagao;

	public TrainComposition(Long codigo, String nome, Integer vagoes[]) {
		
		this.nome = nome;
		
		this.vagoes = vagoes;
		
		this.codigo = codigo;
	}
	
	public TrainComposition() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer[] getVagoes() {
		return vagoes;
	}

	public void setVagoes(Integer[] vagoes) {
		this.vagoes = vagoes;
	}

	public Integer getNovoVagao() {
		return novoVagao;
	}

	public void setNovoVagao(Integer novoVagao) {
		this.novoVagao = novoVagao;
	}

	@Override
	public Long getCodigo() {
		return codigo;
	}

	@Override
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrainComposition other = (TrainComposition) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
