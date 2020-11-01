package model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import constantes.Mensagens;
import model.dao.VacinacaoDAO;
import model.dto.VacinacaoDTO;
import model.seletores.VacinacaoSeletor;
import model.vo.VacinacaoVO;
import utils.Formatador;
import utils.Validador;

public class VacinacaoBO {

	private Validador validador = new Validador();
	private Formatador formatador = new Formatador();

	public ArrayList<VacinacaoDTO> listar(VacinacaoSeletor filtro) {
		VacinacaoDAO vacinacaoDAO = new VacinacaoDAO();
		return vacinacaoDAO.listar(filtro);
	}

	public boolean cadastrar(VacinacaoVO vacinacao) {
		VacinacaoDAO vacinacaoDAO = new VacinacaoDAO();
		int validador = 0;

		if (!this.validador.validarData(vacinacao.getData())) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_DATA, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		if (vacinacao.getUsuario().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINACAO_ERRO_USUARIO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		if (vacinacao.getVacina().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINACAO_ERRO_VACINA, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		if (vacinacao.getReacao().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINACAO_ERRO_REACAO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		if (validador == 0) {
			vacinacao.setData(this.formatador.formatarDataBRLParaSQL(vacinacao.getData()));
			return vacinacaoDAO.cadastrar(vacinacao);
		}
		return false;
	}

	public boolean editar(VacinacaoVO vacinacao) {
		VacinacaoDAO vacinacaoDAO = new VacinacaoDAO();
		int validador = 0;

		if (!this.validador.validarData(vacinacao.getData())) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINA_ERRO_DATA, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}
		
		if (vacinacao.getUsuario().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINACAO_ERRO_USUARIO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		if (vacinacao.getVacina().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINACAO_ERRO_VACINA, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		if (vacinacao.getReacao().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.VACINACAO_ERRO_REACAO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		if (validador == 0) {
			vacinacao.setData(this.formatador.formatarDataBRLParaSQL(vacinacao.getData()));
			return vacinacaoDAO.atualizar(vacinacao);
		}
		return false;
	}

	public boolean excluir(VacinacaoVO vacinacao) {
		VacinacaoDAO vacinacaoDAO = new VacinacaoDAO();
		return vacinacaoDAO.excluir(vacinacao);
	}

}
