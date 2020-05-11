package control;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Usuarios {

	private String login;
	private String senha;
	private String acesso;
	private String usuario;
	private String codigo;

	public boolean confere(String caminho, String codigo) {
		boolean aux = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CPessoas.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.endsWith(codigo) && !codigo.isEmpty()) {
					aux = true;
					break;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aux;
	}

	public void salvar(String caminho, String login, String senha, String acesso, String usuario) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\CPessoas.txt", true));
			int codigo = numeroCadastro(caminho);
			bw.write("LOGIN:" + login + " SENHA:" + senha + " @" + acesso + "@" + usuario + "#" + codigo);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int numeroCadastro(String caminho) {
		int compara = 1001; // ************
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CPessoas.txt"));
			while (br.ready()) {
				String linha = br.readLine();
				if (!linha.isEmpty()) {
					compara = Integer.parseInt(linha.substring(linha.indexOf("#") + 1));
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

	public boolean localizar(String usuario, String caminho) {
		String linha = null;
		boolean aux = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CPessoas.txt"));
			while (br.ready()) {
				linha = br.readLine();
				if (linha.contains(usuario)) {
					setLogin(linha.substring(linha.indexOf(":") + 1, linha.indexOf(" ")));
					setSenha(linha.substring(linha.lastIndexOf(":") + 1, linha.indexOf("@") - 1));
					setAcesso(linha.substring(linha.indexOf("@") + 1, linha.lastIndexOf("@")));
					setCodigo(linha.substring(linha.indexOf("#") + 1));
					this.usuario = usuario;
					aux = true;
					break;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aux;
	}

	public boolean alterar(String caminho, String login, String senha, String acesso, String usuario, String codigo) {
		try {
			String temporario = caminho + "\\Temporario.txt";
			File f = new File(temporario);
			f.createNewFile();
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CPessoas.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			while (br.ready()) {
				String linha = br.readLine();
				if (linha.endsWith(codigo)) {
					bw.write("LOGIN:" + login + " SENHA:" + senha + " @" + acesso + "@" + usuario + "#" + codigo);
					bw.newLine();
				} else {
					bw.write(linha);
					bw.newLine();
				}
			}
			br.close();
			bw.close();
			File renomear = new File(caminho + "\\CPessoas.txt");
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

	public boolean excluir(String usuario, String caminho) {
		String temporario = caminho + "\\Temporario.txt";
		boolean aux = false;
		try {
			File f = new File(temporario);
			f.createNewFile();
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CPessoas.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			while (br.ready()) {
				String linha = br.readLine();
				if (!linha.contains("@" + usuario + "#")) {
					bw.write(linha);
					bw.newLine();
				} else {
					aux = true;
				}
			}
			br.close();
			bw.close();
			File renomear = new File(caminho + "\\CPessoas.txt");
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
			desktop.open(new File(caminho + "\\CPessoas.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getAcesso() {
		return acesso;
	}

	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}

	public String getUsuario() {
		return usuario;
	}

	protected void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
