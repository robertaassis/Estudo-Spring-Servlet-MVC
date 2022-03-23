package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovaEmpresaForm  implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* não é possivel acessar formNovaEmpresa.jsp direto pois fica dentro de WEB-INF/view e o tomCat nao consegue ler direto essa pasta, 
		  a pessoa é obrigada a colocar entrada?acao=NovaEmpresaForm, forçando usar o controller pois os JSP dependem das ações dos controllers */
		// redireciona para o formulario
		return "forward:formNovaEmpresa.jsp";
	}
}
