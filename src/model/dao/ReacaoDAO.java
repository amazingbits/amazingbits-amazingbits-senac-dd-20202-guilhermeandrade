package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Conexao;
import model.vo.ReacaoVO;

public class ReacaoDAO {
	
	/**
	 * lista todas as reações de vacina do banco de dados
	 * @return
	 */
	public ArrayList<ReacaoVO> listar() {
		String sql = "select * from reacao";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<ReacaoVO> lista = new ArrayList<ReacaoVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				ReacaoVO reacao = new ReacaoVO();
				reacao.setId(Integer.parseInt(result.getString("id")));
				reacao.setDescricao(result.getString("descricao"));
				lista.add(reacao);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao carregar as reações à vacina.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return lista;
	}

}
