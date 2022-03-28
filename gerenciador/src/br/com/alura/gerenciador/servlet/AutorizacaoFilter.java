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

/**
 * Servlet Filter implementation class AutorizacaoFilter
 */
//@WebFilter("/entrada") -  CONFIGURADO NO WEB.XML
public class AutorizacaoFilter extends HttpFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
	
		System.out.println("autorizacao filter");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");
		
		// nao permite acessar as paginas sem ter feito login; puxa do session pra saber se tem algum objeto Usuario logado
		HttpSession sessao = request.getSession();
		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado")==null); // se nao tiver logado, retorna true
		boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") ||  paramAcao.equals("LoginForm")); // alguns lugares nao vao precisar estar logado, logo, aqueles que precisarem que ele esteja logado, precisa ehUmaAcaoProtegida ser setado a true
		// apenas as paginas login e loginForm nao precisam estar logado, o resto precisa, logo se for uma delas, ehumaAcaoProtegida vai receber !true=false, entao n é protegida, logo nao precisa fazer login
		
		
		if(ehUmaAcaoProtegida && usuarioNaoEstaLogado) {
			response.sendRedirect("entrada?acao=LoginForm"); 
			return;
		}
						
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	
}
