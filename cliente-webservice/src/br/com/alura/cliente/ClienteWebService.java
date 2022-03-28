package br.com.alura.cliente;

import org.apache.http.client.fluent.Request;

// bibliotecas necessarias em lib e referenced libraries (clique direito na biblioteca, build path, add path)
// HttpClient é usada para simular uma requisição no sistema
public class ClienteWebService {

	public static void main(String[] args) throws Exception {
		
		String conteudo = Request.Post("http://localhost:8080/gerenciador/empresas")
				.addHeader("Accept","application/xml") // bota o valor do cabeçalho em Accept
		.execute().returnContent().asString(); // no console retorna no header pedido o que pegou
		
		System.out.println(conteudo);
	}

}
