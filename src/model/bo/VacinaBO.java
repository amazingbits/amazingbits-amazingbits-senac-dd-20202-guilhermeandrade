package model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import constantes.Mensagens;
import model.dao.VacinaDAO;
import model.dto.VacinaDTO;
import model.seletores.VacinaSeletor;
import model.vo.VacinaVO;
import utils.Formatador;
import utils.Validador;

public class VacinaBO {

	private Formatador formatador = new Formatador();
	private Validador validador = new Validador();
	
	public ArrayList<VacinaDTO> listar(VacinaSeletor filtro) {
		VacinaDAO vacinaDAO = new VacinaDAO();
		return vacinaDAO.listar(filtro);
	}
	
	public boolean cadastrar(VacinaVO vacina) {
		VacinaDAO vacinaDAO = new VacinaDAO();
		int validador = 0;
		
		//descrição
		if(vacina.getDescricao().trim().length() < 3 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_DESCRICAO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}
		
		//estágio
		if(vacina.getEstagio().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_ESTAGIO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}
		
		//data
		if(!this.validador.validarData(vacina.getDataInicio()) && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_DATA, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}
		
		//pesquisador
		if(vacina.getPesquisador().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_PESQUISADOR, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}
		
		//nome já existe?
		if(vacinaDAO.verificarSeExisteVacinaPorNome(vacina.getDescricao())) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_NOME_JA_EXISTE, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}
		
		if(validador == 0) {
			vacina.setDataInicio(this.formatador.formatarDataBRLParaSQL(vacina.getDataInicio()));
			return vacinaDAO.cadastrar(vacina);
		}
		return false;
		
	}
	
	public boolean atualizar(VacinaVO vacina) {
		
		VacinaDAO vacinaDAO = new VacinaDAO();
		int validador = 0;
		
		//descrição
		if(vacina.getDescricao().trim().length() < 3 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_DESCRICAO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}
		
		//estágio
		if(vacina.getEstagio().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_ESTAGIO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}
		
		//data
		if(!this.validador.validarData(vacina.getDataInicio()) && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_DATA, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}
		
		//pesquisador
		if(vacina.getPesquisador().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_PESQUISADOR, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}
		
		//nome já existe?
		if(vacinaDAO.verificarSeExisteVacinaPorNome(vacina.getDescricao()) && !vacinaDAO.retornarNomePorId(vacina.getId()).equalsIgnoreCase(vacina.getDescricao()) ) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_NOME_JA_EXISTE, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}
		
		if(validador == 0) {
			vacina.setDataInicio(formatador.formatarDataBRLParaSQL(vacina.getDataInicio()));
			return vacinaDAO.atualizar(vacina);
		}
		return false;
		
	}
	
	public boolean excluir(VacinaVO vacina) {
		VacinaDAO vacinaDAO = new VacinaDAO();
		return vacinaDAO.excluir(vacina);
	}
	
}
