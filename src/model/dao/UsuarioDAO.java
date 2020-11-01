package model.dao;

import java.util.ArrayList;
import java.sql.*;

import model.Conexao;
import model.dto.UsuarioDTO;
import model.seletores.UsuarioSeletor;
import model.vo.UsuarioVO;

public class UsuarioDAO {
	
	/**
	 * listar usuários otimizado com filtro
	 * @param filtro
	 * @return
	 */
	public ArrayList<UsuarioDTO> listar(UsuarioSeletor filtro) {
		
		String sql = "SELECT * FROM vw_usuario";
		
		if(filtro.isFiltro()) {
			String nome = (filtro.getNome().trim().length() > 0) ? filtro.getNome().trim() : "";
			String dataNascimento = (filtro.getNascimento().trim().length() > 0) ? filtro.getNascimento() : "";
			String sexo = (filtro.getSexo().trim().length() > 0) ? filtro.getSexo() : "";
			String cpf = (filtro.getCpf().trim().length() > 0) ? filtro.getCpf() : "";
			String tipo = (filtro.getTipoUsuario().trim().length() > 0) ? filtro.getTipoUsuario() : "";
			String instituicao = (filtro.getInstituicao().trim().length() > 0) ? filtro.getInstituicao() : "";
			
			boolean primeiroFiltro = false;
			
			if(nome.length() > 0) {
				if(!primeiroFiltro) {
					sql = sql.concat(" WHERE ");
					primeiroFiltro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" nome LIKE '%");
				sql = sql.concat(nome +"%' ");
			}
			
			if(dataNascimento.length() > 0) {
				if(!primeiroFiltro) {
					sql = sql.concat(" WHERE ");
					primeiroFiltro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" nascimento = '");
				sql = sql.concat(dataNascimento + "'");
			}
			
			
			if(sexo.length() > 0 && !sexo.equalsIgnoreCase("Todos")) {
				if(!primeiroFiltro) {
					sql = sql.concat(" WHERE ");
					primeiroFiltro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" sexo = '");
				sql = sql.concat(sexo + "'");
			}
			
			
			if(cpf.length() > 0) {
				if(!primeiroFiltro) {
					sql = sql.concat(" WHERE ");
					primeiroFiltro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" cpf LIKE '%");
				sql = sql.concat(cpf + "%'");
			}
			
			
			if(tipo.length() > 0 && !tipo.equalsIgnoreCase("Todos")) {
				if(!primeiroFiltro) {
					sql = sql.concat(" WHERE ");
					primeiroFiltro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" tipo = '");
				sql = sql.concat(tipo + "'");
			}
			
			
			if(instituicao.length() > 0) {
				if(!primeiroFiltro) {
					sql = sql.concat(" WHERE ");
					primeiroFiltro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" instituicao LIKE '%");
				sql = sql.concat(instituicao + "%'");
			}
		}
		
		sql = sql.concat(" LIMIT " + filtro.getRegistroPorPagina());
		sql = sql.concat(" OFFSET " + filtro.getOffset());
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				UsuarioDTO usuario = new UsuarioDTO();
				usuario.setCodigo(Integer.parseInt(result.getString("codigo")));
				usuario.setNome(result.getString("nome"));
				usuario.setNascimento(result.getString("nascimento"));
				usuario.setSexo(result.getString("sexo"));
				usuario.setCpf(result.getString("cpf"));
				usuario.setTipoUsuario(result.getString("tipo"));
				usuario.setInstituicao(result.getString("instituicao"));
				lista.add(usuario);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao carregar lista de usuários.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return lista;
		
	}
	
	/**
	 * traz todos os pesquisadores
	 * @return
	 */
	public ArrayList<UsuarioVO> listarPesquisadores() {
		String sql = "SELECT * FROM usuario WHERE tipo = 1";
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<UsuarioVO> lista = new ArrayList<UsuarioVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				UsuarioVO usuario = new UsuarioVO();
				usuario.setId(Integer.parseInt(result.getString("id")));
				usuario.setNome(result.getString("nome"));
				lista.add(usuario);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao listar pesquisadores.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return lista;
	}
	
	
	/**
	 * verificar se existe usuário por cpf
	 * @param cpf
	 * @return
	 */
	public boolean verificarSeExisteUsuarioPorCpf(String cpf) {
		String sql = "SELECT * FROM usuario WHERE cpf = '" + cpf + "'";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		int contador = 0;
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				contador++;
			}
		} catch(SQLException e) {
			System.out.println("Erro ao verificar usuário por CPF.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(contador == 0) return false;
		return true;
	}
	
	/**
	 * retorna cpf de usuário através do ID
	 * @param id
	 * @return
	 */
	public String retornarCpfPorId(int id) {
		String sql = "SELECT cpf FROM usuario WHERE id = " + id + " LIMIT 1";
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		String cpf = "";
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				cpf = result.getString("cpf");
			}
		} catch(SQLException e) {
			System.out.println("Falha ao retornar CPF de usuário por ID.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return cpf;
	}
	
	/**
	 * cadastra um novo usuário
	 * @param usuario
	 * @return
	 */
	public boolean cadastrar(UsuarioVO usuario) {
		
		String sql = "INSERT INTO usuario (nome, datanascimento, sexo, cpf, tipo, instituicao) VALUES (";
		sql = sql.concat("'"+usuario.getNome()+"', ");
		sql = sql.concat("'"+usuario.getDatanascimento()+"', ");
		sql = sql.concat(usuario.getSexo().getId()+", ");
		sql = sql.concat("'"+usuario.getCpf()+"', ");
		sql = sql.concat(usuario.getTipo().getId()+", ");
		sql = sql.concat("'"+usuario.getInstituicao()+"')");
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			System.out.println("Falha ao cadastrar usuário.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(result == 0) return false;
		return true;
	}
	
	/**
	 * atualiza o usuário
	 * @param usuario
	 * @return
	 */
	public boolean atualizar(UsuarioVO usuario) {
		
		String sql = "UPDATE usuario SET ";
		sql = sql.concat("nome = '" + usuario.getNome() + "', ");
		sql = sql.concat("datanascimento = '" + usuario.getDatanascimento() + "', ");
		sql = sql.concat("sexo = " + usuario.getSexo().getId() + ", ");
		sql = sql.concat("cpf = '" + usuario.getCpf() + "', ");
		sql = sql.concat("tipo = " + usuario.getTipo().getId() + ", ");
		sql = sql.concat("instituicao = '" + usuario.getInstituicao() + "' ");
		sql = sql.concat("WHERE id = " + usuario.getId());
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			System.out.println("Falha ao atualizar usuário.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(result == 0) return false;
		return true;
	}
	
	/**
	 * excluir usuário
	 * @param usuario
	 * @return
	 */
	public boolean excluir(UsuarioVO usuario) {
		
		String sql = "DELETE FROM usuario WHERE id = " + usuario.getId();
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			System.out.println("Falha ao excluir usuário.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(result == 0) return false;
		return true;
		
	}

}
