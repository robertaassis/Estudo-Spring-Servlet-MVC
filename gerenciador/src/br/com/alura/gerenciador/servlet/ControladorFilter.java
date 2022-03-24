package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;

/**
 * Servlet Filter implementation class ControladorFilter
 */
@WebFilter("/ControladorFilter")
public class ControladorFilter extends HttpFilter implements Filter {
  
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");
		
		String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao; // nome da classe da ação
		
		String nome;
		try { // código abaixo é uma forma "geral" de instanciar qualquer classe pedida
			Class classe = Class.forName(nomeDaClasse); // carrega a classe desse nome 
			Acao acao = (Acao) classe.newInstance(); // instancia a classe em uma interface Acao (coloco ela em interface Acao pois todos os outros controllers implementam ele; ele é o "Pai"; mais geral)
			nome = acao.executa(request,response); // chama a funcao executa; retorna uma string
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}
		
		
		// nao precisa dele pq é o ultimo filter
		//chain.doFilter(request, response);
	}

	

}
