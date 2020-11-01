package controller;

import java.util.ArrayList;

import model.bo.SexoBO;
import model.vo.SexoVO;

public class SexoController {
	
	private SexoBO usuarioBO = new SexoBO();
	
	public ArrayList<SexoVO> listar() {
		return this.usuarioBO.listar();
	}

}
