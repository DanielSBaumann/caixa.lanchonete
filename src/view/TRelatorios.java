package view;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import control.Pagamento;
import control.Produtos;
import control.Relatorios;

public class TRelatorios extends JInternalFrame {
	private JDateChooser dtInicial;
	private JDateChooser dtFinal;
	private JTextArea txtRelatorio;
	private String data;
	private ArrayList<String> lista = new ArrayList<String>();

	public TRelatorios(JDesktopPane desktopPane, String caminho, String operador, String acesso) {
		setBounds(0, 0, 1920, 1080);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Menu Relatorios Gerenciais",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Menu ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(372, 115, 1487, 885);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JButton btnPerido = new JButton("Venda Por Per\u00EDodo");
		btnPerido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ******************************************************
						Relatorios r = new Relatorios();
						// DecimalFormat s = new DecimalFormat("0.00");
						SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
						if (dtInicial.getDate() != null && dtFinal.getDate() != null) {
							String dataI = (String) dataFormatada.format(dtInicial.getDate());
							String dataF = (String) dataFormatada.format(dtFinal.getDate());
							String vetor[] = r.vendaPeriodo(caminho, dataI, dataF, true).split("@");
							txtRelatorio.append("\t\t\t\tBurguer House\n\n\n");
							txtRelatorio.append("Periodo Inicial : " + dataI + "\n");
							txtRelatorio.append("Periodo Final   : " + dataF + "\n");
							txtRelatorio.append("*****************************************\n\n");
							for (int i = 0; i < vetor.length; i++) {
								txtRelatorio.append(vetor[i] + "\n");
							}
							txtRelatorio.append("\n*****************************************\n\n");
							// double total = r.vendaPeriodo(caminho, dataI, dataF);
							// Melhorar exibição
							// txtRelatorio.append(
							// "\t\t Burguer House\n\n\n\nPeriodo Inicial : " + dataI + "\n\nPeriodo Final :
							// "
							// + dataF + "\n\n\n\nVenda Total do periodo : " + s.format(total));
							// txtRelatorio.append("\n");
						} else {
							JOptionPane.showMessageDialog(null,
									"Preencha as datas inicial e final \n para exibir o relatório");
						}
						// ******************************************************
					}
				}).start();
			}
		});
		btnPerido.setBackground(Color.RED);
		btnPerido.setForeground(Color.WHITE);
		btnPerido.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnPerido.setBounds(38, 217, 280, 100);
		panel_1.add(btnPerido);

		JButton btnUsuario = new JButton(
				"<html><body><center>" + "Venda de Usuário por Período" + "</center></body></html>");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ******************************************************
						Relatorios r = new Relatorios();
						SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
						if (dtInicial.getDate() != null && dtFinal.getDate() != null) {
							String usuario = JOptionPane.showInputDialog(null, "Digite nome do operador : ");
							if (usuario != null && !usuario.isEmpty()) {
								String dataI = (String) dataFormatada.format(dtInicial.getDate());
								String dataF = (String) dataFormatada.format(dtFinal.getDate());
								String vetor[] = r.vendaUsuario(caminho, usuario, dataI, dataF).split("@");
								txtRelatorio.append("\t\t\t\tBurguer House\n\n\n");
								txtRelatorio.append("Periodo Inicial : " + dataI + "\n");
								txtRelatorio.append("Periodo Final   : " + dataF + "\n");
								txtRelatorio.append("Operador        : " + usuario + "\n");
								txtRelatorio.append("*****************************************\n\n");
								for (int i = 0; i < vetor.length; i++) {
									txtRelatorio.append(vetor[i] + "\n");
								}
								txtRelatorio.append("\n*****************************************\n\n");
							} else {
								JOptionPane.showMessageDialog(null, "Preencha nome do operador");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Preencha as datas inicial e final \n para exibir o relatório");
						}
						// ******************************************************
					}
				}).start();
			}
		});
		btnUsuario.setForeground(Color.WHITE);
		btnUsuario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnUsuario.setBackground(Color.RED);
		btnUsuario.setBounds(330, 217, 280, 100);
		panel_1.add(btnUsuario);

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
		btnMenu.setBounds(330, 554, 280, 100);
		panel_1.add(btnMenu);

		JButton btnResumo = new JButton("Resumo do dia");
		btnResumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ******************************************************
						Relatorios r = new Relatorios();
						String vetor[] = r.vendaPeriodo(caminho, data, data, true).split("@");
						txtRelatorio.append("\t\t\t\tBurguer House\n\n\n");
						txtRelatorio.append("Resumo do periodo : " + data + "\n");
						// txtRelatorio.append("Periodo Final : " + dataF + "\n");
						txtRelatorio.append("*****************************************\n\n");
						for (int i = 0; i < vetor.length; i++) {
							txtRelatorio.append(vetor[i] + "\n");
						}
						txtRelatorio.append("\n*****************************************\n\n");
						// ******************************************************
					}
				}).start();
			}
		});
		btnResumo.setForeground(Color.WHITE);
		btnResumo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnResumo.setBackground(Color.RED);
		btnResumo.setBounds(38, 328, 280, 100);
		panel_1.add(btnResumo);

		JButton btnNota = new JButton("Nota Fiscal");
		btnNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ******************************************************
						Relatorios r = new Relatorios();
						String nf = JOptionPane.showInputDialog(null,
								"Digite número nota da fiscal a ser localizada : ");
						if (nf != null && !nf.isEmpty()) {
							boolean aux = r.notaFiscal(caminho, nf, lista);
							if (aux == true) {
								txtRelatorio.append("\t\t          Burguer House\n\n\n\nResumo do cupom fiscal \n\n");
								for (int i = 0; i < lista.size(); i++) {
									txtRelatorio.append(lista.get(i) + "\n");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Nota Fiscal não encontrada");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Informe número da Nota Fiscal!");
						}
						// ******************************************************
					}
				}).start();
			}
		});
		btnNota.setForeground(Color.WHITE);
		btnNota.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNota.setBackground(Color.RED);
		btnNota.setBounds(330, 328, 280, 100);
		panel_1.add(btnNota);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Relatorios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(637, 11, 814, 852);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		txtRelatorio = new JTextArea();
		txtRelatorio.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 18));
		txtRelatorio.setEditable(false);
		txtRelatorio.setBounds(10, 21, 772, 820);
		panel_2.add(txtRelatorio);

		JLabel label_1 = new JLabel("Data Inicial");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		label_1.setBounds(38, 106, 131, 35);
		panel_1.add(label_1);

		dtInicial = new JDateChooser();
		dtInicial.setDateFormatString("dd/MM/yy");
		dtInicial.setBounds(38, 154, 161, 50);
		panel_1.add(dtInicial);

		JLabel label_7 = new JLabel("Data Final");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		label_7.setBounds(330, 106, 140, 28);
		panel_1.add(label_7);

		dtFinal = new JDateChooser();
		dtFinal.setDateFormatString("dd/MM/yy");
		dtFinal.setBounds(330, 154, 161, 50);
		panel_1.add(dtFinal);

		JButton btnLimparTela = new JButton("Limpar Tela");
		btnLimparTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// **************************************************
				txtRelatorio.setText("");
				lista.clear();
				// **************************************************
			}
		});
		btnLimparTela.setForeground(Color.WHITE);
		btnLimparTela.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnLimparTela.setBackground(Color.CYAN);
		btnLimparTela.setBounds(470, 807, 140, 56);
		panel_1.add(btnLimparTela);

		JButton btnNotasFiscais = new JButton(
				"<html><body><center>" + "Arquivo Notas Fiscais" + "</center></body></html>");
		btnNotasFiscais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// **********************************************************
						Pagamento p = new Pagamento();
						p.exibirCupom(caminho, true);
						// **********************************************************
					}
				}).start();
			}
		});
		btnNotasFiscais.setForeground(Color.WHITE);
		btnNotasFiscais.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNotasFiscais.setBackground(Color.RED);
		btnNotasFiscais.setBounds(38, 441, 280, 100);
		panel_1.add(btnNotasFiscais);

		JButton btnVendaPorProduto = new JButton(
				"<html><body><center>" + "Venda de Produto por Periodo" + "</center></body></html>");
		btnVendaPorProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ******************************************************
						Relatorios r = new Relatorios();
						SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
						if (dtInicial.getDate() != null && dtFinal.getDate() != null) {
							String produto = JOptionPane.showInputDialog(null, "Digite nome do produto ");
							if (produto != null && !produto.isEmpty()) {
								String dataI = (String) dataFormatada.format(dtInicial.getDate());
								String dataF = (String) dataFormatada.format(dtFinal.getDate());
								String vetor[] = r.produtoPeriodo(caminho, produto, dataI, dataF).split("@");
								txtRelatorio.append("\t\t\t\tBurguer House\n\n\n");
								txtRelatorio.append("Início do periodo : " + dataI + "\n");
								txtRelatorio.append("Fim do periodo : " + dataF + "\n");
								txtRelatorio.append("*****************************************\n\n");
								for (int i = 0; i < vetor.length; i++) {
									txtRelatorio.append(vetor[i] + "\n");
								}
								txtRelatorio.append("\n*****************************************\n\n");
							} else {
								JOptionPane.showMessageDialog(null, "Preencha nome do Produto");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Preencha data inicial e data final");
						}
						// ******************************************************
					}
				}).start();
			}
		});
		btnVendaPorProduto.setForeground(Color.WHITE);
		btnVendaPorProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnVendaPorProduto.setBackground(Color.RED);
		btnVendaPorProduto.setBounds(330, 441, 280, 100);
		panel_1.add(btnVendaPorProduto);

		JButton btnProdutosMaisVendidos = new JButton(
				"<html><body><center>" + "Produtos Mais Vendidos Por Periodo" + "</center></body></html>");
		btnProdutosMaisVendidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						// ****************************************************
						Relatorios r = new Relatorios();
						SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
						if (dtInicial.getDate() != null && dtFinal.getDate() != null) {
							String dataI = (String) dataFormatada.format(dtInicial.getDate());
							String dataF = (String) dataFormatada.format(dtFinal.getDate());
							ArrayList<Produtos> listaProdutos = r.listaMaisVendidos(caminho, dataI, dataF);
							Collections.sort(listaProdutos);
							txtRelatorio.append("\t\t\t\tBurguer House\n\n\n");
							txtRelatorio.append("Início do periodo : " + dataI + "\n");
							txtRelatorio.append("Fim do periodo : " + dataF + "\n");
							txtRelatorio.append("*****************************************\n\n");
							int aux = 0;
							for (int i = listaProdutos.size() - 1; i >= 0; i--) {
								aux++;
								if (aux > 5) {
									break;
								}
								String linha = listaProdutos.get(i).getLinha();
								String vetor[] = linha.split("@");
								txtRelatorio
										.append(vetor[0] + "\n" + vetor[1] + "\n" + vetor[2] + "\n" + vetor[3] + "\n");
								txtRelatorio.append("_____________________________________\n");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Preencha data inicial e data final");
						}
						// ****************************************************
					}
				}).start();
			}
		});
		btnProdutosMaisVendidos.setForeground(Color.WHITE);
		btnProdutosMaisVendidos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnProdutosMaisVendidos.setBackground(Color.RED);
		btnProdutosMaisVendidos.setBounds(38, 554, 280, 100);
		panel_1.add(btnProdutosMaisVendidos);

		JLabel lblCadastroDeProdutos = new JLabel("Relat\u00F3rios");
		lblCadastroDeProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeProdutos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblCadastroDeProdutos.setBounds(798, 45, 430, 64);
		panel.add(lblCadastroDeProdutos);

		JLabel label = new JLabel("Operador : " + operador);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		label.setBackground(Color.WHITE);
		label.setBounds(372, 61, 275, 49);
		panel.add(label);

		Calendar d = Calendar.getInstance();
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		data = (String) dataFormatada.format(d.getTime());
		JLabel lblData = new JLabel(data);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblData.setBounds(1692, 64, 156, 43);
		panel.add(lblData);

		JLabel label_2 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(82, 266, 195, 137);
		panel.add(label_2);

		JLabel label_5 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(82, 115, 195, 137);
		panel.add(label_5);

		JLabel lblLogo = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/menu.png")));
		lblLogo.setBounds(25, 425, 327, 273);
		panel.add(lblLogo);

		JLabel label_6 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		label_6.setBounds(82, 689, 195, 137);
		panel.add(label_6);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblLogo_1 = new JLabel(new ImageIcon(TPagamento.class.getResource("/imagens/logo2.jpg")));
		lblLogo_1.setBounds(82, 841, 195, 137);
		panel.add(lblLogo_1);
		lblLogo_1.setHorizontalAlignment(SwingConstants.CENTER);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setBorder(null);

	}
}
