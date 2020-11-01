package controller;

import java.util.ArrayList;

import model.bo.VacinaBO;
import model.dto.VacinaDTO;
import model.seletores.VacinaSeletor;
import model.vo.VacinaVO;

public class VacinaController {
	
	public ArrayList<VacinaDTO> listar(VacinaSeletor filtro) {
		VacinaBO vacinaBO = new VacinaBO();
		return vacinaBO.listar(filtro);
	}
	
	public boolean cadastrar(VacinaVO vacina) {
		VacinaBO vacinaBO = new VacinaBO();
		return vacinaBO.cadastrar(vacina);
	}
	
	public boolean atualizar(VacinaVO vacina) {
		VacinaBO vacinaBO = new VacinaBO();
		return vacinaBO.atualizar(vacina);
	}
	
	public boolean excluir(VacinaVO vacina) {
		VacinaBO vacinaBO = new VacinaBO();
		return vacinaBO.excluir(vacina);
	}

}
