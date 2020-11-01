package controller;

import java.util.ArrayList;

import model.bo.UsuarioBO;
import model.dto.UsuarioDTO;
import model.seletores.UsuarioSeletor;
import model.vo.UsuarioVO;

public class UsuarioController {
	
	public ArrayList<UsuarioDTO> listar(UsuarioSeletor filtro) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.listar(filtro);
	}
	
	public ArrayList<UsuarioVO> listarPesquisadores() {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.listarPesquisadores();
	}
	
	public boolean cadastrar(UsuarioVO usuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.cadastrar(usuario);
	}
	
	public boolean atualizar(UsuarioVO usuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.atualizar(usuario);
	}
	
	public boolean excluir(UsuarioVO usuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.excluir(usuario);
	}

}
