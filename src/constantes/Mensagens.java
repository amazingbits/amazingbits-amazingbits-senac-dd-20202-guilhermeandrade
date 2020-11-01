package constantes;

public class Mensagens {
	
	/* USU�RIO */
	
	//cadastro
	public static final String USUARIO_ERRO_NOME = "O nome deve possuir mais que 3 caracteres!";
	public static final String USUARIO_ERRO_DATA = "A data de nascimento deve ser v�lida!";
	public static final String USUARIO_ERRO_INSTITUICAO = "A institui��o deve possuir mais de 3 caracteres";
	public static final String USUARIO_ERRO_CPF = "O CPF deve ser v�lido!";
	public static final String USUARIO_ERRO_CPF_EXISTE = "J� existe um usu�rio com este CPF no banco de dados!";
	public static final String USUARIO_ERRO_SEXO = "Por favor, selecione um sexo para cadastrar o usu�rio!";
	public static final String USUARIO_ERRO_TIPO = "Por favor, escolha um tipo de usu�rio pra cadastrar!";
	public static final String USUARIO_SUCESSO = "Usu�rio cadastrado com sucesso!";
	
	/* edi��o */
	public static final String USUARIO_EDICAO_ERRO_SOMENTE_UM_REGISTRO = "Selecione um registro na tabela!";
	public static final String USUARIO_EDICAO_ERRO_APENAS_UM_REGISTRO = "Selecione somente um registro da tabela";
	
	public static final String USUARIO_EDICAO_SUCESSO = "Usu�rio editado com sucesso!";

	/* exclus�o */
	public static final String USUARIO_EXCLUSAO_ERRO_SOMENTE_UM_REGISTRO = "Selecione um registro na tabela!";
	public static final String USUARIO_EXCLUSAO_ERRO_APENAS_UM_REGISTRO = "Selecione somente um registro da tabela";
	public static final String USUARIO_EXCLUSAO_CONFIRMACAO = "Tem certeza que deseja excluir este usu�rio?";
	public static final String USUARIO_EXCLUSAO_SUCESSO = "Usu�rio exclu�do com sucesso!";
	
	
	/* VACINAS */
	
	//cadastro
	public static final String VACINA_ERRO_DESCRICAO = "O nome da vacina deve ter pelo menos 3 caracteres!";
	public static final String VACINA_ERRO_ESTAGIO = "Selecione um est�gio de pesquisa";
	public static final String VACINA_ERRO_PESQUISADOR = "Selecione um pesquisador!";
	public static final String VACINA_ERRO_DATA = "Insira uma data v�lida!";
	public static final String VACINA_ERRO_NOME_JA_EXISTE = "J� existe uma vacina com este nome!";
	public static final String VACINA_CADASTRO_SUCESSO = "Vacina cadastrada com sucesso!";
	
	//editar
	public static final String VACINA_EDICAO_SUCESSO = "Vacina editada com sucesso!";
	
	//excluir
	public static final String VACINA_EXCLUSAO_SUCESSO = "Vacina exclu�da com sucesso!";
	
	
	/* VACINA��O */
	public static final String VACINACAO_ERRO_USUARIO = "Selecione um usu�rio!";
	public static final String VACINACAO_ERRO_VACINA = "Selecione uma vacina!";
	public static final String VACINACAO_ERRO_REACAO = "Selecione uma rea��o!";
	public static final String VACINACAO_CADASTRO_SUCESSO = "Vacina��o cadastrada com sucesso!";
	
	public static final String VACINACAO_EDICAO_SUCESSO = "Vacina��o editada com sucesso!";
	
	public static final String VACINACAO_EXCLUSAO_SUCESSO = "Vacina��o exclu�da com sucesso!";
}
