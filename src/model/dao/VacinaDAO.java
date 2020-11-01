package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Conexao;
import model.dto.VacinaDTO;
import model.seletores.VacinaSeletor;
import model.vo.VacinaVO;

public class VacinaDAO {
	
	/**
	 * lista todas as vacinas
	 * @param filtro
	 * @return
	 */
	public ArrayList<VacinaDTO> listar(VacinaSeletor filtro) {
		
		boolean isFiltro = filtro.isFiltro();
		boolean primeiroRegistro = false;
		
		String sql = "SELECT * FROM vw_vacina ";
		
		if(isFiltro) {
			
			String descricao = filtro.getDescricao().trim().length() > 0 ? filtro.getDescricao().trim() : "";
			String pais = filtro.getPais().trim().length() > 0 ? filtro.getPais().trim() : "";
			String estagio = filtro.getEstagio().trim().length() > 0 ? filtro.getEstagio().trim() : "";
			String data = filtro.getData().trim().length() > 0 ? filtro.getData().trim() : "";
			String pesquisador = filtro.getPesquisador().trim().length() > 0 ? filtro.getPesquisador().trim() : "";
			
			if(descricao.length() > 0) {
				if(!primeiroRegistro) {
					sql = sql.concat(" WHERE ");
					primeiroRegistro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" descricao LIKE '%");
				sql = sql.concat(descricao + "%' ");
			}
			
			if(pais.length() > 0) {
				if(!primeiroRegistro) {
					sql = sql.concat(" WHERE ");
					primeiroRegistro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" pais LIKE '%");
				sql = sql.concat(pais + "%' ");
			}
			
			if(estagio.length() > 0 && !estagio.equalsIgnoreCase("Todos")) {
				if(!primeiroRegistro) {
					sql = sql.concat(" WHERE ");
					primeiroRegistro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" estagio = '");
				sql = sql.concat(estagio + "' ");
			}
			
			if(data.length() > 0) {
				if(!primeiroRegistro) {
					sql = sql.concat(" WHERE ");
					primeiroRegistro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" data = '");
				sql = sql.concat(data + "' ");
			}
			
			if(pesquisador.length() > 0 && !pesquisador.equalsIgnoreCase("Todos")) {
				if(!primeiroRegistro) {
					sql = sql.concat(" WHERE ");
					primeiroRegistro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" pesquisador LIKE '%");
				sql = sql.concat(pesquisador + "%' ");
			}
			
		}
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<VacinaDTO> lista = new ArrayList<VacinaDTO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				VacinaDTO vacina = new VacinaDTO();
				vacina.setCodigo(Integer.parseInt(result.getString("codigo")));
				vacina.setDescricao(result.getString("descricao"));
				vacina.setPais(result.getString("pais"));
				vacina.setEstagio(result.getString("estagio"));
				vacina.setData(result.getString("data"));
				vacina.setPesquisador(result.getString("pesquisador"));
				lista.add(vacina);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao retornar lista de vacinas.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return lista;
	}
	
	/**
	 * verifica se existe vacina com o mesmo nome
	 * @param nome
	 * @return
	 */
	public boolean verificarSeExisteVacinaPorNome(String nome) {
		String sql = "SELECT * FROM vacina WHERE descricao = '" + nome + "'";
		
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
			System.out.println("Falha ao verificar vacina por nome.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(contador == 0) return false;
		return true;
	}
	
	/**
	 * retorna nome de vacina por id
	 * @param id
	 * @return
	 */
	public String retornarNomePorId(int id) {
		String sql = "SELECT descricao FROM vacina WHERE id = " + id;
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		String nome = "";
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				nome = result.getString("descricao");
			}
		} catch(SQLException e) {
			System.out.println("Falha ao buscar nome da vacina por id.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return nome;
	}
	
	
	/**
	 * cadastra uma nova vacina
	 * @param vacina
	 * @return
	 */
	public boolean cadastrar(VacinaVO vacina) {
		String sql = "INSERT INTO vacina (descricao, pais_de_origem, estagio, data_inicio, pesquisador) VALUES (";
		sql = sql.concat("'" + vacina.getDescricao() + "', ");
		sql = sql.concat("'" + vacina.getPaisDeOrigem() + "', ");
		sql = sql.concat(vacina.getEstagio().getId() + ", ");
		sql = sql.concat("'" + vacina.getDataInicio() + "', ");
		sql = sql.concat(vacina.getPesquisador().getId() + ")");
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			System.out.println("Falha ao cadastrar vacina.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(result == 0) return false;
		return true;
		
	}
	
	/**
	 * atualiza uma vacina
	 * @param vacina
	 * @return
	 */
	public boolean atualizar(VacinaVO vacina) {
		String sql = "UPDATE vacina SET ";
		sql = sql.concat("descricao = '" + vacina.getDescricao() + "', ");
		sql = sql.concat("pais_de_origem = '" + vacina.getPaisDeOrigem() + "', ");
		sql = sql.concat("estagio = " + vacina.getEstagio().getId() + ", ");
		sql = sql.concat("data_inicio = '" + vacina.getDataInicio() + "', ");
		sql = sql.concat("pesquisador = " + vacina.getPesquisador().getId());
		sql = sql.concat(" WHERE id = " + vacina.getId());
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			System.out.println("Falha ao editar vacina.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(result == 0) return false;
		return true;
	}
	
	/**
	 * exclui uma vacina
	 * @param vacina
	 * @return
	 */
	public boolean excluir(VacinaVO vacina) {
		String sql = "DELETE FROM vacina WHERE id = " + vacina.getId();
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			System.out.println("Falha ao excluir vacina.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(result == 0) return false;
		return true;
	}

}
