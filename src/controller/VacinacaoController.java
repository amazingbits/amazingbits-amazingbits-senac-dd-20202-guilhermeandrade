package controller;

import java.util.ArrayList;

import model.bo.VacinacaoBO;
import model.dto.VacinacaoDTO;
import model.seletores.VacinacaoSeletor;
import model.vo.VacinacaoVO;

public class VacinacaoController {

	public ArrayList<VacinacaoDTO> listar(VacinacaoSeletor filtro) {
		VacinacaoBO vacinacaoBO = new VacinacaoBO();
		return vacinacaoBO.listar(filtro);
	}
	
	public boolean cadastrar(VacinacaoVO vacinacao) {
		VacinacaoBO vacinacaoBO = new VacinacaoBO();
		return vacinacaoBO.cadastrar(vacinacao);
	}
	
	public boolean atualizar(VacinacaoVO vacinacao) {
		VacinacaoBO vacinacaoBO = new VacinacaoBO();
		return vacinacaoBO.editar(vacinacao);
	}
	
	public boolean excluir(VacinacaoVO vacinacao) {
		VacinacaoBO vacinacaoBO = new VacinacaoBO();
		return vacinacaoBO.excluir(vacinacao);
	}
	
}
