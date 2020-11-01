package model.seletores;

import constantes.Config;

public class VacinaSeletor {
	
	private int codigo;
	private String descricao;
	private String pais;
	private String estagio;
	private String data;
	private String pesquisador;
	
	private boolean isFiltro;
	private Integer offset;
	private Integer registroPorPagina;
	
	public VacinaSeletor() {
		this.isFiltro = false;
		this.offset = 0;
		this.registroPorPagina = Config.REGISTRO_POR_PAGINA;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the estagio
	 */
	public String getEstagio() {
		return estagio;
	}

	/**
	 * @param estagio the estagio to set
	 */
	public void setEstagio(String estagio) {
		this.estagio = estagio;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the pesquisador
	 */
	public String getPesquisador() {
		return pesquisador;
	}

	/**
	 * @param pesquisador the pesquisador to set
	 */
	public void setPesquisador(String pesquisador) {
		this.pesquisador = pesquisador;
	}

	/**
	 * @return the isFiltro
	 */
	public boolean isFiltro() {
		return isFiltro;
	}

	/**
	 * @param isFiltro the isFiltro to set
	 */
	public void setFiltro(boolean isFiltro) {
		this.isFiltro = isFiltro;
	}

	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * @return the registroPorPagina
	 */
	public Integer getRegistroPorPagina() {
		return registroPorPagina;
	}

	/**
	 * @param registroPorPagina the registroPorPagina to set
	 */
	public void setRegistroPorPagina(Integer registroPorPagina) {
		this.registroPorPagina = registroPorPagina;
	}

}
