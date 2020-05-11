package view;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import control.Pagamento;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class TPagamento extends JInternalFrame {
	private JTextField txtSubtotal;
	private JTextField txtPay;
	private String pagamento = "";
	private double sub;
	private JComboBox<String> cmbPagamento;

	public void pay(String numero) {
		pagamento = pagamento + numero;
		if (pagamento.contains(".") && !pagamento.endsWith(".")
				&& pagamento.substring(pagamento.indexOf(".")).length() > 2) {
			pagamento = pagamento.substring(0, pagamento.indexOf(".") + 3);
		}
		txtPay.setText(pagamento);
	}

	public TPagamento(JDesktopPane desktopPane, String caminho, String subtotal, String operador, String acesso) {
		setBounds(0, 0, 1920, 1080);
		sub = Double.parseDouble(subtotal.replace(",", "."));
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblLogo = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(15, 16, 338, 242);
		panel.add(lblLogo);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Menu Pagamento",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(669, 103, 504, 901);
		panel.add(panel_1);

		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setBounds(186, 40, 140, 48);
		panel_1.add(lblSubtotal);
		lblSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

		txtSubtotal = new JTextField();
		txtSubtotal.setBounds(128, 83, 260, 48);
		panel_1.add(txtSubtotal);
		txtSubtotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		txtSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubtotal.setEditable(false);
		txtSubtotal.setColumns(10);
		txtSubtotal.setText(subtotal);

		JLabel label_1 = new JLabel("Formas de Pagamento");
		label_1.setBounds(158, 129, 207, 29);
		panel_1.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);

		cmbPagamento = new JComboBox<String>();
		cmbPagamento.setBounds(181, 159, 145, 35);
		panel_1.add(cmbPagamento);
		cmbPagamento.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		cmbPagamento.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Dinheiro", "D\u00E9bito", "Cr\u00E9dito", "Ticket", "Sodexo", "" }));
		cmbPagamento.setSelectedIndex(-1);

		JButton button_1 = new JButton("1");
		button_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button_1.setBounds(128, 252, 80, 80);
		panel_1.add(button_1);
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.RED);

		JButton button_2 = new JButton("2");
		button_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button_2.setBounds(218, 252, 80, 80);
		panel_1.add(button_2);
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(Color.RED);

		JButton button_3 = new JButton("3");
		button_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button_3.setBounds(308, 252, 80, 80);
		panel_1.add(button_3);
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(Color.RED);

		JButton button_4 = new JButton("4");
		button_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button_4.setBounds(128, 343, 80, 80);
		panel_1.add(button_4);
		button_4.setForeground(Color.WHITE);
		button_4.setBackground(Color.RED);

		JButton button_5 = new JButton("5");
		button_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button_5.setBounds(218, 343, 80, 80);
		panel_1.add(button_5);
		button_5.setForeground(Color.WHITE);
		button_5.setBackground(Color.RED);

		JButton button_6 = new JButton("6");
		button_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button_6.setBounds(308, 343, 80, 80);
		panel_1.add(button_6);
		button_6.setForeground(Color.WHITE);
		button_6.setBackground(Color.RED);

		JButton button_7 = new JButton("7");
		button_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button_7.setBounds(128, 432, 80, 80);
		panel_1.add(button_7);
		button_7.setForeground(Color.WHITE);
		button_7.setBackground(Color.RED);

		JButton button_8 = new JButton("8");
		button_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button_8.setBounds(218, 432, 80, 80);
		panel_1.add(button_8);
		button_8.setForeground(Color.WHITE);
		button_8.setBackground(Color.RED);

		JButton button_9 = new JButton("9");
		button_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button_9.setBounds(308, 432, 80, 80);
		panel_1.add(button_9);
		button_9.setForeground(Color.WHITE);
		button_9.setBackground(Color.RED);

		JButton button_10 = new JButton("0");
		button_10.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button_10.setBounds(128, 524, 80, 80);
		panel_1.add(button_10);
		button_10.setForeground(Color.WHITE);
		button_10.setBackground(Color.RED);

		JButton button_11 = new JButton(".");
		button_11.setBounds(218, 524, 80, 80);
		panel_1.add(button_11);
		button_11.setForeground(Color.WHITE);
		button_11.setBackground(Color.RED);
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!pagamento.contains(".") && !pagamento.isEmpty()) {
					pay(".");
				}
			}
		});
		button_11.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));

		JButton button_12 = new JButton("X");
		button_12.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button_12.setBounds(308, 524, 80, 80);
		panel_1.add(button_12);
		button_12.setForeground(Color.WHITE);
		button_12.setBackground(Color.RED);

		txtPay = new JTextField();
		txtPay.setBounds(128, 615, 260, 64);
		panel_1.add(txtPay);
		txtPay.setHorizontalAlignment(SwingConstants.CENTER);
		txtPay.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		txtPay.setEditable(false);
		txtPay.setColumns(10);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(128, 690, 260, 80);
		panel_1.add(btnRegistrar);
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnRegistrar.setBackground(Color.RED);
		btnRegistrar.setForeground(Color.WHITE);

		JButton button_13 = new JButton("Voltar Caixa");
		button_13.setBounds(192, 781, 135, 82);
		panel_1.add(button_13);
		button_13.setForeground(Color.WHITE);
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ************************************************
						TCaixa tc = new TCaixa(desktopPane, caminho, operador, acesso);
						desktopPane.add(tc);
						tc.setVisible(true);
						dispose();
						// ************************************************
					}
				}).start();
			}
		});
		button_13.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		button_13.setBackground(Color.RED);
		
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						if (cmbPagamento.getSelectedIndex() != -1) {
							String pay = (String) cmbPagamento.getSelectedItem();
							switch (pay) {
							case "Dinheiro": {
								if (pagamento.isEmpty()) {
									pagamento = "0";
								}
								if (Double.parseDouble(pagamento) >= sub) {
									String troco = Double.toString(Double.parseDouble(pagamento) - sub);
									Pagamento p = new Pagamento();
									Calendar d = Calendar.getInstance();
									SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
									String data = (String) dataFormatada.format(d.getTime());
									boolean aux = p.nota(caminho, subtotal, pay, pagamento, troco, data, operador);
									JOptionPane.showMessageDialog(null,
											"Pagamento efetuado com sucesso!\nTroco : " + troco);
									if (aux == true) {
										p.exibirCupom(caminho);
										TCaixa tc = new TCaixa(desktopPane, caminho, operador, acesso);
										desktopPane.add(tc);
										tc.setVisible(true);
										dispose();
									}
								} else {
									JOptionPane.showMessageDialog(null, "Pagamento insuficiente");
								}
								break;
							}
							default: {
								pagamento = subtotal.replace(",", ".");
								if (Double.parseDouble(pagamento) == sub) {
									Pagamento p = new Pagamento();
									Calendar d = Calendar.getInstance();
									SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
									String data = (String) dataFormatada.format(d.getTime());
									boolean aux = p.nota(caminho, subtotal, pay, pagamento, "0,00", data, operador);
									JOptionPane.showMessageDialog(null,
											"Pagamento efetuado com sucesso!\nTroco : 0,00");
									if (aux == true) {
										p.exibirCupom(caminho);
										TCaixa tc = new TCaixa(desktopPane, caminho, operador, acesso);
										desktopPane.add(tc);
										tc.setVisible(true);
										dispose();
									}
								} else {
									JOptionPane.showMessageDialog(null, "Pagamento excedente/insuficiente");
								}
								break;
							}
							}
						}
					}
				}).start();
			}
		});
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!pagamento.isEmpty()) {
					pagamento = pagamento.substring(0, pagamento.length() - 1);
					txtPay.setText(pagamento);
				}
			}
		});
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay("0");
			}
		});
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay("9");
			}
		});
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay("8");
			}
		});
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay("7");
			}
		});
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay("6");
			}
		});
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay("5");
			}
		});
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay("4");
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay("3");
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay("2");
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay("1");
			}
		});

		JLabel label = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(15, 265, 338, 242);
		panel.add(label);

		JLabel label_2 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(15, 513, 338, 242);
		panel.add(label_2);

		JLabel label_3 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(15, 755, 338, 242);
		panel.add(label_3);

		JLabel label_4 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(1567, 16, 338, 242);
		panel.add(label_4);

		JLabel label_5 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(1567, 265, 338, 242);
		panel.add(label_5);

		JLabel label_6 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(1567, 513, 338, 242);
		panel.add(label_6);

		JLabel label_7 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(1567, 755, 338, 242);
		panel.add(label_7);

		JLabel label_8 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(1227, 16, 338, 242);
		panel.add(label_8);

		JLabel label_9 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(1227, 265, 338, 242);
		panel.add(label_9);

		JLabel label_10 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(1227, 513, 338, 242);
		panel.add(label_10);

		JLabel label_11 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(1227, 755, 338, 242);
		panel.add(label_11);

		JLabel label_12 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(316, 16, 338, 242);
		panel.add(label_12);

		JLabel label_13 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(316, 265, 338, 242);
		panel.add(label_13);

		JLabel label_14 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(316, 513, 338, 242);
		panel.add(label_14);

		JLabel label_15 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/pg.png")));
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setBounds(316, 755, 338, 242);
		panel.add(label_15);

		JLabel label_16 = new JLabel("Burguer House");
		label_16.setBounds(612, 20, 618, 72);
		panel.add(label_16);
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setForeground(Color.BLACK);
		label_16.setFont(new Font("Arial Black", Font.PLAIN, 50));

		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setBorder(null);

	}
}
