package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Auxiliar {

	public void arquivos(String caminho) {
		try {
			File f = new File(caminho);
			if (!f.mkdirs()) {
				File produtos = new File(caminho + "\\CProdutos.txt");
				File pessoas = new File(caminho + "\\CPessoas.txt");
				File notas = new File(caminho + "\\CNotasFiscais.txt");
				File lista = new File(caminho + "\\CLista.txt");
				lista.delete();
				File nota = new File(caminho + "\\CNota.txt");
				nota.delete();
				produtos.createNewFile();
				pessoas.createNewFile();
				notas.createNewFile();
				BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CPessoas.txt"));
				String linha = null;
				while (br.ready()) {
					linha = br.readLine();
				}
				br.close();
				if (linha == null) {
					BufferedWriter bw = new BufferedWriter(new FileWriter(pessoas, true));
					bw.write("LOGIN:admin SENHA:admin @adm@admin#1001\n");
					bw.write("LOGIN:daniel SENHA:daniel @Usuário@daniel#1002");
					bw.newLine();
					bw.close();
				}
				linha = null;
				BufferedReader brNovo = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
				while (brNovo.ready()) {
					linha = brNovo.readLine();
				}
				brNovo.close();
				if (linha == null) {
					BufferedWriter bw = new BufferedWriter(new FileWriter(produtos, true));
					bw.write("x-burguer@Sanduiche@6.00@100@1001\n");
					bw.write("hamburger@Sanduiche@5.00@100@1002\n");
					bw.write("fritas pequena@Side Item@4.00@100@1003\n");
					bw.write("nugget 10 unidades@Side Item@4.00@100@1004\n");
					bw.write("sundae@Desert@5.00@100@1005\n");
					bw.write("shake chocolate@Desert@5.00@100@1006\n");
					bw.write("refri lata@Drink@4.00@100@1007\n");
					bw.write("suco laranja@Drink@4.00@100@1008\n");
					bw.newLine();
					bw.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int button(String caminho) {
		int n = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.contains("Sanduiche") || linha.contains("Side Item")) {
					n++;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return n;
	}

	public int button(String caminho, boolean x) {
		int n = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.contains("Drink") || linha.contains("Desert")) {
					n++;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return n;
	}

	public String nomes(String caminho) {
		String nome = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.contains("Sanduiche") || linha.contains("Side Item")) {
					nome = nome + "#" + linha.substring(0, linha.indexOf("@"));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nome;
	}

	public String nomes(String caminho, boolean x) {
		String nome = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.contains("Drink") || linha.contains("Desert")) {
					nome = nome + "#" + linha.substring(0, linha.indexOf("@"));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nome;
	}

	public boolean corButton(String caminho, String sanduiche) {
		boolean aux = true;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.startsWith(sanduiche)) {
					String vetor[] = linha.split("@");
					if (vetor[3].equals("0")) {
						aux = false;
					}
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
