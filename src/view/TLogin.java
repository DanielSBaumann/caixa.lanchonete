package view;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import control.Login;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TLogin extends JInternalFrame {
	private JTextField txtLogin;
	private JPasswordField pswSenha;

	public TLogin(JDesktopPane desktopPane, String caminho) {
		setBounds(0, 0, 1920, 1080);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblLogo = new JLabel(new ImageIcon(TLogin.class.getResource("/imagens/login.jfif")));
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setBackground(new Color(255, 255, 255));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(750, 257, 300, 225);
		panel.add(lblLogo);

		// add(lblImagem);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setBackground(Color.WHITE);
		lblLogin.setBounds(850, 499, 97, 51);
		panel.add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setBounds(800, 549, 210, 44);
		panel.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
		lblSenha.setBackground(Color.WHITE);
		lblSenha.setBounds(841, 592, 117, 51);
		panel.add(lblSenha);

		JButton btnOK = new JButton("ok");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					public void run() {
						// ***********************************************************
						Login l = new Login();
						String acesso = l.login(caminho, txtLogin.getText(), pswSenha.getText());
						if (acesso != null) {
							if (acesso.substring(acesso.indexOf(" ") + 1, acesso.indexOf("@")).contentEquals("adm")) {
								String operador = acesso.substring(acesso.indexOf("@") + 1, acesso.indexOf("#"));
								acesso = "adm";
								TMenu tm = new TMenu(desktopPane, caminho, operador, acesso);
								desktopPane.add(tm);
								tm.setVisible(true);
								dispose();
							} else {
								String operador = acesso.substring(acesso.indexOf("@") + 1, acesso.indexOf("#"));
								acesso = "Usuário";
								TMenu tm = new TMenu(desktopPane, caminho, operador, acesso);
								desktopPane.add(tm);
								tm.setVisible(true);
								dispose();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Login ou senha invalido!!");
						}
						// ***********************************************************
					}
				}).start();
			}
		});
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnOK.setForeground(new Color(255, 255, 255));
		btnOK.setBackground(Color.RED);
		btnOK.setBounds(841, 742, 126, 51);
		panel.add(btnOK);

		pswSenha = new JPasswordField();
		pswSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					new Thread(new Runnable() {
						public void run() {
							// ***********************************************************
							Login l = new Login();
							String acesso = l.login(caminho, txtLogin.getText(), pswSenha.getText());
							if (acesso != null) {
								if (acesso.substring(acesso.indexOf(" ") + 1, acesso.indexOf("@"))
										.contentEquals("adm")) {
									String operador = acesso.substring(acesso.indexOf("@") + 1, acesso.indexOf("#"));
									acesso = "adm";
									TMenu tm = new TMenu(desktopPane, caminho, operador, acesso);
									desktopPane.add(tm);
									tm.setVisible(true);
									dispose();
								} else {
									String operador = acesso.substring(acesso.indexOf("@") + 1, acesso.indexOf("#"));
									acesso = "Usuário";
									TMenu tm = new TMenu(desktopPane, caminho, operador, acesso);
									desktopPane.add(tm);
									tm.setVisible(true);
									dispose();
								}
							} else {
								JOptionPane.showMessageDialog(null, "Login ou senha invalido!!");
							}
							// ***********************************************************
						}
					}).start();
				}
			}
		});
		pswSenha.setHorizontalAlignment(SwingConstants.CENTER);
		pswSenha.setBounds(800, 638, 210, 44);
		panel.add(pswSenha);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setBorder(null);

	}
}
