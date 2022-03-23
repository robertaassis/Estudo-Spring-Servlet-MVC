package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// controlador geral; "pai" que controla o fluxo; todos aqueles que implementam Acao terão que implementar o método executa
public interface Acao {

	String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
