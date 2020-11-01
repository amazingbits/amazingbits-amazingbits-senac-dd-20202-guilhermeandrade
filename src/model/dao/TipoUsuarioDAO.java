package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Conexao;
import model.vo.TipoUsuarioVO;

public class TipoUsuarioDAO {
	
	/**
	 * lista todos os tipos de usuário do banco de dados
	 * @return
	 */
	public ArrayList<TipoUsuarioVO> listar() {
		String sql = "select * from tipo_usuario";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<TipoUsuarioVO> lista = new ArrayList<TipoUsuarioVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				TipoUsuarioVO tipoUsuario = new TipoUsuarioVO();
				tipoUsuario.setId(Integer.parseInt(result.getString("id")));
				tipoUsuario.setDescricao(result.getString("descricao"));
				lista.add(tipoUsuario);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao carregar os tipos de usuário.\nCausa: " + e.getMessage());
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return lista;
	}

}
