package model.bo;

import java.util.ArrayList;

import model.dao.EstagioPesquisaDAO;
import model.vo.EstagioPesquisaVO;

public class EstagioPesquisaBO {
	
	public ArrayList<EstagioPesquisaVO> listar() {
		EstagioPesquisaDAO estagioPesquisaDAO = new EstagioPesquisaDAO();
		return estagioPesquisaDAO.listar();
	}

}
