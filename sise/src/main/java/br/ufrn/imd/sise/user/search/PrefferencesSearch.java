package br.ufrn.imd.sise.user.search;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.ufrn.imd.sise.user.model.Course;
import br.ufrn.imd.sise.user.model.CourseClass;
import br.ufrn.imd.sise.user.model.Prefferences;
import br.ufrn.imd.sise.user.model.Subject;
import br.ufrn.imd.sise.user.model.User;

public class PrefferencesSearch {
	
//	private static final String CLIENT_TARGET = "https://apitestes.info.ufrn.br/authz-server/oauth/token";
//	private static final String CLIENT_ID = "sesi-id";
//	private static final String CLIENT_SECRET = "segredo";
//	private static final String GRANT_TYPE = "client_credentials";
	
	private static final String ACESS_PERFIL_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/perfilusuario/";
	private static final String ACESS_TOKEN = "6d2bd6a4-8196-4f20-8b5d-8916d3d2770a";
	
	private static final String ACESS_VINCULO_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/listavinculos/usuario";
	private static final String ACESS_DISCIPLINAS_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/matriculacomponente/discente/";//{idDiscente}/all;
	private static final String ACESS_DISCIPLINA_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/turma/usuario/";

	private void printTime(String titulo, long inicio){
		long fim  = System.currentTimeMillis();  
		System.out.println(titulo+" time: "+ (fim - inicio)/1000.0  +"seconds");
	}
	
	private String getResponseString(String request){
		//long inicio = System.currentTimeMillis();  
		
		
		Client client = ClientBuilder.newClient();
		
		Response response = client.target(request)
				  .request().header("Authorization", "Bearer "+ ACESS_TOKEN).method("GET");
		
		String stringResponse = response.readEntity(String.class);
		

		//printTime("...getResponseString", inicio);
		
		return stringResponse;
	}
	
	private String colsultProfileName(int idUser){
		long inicio = System.currentTimeMillis();
		
		String stringResponse = getResponseString(ACESS_PERFIL_USER+idUser);

		String name = null;
		
		try {

			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(stringResponse);
			
			JSONObject jsonObject = (JSONObject) obj;

			name = jsonObject.get("nome").toString();

		} catch (ParseException e) {
			e.printStackTrace();
		}

		printTime("..colsultProfileName", inicio);
		
		return name;
	}
	
	private Course colsultCourse(){
		long inicio = System.currentTimeMillis();

		String stringResponse = getResponseString(ACESS_VINCULO_USER);
		
		Course course = new Course();
		
		try {

			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(stringResponse);
			
			JSONObject jsonObject = (JSONObject) obj;

			JSONArray discentes = (JSONArray) jsonObject.get("discentes");
			
			JSONObject discente =  (JSONObject) discentes.get(0);

//			int idUsuario = Integer.parseInt(discente.get("idUsuario").toString()) ;
//			int idDiscente = Integer.parseInt(discente.get("id").toString()) ;
//			int matricula = Integer.parseInt(discente.get("matricula").toString());
			String curso = discente.get("curso").toString();
			

			course.setName(curso);
			
			printTime(".colsultCourse", inicio);
			return course;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private User consultUser(){
		long inicio = System.currentTimeMillis();

		String stringResponse = getResponseString(ACESS_VINCULO_USER);
		
		User user = new User();
		
		try {

			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(stringResponse);
			
			JSONObject jsonObject = (JSONObject) obj;

			JSONArray discentes = (JSONArray) jsonObject.get("discentes");
			
			JSONObject discente =  (JSONObject) discentes.get(0);

			int idUsuario = Integer.parseInt(discente.get("idUsuario").toString()) ;
			int idDiscente = Integer.parseInt(discente.get("id").toString()) ;
			int matricula = Integer.parseInt(discente.get("matricula").toString());
//			String curso = discente.get("curso").toString();
			
			user.setId(idUsuario);
			user.setId(idDiscente);
			user.setIdMatriculation(matricula);
			user.setName(colsultProfileName(idUsuario));
			
			printTime(".consultUser", inicio);

			
			return user;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	private List<CourseClass> consultCourseClass(int idUser){
		long inicio = System.currentTimeMillis();
		
		String stringResponse = getResponseString(ACESS_DISCIPLINAS_USER+idUser+"/all");
		
		List<CourseClass> coursesClass = new ArrayList<CourseClass>();
		
		try {

			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(stringResponse);
			
			JSONArray turmas = (JSONArray) obj;

			for(Object turma : turmas){
				JSONObject turmaJASON = (JSONObject) turma;
				
				int idTurma = Integer.parseInt(turmaJASON.get("idTurma").toString()) ;
				String description = turmaJASON.get("idTurma").toString();
				
				CourseClass courseClass = new CourseClass();
				
				courseClass.setId(idTurma);
				courseClass.setDescription(description);
				courseClass.setSubject(consultSubject(idTurma));
				
				coursesClass.add(courseClass);
			}
			
			printTime(".consultCourseClass", inicio);
			
			return coursesClass;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Subject consultSubject(int courseClassId){
		long inicio = System.currentTimeMillis();
		
		String stringResponse = getResponseString(ACESS_DISCIPLINA_USER+courseClassId);
		
		Subject susbject = new Subject();
		
		try {

			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(stringResponse);
			
			JSONObject disciplina = (JSONObject) obj;

			String nomeComponente = disciplina.get("nomeComponente").toString() ;
			String codigoComponente = disciplina.get("codigoComponente").toString() ;
			int idDisciplina = Integer.parseInt(disciplina.get("idDisciplina").toString()) ;
			
			susbject.setCode(codigoComponente);
			susbject.setId(idDisciplina);
			susbject.setName(nomeComponente);
			
			printTime("..consultSubject", inicio);
			
			return susbject;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Prefferences searchPrefferences(){
		long inicio = System.currentTimeMillis();
		
		Prefferences prefferences = new Prefferences();
		
		User user = consultUser();
		List<CourseClass> coursesClass = consultCourseClass(user.getId());
		Course course = colsultCourse();
		
		prefferences.setUser(user);
		prefferences.setCoursesClass(coursesClass);
		prefferences.setCourse(course);
		
		printTime("searchPrefferences", inicio);
		
		return prefferences;
		
	}
	 
	public static void main( String[] args ) {
		PrefferencesSearch pSearchEngine = new PrefferencesSearch();
		Prefferences prefferences = pSearchEngine.searchPrefferences();
		System.out.println(prefferences.toString());
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
