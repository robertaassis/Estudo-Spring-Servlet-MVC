package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

@WebServlet("/empresas")

// devolve empresas num estado genérico
public class EmpresasService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Empresa> empresasJSON = new Banco().getEmpresas();
		
		String valor = request.getHeader("Accept"); // pega qual o valor do accept 
		
		if(valor.contains("xml")) {

			  List<Empresa> empresasXML = new Banco().getEmpresas(); 
			  XStream xstream = new XStream(); // biblioteca em webcontent/web-inf/lib 
			  xstream.alias("empresa", Empresa.class); // onde estiver o nome da classe <br.com.alura.gerenciador.modelo.Empresa>, vou substituir por <empresa>
			  String xml = xstream.toXML(empresasXML);
			  
			  response.setContentType("application/xml"); // devolve no formato json
			  response.getWriter().print(xml);
			  
		}
		else if(valor.contains("json")) {
			 Gson gson = new Gson(); // biblioteca em webcontent/web-inf/lib String json =
			 String json = gson.toJson(empresasJSON);
			  
			  response.setContentType("application/json"); // devolve no formato json
			  response.getWriter().print(json);
		} else { // nao escolheu xml nem json
			response.setContentType("application/json");
			response.getWriter().print("{'message':'no content'}");
		}
		

		/*
		 * [ {"id":1,"nome":"Alura","dataAbertura":"Mar 24, 2022 3:20:36 PM"},
		 * {"id":2,"nome":"Caelum","dataAbertura":"Mar 24, 2022 3:20:36 PM"} ]
		 */

	}

}
