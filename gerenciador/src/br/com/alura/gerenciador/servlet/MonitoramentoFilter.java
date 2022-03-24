package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


// CONFIGURADO NO WEB.XML
//@WebFilter("/entrada") // todas as requisições que vão chegar no servlet, vão chegar no filter também, ou seja, vai passar por aqui as requisições também
public class MonitoramentoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("monitoramento filter");
		long antes = System.currentTimeMillis();
		String acao = request.getParameter("acao");
		
		// executa a ação
		chain.doFilter(request, response);
		
		long depois = System.currentTimeMillis();
		
		System.out.println("tempo de execução: "+ (acao) + "->" + (depois-antes)); // calcula tempo de execução desse programa
		
	}
}
