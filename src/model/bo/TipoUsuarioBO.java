package model.bo;

import java.util.ArrayList;

import model.dao.TipoUsuarioDAO;
import model.vo.TipoUsuarioVO;

public class TipoUsuarioBO {
	
	private TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
	
	public ArrayList<TipoUsuarioVO> listar() {
		return this.tipoUsuarioDAO.listar();
	}

}
