package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Usuario;

public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// implementação para usuario; formLogin é redirecionado pra ca
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Banco banco = new Banco();
		Usuario usuario = banco.existeUsuario(login,senha); //procura o usuario e se o achar, retorna usuario
		
		// achou o usuario, então ele fez login e existe
		if(usuario!=null) {
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuarioLogado", usuario); // grava o usuario na sessao pra poder usa-lo em outros lugares
			return "redirect:entrada?acao=ListaEmpresas"; // apenas aqueles que tem login conseguem ver a lista
			
		}
		return "redirect:entrada?acao=LoginForm"; // quem nao tem, fica na parte de login
		
		
	}

}
