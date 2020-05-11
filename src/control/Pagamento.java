package control;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Pagamento {

	public boolean nota(String caminho, String subtotal, String pagamento, String pay, String troco, String data,
			String operador) {
		try {
			int nf = numeroNF(caminho);
			File n = new File(caminho + "\\CNota.txt");
			n.createNewFile();
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CLista.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\CNotasFiscais.txt", true));
			BufferedWriter nota = new BufferedWriter(new FileWriter(caminho + "\\CNota.txt"));
			bw.write("Data : " + data + " Operador : " + operador + " NF : " + nf + "\n");
			String linhaFormatada = String.format("%-5.5s %-29.29s %4.3s %8.5s %9.5s", "nº", "produto", "qtd", "preço",
					"total");
			bw.write("\n\n                Burguer House\n\n\n" + linhaFormatada + "\n\n");
			nota.write("Data : " + data + " Operador : " + operador + " NF : " + nf + "\n");
			nota.write("\n\n                Burguer House\n\n\n" + linhaFormatada + "\n\n");
			while (br.ready()) {
				String linha = br.readLine();
				bw.write(linha + "\n");
				nota.write(linha + "\n");
			}
			bw.write("\n\n\nSubtotal : " + subtotal + "\nPago : " + pay + "\tForma de pagamento : " + pagamento + "\nTroco : "
					+ troco + "\n");
			bw.write("*******************************\n");
			nota.write("\n\n\nSubtotal : " + subtotal + "\nPago : " + pay + "\tForma de pagamento : " + pagamento
					+ "\nTroco : " + troco + "\n");
			nota.write("*******************************\n");
			br.close();
			bw.close();
			nota.close();
			Compras c = new Compras();
			c.clear(caminho);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int numeroNF(String caminho) {
		int nf = 1001; // ************
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CNotasFiscais.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.startsWith("Data")) {
					nf++;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nf;
	}

	public void exibirCupom(String caminho) {
		try {
			Desktop desktop = Desktop.getDesktop();
			desktop.open(new File(caminho + "\\CNota.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exibirCupom(String caminho,boolean aux) {
		try {
			Desktop desktop = Desktop.getDesktop();
			desktop.open(new File(caminho + "\\CNotasFiscais.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
