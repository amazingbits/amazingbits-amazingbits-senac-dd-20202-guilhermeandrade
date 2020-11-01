package model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import constantes.Mensagens;
import model.dao.UsuarioDAO;
import model.dto.UsuarioDTO;
import model.seletores.UsuarioSeletor;
import model.vo.UsuarioVO;
import utils.Formatador;
import utils.Validador;
import constantes.Mensagens;

public class UsuarioBO {

	private Validador validador = new Validador();
	private Formatador formatador = new Formatador();

	public ArrayList<UsuarioDTO> listar(UsuarioSeletor filtro) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.listar(filtro);
	}
	
	public ArrayList<UsuarioVO> listarPesquisadores() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.listarPesquisadores();
	}

	public boolean cadastrar(UsuarioVO usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		/* VALIDAÇÕES */
		int validador = 0;

		// nome
		if (usuario.getNome().length() < 3 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_NOME, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// tipo
		if (usuario.getTipo().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_TIPO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// sexo
		if (usuario.getSexo().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_SEXO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// data nascimento
		if (!this.validador.validarData(usuario.getDatanascimento()) && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_DATA, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// instituição
		if (usuario.getTipo().getId() == 1 && usuario.getInstituicao().length() < 3 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_INSTITUICAO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// cpf válido
		if (!this.validador.validarCPF(usuario.getCpf()) && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_CPF, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// cpf já existe no banco
		if (usuarioDAO.verificarSeExisteUsuarioPorCpf(usuario.getCpf()) && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_CPF_EXISTE, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		if (validador == 0) {
			usuario.setDatanascimento(this.formatador.formatarDataBRLParaSQL(usuario.getDatanascimento()));
			return usuarioDAO.cadastrar(usuario);
		}
		return false;

	}

	public boolean atualizar(UsuarioVO usuario) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		String cpfBanco = usuarioDAO.retornarCpfPorId(usuario.getId());

		/* VALIDAÇÕES */
		int validador = 0;

		// nome
		if (usuario.getNome().length() < 3 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_NOME, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// tipo
		if (usuario.getTipo().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_TIPO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// sexo
		if (usuario.getSexo().getId() == 0 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_SEXO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// data nascimento
		if (!this.validador.validarData(usuario.getDatanascimento()) && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_DATA, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// instituição
		if (usuario.getTipo().getId() == 1 && usuario.getInstituicao().length() < 3 && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_INSTITUICAO, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// cpf válido
		if (!this.validador.validarCPF(usuario.getCpf()) && validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_CPF, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		// cpf já existe no banco
		if (usuarioDAO.verificarSeExisteUsuarioPorCpf(usuario.getCpf()) && !cpfBanco.equalsIgnoreCase(usuario.getCpf())
				&& validador == 0) {
			JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_CPF_EXISTE, "ERRO", JOptionPane.ERROR_MESSAGE);
			validador++;
		}

		if (validador == 0) {
			usuario.setDatanascimento(this.formatador.formatarDataBRLParaSQL(usuario.getDatanascimento()));
			return usuarioDAO.atualizar(usuario);
		}
		return false;
	}

	public boolean excluir(UsuarioVO usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.excluir(usuario))
			return true;
		JOptionPane.showMessageDialog(null, Mensagens.USUARIO_ERRO_CPF_EXISTE, "ERRO", JOptionPane.ERROR_MESSAGE);
		return false;
	}

}
