package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import constantes.Config;
import constantes.Mensagens;
import controller.SexoController;
import controller.TipoUsuarioController;
import controller.UsuarioController;
import model.dto.UsuarioDTO;
import model.seletores.UsuarioSeletor;
import model.vo.SexoVO;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;
import utils.Formatador;
import utils.Validador;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GerenciarUsuarios extends JPanel {
	private JTextField txtNome;
	private JTextField txtInstituicao;
	private JTable table;
	private int offset = 0;
	private Formatador formatador = new Formatador();
	private Validador validador = new Validador();
	private UsuarioSeletor filtro = new UsuarioSeletor();
	
	/**
	 * Create the panel.
	 */
	public GerenciarUsuarios() {
		setLayout(null);
		
		SexoController sexoController = new SexoController();
		ArrayList<SexoVO> listaSexo = sexoController.listar();
		
		TipoUsuarioController tipoUsuarioController = new TipoUsuarioController();
		ArrayList<TipoUsuarioVO> listaTipoUsuario = tipoUsuarioController.listar();
		
		JLabel lblTitulo = new JLabel("Gerenciamento de Usu\u00E1rios");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblTitulo.setBounds(10, 11, 680, 26);
		add(lblTitulo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblNome.setBounds(10, 48, 76, 14);
		add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 63, 197, 36);
		add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNascimento = new JLabel("Dt. Nascimento");
		lblNascimento.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblNascimento.setBounds(480, 48, 210, 14);
		add(lblNascimento);
		
		JFormattedTextField txtNascimento = new JFormattedTextField();
		formatador.mascara("##/##/####", txtNascimento);
		txtNascimento.setBounds(480, 63, 210, 36);
		add(txtNascimento);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblSexo.setBounds(241, 48, 76, 14);
		add(lblSexo);
		
		JComboBox cbSexo = new JComboBox();
		cbSexo.setBounds(241, 63, 197, 36);
		cbSexo.addItem("0 - Todos");
		for(int i = 0; i < listaSexo.size(); i++) {
			
			int index = listaSexo.get(i).getId();
			String value = listaSexo.get(i).getDescricao();
			
			cbSexo.addItem(index + " - " + value);
		}
		add(cbSexo);
		
		JFormattedTextField txtCpf = new JFormattedTextField();
		formatador.mascara("###.###.###-##", txtCpf);
		txtCpf.setBounds(480, 121, 210, 36);
		add(txtCpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblCpf.setBounds(480, 106, 210, 14);
		add(lblCpf);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setBounds(241, 121, 198, 36);
		add(cbTipo);
		cbTipo.addItem("0 - Todos");
		for(int i = 0; i < listaTipoUsuario.size(); i++) {
			
			int index = listaTipoUsuario.get(i).getId();
			String value = listaTipoUsuario.get(i).getDescricao();
			
			cbTipo.addItem(index + " - " + value);
		}
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblTipo.setBounds(241, 106, 76, 14);
		add(lblTipo);
		
		txtInstituicao = new JTextField();
		txtInstituicao.setColumns(10);
		txtInstituicao.setBounds(10, 121, 197, 36);
		add(txtInstituicao);
		
		JLabel lblInsituicao = new JLabel("Institui\u00E7\u00E3o");
		lblInsituicao.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblInsituicao.setBounds(10, 106, 76, 14);
		add(lblInsituicao);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(282, 168, 89, 23);
		add(btnFiltrar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(381, 168, 110, 23);
		add(btnCadastrar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(501, 168, 89, 23);
		add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(601, 168, 89, 23);
		add(btnExcluir);
		
		JButton btnAnterior = new JButton("<");
		btnAnterior.setBounds(10, 439, 89, 23);
		add(btnAnterior);
		
		JButton btnProxima = new JButton(">");
		btnProxima.setBounds(601, 441, 89, 23);
		add(btnProxima);
		
		/**
		 * TABELA
		 */
		
		/* carregar itens da tabela */
		UsuarioController usuarioController = new UsuarioController();
		ArrayList<UsuarioDTO> listaUsuarios = usuarioController.listar(this.filtro);
		
		/* ======estados dos botões de paginação===== */
		if (listaUsuarios.size() < Config.REGISTRO_POR_PAGINA) {
			btnProxima.setEnabled(false);
		} else {
			btnProxima.setEnabled(true);
		}

		if (this.offset == 0) {
			btnAnterior.setEnabled(false);
		} else {
			btnAnterior.setEnabled(true);
		}
		/* ======================================== */
		
		/* colunas */
		String[] colunas = {"Código", "Nome", "Nascimento", "Sexo", "CPF", "Tipo", "Instituição"};
		
		/* definindo modelo da tabela */
		DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		/* construindo a tabela seguindo o modelo criado */
		final JTable tabela = new JTable(modeloTabela);
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		tabela.setPreferredScrollableViewportSize(new Dimension(500, 50));
		tabela.setFillsViewportHeight(true);
		
		/* populando tabela */
		for(int i = 0; i < listaUsuarios.size(); i++) {
			modelo.addRow(new Object[] {
				listaUsuarios.get(i).getCodigo(),
				listaUsuarios.get(i).getNome(),
				listaUsuarios.get(i).getNascimento(),
				listaUsuarios.get(i).getSexo(),
				listaUsuarios.get(i).getCpf(),
				listaUsuarios.get(i).getTipoUsuario(),
				listaUsuarios.get(i).getInstituicao()
			});
		}
		
		/* imprimindo tabela */
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 202, 680, 228);
		add(scrollPane);
		
		/**
		 * EVENTOS
		 */
		
		/* cbTipo */
		txtInstituicao.setEnabled(false);
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int selected = cbTipo.getSelectedIndex();
				if(selected == 1) {
					txtInstituicao.setEnabled(true);
				} else {
					txtInstituicao.setText("");
					txtInstituicao.setEnabled(false);
				}
				
			}
		});
		
		/* Cadastrar */
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = txtNome.getText().trim().replaceAll("[^A-Za-z ]+", "");
				String nascimento = txtNascimento.getText().trim().replace(" ", "");
				String cpf = txtCpf.getText().trim().replace(".", "").replace("-", "");
				int sexo = formatador.retornarIndice(cbSexo.getSelectedItem().toString());
				int tipo = formatador.retornarIndice(cbTipo.getSelectedItem().toString());
				String instituicao = txtInstituicao.getText();
				
				UsuarioVO usuario = new UsuarioVO();
				SexoVO sexoVO = new SexoVO();
				TipoUsuarioVO tipoVO = new TipoUsuarioVO();
				
				sexoVO.setId(sexo);
				tipoVO.setId(tipo);
				
				usuario.setNome(nome);
				usuario.setSexo(sexoVO);
				usuario.setDatanascimento(nascimento);
				usuario.setInstituicao(instituicao);
				usuario.setTipo(tipoVO);
				usuario.setCpf(cpf);
				
				UsuarioController usuarioController = new UsuarioController();
				if(usuarioController.cadastrar(usuario)) {
					JOptionPane.showMessageDialog(null, Mensagens.USUARIO_SUCESSO);
					txtNome.setText("");
					cbSexo.setSelectedIndex(0);
					txtNascimento.setText("");
					txtInstituicao.setText("");
					cbTipo.setSelectedIndex(0);
					txtCpf.setText("");
					
					//atualizar tabela
					modelo.setRowCount(0);
					ArrayList<UsuarioDTO> listaAtualizada = usuarioController.listar(filtro);
					
					offset = 0;
					
					/* ======estados dos botões de paginação===== */
					if (listaAtualizada.size() < Config.REGISTRO_POR_PAGINA) {
						btnProxima.setEnabled(false);
					} else {
						btnProxima.setEnabled(true);
					}

					if (offset == 0) {
						btnAnterior.setEnabled(false);
					} else {
						btnAnterior.setEnabled(true);
					}
					/* ======================================== */
					
					for(int i = 0; i < listaAtualizada.size(); i++) {
						modelo.addRow(new Object[] {
							listaAtualizada.get(i).getCodigo(),
							listaAtualizada.get(i).getNome(),
							listaAtualizada.get(i).getNascimento(),
							listaAtualizada.get(i).getSexo(),
							listaAtualizada.get(i).getCpf(),
							listaAtualizada.get(i).getTipoUsuario(),
							listaAtualizada.get(i).getInstituicao()	
						});
					}
				}
				
			}
		});
		
		/* filtrar */
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nome = txtNome.getText().trim().replaceAll("[^A-Za-z ]+", "");
				String nascimento = txtNascimento.getText().trim().replace(" ", "");
				String cpf = txtCpf.getText().trim().replace(".", "").replace("-", "").replace(" ", "");
				String instituicao = txtInstituicao.getText();
				String sexo = formatador.retornarValor(cbSexo.getSelectedItem().toString());
				String tipo = formatador.retornarValor(cbTipo.getSelectedItem().toString());
				
				if(nascimento.equalsIgnoreCase("//")) nascimento = "";
				if(nascimento.trim().length() != 10) nascimento = "";
				if(!validador.validarData(nascimento)) nascimento = "";
				
				UsuarioSeletor filtro = new UsuarioSeletor();
				filtro.setFiltro(true);
				filtro.setNome(nome);
				filtro.setNascimento(nascimento);
				filtro.setCpf(cpf);
				filtro.setInstituicao(instituicao);
				filtro.setSexo(sexo);
				filtro.setTipoUsuario(tipo);
				
				ArrayList<UsuarioDTO> listaFiltrada = usuarioController.listar(filtro);
				
				offset = 0;
				
				/* ======estados dos botões de paginação===== */
				if (listaFiltrada.size() < Config.REGISTRO_POR_PAGINA) {
					btnProxima.setEnabled(false);
				} else {
					btnProxima.setEnabled(true);
				}

				if (offset == 0) {
					btnAnterior.setEnabled(false);
				} else {
					btnAnterior.setEnabled(true);
				}
				/* ======================================== */
				
				//atualizar tabela
				modelo.setRowCount(0);
				for(int i = 0; i < listaFiltrada.size(); i++) {
					modelo.addRow(new Object[] {
						listaFiltrada.get(i).getCodigo(),
						listaFiltrada.get(i).getNome(),
						listaFiltrada.get(i).getNascimento(),
						listaFiltrada.get(i).getSexo(),
						listaFiltrada.get(i).getCpf(),
						listaFiltrada.get(i).getTipoUsuario(),
						listaFiltrada.get(i).getInstituicao()	
					});
				}
			}
		});
		
		/* editar */
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionada = tabela.getSelectedRow();
				int totalDeLinhasSelecionadas = tabela.getSelectedRowCount();
				int verificacao = 0;
				
				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, Mensagens.USUARIO_EDICAO_ERRO_SOMENTE_UM_REGISTRO);
					verificacao++;
				}

				if (totalDeLinhasSelecionadas > 1 && verificacao == 0) {
					JOptionPane.showMessageDialog(null, Mensagens.USUARIO_EDICAO_ERRO_APENAS_UM_REGISTRO);
					verificacao++;
				}
				
				if(verificacao == 0) {
					
					int id = (int) tabela.getValueAt(linhaSelecionada, 0);
					String nome = txtNome.getText().trim().replaceAll("[^A-Za-z ]+", "");
					String nascimento = txtNascimento.getText().trim().replace(" ", "");
					String cpf = txtCpf.getText().trim().replace(".", "").replace("-", "");
					int sexo = formatador.retornarIndice(cbSexo.getSelectedItem().toString());
					int tipo = formatador.retornarIndice(cbTipo.getSelectedItem().toString());
					String instituicao = txtInstituicao.getText();
					
					TipoUsuarioVO tipoVO = new TipoUsuarioVO();
					SexoVO sexoVO = new SexoVO();
					UsuarioVO usuario = new UsuarioVO();
					
					tipoVO.setId(tipo);
					sexoVO.setId(sexo);
					
					usuario.setId(id);
					usuario.setNome(nome);
					usuario.setDatanascimento(nascimento);
					usuario.setCpf(cpf);
					usuario.setInstituicao(instituicao);
					usuario.setSexo(sexoVO);
					usuario.setTipo(tipoVO);
					
					UsuarioController usuarioController = new UsuarioController();
					boolean atualizar = usuarioController.atualizar(usuario);
					if(atualizar) {
						JOptionPane.showMessageDialog(null, Mensagens.USUARIO_EDICAO_SUCESSO);
						txtNome.setText("");
						cbSexo.setSelectedIndex(0);
						txtNascimento.setText("");
						txtInstituicao.setText("");
						cbTipo.setSelectedIndex(0);
						txtCpf.setText("");
						
						//atualizar tabela
						modelo.setRowCount(0);
						ArrayList<UsuarioDTO> listaAtualizada = usuarioController.listar(filtro);
						
						offset = 0;
						
						/* ======estados dos botões de paginação===== */
						if (listaAtualizada.size() < Config.REGISTRO_POR_PAGINA) {
							btnProxima.setEnabled(false);
						} else {
							btnProxima.setEnabled(true);
						}

						if (offset == 0) {
							btnAnterior.setEnabled(false);
						} else {
							btnAnterior.setEnabled(true);
						}
						/* ======================================== */
						
						for(int i = 0; i < listaAtualizada.size(); i++) {
							modelo.addRow(new Object[] {
								listaAtualizada.get(i).getCodigo(),
								listaAtualizada.get(i).getNome(),
								listaAtualizada.get(i).getNascimento(),
								listaAtualizada.get(i).getSexo(),
								listaAtualizada.get(i).getCpf(),
								listaAtualizada.get(i).getTipoUsuario(),
								listaAtualizada.get(i).getInstituicao()	
							});
						}
					}
				}
				
			}
		});
		
		
		/* excluir */
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionada = tabela.getSelectedRow();
				int totalDeLinhasSelecionadas = tabela.getSelectedRowCount();
				int verificacao = 0;
				
				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, Mensagens.USUARIO_EXCLUSAO_ERRO_SOMENTE_UM_REGISTRO);
					verificacao++;
				}

				if (totalDeLinhasSelecionadas > 1 && verificacao == 0) {
					JOptionPane.showMessageDialog(null, Mensagens.USUARIO_EXCLUSAO_ERRO_APENAS_UM_REGISTRO);
					verificacao++;
				}
				
				if(verificacao == 0) {
					
					int confirm = JOptionPane.showConfirmDialog(null, Mensagens.USUARIO_EXCLUSAO_CONFIRMACAO);
					
					if(confirm == 0) {
						
						UsuarioController usuarioController = new UsuarioController();
						int id = (int) tabela.getValueAt(linhaSelecionada, 0);
						
						UsuarioVO usuario = new UsuarioVO();
						usuario.setId(id);
						
						if(usuarioController.excluir(usuario)) {
							JOptionPane.showMessageDialog(null, Mensagens.USUARIO_EXCLUSAO_SUCESSO);
							((DefaultTableModel) tabela.getModel()).removeRow(linhaSelecionada);
						}
						
					}
					
				}
				
			}
		});
		
		/* <<< */
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (offset > 0) {
					offset -= Config.REGISTRO_POR_PAGINA;
				}
				
				String nome = txtNome.getText().trim().replaceAll("[^A-Za-z ]+", "");
				String nascimento = txtNascimento.getText().trim().replace(" ", "");
				String cpf = txtCpf.getText().trim().replace(".", "").replace("-", "").replace(" ", "");
				String instituicao = txtInstituicao.getText();
				String sexo = formatador.retornarValor(cbSexo.getSelectedItem().toString());
				String tipo = formatador.retornarValor(cbTipo.getSelectedItem().toString());
				
				if(nascimento.equalsIgnoreCase("//")) nascimento = "";
				if(nascimento.trim().length() != 10) nascimento = "";
				if(!validador.validarData(nascimento)) nascimento = "";
				
				UsuarioSeletor filtro = new UsuarioSeletor();
				filtro.setFiltro(true);
				filtro.setNome(nome);
				filtro.setNascimento(nascimento);
				filtro.setCpf(cpf);
				filtro.setInstituicao(instituicao);
				filtro.setSexo(sexo);
				filtro.setTipoUsuario(tipo);
				filtro.setOffset(offset);
				
				UsuarioController usuarioController = new UsuarioController();
				ArrayList<UsuarioDTO> lista = usuarioController.listar(filtro);
				
				/* ======estados dos botões de paginação===== */
				if (lista.size() < Config.REGISTRO_POR_PAGINA) {
					btnProxima.setEnabled(false);
				} else {
					btnProxima.setEnabled(true);
				}

				if (offset == 0) {
					btnAnterior.setEnabled(false);
				} else {
					btnAnterior.setEnabled(true);
				}
				/* ======================================== */
				
				//atualizar tabela
				modelo.setRowCount(0);
				for(int i = 0; i < lista.size(); i++) {
					modelo.addRow(new Object[] {
							lista.get(i).getCodigo(),
							lista.get(i).getNome(),
							lista.get(i).getNascimento(),
							lista.get(i).getSexo(),
							lista.get(i).getCpf(),
							lista.get(i).getTipoUsuario(),
							lista.get(i).getInstituicao()	
					});
				}
				
			}
		});
		
		
		/* >>> */
		btnProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				offset += Config.REGISTRO_POR_PAGINA;
				
				String nome = txtNome.getText().trim().replaceAll("[^A-Za-z ]+", "");
				String nascimento = txtNascimento.getText().trim().replace(" ", "");
				String cpf = txtCpf.getText().trim().replace(".", "").replace("-", "").replace(" ", "");
				String instituicao = txtInstituicao.getText();
				String sexo = formatador.retornarValor(cbSexo.getSelectedItem().toString());
				String tipo = formatador.retornarValor(cbTipo.getSelectedItem().toString());
				
				if(nascimento.equalsIgnoreCase("//")) nascimento = "";
				if(nascimento.trim().length() != 10) nascimento = "";
				if(!validador.validarData(nascimento)) nascimento = "";
				
				UsuarioSeletor filtro = new UsuarioSeletor();
				filtro.setFiltro(true);
				filtro.setNome(nome);
				filtro.setNascimento(nascimento);
				filtro.setCpf(cpf);
				filtro.setInstituicao(instituicao);
				filtro.setSexo(sexo);
				filtro.setTipoUsuario(tipo);
				filtro.setOffset(offset);
				
				UsuarioController usuarioController = new UsuarioController();
				ArrayList<UsuarioDTO> lista = usuarioController.listar(filtro);
				
				/* ======estados dos botões de paginação===== */
				if (lista.size() < Config.REGISTRO_POR_PAGINA) {
					btnProxima.setEnabled(false);
				} else {
					btnProxima.setEnabled(true);
				}

				if (offset == 0) {
					btnAnterior.setEnabled(false);
				} else {
					btnAnterior.setEnabled(true);
				}
				/* ======================================== */
				
				//atualizar tabela
				modelo.setRowCount(0);
				for(int i = 0; i < lista.size(); i++) {
					modelo.addRow(new Object[] {
							lista.get(i).getCodigo(),
							lista.get(i).getNome(),
							lista.get(i).getNascimento(),
							lista.get(i).getSexo(),
							lista.get(i).getCpf(),
							lista.get(i).getTipoUsuario(),
							lista.get(i).getInstituicao()	
					});
				}
				
				
			}
		});

	}
}
