package controller;

import java.util.ArrayList;

import model.bo.TipoUsuarioBO;
import model.vo.TipoUsuarioVO;

public class TipoUsuarioController {
	
	private TipoUsuarioBO tipoUsuarioBO = new TipoUsuarioBO();
	
	public ArrayList<TipoUsuarioVO> listar() {
		return this.tipoUsuarioBO.listar();
	}

}
