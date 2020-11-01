package model.vo;

public class UsuarioVO {
	
	private int id;
	private String nome;
	private String datanascimento;
	private SexoVO sexo;
	private String cpf;
	private TipoUsuarioVO tipo;
	private String instituicao;
	
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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the datanascimento
	 */
	public String getDatanascimento() {
		return datanascimento;
	}
	/**
	 * @param datanascimento the datanascimento to set
	 */
	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
	}
	/**
	 * @return the sexo
	 */
	public SexoVO getSexo() {
		return sexo;
	}
	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(SexoVO sexo) {
		this.sexo = sexo;
	}
	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}
	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/**
	 * @return the tipo
	 */
	public TipoUsuarioVO getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoUsuarioVO tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the instituicao
	 */
	public String getInstituicao() {
		return instituicao;
	}
	/**
	 * @param instituicao the instituicao to set
	 */
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

}
