package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;


@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramAcao = request.getParameter("acao"); // ex: entrada?acao=RemoveEmpresa
		
		// nao permite acessar as paginas sem ter feito login; puxa do session pra saber se tem algum objeto Usuario logado
		HttpSession sessao = request.getSession();
		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado")==null); // se nao tiver logado, retorna true
		boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") ||  paramAcao.equals("LoginForm")); // alguns lugares nao vao precisar estar logado, logo, aqueles que precisarem que ele esteja logado, precisa ehUmaAcaoProtegida ser setado a true
		// apenas as paginas login e loginForm nao precisam estar logado, o resto precisa, logo se for uma delas, ehumaAcaoProtegida vai receber !true=false, entao n é protegida, logo nao precisa fazer login
		
		
		if(ehUmaAcaoProtegida && usuarioNaoEstaLogado) {
			response.sendRedirect("entrada?acao=LoginForm");
			return;
		}
				
		
		String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao; // nome da classe da ação
		
		String nome;
		try { // código abaixo é uma forma "geral" de instanciar qualquer classe pedida
			Class classe = Class.forName(nomeDaClasse); // carrega a classe desse nome 
			Acao acao = (Acao) classe.newInstance(); // instancia a classe em uma interface Acao (coloco ela em interface Acao pois todos os outros controllers implementam ele; ele é o "Pai"; mais geral)
			nome = acao.executa(request,response); // chama a funcao executa; retorna uma string
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}
		
		// separa a string no : devolvendo um array de 2 posições
		// EX: forward:listaEmpresas.jsp. tipoEEndereco[0]=forward, tipoEEndereco[1]=listaEmpresas.jsp
		
		// forward e redirect são comandos diferentes.
		/* O redirect envia uma requisição para o browser colocando um header de redirecionamento, ou seja, o processamento da página termina e todos os dados da requisição 
		  se perdem. O forward é um redirecionamento no server aproveitando-se os dados da requisição atual */
		String[] tipoEEndereco = nome.split(":");
		if(tipoEEndereco[0].equals("forward")) { 
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]); // chama a view
			rd.forward(request, response);
		} else {
			response.sendRedirect(tipoEEndereco[1]); // chama a view
		}
		
		
		// Muito trabalho
		
		//paramAcao.executa(req,res)
		
//		String nome = null;
//		if(paramAcao.equals("ListaEmpresas")) {
//			ListaEmpresas acao = new ListaEmpresas();
//			nome = acao.executa(request, response);
//		} else if(paramAcao.equals("RemoveEmpresa")) {
//			RemoveEmpresa acao = new RemoveEmpresa();
//			nome = acao.executa(request, response);
//		} else if(paramAcao.equals("MostraEmpresa")) {
//			MostraEmpresa acao = new MostraEmpresa();
//			nome = acao.executa(request, response);
//		} else if(paramAcao.equals("AlteraEmpresa")) {
//			AlteraEmpresa acao = new AlteraEmpresa();
//			nome = acao.executa(request, response);
//		} else if(paramAcao.equals("NovaEmpresa")) {
//			NovaEmpresa acao = new NovaEmpresa();
//			nome = acao.executa(request, response);
//		} else if(paramAcao.equals("NovaEmpresaForm")) {
//			NovaEmpresaForm acao = new NovaEmpresaForm();
//			nome = acao.executa(request, response);
//		}
		
	}

}
