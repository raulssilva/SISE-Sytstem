package br.ufrn.imd.sise.oauth;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RequestAuthorizationTest {
	

	
	@Test
	@Parameters(method = "getRequestsParams")
	public void getResponseTest(String request){
		RequestAuthorization auth = new RequestAuthorization();
		String stringResponse = auth.getResponse(request);
		assertNotNull(stringResponse, "Response for request is valid");
		//TODO tratar exeções
//		{
//			"docentes":[],
//			"discentes":[{
//				"idUsuario":361457,
//				"numero":1,
//				"id":642534,
//				"matricula":"2014025963",
//				"curso":"Curso: TECNOLOGIA DA INFORMAÇÃO/IMD - NATAL - BACHARELADO",
//				"idGestoraAcademica":605
//			}],
//			"docentesExterno":[],
//			"responsaveis":[],
//			"tutores":[],
//			"tutoresIMD":[],
//			"coordenadoresPolo":[],
//			"secretarios":[],
//			"familiares":[],
//			"coordenadoresGerais":[],
//			"coordenadoresUnidades":[],
//			"concedentesEstagios":[],
//			"outros":[],
//			"id":0
//		}

	}
	
	
	
	
	public Object[] getRequestsParams() {
		return new Object[] {
				new Object[]{"https://apitestes.info.ufrn.br/ensino-services/services/consulta/perfilusuario/"},
				new Object[]{"https://apitestes.info.ufrn.br/ensino-services/services/consulta/perfilusuario/"}
		};
	}

}
