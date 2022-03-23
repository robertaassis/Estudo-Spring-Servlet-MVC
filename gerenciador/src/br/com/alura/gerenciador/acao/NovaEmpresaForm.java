package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovaEmpresaForm  implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// pra nao precisar acessar formNovaEmpresa.jsp, a pessoa so tem que colocar como entrada?acao=NovaEmpresaForm
		// redireciona para o formulario
		return "forward:formNovaEmpresa.jsp";
	}
}
