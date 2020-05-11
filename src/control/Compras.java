package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Compras {

	public String linha(String caminho, String quantidade, String sanduiche, String codigo) {
		String linha = null;
		double totalLinha = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			while (br.ready()) {
				linha = br.readLine();
				if (linha.startsWith(sanduiche)) {
					String aux[] = linha.split("@");
					double valor = Double.parseDouble(aux[2]);
					totalLinha += Double.parseDouble(quantidade) * valor;
					linha = String.format("%-5.5s %-29.29s %4.3s %8.2f %9.2f", codigo, sanduiche, quantidade, valor,
							totalLinha);
					break;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linha;
	}

	public double cancelarItem(String codigo, String caminho, ArrayList<String> lista) {
		double totalLinha = 0;
		String descricao = null;
		String quantidade = null;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).startsWith(codigo)) {
				String linha = lista.get(i);
				descricao = linha.substring(6, 38);
				quantidade = linha.substring(37, 42);
				linha = linha.replace(",", ".");
				String total = linha.substring(53);
				descricao = descricao.trim();
				quantidade = quantidade.trim();
				total = total.trim();
				totalLinha = Double.parseDouble(total);
				lista.remove(i);
				lista.add(i, codigo + "               ITEM CANCELADO     \n");
				Produtos p = new Produtos();
				p.alterarEstoque(caminho, descricao, quantidade, true);
			}
		}
		return totalLinha;
	}

	public boolean confereLista(String caminho) {
		File lista = new File(caminho + "\\CLista.txt");
		if (lista.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public void clear(String caminho) {
		File lista = new File(caminho + "\\CLista.txt");
		lista.delete();
	}

	public String listaCompras(String caminho) {
		String lista = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CLista.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				lista += linha + "@";
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void listaCompras(String caminho, String linha) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\CLista.txt", true));
			bw.write(linha);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
