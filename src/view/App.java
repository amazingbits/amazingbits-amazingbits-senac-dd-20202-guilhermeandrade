package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
					frame.getContentPane().setBackground(Color.WHITE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setTitle("Projeto Covid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 570);
		
		//imagens
		Image imgPrincipal = new ImageIcon(this.getClass().getResource("/cv.gif")).getImage();
		Image imgUsuarios = new ImageIcon(this.getClass().getResource("/profile.png")).getImage();
		Image imgVacinas = new ImageIcon(this.getClass().getResource("/coronavirus.png")).getImage();
		Image imgVacinacao = new ImageIcon(this.getClass().getResource("/needle.png")).getImage();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuario = new JMenu("Usu\u00E1rios");
		mnUsuario.setIcon(new ImageIcon(imgUsuarios));
		menuBar.add(mnUsuario);
		
		JMenu mnVacina = new JMenu("Vacina");
		mnVacina.setIcon(new ImageIcon(imgVacinas));
		menuBar.add(mnVacina);
		
		JMenu mnVacinacao = new JMenu("Vacina\u00E7\u00E3o");
		mnVacinacao.setIcon(new ImageIcon(imgVacinacao));
		menuBar.add(mnVacinacao);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImagemPrincipal = new JLabel("");
		lblImagemPrincipal.setIcon(new ImageIcon(imgPrincipal));
		lblImagemPrincipal.setBounds(34, -86, 640, 640);
		contentPane.add(lblImagemPrincipal);
		
		//navegação
		mnUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setContentPane(new GerenciarUsuarios());
				revalidate();
			}
		});
		
		mnVacina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setContentPane(new GerenciarVacinas());
				revalidate();
			}
		});
		
		mnVacinacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setContentPane(new GerenciarVacinacao());
				revalidate();
			}
		});
	}

}
