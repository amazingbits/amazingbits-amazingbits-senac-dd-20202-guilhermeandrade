package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Conexao;
import model.vo.SexoVO;

public class SexoDAO {
	
	/**
	 * lista todos os sexos do banco de dados
	 * @return
	 */
	public ArrayList<SexoVO> listar() {
		String sql = "select * from sexo";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<SexoVO> lista = new ArrayList<SexoVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				SexoVO sexo = new SexoVO();
				sexo.setId(Integer.parseInt(result.getString("id")));
				sexo.setDescricao(result.getString("descricao"));
				lista.add(sexo);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao carregar os sexos.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return lista;
	}

}
