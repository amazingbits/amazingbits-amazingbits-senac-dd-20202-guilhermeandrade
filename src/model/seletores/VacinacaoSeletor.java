package model.seletores;

import constantes.Config;

public class VacinacaoSeletor {

	private int codigo;
	private String usuario;
	private String vacina;
	private String reacao;
	private String data;
	
	private boolean isFiltro;
	private Integer offset;
	private Integer registroPorPagina;
	
	public VacinacaoSeletor() {
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the vacina
	 */
	public String getVacina() {
		return vacina;
	}

	/**
	 * @param vacina the vacina to set
	 */
	public void setVacina(String vacina) {
		this.vacina = vacina;
	}

	/**
	 * @return the reacao
	 */
	public String getReacao() {
		return reacao;
	}

	/**
	 * @param reacao the reacao to set
	 */
	public void setReacao(String reacao) {
		this.reacao = reacao;
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
