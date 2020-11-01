package model.dao;

import java.sql.*;
import java.util.ArrayList;

import model.Conexao;
import model.vo.EstagioPesquisaVO;

public class EstagioPesquisaDAO {
	
	/**
	 * lista todos os estágios de pesquisa do banco de dados
	 * @return
	 */
	public ArrayList<EstagioPesquisaVO> listar() {
		String sql = "select * from estagio_pesquisa";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<EstagioPesquisaVO> lista = new ArrayList<EstagioPesquisaVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				EstagioPesquisaVO estagioPesquisa = new EstagioPesquisaVO();
				estagioPesquisa.setId(Integer.parseInt(result.getString("id")));
				estagioPesquisa.setDescricao(result.getString("descricao"));
				lista.add(estagioPesquisa);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao carregar os estágios de pesquisa.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return lista;
	}

}
