package model.dto;

public class VacinacaoDTO {
	
	private int codigo;
	private String usuario;
	private String vacina;
	private String reacao;
	private String data;
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

}
