package controller;

import java.util.ArrayList;

import model.bo.ReacaoBO;
import model.vo.ReacaoVO;

public class ReacaoController {
	
	public ArrayList<ReacaoVO> listar() {
		ReacaoBO reacaoBO = new ReacaoBO();
		return reacaoBO.listar();
	}

}
