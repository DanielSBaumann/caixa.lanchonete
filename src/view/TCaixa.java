package view;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import control.Auxiliar;
import control.Compras;
import control.Produtos;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class TCaixa extends JInternalFrame {
	private JTextField txtQuantidade;
	private JTextField txtSubtotal;
	private JTextArea txtPedido;
	private String quantidade = "1";
	private boolean aux = false;
	private ArrayList<String> lista = new ArrayList<String>();

	public void quantidade(String numero) {
		if (aux == true) {
			quantidade += numero;
			aux = true;
			txtQuantidade.setText(quantidade);
		} else {
			if (!numero.equals("0")) {
				quantidade = numero;
				aux = true;
				txtQuantidade.setText(quantidade);
			}
		}
	}

	public double subtotal() {
		double subtotal = 0;
		for (int i = 0; i < lista.size(); i++) {
			String linha = lista.get(i);
			linha = linha.replace(",", ".");
			if (!linha.contains("ITEM CANCELADO")) {
				linha = linha.substring(50);
				while (linha.contains(" ")) {
					linha = linha.replace(" ", "");
				}
				subtotal += Double.parseDouble(linha);
			}
		}
		return subtotal;
	}

	public TCaixa(JDesktopPane desktopPane, String caminho, String operador, String acesso) {
		setBounds(0, 0, 1920, 1080);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 83, 500, 890);
		panel.add(scrollPane);

		JPanel pnl1 = new JPanel();
		pnl1.setBorder(new TitledBorder(null, "Sanduiches e Acompanhamentos", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		scrollPane.setViewportView(pnl1);
		pnl1.setBackground(Color.WHITE);
		pnl1.setLayout(null);

		Auxiliar a = new Auxiliar();
		int painel1 = a.button(caminho);
		int painel2 = a.button(caminho, true);
		String nome1[] = a.nomes(caminho).split("#");
		String nome2[] = a.nomes(caminho, true).split("#");

		int x = 15, y = 16;
		// painel1
		for (int n = 1; n <= painel1; n++) {
			JButton btn_ = new JButton(nome1[n]);
			btn_.setText("<html><body><center>" + nome1[n] + "</center></body></html>");
			String sanduiche = nome1[n];
			btn_.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread(new Runnable() {
						public void run() {
							// *********************************************
							Compras c = new Compras();
							Produtos p = new Produtos();
							if (quantidade.isEmpty()) {
								quantidade = "1";
							}
							boolean cor = a.corButton(caminho, sanduiche);
							if (cor == false) {
								btn_.setBackground(Color.LIGHT_GRAY);
							} else {
								btn_.setBackground(Color.RED);
							}
							boolean aux = p.produto(caminho, sanduiche, quantidade);
							if (aux == true) {
								String linha = c.linha(caminho, quantidade, sanduiche,
										Integer.toString(lista.size() + 1));
								p.alterarEstoque(caminho, sanduiche, quantidade);
								lista.add(linha + "\n");
								txtPedido.append(linha + "\n");
								quantidade = "";
								txtQuantidade.setText("");
								DecimalFormat s = new DecimalFormat("0.00");
								txtSubtotal.setText(s.format(subtotal()));
							} else {
								JOptionPane.showMessageDialog(null, "Quantidade indisponivel em estoque!");
							}
							// *********************************************
						}
					}).start();
				}
			});
			btn_.setForeground(Color.WHITE);
			btn_.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			boolean cor = a.corButton(caminho, nome1[n]);
			if (cor == false) {
				btn_.setBackground(Color.LIGHT_GRAY);
			} else {
				btn_.setBackground(Color.RED);
			}
			btn_.setBounds(x, y, 150, 150);
			pnl1.add(btn_);
			x += 156;
			if (n % 3 == 0) {
				y += 156;
				x = 15;
			}
		}

		x = 15;
		y = 16;

		txtPedido = new JTextArea();
		txtPedido.setFont(new Font("Monospaced", Font.PLAIN, 11));
		txtPedido.setEditable(false);
		txtPedido.setBounds(1421, 86, 448, 791);
		panel.add(txtPedido);
		String linha = String.format("%-5.5s %-29.29s %4.3s %8.5s %9.5s", "nº", "produto", "qtd", "preço", "total");
		txtPedido.append("                        Burguer House\n\n\n" + linha + "\n\n");

		JLabel label = new JLabel("Subtotal");
		label.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label.setForeground(new Color(255, 255, 255));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		label.setBounds(1463, 904, 132, 57);
		panel.add(label);

		txtSubtotal = new JTextField();
		txtSubtotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		txtSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubtotal.setEditable(false);
		txtSubtotal.setColumns(10);
		txtSubtotal.setBounds(1605, 896, 264, 77);
		panel.add(txtSubtotal);

		JLabel lblOperador = new JLabel("Operador : " + operador);
		lblOperador.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblOperador.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperador.setForeground(Color.WHITE);
		lblOperador.setBackground(Color.WHITE);
		lblOperador.setBounds(1423, 54, 187, 38);
		panel.add(lblOperador);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(543, 83, 500, 890);
		panel.add(scrollPane_1);

		JPanel pnl2 = new JPanel();
		pnl2.setBorder(
				new TitledBorder(null, "Bebidas e Sobremesas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setViewportView(pnl2);
		pnl2.setBackground(Color.WHITE);
		pnl2.setLayout(null);

		Calendar d = Calendar.getInstance();
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		String data = (String) dataFormatada.format(d.getTime());
		JLabel lblData = new JLabel(data);
		lblData.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblData.setForeground(Color.WHITE);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setBounds(1745, 63, 114, 19);
		panel.add(lblData);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Quantidade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(1067, 83, 310, 455);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JButton btn1 = new JButton("1");
		btn1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btn1.setBounds(31, 22, 80, 80);
		panel_1.add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quantidade("1");
			}
		});
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);

		JButton btn2 = new JButton("2");
		btn2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btn2.setBounds(115, 22, 80, 80);
		panel_1.add(btn2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantidade("2");
			}
		});
		btn2.setBackground(Color.BLACK);
		btn2.setForeground(Color.WHITE);

		JButton btn3 = new JButton("3");
		btn3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btn3.setBounds(199, 22, 80, 80);
		panel_1.add(btn3);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantidade("3");
			}
		});
		btn3.setBackground(Color.BLACK);
		btn3.setForeground(Color.WHITE);

		JButton btn4 = new JButton("4");
		btn4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btn4.setBounds(31, 104, 80, 80);
		panel_1.add(btn4);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantidade("4");
			}
		});
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);

		JButton btn5 = new JButton("5");
		btn5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btn5.setBounds(115, 104, 80, 80);
		panel_1.add(btn5);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantidade("5");
			}
		});
		btn5.setForeground(Color.WHITE);
		btn5.setBackground(Color.BLACK);

		JButton btn6 = new JButton("6");
		btn6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btn6.setBounds(199, 104, 80, 80);
		panel_1.add(btn6);
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantidade("6");
			}
		});
		btn6.setForeground(Color.WHITE);
		btn6.setBackground(Color.BLACK);

		JButton btn7 = new JButton("7");
		btn7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btn7.setBounds(31, 185, 80, 80);
		panel_1.add(btn7);
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantidade("7");
			}
		});
		btn7.setForeground(Color.WHITE);
		btn7.setBackground(Color.BLACK);

		JButton btn8 = new JButton("8");
		btn8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btn8.setBounds(115, 185, 80, 80);
		panel_1.add(btn8);
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantidade("8");
			}
		});
		btn8.setForeground(Color.WHITE);
		btn8.setBackground(Color.BLACK);

		JButton btn9 = new JButton("9");
		btn9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btn9.setBounds(199, 185, 80, 80);
		panel_1.add(btn9);
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantidade("9");
			}
		});
		btn9.setForeground(Color.WHITE);
		btn9.setBackground(Color.BLACK);

		JButton btn0 = new JButton("0");
		btn0.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btn0.setBounds(31, 269, 164, 80);
		panel_1.add(btn0);
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantidade("0");
			}
		});
		btn0.setForeground(Color.WHITE);
		btn0.setBackground(Color.BLACK);

		JButton btnX = new JButton("X");
		btnX.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnX.setBounds(199, 269, 80, 80);
		panel_1.add(btnX);
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!quantidade.isEmpty()) {
					quantidade = quantidade.substring(0, quantidade.length() - 1);
					txtQuantidade.setText(quantidade);
					if (quantidade.isEmpty()) {
						quantidade = "";
					}
				}
			}
		});
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.BLACK);

		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(31, 368, 249, 58);
		panel_1.add(txtQuantidade);
		txtQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantidade.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		txtQuantidade.setEditable(false);
		txtQuantidade.setColumns(10);

		JLabel lblNewLabel = new JLabel("Burguer House");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 50));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(548, 10, 618, 72);
		panel.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1067, 548, 310, 425);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Menu", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton btnFinalizar = new JButton("Finalizar Compra");
		btnFinalizar.setBounds(27, 32, 250, 80);
		panel_2.add(btnFinalizar);
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ***************************************************
						if (!txtSubtotal.getText().isEmpty() && !txtSubtotal.getText().equals("0,00")) {
							Compras c = new Compras();
							for (int i = 0; i < lista.size(); i++) {
								c.listaCompras(caminho, lista.get(i));
							}
							TPagamento tp = new TPagamento(desktopPane, caminho, txtSubtotal.getText(), operador,
									acesso);
							desktopPane.add(tp);
							tp.setVisible(true);
							dispose();
						}
						// ***************************************************
					}
				}).start();
			}
		});
		btnFinalizar.setForeground(Color.WHITE);
		btnFinalizar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnFinalizar.setBackground(Color.RED);

		JButton btnCancelarItem = new JButton("Cancelar Item");
		btnCancelarItem.setBounds(26, 123, 250, 80);
		panel_2.add(btnCancelarItem);
		btnCancelarItem.setForeground(Color.WHITE);
		btnCancelarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// *********************************************
						if (acesso == "adm") {
							if (!lista.isEmpty()) {
								Compras c = new Compras();
								String codigo = JOptionPane.showInputDialog(null,
										"Digite codigo do produto a ser cancelado :");
								if (!codigo.equals("") && codigo != null) {
									double subtrair = c.cancelarItem(codigo, caminho, lista);
									txtPedido.setText("");
									txtPedido.append("                        Burguer House\n\n\n" + linha + "\n\n");
									for (int i = 0; i < lista.size(); i++) {
										txtPedido.append(lista.get(i));
									}
									String sub = txtSubtotal.getText();
									sub = sub.replace(",", ".");
									double subtotal = Double.parseDouble(sub) - subtrair;
									DecimalFormat s = new DecimalFormat("0.00");
									txtSubtotal.setText(s.format(subtotal));
									if(subtotal==0) {
										txtSubtotal.setText("");
									}
								}
							}
						} else {
							String senha = JOptionPane.showInputDialog(null,
									"Digite a senha gerencial \npara realizar a operação :");
							if (senha.contentEquals("admin")) {
								Compras c = new Compras();
								String codigo = JOptionPane.showInputDialog(null,
										"Digite codigo do produto a ser cancelado :");
								if (!codigo.equals("") && codigo != null) {
									double subtrair = c.cancelarItem(codigo, caminho, lista);
									txtPedido.setText("");
									txtPedido.append("                        Burguer House\n\n\n" + linha + "\n\n");
									for (int i = 0; i < lista.size(); i++) {
										txtPedido.append(lista.get(i));
									}
									String sub = txtSubtotal.getText();
									sub = sub.replace(",", ".");
									double subtotal = Double.parseDouble(sub) - subtrair;
									DecimalFormat s = new DecimalFormat("0.00");
									txtSubtotal.setText(s.format(subtotal));
								}
							} else {
								JOptionPane.showMessageDialog(null, "Senha invalida!!");
							}
						}
						// *********************************************
					}
				}).start();
			}
		});
		btnCancelarItem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnCancelarItem.setBackground(Color.RED);

		JButton btnCancelarVenda = new JButton("Cancelar Venda");
		btnCancelarVenda.setBounds(26, 214, 250, 80);
		panel_2.add(btnCancelarVenda);
		btnCancelarVenda.setForeground(Color.WHITE);
		btnCancelarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					public void run() {
						// *********************************************
						if (acesso == "adm") {
							for (int i = 0; i < lista.size(); i++) {
								String linha = lista.get(i);
								if (linha.contains("ITEM CANCELADO")) {
									continue;
								}
								String descricao = linha.substring(6, 36);
								descricao = descricao.trim();
								String quantidade = linha.substring(36, 42);
								quantidade = quantidade.trim();
								Produtos p = new Produtos();
								p.alterarEstoque(caminho, descricao, quantidade, true);
							}
							lista.clear();
							txtPedido.setText("");
							txtPedido.append("                        Burguer House\n\n\n" + linha + "\n\n");
							txtSubtotal.setText("");
							quantidade = "";
							txtQuantidade.setText("");
						} else {
							String senha = JOptionPane.showInputDialog(null,
									"Digite a senha gerencial \npara realizar a operação :");
							if (senha.contentEquals("admin")) {
								for (int i = 0; i < lista.size(); i++) {
									String linha = lista.get(i);
									if (linha.contains("ITEM CANCELADO")) {
										continue;
									}
									String descricao = linha.substring(6, 36);
									descricao = descricao.trim();
									String quantidade = linha.substring(36, 42);
									quantidade = quantidade.trim();
									Produtos p = new Produtos();
									p.alterarEstoque(caminho, descricao, quantidade, true);
								}
								lista.clear();
								txtPedido.setText("");
								txtPedido.append("                        Burguer House\n\n\n" + linha + "\n\n");
								txtSubtotal.setText("");
								quantidade = "";
								txtQuantidade.setText("");
							} else {
								JOptionPane.showMessageDialog(null, "Senha invalida!!");
							}
						}
						// *********************************************
					}
				}).start();
			}
		});
		btnCancelarVenda.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnCancelarVenda.setBackground(Color.RED);

		JButton btnLogin = new JButton("Menu");
		btnLogin.setBounds(26, 305, 250, 80);
		panel_2.add(btnLogin);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// **************************************************
						if (lista.isEmpty() || txtSubtotal.getText().equals("")) {
							TMenu tm = new TMenu(desktopPane, caminho, operador, acesso);
							desktopPane.add(tm);
							tm.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Finalize a compra antes\n de retornar ao Menu!");
						}
						// **************************************************
					}
				}).start();
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnLogin.setBackground(Color.RED);

		// painel2
		for (int n = 1; n <= painel2; n++) {
			JButton btn_ = new JButton(nome2[n]);
			btn_.setText("<html><body><center>" + nome2[n] + "</center></body></html>");
			String sanduiche = nome2[n];
			btn_.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread(new Runnable() {
						public void run() {
							// *********************************************
							Compras c = new Compras();
							Produtos p = new Produtos();
							if (quantidade.isEmpty()) {
								quantidade = "1";
							}
							boolean cor = a.corButton(caminho, sanduiche);
							if (cor == false) {
								btn_.setBackground(Color.LIGHT_GRAY);
							} else {
								btn_.setBackground(Color.RED);
							}
							boolean aux = p.produto(caminho, sanduiche, quantidade);
							if (aux == true) {
								String linha = c.linha(caminho, quantidade, sanduiche,
										Integer.toString(lista.size() + 1));
								p.alterarEstoque(caminho, sanduiche, quantidade);
								lista.add(linha + "\n");
								txtPedido.append(linha + "\n");
								quantidade = "";
								txtQuantidade.setText("");
								DecimalFormat s = new DecimalFormat("0.00");
								txtSubtotal.setText(s.format(subtotal()));
							} else {
								JOptionPane.showMessageDialog(null, "Quantidade indisponivel em estoque!", null, 0);
							}
							// *********************************************
						}
					}).start();
				}
			});
			btn_.setForeground(Color.WHITE);
			btn_.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			boolean cor = a.corButton(caminho, nome2[n]);
			if (cor == false) {
				btn_.setBackground(Color.LIGHT_GRAY);
			} else {
				btn_.setBackground(Color.RED);
			}
			btn_.setBounds(x, y, 150, 150);
			pnl2.add(btn_);
			x += 156;
			if (n % 3 == 0) {
				y += 156;
				x = 15;
			}
		}

		Compras c = new Compras();
		boolean aux = c.confereLista(caminho);
		if (aux == true) {
			String vetor[] = c.listaCompras(caminho).split("@");
			for (int i = 0; i < vetor.length; i++) {
				lista.add(vetor[i] + "\n");
				txtPedido.append(vetor[i] + "\n");
				quantidade = "";
				txtQuantidade.setText("");
				DecimalFormat s = new DecimalFormat("0.00");
				txtSubtotal.setText(s.format(subtotal()));
			}
			c.clear(caminho);
		}

		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setBorder(null);

	}
}
