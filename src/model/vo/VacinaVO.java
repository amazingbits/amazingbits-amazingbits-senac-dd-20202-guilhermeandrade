package model.vo;

public class VacinaVO {

	private int id;
	private String descricao;
	private String paisDeOrigem;
	private EstagioPesquisaVO estagio;
	private String dataInicio;
	private UsuarioVO pesquisador;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the paisDeOrigem
	 */
	public String getPaisDeOrigem() {
		return paisDeOrigem;
	}
	/**
	 * @param paisDeOrigem the paisDeOrigem to set
	 */
	public void setPaisDeOrigem(String paisDeOrigem) {
		this.paisDeOrigem = paisDeOrigem;
	}
	/**
	 * @return the estagio
	 */
	public EstagioPesquisaVO getEstagio() {
		return estagio;
	}
	/**
	 * @param estagio the estagio to set
	 */
	public void setEstagio(EstagioPesquisaVO estagio) {
		this.estagio = estagio;
	}
	/**
	 * @return the dataInicio
	 */
	public String getDataInicio() {
		return dataInicio;
	}
	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	/**
	 * @return the pesquisador
	 */
	public UsuarioVO getPesquisador() {
		return pesquisador;
	}
	/**
	 * @param pesquisador the pesquisador to set
	 */
	public void setPesquisador(UsuarioVO pesquisador) {
		this.pesquisador = pesquisador;
	}
	
}
