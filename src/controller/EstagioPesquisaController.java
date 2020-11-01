package controller;

import java.util.ArrayList;

import model.bo.EstagioPesquisaBO;
import model.vo.EstagioPesquisaVO;

public class EstagioPesquisaController {
	
	public ArrayList<EstagioPesquisaVO> listar() {
		EstagioPesquisaBO estagioPesquisaBO = new EstagioPesquisaBO();
		return estagioPesquisaBO.listar();
	}

}
