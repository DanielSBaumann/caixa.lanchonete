package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Relatorios {

	public double vendaPeriodo(String caminho, String dataInicial, String dataFinal) {
		double total = 0;
		int d = Integer.parseInt(dataFinal.substring(0, 2));
		d++;
		dataFinal = dataFinal.replace(dataFinal.substring(0, 2), Integer.toString(d));
		try {
			boolean aux = false;
			File f = new File(caminho + "\\CPrevia.txt");
			f.createNewFile();
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CNotasFiscais.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\CPrevia.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.startsWith("Data : " + dataInicial)) {
					aux = true;
				}
				if (aux == true && linha.startsWith("Subtotal")) {
					bw.write(linha + "\n");
				}
				if (linha.startsWith("Data : " + dataFinal)) {
					break;
				}
			}
			br.close();
			bw.close();
			BufferedReader brNovo = new BufferedReader(new FileReader(caminho + "\\CPrevia.txt"));
			while (brNovo.ready()) {
				String linha = brNovo.readLine();
				linha = linha.substring(11);
				total += Double.parseDouble(linha.replace(",", "."));
			}
			brNovo.close();
			f.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return total;
	}

	public String vendaPeriodo(String caminho, String dataInicial, String dataFinal, boolean a) {
		double dinheiro = 0;
		double debito = 0;
		double credito = 0;
		double ticket = 0;
		double sodexo = 0;
		double troco = 0;
		double total = 0;
		int d = Integer.parseInt(dataFinal.substring(0, 2));
		d++;
		dataFinal = dataFinal.replace(dataFinal.substring(0, 2), Integer.toString(d));
		try {
			boolean aux = false;
			File f = new File(caminho + "\\CPrevia.txt");
			f.createNewFile();
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CNotasFiscais.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\CPrevia.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.startsWith("Data : " + dataInicial)) {
					aux = true;
				}
				if (aux == true && linha.startsWith("Subtotal") || aux == true && linha.startsWith("Pago")
						|| aux == true && linha.startsWith("Troco")) {
					bw.write(linha + "\n");
				}
				if (linha.startsWith("Data : " + dataFinal)) {
					break;
				}
			}
			br.close();
			bw.close();
			BufferedReader brNovo = new BufferedReader(new FileReader(caminho + "\\CPrevia.txt"));
			while (brNovo.ready()) {
				String linha = brNovo.readLine();
				if (linha.contains("Subtotal")) {
					linha = linha.substring(11);
					total += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Troco")) {
					linha = linha.substring(8);
					troco += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Dinheiro")) {
					linha = linha.substring(7, 10);
					linha = linha.trim();
					dinheiro += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Débito")) {
					linha = linha.substring(7, 12);
					linha = linha.trim();
					debito += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Crédito")) {
					linha = linha.substring(7, 12);
					linha = linha.trim();
					credito += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Ticket")) {
					linha = linha.substring(7, 12);
					linha = linha.trim();
					ticket += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Sodexo")) {
					linha = linha.substring(7, 12);
					linha = linha.trim();
					sodexo += Double.parseDouble(linha.replace(",", "."));
				}
			}
			brNovo.close();
			f.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dinheiro -= troco;
		DecimalFormat s = new DecimalFormat("0.00");
		String linha = "Total Dinheiro   : " + s.format(dinheiro) + "@" + "Total Débito     : " + s.format(debito) + "@"
				+ "Total Crédito    : " + s.format(credito) + "@" + "Total Ticket     : " + s.format(ticket) + "@"
				+ "Total Sodexo     : " + s.format(sodexo) + "@" + "Total do Periodo : " + s.format(total);
		return linha;
	}

	public String vendaUsuario(String caminho, String usuario, String dataInicial, String dataFinal) {
		double dinheiro = 0;
		double debito = 0;
		double credito = 0;
		double ticket = 0;
		double sodexo = 0;
		double troco = 0;
		double total = 0;
		int d = Integer.parseInt(dataFinal.substring(0, 2));
		d++;
		dataFinal = dataFinal.replace(dataFinal.substring(0, 2), Integer.toString(d));
		try {
			File f = new File(caminho + "\\CPrevia.txt");
			f.createNewFile();
			boolean aux = false;
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CNotasFiscais.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\CPrevia.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.startsWith("Data : " + dataInicial)) {
					aux = true;
				}
				if (aux == true) {
					bw.write(linha + "\n");
				}
				if (linha.startsWith("Data : " + dataFinal)) {
					break;
				}
			}
			br.close();
			bw.close();
			aux = false;
			File fn = new File(caminho + "\\CPrevia2.txt");
			fn.createNewFile();
			BufferedReader brNovo = new BufferedReader(new FileReader(caminho + "\\CPrevia.txt"));
			BufferedWriter bwNovo = new BufferedWriter(new FileWriter(caminho + "\\CPrevia2.txt"));
			while (brNovo.ready()) {
				String linha = brNovo.readLine();
				if (linha.startsWith("Data ") && linha.contains("Operador : " + usuario)) {
					aux = true;
				}
				if (aux == true) {
					bwNovo.write(linha + "\n");
				}
				if (linha.startsWith("Data : ") && !linha.substring(linha.indexOf(":") + 2).contains(usuario)) {// &&!linha.substring(linha.indexOf(":")+2).startsWith(usuario)
					aux = false;
				}
			}
			brNovo.close();
			bwNovo.close();
			BufferedReader brNovo2 = new BufferedReader(new FileReader(caminho + "\\CPrevia2.txt"));
			while (brNovo2.ready()) {
				String linha = brNovo2.readLine();
				if (linha.contains("Subtotal")) {
					linha = linha.substring(11);
					total += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Troco")) {
					linha = linha.substring(8);
					troco += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Dinheiro")) {
					linha = linha.substring(7, 10);
					linha = linha.trim();
					dinheiro += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Débito")) {
					linha = linha.substring(7, 12);
					linha = linha.trim();
					debito += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Crédito")) {
					linha = linha.substring(7, 12);
					linha = linha.trim();
					credito += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Ticket")) {
					linha = linha.substring(7, 12);
					linha = linha.trim();
					ticket += Double.parseDouble(linha.replace(",", "."));
				}
				if (linha.contains("Sodexo")) {
					linha = linha.substring(7, 12);
					linha = linha.trim();
					sodexo += Double.parseDouble(linha.replace(",", "."));
				}
			}
			brNovo2.close();
			f.delete();
			fn.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dinheiro -= troco;
		DecimalFormat s = new DecimalFormat("0.00");
		String linha = "Total Dinheiro   : " + s.format(dinheiro) + "@" + "Total Débito     : " + s.format(debito) + "@"
				+ "Total Crédito    : " + s.format(credito) + "@" + "Total Ticket     : " + s.format(ticket) + "@"
				+ "Total Sodexo     : " + s.format(sodexo) + "@" + "Total do Periodo : " + s.format(total);
		return linha;
	}

	public String produtoPeriodo(String caminho, String produto, String dataInicial, String dataFinal) {
		double valorTotal = 0;
		int quantidade = 0;
		int d = Integer.parseInt(dataFinal.substring(0, 2));
		d++;
		dataFinal = dataFinal.replace(dataFinal.substring(0, 2), Integer.toString(d));
		try {
			boolean aux = false;
			File f = new File(caminho + "\\CPrevia.txt");
			f.createNewFile();
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CNotasFiscais.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\CPrevia.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.startsWith("Data : " + dataInicial)) {
					aux = true;
				}
				if (aux == true) {
					bw.write(linha + "\n");
				}
				if (linha.startsWith("Data : " + dataFinal)) {
					break;
				}
			}
			br.close();
			bw.close();
			BufferedReader brNovo = new BufferedReader(new FileReader(caminho + "\\CPrevia.txt"));
			while (brNovo.ready()) {
				String linha = brNovo.readLine();
				if (linha.contains(produto)) {
					String qtd = linha.substring(36, 42);
					qtd = qtd.trim();
					String sub = linha.substring(53);
					sub = sub.trim();
					sub = sub.replace(",", ".");
					valorTotal += Double.parseDouble(sub);
					quantidade += Double.parseDouble(qtd);
				}
			}
			brNovo.close();
			f.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DecimalFormat s = new DecimalFormat("0.00");
		double valor = valorTotal / quantidade;
		String linha = "Produto    :    " + produto + "@" + "Valor do produto  : " + s.format(valor) + "@"
				+ "Quantidade Vendida no Período : " + quantidade + " unidade(s)" + "@"
				+ "Valor Total dos Produtos      : " + s.format(valorTotal);
		return linha;
	}

	public String produtoPeriodo(String caminho, String produto, String dataInicial, String dataFinal, boolean n) {
		double valorTotal = 0;
		int quantidade = 0;
		int d = Integer.parseInt(dataFinal.substring(0, 2));
		d++;
		dataFinal = dataFinal.replace(dataFinal.substring(0, 2), Integer.toString(d));
		try {
			boolean aux = false;
			File f = new File(caminho + "\\CPrevia.txt");
			f.createNewFile();
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CNotasFiscais.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\CPrevia.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.startsWith("Data : " + dataInicial)) {
					aux = true;
				}
				if (aux == true) {
					bw.write(linha + "\n");
				}
				if (linha.startsWith("Data : " + dataFinal)) {
					break;
				}
			}
			br.close();
			bw.close();
			BufferedReader brNovo = new BufferedReader(new FileReader(caminho + "\\CPrevia.txt"));
			while (brNovo.ready()) {
				String linha = brNovo.readLine();
				if (linha.contains(produto)) {
					String qtd = linha.substring(36, 42);
					qtd = qtd.trim();
					String sub = linha.substring(53);
					sub = sub.trim();
					sub = sub.replace(",", ".");
					valorTotal += Double.parseDouble(sub);
					quantidade += Double.parseDouble(qtd);
				}
			}
			brNovo.close();
			f.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DecimalFormat s = new DecimalFormat("0.00");
		double valor = valorTotal / quantidade;
		String linha = "Produto : " + produto + "@" + "Valor : " + s.format(valor) + "@" + "Quantidade Vendida : "
				+ quantidade + "@" + "Valor Total : " + s.format(valorTotal);
		return linha;
	}

	public ArrayList<Produtos> listaMaisVendidos(String caminho, String dataInicial, String dataFinal) {
		ArrayList<Produtos> listaProdutos = new ArrayList<Produtos>();
		ArrayList<String> nomes = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				nomes.add(linha.substring(0, linha.indexOf("@")));
			}
			br.close();
			for (int i = 0; i < nomes.size(); i++) {
				String linha = produtoPeriodo(caminho, nomes.get(i), dataInicial, dataFinal, true);
				String vetor[] = linha.split("@");
				int quantidade = Integer.parseInt(vetor[2].substring(21));
				if (quantidade > 0) {
					Produtos p = new Produtos(quantidade, linha);
					listaProdutos.add(p);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaProdutos;
	}

	public boolean notaFiscal(String caminho, String nf, ArrayList<String> lista) {
		boolean aux = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CNotasFiscais.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.endsWith(nf)) {
					aux = true;
				}
				if (aux == true) {
					lista.add(linha);
				}
				if (linha.contains("*") && aux == true) {
					break;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aux;
	}

}