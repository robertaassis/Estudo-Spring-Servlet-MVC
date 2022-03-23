package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sessao = request.getSession();
		//sessao.removeAttribute("usuarioLogado"); // remove atributo; como estará removido, seremos redirecionados a pagina de login. Mesmo eu tentando acessar outras paginas, por ter removido o atributo, to deslogado, entao nao consigo acessar
		sessao.invalidate(); // destroi o cookie; destroi todas as informações da sessao. Melhor que o de cima, pq se nao teriamos que destruir cada atributo ligado a sessao
		return "redirect:entrada?acao=LoginForm";
	}

}
