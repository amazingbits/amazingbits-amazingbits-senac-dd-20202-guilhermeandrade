package model.bo;

import java.util.ArrayList;

import model.dao.ReacaoDAO;
import model.vo.ReacaoVO;

public class ReacaoBO {
	
	public ArrayList<ReacaoVO> listar() {
		ReacaoDAO reacaoDAO = new ReacaoDAO();
		return reacaoDAO.listar();
	}

}
