package control;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Produtos implements Comparable<Produtos> {

	public Produtos(int quantidade, String linha) {
		this.setQuantidade(quantidade);
		this.setLinha(linha);
	}

	public Produtos() {

	}

	private String codigo;
	private String descricao;
	private String tipo;
	private String valor;
	private String estoque;
	private String linha;
	private int quantidade;

	public boolean salvar(String caminho, String codigo, String descricao, String tipo, String valor, String estoque) {
		if (codigo.isEmpty()) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\CProdutos.txt", true));
				int numeroCodigo = numeroCadastro(caminho);
				codigo = Integer.toString(numeroCodigo);
				bw.write(descricao + "@" + tipo + "@" + valor + "@" + estoque + "@" + codigo);
				bw.newLine();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}

	public void alterar() {

	}

	public int numeroCadastro(String caminho) {
		int compara = 1001; // ************
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (!linha.isEmpty()) {
					compara = Integer.parseInt(linha.substring(linha.lastIndexOf("@") + 1));
					compara++;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return compara;
	}

	public boolean localizar(String codigoProcurado, String caminho) {
		int aux = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			String linha = null;
			while (br.ready()) {
				linha = br.readLine();
				String produto[] = linha.split("@");
				if (produto[3].endsWith(codigoProcurado)) {
					String vetor[] = linha.split("@");
					setDescricao(vetor[0]);
					setTipo(vetor[1]);
					setValor(vetor[2]);
					setCodigo(vetor[3]);
					setEstoque(vetor[4]);
					aux++;
				}
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (aux == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean localizar(String nomeProcurado, String caminho, boolean n) {
		int aux = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			String linha = null;
			while (br.ready()) {
				linha = br.readLine();
				if (linha.startsWith(nomeProcurado)) {
					String vetor[] = linha.split("@");
					setDescricao(vetor[0]);
					setTipo(vetor[1]);
					setValor(vetor[2]);
					setCodigo(vetor[4]);
					setEstoque(vetor[3]);
					aux++;
				}
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (aux == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean alterar(String caminho, String codigo, String descricao, String tipo, String valor, String estoque) {
		try {
			String temporario = caminho + "\\Temporario.txt";
			File f = new File(temporario);
			f.createNewFile();
			BufferedReader brNovo = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			BufferedWriter bwNovo = new BufferedWriter(new FileWriter(f));
			while (brNovo.ready()) {
				String novaLinha = brNovo.readLine();
				if (novaLinha.endsWith(codigo)) {
					bwNovo.write(descricao + "@" + tipo + "@" + valor + "@" + estoque + "@" + codigo);
					bwNovo.newLine();
				} else {
					bwNovo.write(novaLinha);
					bwNovo.newLine();
				}
			}
			brNovo.close();
			bwNovo.close();
			File renomear = new File(caminho + "\\CProdutos.txt");
			renomear.delete();
			f.renameTo(renomear);
			return true;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public boolean produto(String caminho, String nome, String quantidade) {
		boolean aux = true;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.startsWith(nome)) {
					String vetor[] = linha.split("@");
					if (Integer.parseInt(vetor[3]) < Integer.parseInt(quantidade)) {
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

	public boolean alterarEstoque(String caminho, String descricao, String quantidade) {
		try {
			String temporario = caminho + "\\Temporario.txt";
			File f = new File(temporario);
			f.createNewFile();
			BufferedReader brNovo = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			BufferedWriter bwNovo = new BufferedWriter(new FileWriter(f));
			while (brNovo.ready()) {
				String novaLinha = brNovo.readLine();
				String vetor[] = novaLinha.split("@");
				int aux = Integer.parseInt(vetor[3]) - Integer.parseInt(quantidade);
				vetor[3] = Integer.toString(aux);
				if (novaLinha.startsWith(descricao)) {
					bwNovo.write(vetor[0] + "@" + vetor[1] + "@" + vetor[2] + "@" + vetor[3] + "@" + vetor[4]);
					bwNovo.newLine();
				} else {
					bwNovo.write(novaLinha);
					bwNovo.newLine();
				}
			}
			brNovo.close();
			bwNovo.close();
			File renomear = new File(caminho + "\\CProdutos.txt");
			renomear.delete();
			f.renameTo(renomear);
			return true;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public boolean alterarEstoque(String caminho, String descricao, String quantidade, boolean n) {
		try {
			String temporario = caminho + "\\Temporario.txt";
			File f = new File(temporario);
			f.createNewFile();
			BufferedReader brNovo = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			BufferedWriter bwNovo = new BufferedWriter(new FileWriter(f));
			while (brNovo.ready()) {
				String novaLinha = brNovo.readLine();
				String vetor[] = novaLinha.split("@");
				int aux = Integer.parseInt(vetor[3]) + Integer.parseInt(quantidade);
				vetor[3] = Integer.toString(aux);
				if (novaLinha.startsWith(descricao)) {
					bwNovo.write(vetor[0] + "@" + vetor[1] + "@" + vetor[2] + "@" + vetor[3] + "@" + vetor[4]);
					bwNovo.newLine();
				} else {
					bwNovo.write(novaLinha);
					bwNovo.newLine();
				}
			}
			brNovo.close();
			bwNovo.close();
			File renomear = new File(caminho + "\\CProdutos.txt");
			renomear.delete();
			f.renameTo(renomear);
			return true;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public boolean excluir(String nomeProcurado, String caminho) {
		String temporario = caminho + "\\Temporario.txt";
		boolean aux = false;
		try {
			File f = new File(temporario);
			f.createNewFile();
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CProdutos.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			while (br.ready()) {
				String linha = br.readLine();
				if (!linha.startsWith(nomeProcurado)) {
					bw.write(linha);
					bw.newLine();
				} else {
					aux = true;
				}
			}
			br.close();
			bw.close();
			File renomear = new File(caminho + "\\CProdutos.txt");
			renomear.delete();
			f.renameTo(renomear);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return aux;
	}

	public void exibirArquivo(String caminho) {
		try {
			Desktop desktop = Desktop.getDesktop();
			desktop.open(new File(caminho + "\\CProdutos.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCodigo() {
		return codigo;
	}

	protected void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	protected void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	protected void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	protected void setValor(String valor) {
		this.valor = valor;
	}

	public String getEstoque() {
		return estoque;
	}

	protected void setEstoque(String estoque) {
		this.estoque = estoque;
	}

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int compareTo(Produtos outraQuantidade) {
		if (this.quantidade < outraQuantidade.getQuantidade()) {
			return -1;
		} else if (this.quantidade > outraQuantidade.getQuantidade()) {
			return 1;
		} else {
			return 0;
		}
	}
}
