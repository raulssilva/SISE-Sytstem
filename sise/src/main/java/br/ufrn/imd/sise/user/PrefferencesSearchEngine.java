package br.ufrn.imd.sise.user;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PrefferencesSearchEngine {
	
//	private static final String CLIENT_TARGET = "https://apitestes.info.ufrn.br/authz-server/oauth/token";
//	private static final String CLIENT_ID = "sesi-id";
//	private static final String CLIENT_SECRET = "segredo";
//	private static final String GRANT_TYPE = "client_credentials";
	
	
	private static final String ACESS_PERFIL_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/perfilusuario/361457";
	private static final String ACESS_TOKEN = "6d2bd6a4-8196-4f20-8b5d-8916d3d2770a";
	 
	
	public static void main( String[] args ) {
		
		//Requisição do perfil do usuário
		Client client = ClientBuilder.newClient();
		Response response = client.target(ACESS_PERFIL_USER)
		  .request().header("Authorization", "Bearer "+ACESS_TOKEN).method("GET");
		//TODO tratar exceção para ACESS_TOKEN inválido

		//Resposta em uma String
		String stringResponse = response.readEntity(String.class);

		//Variáveis que serão lidas do JSON
		String nome;
		String email;
		int idUsuario;
		int id;
		String matricula;
		String curso;
		
		try {
			//Cria-se um JSONparser para ler as infomrações
			JSONParser parser = new JSONParser();
			
			//Cria-se um JSONObject de acordo com JASON retornado
			Object obj = parser.parse(stringResponse);
			JSONObject jsonObject = (JSONObject) obj;

			//Atributos lidos
			nome = jsonObject.get("nome").toString();
			email = jsonObject.get("email").toString();
			
			//é necessario entrar em um vinculo do aluno
			//TODO Tratar erros de leitura errada, ou caso não possua o vinculo de discente 
			JSONObject listaVinculosUsuario = (JSONObject) jsonObject.get("listaVinculosUsuario");
			JSONArray discentes = (JSONArray) listaVinculosUsuario.get("discentes");
			JSONObject discente =  (JSONObject) discentes.get(0);

			//Leitura dos atributos correspoentes ao vinculo
			idUsuario = Integer.parseInt(discente.get("idUsuario").toString()) ;
			id = Integer.parseInt(discente.get("id").toString()) ;
			matricula = discente.get("matricula").toString();
			curso = discente.get("curso").toString();
			
			//PARA TESTES
			System.out.println("Nome: "+nome);
			System.out.println("email: "+email);
			System.out.println("idUsuario: "+idUsuario);
			System.out.println("id: "+id);
			System.out.println("matricula: "+matricula);
			System.out.println("curso: "+curso);
			
			Course course = new Course();
			course.setName(curso);
			
			User user = new User();
			user.setId(idUsuario);
			user.setName(nome);
			
			Prefferences prefferences = new Prefferences();
			prefferences.setCourse(course);
		

		} catch (ParseException e) {
			e.printStackTrace();
		}

    }
	
//	public void authorization(){
//		Client client = ClientBuilder.newClient();
//		Response response = client.target(CLIENT_TARGET)
//				.queryParam("client_id", CLIENT_ID)
//				.queryParam("client_secret", CLIENT_SECRET)
//				.queryParam("grant_type", GRANT_TYPE)
//		  .request().method("POST");
//		//POST http://apitestes.info.ufrn.br/authz-server/oauth/token?client_id=AppId&client_secret=AppSecret&grant_type=client_credentials
//		System.out.println("status: " + response.getStatus());
//		System.out.println("headers: " + response.getHeaders());
//		String stringResponse = response.readEntity(String.class);
//		System.out.println("body:" + stringResponse);
//		System.out.println("--------------------------------------");
//		//-----------------------------------------------------------------------
//		
//		//body:{"access_token":"0c9951b2-97e3-4131-a8a0-b96ea1f5d65b","token_type":"bearer","expires_in":7650041,"scope":"read"}	
//		
//		String access_token =  "";
//		String token_type =  "";
//		//Integer expires_in = "";
//		String scope= "";
//		
//		JSONParser parser = new JSONParser();
//		try {
//			Object obj = parser.parse(stringResponse);
//			
//			JSONObject jsonObject = (JSONObject) obj;
//
//			access_token = jsonObject.get("access_token").toString();
//			token_type = jsonObject.get("token_type").toString();
//			Integer expires_in = Integer.parseInt(jsonObject.get("expires_in").toString()) ;
//			scope = jsonObject.get("scope").toString();
//			
//			System.out.println(access_token);
//			System.out.println(token_type);
//			System.out.println(expires_in);
//			System.out.println(scope);
//
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("--------------------------------------");
//		
//		//https://api.ufrn.br/portal-services/services/noticias/lista/SIGAA
//		Client client2 = ClientBuilder.newClient();
//		Response response2 = client2.target("https://apitestes.info.ufrn.br/bolsas-services/services/consulta/oportunidadebolsa/monitoria")
//		//Response response2 = client.target("https://apitestes.info.ufrn.br/portal-services/services/noticias/lista/SIGAA")
//		  .request()
//		  .header("Authorization", token_type+" "+access_token)
//		  //.header("Accept", "application/json")
//		  .method("GET");
//		//GET http://apitestes.info.ufrn.br/telefone-services/services/consulta/telefone/ccet 
//		//Authorization: Bearer 111
//		System.out.println("status: " + response2.getStatus());
//		System.out.println("headers: " + response2.getHeaders());
//		String stringResponse2 = response2.readEntity(String.class);
//		System.out.println("body:" + stringResponse2);
//		System.out.println("--------------------------------------");
//	}
	

}
