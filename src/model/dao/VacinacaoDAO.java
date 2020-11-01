package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Conexao;
import model.dto.VacinacaoDTO;
import model.seletores.VacinacaoSeletor;
import model.vo.VacinacaoVO;

public class VacinacaoDAO {
	
	public ArrayList<VacinacaoDTO> listar(VacinacaoSeletor filtro) {
		
		String sql = "SELECT * FROM vw_vacinacao ";
		boolean primeiroRegistro = false;
		
		if(filtro.isFiltro()) {
			
			String usuario = filtro.getUsuario().trim().length() > 0 ? filtro.getUsuario().trim() : "";
			String vacina = filtro.getVacina().trim().length() > 0 ? filtro.getVacina().trim() : "";
			String reacao = filtro.getReacao().trim().length() > 0 ? filtro.getReacao().trim() : "";
			String data = filtro.getData().trim().length() > 0 ? filtro.getData().trim() : "";
			
			if(usuario.length() > 0 && !usuario.equalsIgnoreCase("Todos")) {
				if(!primeiroRegistro) {
					sql = sql.concat(" WHERE ");
					primeiroRegistro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" usuario = '");
				sql = sql.concat(usuario + "' ");
			}
			
			if(vacina.length() > 0 && !vacina.equalsIgnoreCase("Todos")) {
				if(!primeiroRegistro) {
					sql = sql.concat(" WHERE ");
					primeiroRegistro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" vacina = '");
				sql = sql.concat(vacina + "' ");
			}
			
			if(reacao.length() > 0 && !reacao.equalsIgnoreCase("Todos")) {
				if(!primeiroRegistro) {
					sql = sql.concat(" WHERE ");
					primeiroRegistro = true;
				} else {
					sql = sql.concat(" AND ");
				}
				sql = sql.concat(" reacao = '");
				sql = sql.concat(reacao + "' ");
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
		}
		
		sql = sql.concat(" LIMIT " + filtro.getRegistroPorPagina());
		sql = sql.concat(" OFFSET " + filtro.getOffset());
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<VacinacaoDTO> lista = new ArrayList<VacinacaoDTO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				VacinacaoDTO vacinacao = new VacinacaoDTO();
				vacinacao.setCodigo(Integer.parseInt(result.getString("codigo")));
				vacinacao.setUsuario(result.getString("usuario"));
				vacinacao.setVacina(result.getString("vacina"));
				vacinacao.setReacao(result.getString("reacao"));
				vacinacao.setData(result.getString("data"));
				lista.add(vacinacao);
			}
		} catch(SQLException e) {
			System.out.println("Falha ao listar todas as vacinações.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return lista;
	}
	
	public boolean cadastrar(VacinacaoVO vacinacao) {
		String sql = "INSERT INTO vacinacao (idusuario, idvacina, reacao, data) VALUES (";
		sql = sql.concat(vacinacao.getUsuario().getId() + ", ");
		sql = sql.concat(vacinacao.getVacina().getId() + ", ");
		sql = sql.concat(vacinacao.getReacao().getId() + ", ");
		sql = sql.concat("'" + vacinacao.getData() + "')");
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			System.out.println("Falha ao inserir vacinação.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(result != 0) return true;
		return false;
	}
	
	public boolean atualizar(VacinacaoVO vacinacao) {
		String sql = "UPDATE vacinacao SET ";
		sql = sql.concat("idusuario = " + vacinacao.getUsuario().getId() + ", ");
		sql = sql.concat("idvacina = " + vacinacao.getVacina().getId() + ", ");
		sql = sql.concat("reacao = " + vacinacao.getReacao().getId() + ", ");
		sql = sql.concat("data = '" + vacinacao.getData() + "' ");
		sql = sql.concat("WHERE id = " + vacinacao.getId());
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			System.out.println("Falha ao inserir vacinação.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(result != 0) return true;
		return false;
	}
	
	public boolean excluir(VacinacaoVO vacinacao) {
		String sql = "DELETE FROM vacinacao WHERE id = " + vacinacao.getId();
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			System.out.println("Falha ao inserir vacinação.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(result != 0) return true;
		return false;
	}

}
