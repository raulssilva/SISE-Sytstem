package br.ufrn.imd.sise.oauth;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.ufrn.imd.sise.oauth.exceptions.InvalidServiceRequestException;
import br.ufrn.imd.sise.oauth.exceptions.RequestException;
import br.ufrn.imd.sise.oauth.exceptions.UnauthorizedServiceRequestException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RequestAuthorizationTest {
	

	
	@Test
	@Parameters(method = "getRequestsParams")
	public void getResponseTest(String request){
		RequestAuthorization auth = new RequestAuthorization();
		String stringResponse = null;
		try {
			stringResponse = auth.getResponse(request);
		} catch (InvalidServiceRequestException | UnauthorizedServiceRequestException | RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
