package view;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import control.Produtos;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class TProdutos extends JInternalFrame {
	private JTextField txtCodigo;
	private JTextField txtDescricao;
	private JTextField txtQuantidade;
	private JTextField txtValor;
	private JComboBox<String> cmbTipo;

	public void limparTela() {
		txtCodigo.setText("");
		txtDescricao.setText("");
		txtValor.setText("");
		txtQuantidade.setText("");
		cmbTipo.setSelectedIndex(-1);
	}

	public TProdutos(JDesktopPane desktopPane, String caminho, String operador, String acesso) {
		setBounds(0, 0, 1920, 1080);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Menu cadastro de Produtos",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblLogo = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/menu.png")));
		lblLogo.setBounds(706, 11, 446, 306);
		panel.add(lblLogo);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es do Produto", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(318, 382, 1267, 618);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblCodigo.setBounds(223, 81, 120, 40);
		panel_1.add(lblCodigo);
		lblCodigo.setVisible(false);

		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(332, 85, 108, 40);
		panel_1.add(txtCodigo);
		txtCodigo.setVisible(false);

		JLabel label_2 = new JLabel("Tipo de Produto");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		label_2.setBounds(130, 281, 187, 40);
		panel_1.add(label_2);

		cmbTipo = new JComboBox<String>();
		cmbTipo.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Sanduiche", "Side Item", "Desert", "Drink" }));
		cmbTipo.setSelectedIndex(-1);
		cmbTipo.setBounds(331, 285, 88, 36);
		panel_1.add(cmbTipo);

		JLabel label_3 = new JLabel("Produto");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		label_3.setBounds(204, 139, 139, 39);
		panel_1.add(label_3);

		txtDescricao = new JTextField();
		txtDescricao.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		txtDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(332, 130, 725, 64);
		panel_1.add(txtDescricao);

		JLabel label_4 = new JLabel("Qtd em Estoque");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		label_4.setBounds(141, 212, 187, 56);
		panel_1.add(label_4);

		txtQuantidade = new JTextField();
		txtQuantidade.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		txtQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(332, 212, 176, 64);
		panel_1.add(txtQuantidade);

		JLabel label_5 = new JLabel("Valor Unit\u00E1rio");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		label_5.setBounds(699, 212, 172, 40);
		panel_1.add(label_5);

		txtValor = new JTextField();
		txtValor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setColumns(10);
		txtValor.setBounds(881, 205, 176, 64);
		panel_1.add(txtValor);

		JButton btnNewButton = new JButton("Salvar ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ******************************************************
						if (txtCodigo.getText().isEmpty()) {
							if (!txtDescricao.getText().isEmpty() && !txtValor.getText().isEmpty()
									&& !txtQuantidade.getText().isEmpty() && cmbTipo.getSelectedIndex() != -1) {
								Produtos p = new Produtos();
								// Ajustar metodo não salvar sem todos itens preenchidos
								// Verificar descrição so com letras e espaço
								// verificar valores e quantidade só com numeros
								boolean aux = p.salvar(caminho, txtCodigo.getText(), txtDescricao.getText(),
										cmbTipo.getSelectedItem().toString(), txtValor.getText(),
										txtQuantidade.getText());
								if (aux != true) {
									JOptionPane.showMessageDialog(null, "Erro ao salvar dados");
									limparTela();
								} else {
									limparTela();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Preencha todos os campos\n para efetuar o cadastro!");
							}
						} else {
							Produtos p = new Produtos();
							boolean aux;
							aux = p.alterar(caminho, txtCodigo.getText(), txtDescricao.getText(),
									(String) cmbTipo.getSelectedItem(), txtValor.getText(), txtQuantidade.getText());
							if (aux == true) {
								limparTela();
							} else {
								JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro!");
								limparTela();
							}
						}
						// ******************************************************
					}
				}).start();
			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNewButton.setBounds(298, 348, 230, 100);
		panel_1.add(btnNewButton);

		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ******************************************************
						String codigoProcurado = JOptionPane.showInputDialog(null,
								"Digite nome do produto a ser localizado :");
						Produtos p = new Produtos();
						if (p.localizar(codigoProcurado, caminho, true)) {
							txtDescricao.setText(p.getDescricao());
							txtCodigo.setText(p.getCodigo());
							txtValor.setText(p.getValor());
							cmbTipo.setSelectedItem(p.getTipo());
							txtQuantidade.setText(p.getEstoque());
						} else {
							JOptionPane.showMessageDialog(null, "Produto não localizado!");
						}
						// ******************************************************
					}
				}).start();
			}
		});
		btnLocalizar.setForeground(Color.WHITE);
		btnLocalizar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnLocalizar.setBackground(Color.RED);
		btnLocalizar.setBounds(538, 348, 230, 100);
		panel_1.add(btnLocalizar);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ******************************************************
						TMenu tm = new TMenu(desktopPane, caminho, operador, acesso);
						desktopPane.add(tm);
						tm.setVisible(true);
						dispose();
						// ******************************************************
					}
				}).start();
			}
		});
		btnMenu.setForeground(Color.WHITE);
		btnMenu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnMenu.setBackground(Color.RED);
		btnMenu.setBounds(778, 348, 230, 100);
		panel_1.add(btnMenu);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ******************************************************
						if (!txtCodigo.getText().isEmpty()) {
							Produtos p = new Produtos();
							boolean aux;
							aux = p.alterar(caminho, txtCodigo.getText(), txtDescricao.getText(),
									(String) cmbTipo.getSelectedItem(), txtValor.getText(), txtQuantidade.getText());
							if (aux == true) {
								limparTela();
							} else {
								JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro!");
								limparTela();
							}
						} else {
							String codigoProcurado = JOptionPane.showInputDialog(null,
									"Digite nome do produto a ser localizado :");
							Produtos p = new Produtos();
							if (p.localizar(codigoProcurado, caminho, true)) {
								txtDescricao.setText(p.getDescricao());
								txtCodigo.setText(p.getCodigo());
								txtValor.setText(p.getValor());
								cmbTipo.setSelectedItem(p.getTipo());
								txtQuantidade.setText(p.getEstoque());
							} else {
								JOptionPane.showMessageDialog(null, "Produto não localizado!");
							}
						}
						// ******************************************************
					}
				}).start();
			}
		});
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnEditar.setBackground(Color.RED);
		btnEditar.setBounds(298, 461, 230, 100);
		panel_1.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ******************************************************
						if (txtCodigo.getText().isEmpty()) {
							String nome = JOptionPane.showInputDialog(null, "Digite nome produto a ser excluido :");
							Produtos p = new Produtos();
							boolean aux = p.excluir(nome, caminho);
							if (aux == true) {
								JOptionPane.showMessageDialog(null, "Cadastro deletado!");
								limparTela();
							} else {
								JOptionPane.showMessageDialog(null, "Produto não localizado!");
								limparTela();
							}
						} else {
							Produtos p = new Produtos();
							boolean aux = p.excluir(txtDescricao.getText(), caminho);
							if (aux == true) {
								JOptionPane.showMessageDialog(null, "Cadastro deletado!");
								limparTela();
							}
						}
						// ******************************************************
					}
				}).start();
			}
		});
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setBounds(538, 459, 230, 100);
		panel_1.add(btnExcluir);

		JButton btnLimparTela = new JButton("Limpar Tela");
		btnLimparTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// **************************************************
				limparTela();
				// **************************************************
			}
		});
		btnLimparTela.setForeground(Color.WHITE);
		btnLimparTela.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnLimparTela.setBackground(Color.CYAN);
		btnLimparTela.setBounds(141, 503, 140, 56);
		panel_1.add(btnLimparTela);
		
		JButton btnArquivoProdutos = new JButton("<html><body><center>" + "Arquivo Produtos Cadastrados" + "</center></body></html>");
		btnArquivoProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//*****************************************************
				Produtos p=new Produtos();
				p.exibirArquivo(caminho);
				//*****************************************************
				
			}
		});
		btnArquivoProdutos.setForeground(Color.WHITE);
		btnArquivoProdutos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnArquivoProdutos.setBackground(Color.RED);
		btnArquivoProdutos.setBounds(778, 459, 230, 100);
		panel_1.add(btnArquivoProdutos);

		JLabel lblCadastroDeProdutos = new JLabel("Cadastro de Produtos");
		lblCadastroDeProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeProdutos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblCadastroDeProdutos.setBounds(693, 322, 495, 49);
		panel.add(lblCadastroDeProdutos);

		JLabel label = new JLabel("Operador : " + operador);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		label.setBackground(Color.WHITE);
		label.setBounds(317, 330, 256, 49);
		panel.add(label);

		Calendar d = Calendar.getInstance();
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		String data = (String) dataFormatada.format(d.getTime());
		JLabel label_10 = new JLabel(data);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		label_10.setBounds(1431, 330, 154, 41);
		panel.add(label_10);

		JLabel label_6 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(143, 102, 195, 137);
		panel.add(label_6);

		JLabel label_7 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(348, 102, 195, 137);
		panel.add(label_7);

		JLabel label_8 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(553, 102, 195, 137);
		panel.add(label_8);

		JLabel label_9 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(1110, 102, 195, 137);
		panel.add(label_9);

		JLabel label_11 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(1315, 102, 195, 137);
		panel.add(label_11);

		JLabel label_12 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(1520, 102, 195, 137);
		panel.add(label_12);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setBorder(null);

	}
}
