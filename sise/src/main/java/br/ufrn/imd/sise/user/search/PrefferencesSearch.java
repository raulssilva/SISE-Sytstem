package br.ufrn.imd.sise.user.search;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.ufrn.imd.sise.oauth.RequestAuthorization;
import br.ufrn.imd.sise.user.model.Course;
import br.ufrn.imd.sise.user.model.CourseClass;
import br.ufrn.imd.sise.user.model.Prefferences;
import br.ufrn.imd.sise.user.model.Subject;
import br.ufrn.imd.sise.user.model.User;

public class PrefferencesSearch {
	
	private static final String ACESS_PERFIL_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/perfilusuario/";
	
	//private static final String ACESS_TOKEN = "6d2bd6a4-8196-4f20-8b5d-8916d3d2770a";
	private static final String ACESS_VINCULO_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/listavinculos/usuario";
	private static final String ACESS_DISCIPLINAS_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/matriculacomponente/discente/";//{idDiscente}/all;
	private static final String ACESS_DISCIPLINA_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/turma/usuario/";

	private void printTime(String titulo, long inicio){
		long fim  = System.currentTimeMillis();  
		System.out.println(titulo+" time: "+ (fim - inicio)/1000.0  +"seconds");
	}
	
	private String getResponseString(String request){
		
		RequestAuthorization auth = new RequestAuthorization();
		String stringResponse = auth.getResponse(request);
		
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
		System.out.println(stringResponse);
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
	
}
