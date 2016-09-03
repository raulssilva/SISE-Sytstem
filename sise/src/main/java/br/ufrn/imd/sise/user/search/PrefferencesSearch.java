package br.ufrn.imd.sise.user.search;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.ufrn.imd.sise.oauth.RequestAuthorization;
import br.ufrn.imd.sise.oauth.exceptions.InvalidServiceRequestException;
import br.ufrn.imd.sise.oauth.exceptions.RequestException;
import br.ufrn.imd.sise.oauth.exceptions.UnauthorizedServiceRequestException;
import br.ufrn.imd.sise.user.model.Course;
import br.ufrn.imd.sise.user.model.CourseClass;
import br.ufrn.imd.sise.user.model.Prefferences;
import br.ufrn.imd.sise.user.model.Subject;
import br.ufrn.imd.sise.user.model.User;
import br.ufrn.imd.sise.user.search.exceptions.AcessVinculoUserException;
import br.ufrn.imd.sise.user.search.exceptions.ConsultCourseClassException;
import br.ufrn.imd.sise.user.search.exceptions.CourseClassIdException;
import br.ufrn.imd.sise.user.search.exceptions.InvalidIdUserException;
import br.ufrn.imd.sise.user.search.exceptions.ServiceAPIException;

public class PrefferencesSearch {
	
	private static final String ACESS_PERFIL_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/perfilusuario/";
	private static final String ACESS_VINCULO_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/listavinculos/usuario";
	private static final String ACESS_DISCIPLINAS_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/matriculacomponente/discente/";//{idDiscente}/all;
	private static final String ACESS_DISCIPLINA_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/turma/usuario/";

	private RequestAuthorization authorization;
	
	public PrefferencesSearch(RequestAuthorization authorization){
		this.authorization = authorization;
	}
	
	public Prefferences searchPrefferences() throws UnauthorizedServiceRequestException, RequestException, ServiceAPIException, ParseException{

		Prefferences prefferences = null;
		
		try {
			
			User user = consultUser();
			List<CourseClass> coursesClass = consultCourseClass(user.getIdStudent());
			Course course = colsultCourse();
			
			prefferences = new Prefferences(course, coursesClass, user);
		
		} catch (InvalidIdUserException | AcessVinculoUserException | ConsultCourseClassException | CourseClassIdException e) {
			throw new ServiceAPIException(e);
		}
		return prefferences;
	}
	
	public RequestAuthorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(RequestAuthorization authorization) {
		this.authorization = authorization;
	}

//	private void printTime(String titulo, long inicio){
//		long fim  = System.currentTimeMillis();  
//		System.out.println(titulo+" time: "+ (fim - inicio)/1000.0  +"seconds");
//	}

	private String getResponseString(String request) throws InvalidServiceRequestException, UnauthorizedServiceRequestException, RequestException{
		String stringResponse = authorization.getResponse(request);
		return stringResponse;
	}
	
	private JSONObject extractJSONObject(String stringJson) throws ParseException{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(stringJson);
		return (JSONObject) obj;
	}
	
	private String extractStringJSONObject(String stringJson, String stringObj) throws ParseException{
		JSONObject jsonObject = extractJSONObject(stringJson);
		return jsonObject.get(stringObj).toString();
	}

	private JSONArray extractJSONArray(String stringJson,String stringObj) throws ParseException{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(stringJson);
		JSONObject jsonObject = (JSONObject) obj;
		return (JSONArray) jsonObject.get(stringObj);
	}
	
	private JSONArray extractJSONArray(String stringJson) throws ParseException{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(stringJson);
		return (JSONArray) obj;
	}
	
	private String colsultProfileName(int idUser) throws InvalidIdUserException, UnauthorizedServiceRequestException, RequestException, ParseException{

		String stringResponse;
		
		try {
			stringResponse = getResponseString(ACESS_PERFIL_USER+idUser);
		} catch (InvalidServiceRequestException e1) {
			throw new InvalidIdUserException(ACESS_PERFIL_USER, idUser);
		} 

		String name = extractStringJSONObject(stringResponse, "nome");

		return name;
	}
	
	private Course colsultCourse() throws UnauthorizedServiceRequestException, RequestException, AcessVinculoUserException, ParseException{
		String stringResponse = null;
		try {
			stringResponse = getResponseString(ACESS_VINCULO_USER);
		} catch (InvalidServiceRequestException e1) {
			throw new AcessVinculoUserException(ACESS_VINCULO_USER);
		} 
		
		Course course = new Course();
		
		JSONArray discentes = extractJSONArray(stringResponse, "discentes");
		
		JSONObject discente =  (JSONObject) discentes.get(0);

		String curso = discente.get("curso").toString();
		
		course.setName(curso);
		
		return course;
	}
	
	private User consultUser() throws UnauthorizedServiceRequestException, RequestException, InvalidIdUserException, AcessVinculoUserException, ParseException{

		String stringResponse = null;
		try {
			stringResponse = getResponseString(ACESS_VINCULO_USER);
		} catch (InvalidServiceRequestException e1) {
			throw new AcessVinculoUserException(ACESS_VINCULO_USER);
		} 

		User user = new User();
		
		JSONArray discentes = extractJSONArray(stringResponse, "discentes");
		JSONObject discente =  (JSONObject) discentes.get(0);

		int idUsuario = Integer.parseInt(discente.get("idUsuario").toString()) ;
		int idDiscente = Integer.parseInt(discente.get("id").toString()) ;
		int matricula = Integer.parseInt(discente.get("matricula").toString());
		
		user.setId(idUsuario);
		user.setIdStudent(idDiscente);
		user.setIdMatriculation(matricula);
		user.setName(colsultProfileName(idUsuario));

		return user;	
	}
	
	private List<CourseClass> consultCourseClass(int idUser) throws UnauthorizedServiceRequestException, RequestException, ConsultCourseClassException, ParseException, CourseClassIdException{

		String stringResponse = null;
		try {
			stringResponse = getResponseString(ACESS_DISCIPLINAS_USER+idUser+"/all");
		} catch (InvalidServiceRequestException e1) {
			throw new ConsultCourseClassException(ACESS_DISCIPLINAS_USER+idUser+"/all", idUser);
		} 
		
		List<CourseClass> coursesClass = new ArrayList<CourseClass>();

		JSONArray turmas = extractJSONArray(stringResponse);
		
		for(Object turma : turmas){
			JSONObject turmaJASON = (JSONObject) turma;
			
			int idTurma = Integer.parseInt(turmaJASON.get("idTurma").toString()) ;
			String description = turmaJASON.get("idTurma").toString();
			Subject subject = consultSubject(idTurma);
			
			CourseClass courseClass = new CourseClass(idTurma, subject, description);
			
			coursesClass.add(courseClass);
		}
		
		return coursesClass;

	}
	
	private Subject consultSubject(int courseClassId) throws UnauthorizedServiceRequestException, RequestException, ParseException, CourseClassIdException{
		
		String stringResponse = null;
		try {
			stringResponse = getResponseString(ACESS_DISCIPLINA_USER+courseClassId);
		} catch (InvalidServiceRequestException e1) {
			throw new CourseClassIdException(ACESS_DISCIPLINA_USER, courseClassId);
		} 
		
		JSONObject disciplina = extractJSONObject(stringResponse);

		String nomeComponente = disciplina.get("nomeComponente").toString() ;
		String codigoComponente = disciplina.get("codigoComponente").toString() ;
		int idDisciplina = Integer.parseInt(disciplina.get("idDisciplina").toString()) ;
		
		Subject susbject = new Subject(idDisciplina, nomeComponente, codigoComponente);

		return susbject;
	}
	
}
