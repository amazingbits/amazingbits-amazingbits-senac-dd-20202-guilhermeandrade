package model.vo;

public class VacinacaoVO {
	
	private int id;
	private UsuarioVO usuario;
	private VacinaVO vacina;
	private ReacaoVO reacao;
	private String data;
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
	 * @return the usuario
	 */
	public UsuarioVO getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the vacina
	 */
	public VacinaVO getVacina() {
		return vacina;
	}
	/**
	 * @param vacina the vacina to set
	 */
	public void setVacina(VacinaVO vacina) {
		this.vacina = vacina;
	}
	/**
	 * @return the reacao
	 */
	public ReacaoVO getReacao() {
		return reacao;
	}
	/**
	 * @param reacao the reacao to set
	 */
	public void setReacao(ReacaoVO reacao) {
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
