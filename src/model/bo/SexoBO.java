package model.bo;

import java.util.ArrayList;

import model.dao.SexoDAO;
import model.vo.SexoVO;

public class SexoBO {

	private SexoDAO sexoDAO = new SexoDAO();
	
	public ArrayList<SexoVO> listar() {
		return this.sexoDAO.listar();
	}
	
}
