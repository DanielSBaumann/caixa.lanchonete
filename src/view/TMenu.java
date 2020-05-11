package view;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class TMenu extends JInternalFrame {

	public TMenu(JDesktopPane desktopPane, String caminho, String operador, String acesso) {
		setBounds(0, 0, 1920, 1080);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblLogo = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/menu.png")));
		lblLogo.setBounds(706, 11, 446, 306);
		panel.add(lblLogo);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Op\u00E7\u00F5es do Menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(295, 349, 1259, 632);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setBounds(573, 11, 107, 37);
		panel_1.add(lblMenu);
		lblMenu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnNewButton = new JButton("Caixa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// *********************************************************************************
						TCaixa tc = new TCaixa(desktopPane, caminho, operador, acesso);
						desktopPane.add(tc);
						tc.setVisible(true);
						dispose();
						// *********************************************************************************
					}
				}).start();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(156, 94, 300, 180);
		panel_1.add(btnNewButton);

		JButton btnCadastroProdutos = new JButton("Cadastro Produtos");
		btnCadastroProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// *****************************************************
						if (acesso.equals("adm")) {
							TProdutos tp = new TProdutos(desktopPane, caminho, operador, acesso);
							desktopPane.add(tp);
							tp.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Acesso não autorizado");
						}
						// *****************************************************
					}
				}).start();
			}
		});
		btnCadastroProdutos.setForeground(Color.WHITE);
		btnCadastroProdutos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		if (acesso.equals("adm")) {
			btnCadastroProdutos.setBackground(Color.RED);
		} else {
			btnCadastroProdutos.setBackground(Color.LIGHT_GRAY);
		}
		btnCadastroProdutos.setBounds(476, 94, 300, 180);
		panel_1.add(btnCadastroProdutos);

		JButton btnCadastroUsuarios = new JButton("Cadastro Usu\u00E1rios");
		btnCadastroUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// *****************************************************
						if (acesso.equals("adm")) {
							TPessoas tp = new TPessoas(desktopPane, caminho, operador, acesso);
							desktopPane.add(tp);
							tp.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Acesso não autorizado");
						}
						// *****************************************************
					}
				}).start();
			}
		});
		btnCadastroUsuarios.setForeground(Color.WHITE);
		btnCadastroUsuarios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		if (acesso.equals("adm")) {
			btnCadastroUsuarios.setBackground(Color.RED);
		} else {
			btnCadastroUsuarios.setBackground(Color.LIGHT_GRAY);
		}
		btnCadastroUsuarios.setBounds(793, 94, 300, 180);
		panel_1.add(btnCadastroUsuarios);

		JButton btnRelatriosDeVendas = new JButton("Relat\u00F3rios");
		btnRelatriosDeVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// *****************************************************
						if (acesso.equals("adm")) {
							TRelatorios tr = new TRelatorios(desktopPane, caminho, operador, acesso);
							desktopPane.add(tr);
							tr.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Acesso não autorizado");
						}
						// *****************************************************
					}
				}).start();
			}
		});
		btnRelatriosDeVendas.setForeground(Color.WHITE);
		btnRelatriosDeVendas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		if (acesso.equals("adm")) {
			btnRelatriosDeVendas.setBackground(Color.RED);
		} else {
			btnRelatriosDeVendas.setBackground(Color.LIGHT_GRAY);
		}
		btnRelatriosDeVendas.setBounds(156, 288, 300, 180);
		panel_1.add(btnRelatriosDeVendas);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// *************************************************
						TLogin tl = new TLogin(desktopPane, caminho);
						tl.setLocation(0, 0);
						desktopPane.add(tl);
						tl.setVisible(true);
						// *************************************************
					}
				}).start();
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnLogin.setBackground(Color.RED);
		btnLogin.setBounds(476, 288, 300, 180);
		panel_1.add(btnLogin);

		Calendar d = Calendar.getInstance();
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		String data = (String) dataFormatada.format(d.getTime());

		JLabel label_2 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(1519, 102, 195, 137);
		panel.add(label_2);

		JLabel label_3 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(1314, 102, 195, 137);
		panel.add(label_3);

		JLabel label_4 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(1109, 102, 195, 137);
		panel.add(label_4);

		JLabel label_5 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(552, 102, 195, 137);
		panel.add(label_5);

		JLabel label_6 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(347, 102, 195, 137);
		panel.add(label_6);

		JLabel label_7 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(142, 102, 195, 137);
		panel.add(label_7);

		JLabel label = new JLabel("Operador : " + operador);
		label.setBounds(295, 295, 247, 62);
		panel.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		label.setBackground(Color.WHITE);
		JLabel label_1 = new JLabel(data);
		label_1.setBounds(1386, 314, 168, 34);
		panel.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setBorder(null);

	}
}
