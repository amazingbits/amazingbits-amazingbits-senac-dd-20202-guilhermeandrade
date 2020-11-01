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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import constantes.Config;
import constantes.Mensagens;
import controller.EstagioPesquisaController;
import controller.UsuarioController;
import controller.VacinaController;
import model.dto.VacinaDTO;
import model.seletores.VacinaSeletor;
import model.vo.EstagioPesquisaVO;
import model.vo.UsuarioVO;
import model.vo.VacinaVO;
import utils.Formatador;
import utils.Validador;

public class GerenciarVacinas extends JPanel {
	private JTextField txtNome;
	private JTextField txtPais;
	private JTable table;
	
	private int offset = 0;
	private Formatador formatador = new Formatador();
	private Validador validador = new Validador();
	private VacinaSeletor filtro = new VacinaSeletor();

	/**
	 * Create the panel.
	 */
	public GerenciarVacinas() {
		setLayout(null);
		
		JLabel lblGerenciamentoDeVacinas = new JLabel("Gerenciamento de Vacinas");
		lblGerenciamentoDeVacinas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerenciamentoDeVacinas.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblGerenciamentoDeVacinas.setBounds(10, 11, 680, 26);
		add(lblGerenciamentoDeVacinas);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblNome.setBounds(10, 48, 76, 14);
		add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(10, 63, 197, 36);
		add(txtNome);
		
		JLabel lblPais = new JLabel("Pa\u00EDs de Origem");
		lblPais.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblPais.setBounds(241, 48, 197, 14);
		add(lblPais);
		
		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(241, 63, 197, 36);
		add(txtPais);
		
		EstagioPesquisaController estagioPesquisaController = new EstagioPesquisaController();
		ArrayList<EstagioPesquisaVO> estagios = estagioPesquisaController.listar();
		
		JComboBox cbEstagio = new JComboBox();
		cbEstagio.addItem("0 - Todos");
		for(int i = 0; i < estagios.size(); i++) {
			cbEstagio.addItem(estagios.get(i).getId() + " - " + estagios.get(i).getDescricao());
		}
		cbEstagio.setBounds(480, 63, 197, 36);
		add(cbEstagio);
		
		JLabel lblEstagio = new JLabel("Estágio da Pesquisa");
		lblEstagio.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblEstagio.setBounds(480, 48, 197, 14);
		add(lblEstagio);
		
		JFormattedTextField txtDtInicio = new JFormattedTextField();
		txtDtInicio.setBounds(10, 121, 197, 36);
		formatador.mascara("##/##/####", txtDtInicio);
		add(txtDtInicio);
		
		JLabel lblDtInicio = new JLabel("Dt. In\u00EDcio");
		lblDtInicio.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblDtInicio.setBounds(10, 106, 210, 14);
		add(lblDtInicio);
		
		JLabel lblPesquisador = new JLabel("Pesquisador");
		lblPesquisador.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblPesquisador.setBounds(241, 106, 197, 14);
		add(lblPesquisador);
		
		UsuarioController usuarioController = new UsuarioController();
		ArrayList<UsuarioVO> usuarios = usuarioController.listarPesquisadores();
		
		JComboBox cbPesquisador = new JComboBox();
		cbPesquisador.addItem("0 - Todos");
		for(int i = 0; i < usuarios.size(); i++) {
			cbPesquisador.addItem(usuarios.get(i).getId() + " - " + usuarios.get(i).getNome());
		}
		cbPesquisador.setBounds(241, 121, 197, 36);
		add(cbPesquisador);
		
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
		btnAnterior.setBounds(10, 439, 89, 23);
		add(btnAnterior);
		
		JButton btnProxima = new JButton(">");
		btnProxima.setBounds(601, 441, 89, 23);
		add(btnProxima);
		
		/**
		 * TABELA
		 */
		
		//carregar itens da tabela
		VacinaController vacinaController = new VacinaController();
		this.filtro.setFiltro(false);
		ArrayList<VacinaDTO> listaVacinas = vacinaController.listar(this.filtro);

		/* ======estados dos botões de paginação===== */
		if (listaVacinas.size() < Config.REGISTRO_POR_PAGINA) {
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
		String[] colunas = {"Código", "Descrição", "País", "Estágio", "Data", "Pesquisador"};
		
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
		for(int i = 0; i < listaVacinas.size(); i++) {
			modelo.addRow(new Object[] {
					listaVacinas.get(i).getCodigo(),
					listaVacinas.get(i).getDescricao(),
					listaVacinas.get(i).getPais(),
					listaVacinas.get(i).getEstagio(),
					listaVacinas.get(i).getData(),
					listaVacinas.get(i).getPesquisador()
			});
		}
		
		/* imprimindo tabela */
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(12, 202, 678, 226);
		add(scrollPane);
		
		
		
		/* EVENTOS */
		
		/* cadastrar */
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nome = txtNome.getText().trim();
				String pais = txtPais.getText().trim();
				int estagio = formatador.retornarIndice(cbEstagio.getSelectedItem().toString());
				String data = txtDtInicio.getText().trim().replace(" ", "");
				int pesquisador = formatador.retornarIndice(cbPesquisador.getSelectedItem().toString());
				
				if(data.equalsIgnoreCase("//")) data = "";
				
				EstagioPesquisaVO estagioVO = new EstagioPesquisaVO();
				estagioVO.setId(estagio);
				UsuarioVO pesquisadorVO = new UsuarioVO();
				pesquisadorVO.setId(pesquisador);
				
				VacinaVO vacina = new VacinaVO();
				vacina.setDescricao(nome);
				vacina.setPaisDeOrigem(pais);
				vacina.setEstagio(estagioVO);
				vacina.setDataInicio(data);
				vacina.setPesquisador(pesquisadorVO);
				
				VacinaController vacinaController = new VacinaController();
				if(vacinaController.cadastrar(vacina)) {
					JOptionPane.showMessageDialog(null, Mensagens.VACINA_CADASTRO_SUCESSO);
					txtNome.setText("");
					txtPais.setText("");
					cbEstagio.setSelectedIndex(0);
					txtDtInicio.setText("");
					cbPesquisador.setSelectedIndex(0);
					
					modelo.setRowCount(0);
					ArrayList<VacinaDTO> lista = vacinaController.listar(filtro);
					
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
								lista.get(i).getDescricao(),
								lista.get(i).getPais(),
								lista.get(i).getEstagio(),
								lista.get(i).getData(),
								lista.get(i).getPesquisador()	
						});
					}
					
				}
				
			}
		});
		
		
		/* filtrar */
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nome = txtNome.getText().trim();
				String pais = txtPais.getText().trim();
				String estagio = formatador.retornarValor(cbEstagio.getSelectedItem().toString());
				String data = txtDtInicio.getText().trim().replace(" ", "");
				String pesquisador = formatador.retornarValor(cbPesquisador.getSelectedItem().toString());
				
				if(data.equalsIgnoreCase("//")) data = "";
				
				VacinaSeletor filtro = new VacinaSeletor();
				filtro.setFiltro(true);
				filtro.setDescricao(nome);
				filtro.setPais(pais);
				filtro.setEstagio(estagio);
				filtro.setData(data);
				filtro.setPesquisador(pesquisador);
				
				VacinaController vacinaController = new VacinaController();
				ArrayList<VacinaDTO> lista = vacinaController.listar(filtro);
				
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
				
				modelo.setRowCount(0);
				for(int i = 0; i < lista.size(); i++) {
					modelo.addRow(new Object[] {
							lista.get(i).getCodigo(),
							lista.get(i).getDescricao(),
							lista.get(i).getPais(),
							lista.get(i).getEstagio(),
							lista.get(i).getData(),
							lista.get(i).getPesquisador()	
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
					String nome = txtNome.getText().trim();
					String pais = txtPais.getText().trim();
					int estagio = formatador.retornarIndice(cbEstagio.getSelectedItem().toString());
					String data = txtDtInicio.getText().trim().replace(" ", "");
					int pesquisador = formatador.retornarIndice(cbPesquisador.getSelectedItem().toString());
					
					if(data.equalsIgnoreCase("//")) data = "";
					
					EstagioPesquisaVO estagioVO = new EstagioPesquisaVO();
					estagioVO.setId(estagio);
					UsuarioVO pesquisadorVO = new UsuarioVO();
					pesquisadorVO.setId(pesquisador);
					
					VacinaVO vacina = new VacinaVO();
					vacina.setId(id);
					vacina.setDescricao(nome);
					vacina.setPaisDeOrigem(pais);
					vacina.setEstagio(estagioVO);
					vacina.setDataInicio(data);
					vacina.setPesquisador(pesquisadorVO);
					
					VacinaController vacinaController = new VacinaController();
					if(vacinaController.atualizar(vacina)) {
						JOptionPane.showMessageDialog(null, Mensagens.VACINA_EDICAO_SUCESSO);
						txtNome.setText("");
						txtPais.setText("");
						cbEstagio.setSelectedIndex(0);
						txtDtInicio.setText("");
						cbPesquisador.setSelectedIndex(0);
						
						modelo.setRowCount(0);
						ArrayList<VacinaDTO> lista = vacinaController.listar(filtro);
						
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
									lista.get(i).getDescricao(),
									lista.get(i).getPais(),
									lista.get(i).getEstagio(),
									lista.get(i).getData(),
									lista.get(i).getPesquisador()	
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
					VacinaController vacinaController = new VacinaController();
					
					VacinaVO vacina = new VacinaVO();
					vacina.setId(id);
					
					if(vacinaController.excluir(vacina)) {
						JOptionPane.showMessageDialog(null, Mensagens.VACINA_EXCLUSAO_SUCESSO);
						((DefaultTableModel) tabela.getModel()).removeRow(linhaSelecionada);
					}
					
				}
				
			}
		});
		
		
		//<<<
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (offset > 0) {
					offset -= Config.REGISTRO_POR_PAGINA;
				}
				
				String nome = txtNome.getText().trim();
				String pais = txtPais.getText().trim();
				String estagio = formatador.retornarValor(cbEstagio.getSelectedItem().toString());
				String data = txtDtInicio.getText().trim().replace(" ", "");
				String pesquisador = formatador.retornarValor(cbPesquisador.getSelectedItem().toString());
				
				if(data.equalsIgnoreCase("//")) data = "";
				
				VacinaSeletor filtro = new VacinaSeletor();
				filtro.setFiltro(true);
				filtro.setDescricao(nome);
				filtro.setPais(pais);
				filtro.setEstagio(estagio);
				filtro.setData(data);
				filtro.setPesquisador(pesquisador);
				filtro.setOffset(offset);
				
				VacinaController vacinaController = new VacinaController();
				ArrayList<VacinaDTO> lista = vacinaController.listar(filtro);
				
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
				
				modelo.setRowCount(0);
				for(int i = 0; i < lista.size(); i++) {
					modelo.addRow(new Object[] {
							lista.get(i).getCodigo(),
							lista.get(i).getDescricao(),
							lista.get(i).getPais(),
							lista.get(i).getEstagio(),
							lista.get(i).getData(),
							lista.get(i).getPesquisador()	
					});
				}
				
				
			}
		});
		
		//>>>
		btnProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				offset += Config.REGISTRO_POR_PAGINA;
				
				String nome = txtNome.getText().trim();
				String pais = txtPais.getText().trim();
				String estagio = formatador.retornarValor(cbEstagio.getSelectedItem().toString());
				String data = txtDtInicio.getText().trim().replace(" ", "");
				String pesquisador = formatador.retornarValor(cbPesquisador.getSelectedItem().toString());
				
				if(data.equalsIgnoreCase("//")) data = "";
				
				VacinaSeletor filtro = new VacinaSeletor();
				filtro.setFiltro(true);
				filtro.setDescricao(nome);
				filtro.setPais(pais);
				filtro.setEstagio(estagio);
				filtro.setData(data);
				filtro.setPesquisador(pesquisador);
				filtro.setOffset(offset);
				
				VacinaController vacinaController = new VacinaController();
				ArrayList<VacinaDTO> lista = vacinaController.listar(filtro);
				
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
				
				modelo.setRowCount(0);
				for(int i = 0; i < lista.size(); i++) {
					modelo.addRow(new Object[] {
							lista.get(i).getCodigo(),
							lista.get(i).getDescricao(),
							lista.get(i).getPais(),
							lista.get(i).getEstagio(),
							lista.get(i).getData(),
							lista.get(i).getPesquisador()	
					});
				}
				
				
			}
		});
	}
}
