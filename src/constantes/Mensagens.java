package constantes;

public class Mensagens {
	
	/* USUÁRIO */
	
	//cadastro
	public static final String USUARIO_ERRO_NOME = "O nome deve possuir mais que 3 caracteres!";
	public static final String USUARIO_ERRO_DATA = "A data de nascimento deve ser válida!";
	public static final String USUARIO_ERRO_INSTITUICAO = "A instituição deve possuir mais de 3 caracteres";
	public static final String USUARIO_ERRO_CPF = "O CPF deve ser válido!";
	public static final String USUARIO_ERRO_CPF_EXISTE = "Já existe um usuário com este CPF no banco de dados!";
	public static final String USUARIO_ERRO_SEXO = "Por favor, selecione um sexo para cadastrar o usuário!";
	public static final String USUARIO_ERRO_TIPO = "Por favor, escolha um tipo de usuário pra cadastrar!";
	public static final String USUARIO_SUCESSO = "Usuário cadastrado com sucesso!";
	
	/* edição */
	public static final String USUARIO_EDICAO_ERRO_SOMENTE_UM_REGISTRO = "Selecione um registro na tabela!";
	public static final String USUARIO_EDICAO_ERRO_APENAS_UM_REGISTRO = "Selecione somente um registro da tabela";
	
	public static final String USUARIO_EDICAO_SUCESSO = "Usuário editado com sucesso!";

	/* exclusão */
	public static final String USUARIO_EXCLUSAO_ERRO_SOMENTE_UM_REGISTRO = "Selecione um registro na tabela!";
	public static final String USUARIO_EXCLUSAO_ERRO_APENAS_UM_REGISTRO = "Selecione somente um registro da tabela";
	public static final String USUARIO_EXCLUSAO_CONFIRMACAO = "Tem certeza que deseja excluir este usuário?";
	public static final String USUARIO_EXCLUSAO_SUCESSO = "Usuário excluído com sucesso!";
	
	
	/* VACINAS */
	
	//cadastro
	public static final String VACINA_ERRO_DESCRICAO = "O nome da vacina deve ter pelo menos 3 caracteres!";
	public static final String VACINA_ERRO_ESTAGIO = "Selecione um estágio de pesquisa";
	public static final String VACINA_ERRO_PESQUISADOR = "Selecione um pesquisador!";
	public static final String VACINA_ERRO_DATA = "Insira uma data válida!";
	public static final String VACINA_ERRO_NOME_JA_EXISTE = "Já existe uma vacina com este nome!";
	public static final String VACINA_CADASTRO_SUCESSO = "Vacina cadastrada com sucesso!";
	
	//editar
	public static final String VACINA_EDICAO_SUCESSO = "Vacina editada com sucesso!";
	
	//excluir
	public static final String VACINA_EXCLUSAO_SUCESSO = "Vacina excluída com sucesso!";
	
	
	/* VACINAÇÃO */
	public static final String VACINACAO_ERRO_USUARIO = "Selecione um usuário!";
	public static final String VACINACAO_ERRO_VACINA = "Selecione uma vacina!";
	public static final String VACINACAO_ERRO_REACAO = "Selecione uma reação!";
	public static final String VACINACAO_CADASTRO_SUCESSO = "Vacinação cadastrada com sucesso!";
	
	public static final String VACINACAO_EDICAO_SUCESSO = "Vacinação editada com sucesso!";
	
	public static final String VACINACAO_EXCLUSAO_SUCESSO = "Vacinação excluída com sucesso!";
}
