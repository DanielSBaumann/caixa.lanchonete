package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Login {

	public String login(String caminho, String login, String senha) {
		String linha = null;
		String acesso = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho + "\\CPessoas.txt"));
			while (br.ready()) {
				linha = br.readLine();
				if (linha.substring(linha.indexOf(":") + 1, linha.indexOf(" ")).equals(login)
						&& linha.substring(linha.lastIndexOf(":") + 1, linha.indexOf("@")-1).equals(senha)) {
					acesso = linha.substring(linha.indexOf(":") + 1, linha.indexOf(" ")) + " "
							+ linha.substring(linha.indexOf("@") + 1);
					break;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return acesso;
	}

}
