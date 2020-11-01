package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import constantes.Config;
import constantes.Mensagens;
import controller.ReacaoController;
import controller.UsuarioController;
import controller.VacinaController;
import controller.VacinacaoController;
import model.dto.UsuarioDTO;
import model.dto.VacinaDTO;
import model.dto.VacinacaoDTO;
import model.seletores.UsuarioSeletor;
import model.seletores.VacinaSeletor;
import model.seletores.VacinacaoSeletor;
import model.vo.ReacaoVO;
import model.vo.UsuarioVO;
import model.vo.VacinaVO;
import model.vo.VacinacaoVO;
import utils.Formatador;
import utils.Validador;

public class GerenciarVacinacao extends JPanel {
	private JTable table;

	private int offset = 0;
	private VacinacaoSeletor filtro = new VacinacaoSeletor();
	private Formatador formatador = new Formatador();
	private Validador validador = new Validador();
	
	/**
	 * Create the panel.
	 */
	public GerenciarVacinacao() {
		setLayout(null);
		
		JLabel lblGerenciamentoDeVacinao = new JLabel("Gerenciamento de Vacina\u00E7\u00E3o");
		lblGerenciamentoDeVacinao.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerenciamentoDeVacinao.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblGerenciamentoDeVacinao.setBounds(10, 11, 680, 26);
		add(lblGerenciamentoDeVacinao);
		
		UsuarioController usuarioController = new UsuarioController();
		UsuarioSeletor usuarioSeletor = new UsuarioSeletor();
		ArrayList<UsuarioDTO> usuarios = usuarioController.listar(usuarioSeletor);
		
		JComboBox cbUsuario = new JComboBox();
		cbUsuario.addItem("0 - Todos");
		for(int i = 0; i < usuarios.size(); i++) {
			cbUsuario.addItem(usuarios.get(i).getCodigo() + " - " + usuarios.get(i).getNome());
		}
		cbUsuario.setBounds(10, 63, 300, 36);
		add(cbUsuario);
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblUsuario.setBounds(10, 48, 197, 14);
		add(lblUsuario);
		
		VacinaController vacinaController = new VacinaController();
		VacinaSeletor vacinaSeletor = new VacinaSeletor();
		ArrayList<VacinaDTO> vacinas = vacinaController.listar(vacinaSeletor);
		
		JComboBox cbVacina = new JComboBox();
		cbVacina.addItem("0 - Todos");
		for(int i = 0; i < vacinas.size(); i++) {
			cbVacina.addItem(vacinas.get(i).getCodigo() + " - " + vacinas.get(i).getDescricao());
		}
		cbVacina.setBounds(390, 63, 300, 36);
		add(cbVacina);
		
		JLabel lblVacina = new JLabel("Vacina");
		lblVacina.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblVacina.setBounds(390, 48, 197, 14);
		add(lblVacina);
		
		ReacaoController reacaoController = new ReacaoController();
		ArrayList<ReacaoVO> reacoes = reacaoController.listar();
		
		JComboBox cbReacao = new JComboBox();
		cbReacao.addItem("0 - Todos");
		for(int i = 0; i < reacoes.size(); i++) {
			cbReacao.addItem(reacoes.get(i).getId() + " - " + reacoes.get(i).getDescricao());
		}
		cbReacao.setBounds(10, 121, 300, 36);
		add(cbReacao);
		
		JLabel lblReacao = new JLabel("Rea\u00E7\u00E3o");
		lblReacao.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblReacao.setBounds(10, 106, 197, 14);
		add(lblReacao);
		
		JFormattedTextField txtData = new JFormattedTextField();
		this.formatador.mascara("##/##/####", txtData);
		txtData.setBounds(390, 121, 300, 36);
		add(txtData);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblData.setBounds(390, 106, 300, 14);
		add(lblData);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(601, 168, 89, 23);
		add(btnExcluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(501, 168, 89, 23);
		add(btnEditar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(381, 168, 110, 23);
		add(btnCadastrar);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(282, 168, 89, 23);
		add(btnFiltrar);
		
		JButton btnAnterior = new JButton("<");
		btnAnterior.setBounds(10, 436, 89, 23);
		add(btnAnterior);
		
		JButton btnProxima = new JButton(">");
		btnProxima.setBounds(601, 438, 89, 23);
		add(btnProxima);
		
		/* TABELA */
		
		//carregar itens da tabela
		VacinacaoController vacinacaoController = new VacinacaoController();
		this.filtro.setFiltro(false);
		ArrayList<VacinacaoDTO> listaVacinacao = vacinacaoController.listar(this.filtro);
		
		/* ======estados dos botões de paginação===== */
		if (listaVacinacao.size() < Config.REGISTRO_POR_PAGINA) {
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
		
		//colunas
		String[] colunas = {"Código", "Usuário", "Vacina", "Reação", "Data"};
		
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
		
		//populando tabela
		for(int i = 0; i < listaVacinacao.size(); i++) {
			modelo.addRow(new Object[] {
					listaVacinacao.get(i).getCodigo(),
					listaVacinacao.get(i).getUsuario(),
					listaVacinacao.get(i).getVacina(),
					listaVacinacao.get(i).getReacao(),
					listaVacinacao.get(i).getData()
			});
		}
		
		/* imprimindo tabela */
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(14, 201, 676, 224);
		add(scrollPane);
		
		
		
		
		/* EVENTOS */
		
		/* cadastrar */
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int usuario = formatador.retornarIndice(cbUsuario.getSelectedItem().toString());
				int vacina = formatador.retornarIndice(cbVacina.getSelectedItem().toString());
				int reacao = formatador.retornarIndice(cbReacao.getSelectedItem().toString());
				String data = txtData.getText().replace(" ", "");
				
				if(data.equalsIgnoreCase("//")) data = "";
				
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setId(usuario);
				
				VacinaVO vacinaVO = new VacinaVO();
				vacinaVO.setId(vacina);
				
				ReacaoVO reacaoVO = new ReacaoVO();
				reacaoVO.setId(reacao);
				
				VacinacaoVO vacinacao = new VacinacaoVO();
				vacinacao.setUsuario(usuarioVO);
				vacinacao.setVacina(vacinaVO);
				vacinacao.setReacao(reacaoVO);
				vacinacao.setData(data);
				
				VacinacaoController vacinacaoController = new VacinacaoController();
				if(vacinacaoController.cadastrar(vacinacao)) {
					JOptionPane.showMessageDialog(null, Mensagens.VACINACAO_CADASTRO_SUCESSO);
					cbUsuario.setSelectedIndex(0);
					cbVacina.setSelectedIndex(0);
					cbReacao.setSelectedIndex(0);
					txtData.setText("");
					
					modelo.setRowCount(0);
					ArrayList<VacinacaoDTO> lista = vacinacaoController.listar(filtro);
					
					offset = 0;
					
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
					
					for(int i = 0; i < lista.size(); i++) {
						modelo.addRow(new Object[] {
								lista.get(i).getCodigo(),
								lista.get(i).getUsuario(),
								lista.get(i).getVacina(),
								lista.get(i).getReacao(),
								lista.get(i).getData()
						});
					}
					
				}
				
			}
		});
		
		
		/* filtrar */
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usuario = formatador.retornarValor(cbUsuario.getSelectedItem().toString());
				String vacina = formatador.retornarValor(cbVacina.getSelectedItem().toString());
				String reacao = formatador.retornarValor(cbReacao.getSelectedItem().toString());
				String data = txtData.getText().replace(" ", "");
				int verificar = 0;
				
				if(data.equalsIgnoreCase("//") && verificar == 0) {
					data = "";
					verificar++;
				}
				
				if(verificar == 0) {
					if(!validador.validarData(data)) data = "";
				}
				
				VacinacaoSeletor filtro = new VacinacaoSeletor();
				filtro.setFiltro(true);
				filtro.setUsuario(usuario);
				filtro.setVacina(vacina);
				filtro.setReacao(reacao);
				filtro.setData(data);
				
				VacinacaoController vacinacaoController = new VacinacaoController();
				ArrayList<VacinacaoDTO> listaFiltrada = vacinacaoController.listar(filtro);
				
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
				
				modelo.setRowCount(0);
				for(int i = 0; i < listaFiltrada.size(); i++) {
					modelo.addRow(new Object[] {
							listaFiltrada.get(i).getCodigo(),
							listaFiltrada.get(i).getUsuario(),
							listaFiltrada.get(i).getVacina(),
							listaFiltrada.get(i).getReacao(),
							listaFiltrada.get(i).getData()	
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
					
					int usuario = formatador.retornarIndice(cbUsuario.getSelectedItem().toString());
					int vacina = formatador.retornarIndice(cbVacina.getSelectedItem().toString());
					int reacao = formatador.retornarIndice(cbReacao.getSelectedItem().toString());
					String data = txtData.getText().replace(" ", "");
					
					if(data.equalsIgnoreCase("//")) data = "";
					
					UsuarioVO usuarioVO = new UsuarioVO();
					usuarioVO.setId(usuario);
					
					VacinaVO vacinaVO = new VacinaVO();
					vacinaVO.setId(vacina);
					
					ReacaoVO reacaoVO = new ReacaoVO();
					reacaoVO.setId(reacao);
					
					VacinacaoVO vacinacao = new VacinacaoVO();
					vacinacao.setId(id);
					vacinacao.setUsuario(usuarioVO);
					vacinacao.setVacina(vacinaVO);
					vacinacao.setReacao(reacaoVO);
					vacinacao.setData(data);
					
					VacinacaoController vacinacaoController = new VacinacaoController();
					if(vacinacaoController.atualizar(vacinacao)) {
						JOptionPane.showMessageDialog(null, Mensagens.VACINACAO_EDICAO_SUCESSO);
						cbUsuario.setSelectedIndex(0);
						cbVacina.setSelectedIndex(0);
						cbReacao.setSelectedIndex(0);
						txtData.setText("");
						
						modelo.setRowCount(0);
						ArrayList<VacinacaoDTO> lista = vacinacaoController.listar(filtro);
						
						offset = 0;
						
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
						
						for(int i = 0; i < lista.size(); i++) {
							modelo.addRow(new Object[] {
									lista.get(i).getCodigo(),
									lista.get(i).getUsuario(),
									lista.get(i).getVacina(),
									lista.get(i).getReacao(),
									lista.get(i).getData()
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
					JOptionPane.showMessageDialog(null, Mensagens.USUARIO_EDICAO_ERRO_SOMENTE_UM_REGISTRO);
					verificacao++;
				}

				if (totalDeLinhasSelecionadas > 1 && verificacao == 0) {
					JOptionPane.showMessageDialog(null, Mensagens.USUARIO_EDICAO_ERRO_APENAS_UM_REGISTRO);
					verificacao++;
				}
				
				if(verificacao == 0) {
					
					int id = (int) tabela.getValueAt(linhaSelecionada, 0);
					
					VacinacaoController vacinacaoController = new VacinacaoController();
					VacinacaoVO vacinacao = new VacinacaoVO();
					vacinacao.setId(id);
					
					if(vacinacaoController.excluir(vacinacao)) {
						JOptionPane.showMessageDialog(null, Mensagens.VACINACAO_EXCLUSAO_SUCESSO);
						((DefaultTableModel) tabela.getModel()).removeRow(linhaSelecionada);
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
				
				
				String usuario = formatador.retornarValor(cbUsuario.getSelectedItem().toString());
				String vacina = formatador.retornarValor(cbVacina.getSelectedItem().toString());
				String reacao = formatador.retornarValor(cbReacao.getSelectedItem().toString());
				String data = txtData.getText().replace(" ", "");
				int verificar = 0;
				
				if(data.equalsIgnoreCase("//") && verificar == 0) {
					data = "";
					verificar++;
				}
				
				if(verificar == 0) {
					if(!validador.validarData(data)) data = "";
				}
				
				VacinacaoSeletor filtro = new VacinacaoSeletor();
				filtro.setFiltro(true);
				filtro.setUsuario(usuario);
				filtro.setVacina(vacina);
				filtro.setReacao(reacao);
				filtro.setData(data);
				filtro.setOffset(offset);
				
				VacinacaoController vacinacaoController = new VacinacaoController();
				ArrayList<VacinacaoDTO> listaFiltrada = vacinacaoController.listar(filtro);
				
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
				
				modelo.setRowCount(0);
				for(int i = 0; i < listaFiltrada.size(); i++) {
					modelo.addRow(new Object[] {
							listaFiltrada.get(i).getCodigo(),
							listaFiltrada.get(i).getUsuario(),
							listaFiltrada.get(i).getVacina(),
							listaFiltrada.get(i).getReacao(),
							listaFiltrada.get(i).getData()	
					});
				}
				
				
				
			}
		});
		
		
		/* >>> */
		btnProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				offset += Config.REGISTRO_POR_PAGINA;
				
				
				String usuario = formatador.retornarValor(cbUsuario.getSelectedItem().toString());
				String vacina = formatador.retornarValor(cbVacina.getSelectedItem().toString());
				String reacao = formatador.retornarValor(cbReacao.getSelectedItem().toString());
				String data = txtData.getText().replace(" ", "");
				int verificar = 0;
				
				if(data.equalsIgnoreCase("//") && verificar == 0) {
					data = "";
					verificar++;
				}
				
				if(verificar == 0) {
					if(!validador.validarData(data)) data = "";
				}
				
				VacinacaoSeletor filtro = new VacinacaoSeletor();
				filtro.setFiltro(true);
				filtro.setUsuario(usuario);
				filtro.setVacina(vacina);
				filtro.setReacao(reacao);
				filtro.setData(data);
				filtro.setOffset(offset);
				
				VacinacaoController vacinacaoController = new VacinacaoController();
				ArrayList<VacinacaoDTO> listaFiltrada = vacinacaoController.listar(filtro);
				
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
				
				modelo.setRowCount(0);
				for(int i = 0; i < listaFiltrada.size(); i++) {
					modelo.addRow(new Object[] {
							listaFiltrada.get(i).getCodigo(),
							listaFiltrada.get(i).getUsuario(),
							listaFiltrada.get(i).getVacina(),
							listaFiltrada.get(i).getReacao(),
							listaFiltrada.get(i).getData()	
					});
				}
				
				
				
				
			}
		});
		
		
	}

}
