package model.seletores;

import constantes.Config;

public class UsuarioSeletor {
	
	private int codigo;
	private String nascimento;
	private String nome;

	private String sexo;
	private String cpf;
	private String tipoUsuario;
	private String instituicao;
	
	private boolean isFiltro;
	private Integer offset;
	private Integer registroPorPagina;
	
	public UsuarioSeletor() {
		this.offset = 0;
		this.registroPorPagina = Config.REGISTRO_POR_PAGINA;
		this.isFiltro = false;
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
	 * @return the nascimento
	 */
	public String getNascimento() {
		return nascimento;
	}

	/**
	 * @param nascimento the nascimento to set
	 */
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
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
	 * @return the tipoUsuario
	 */
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario the tipoUsuario to set
	 */
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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
