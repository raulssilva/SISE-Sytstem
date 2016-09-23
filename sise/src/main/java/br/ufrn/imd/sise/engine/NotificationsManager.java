package br.ufrn.imd.sise.engine;

import java.util.List;

import org.json.simple.parser.ParseException;

import br.ufrn.imd.sise.engine.filter.Filter;
import br.ufrn.imd.sise.engine.model.Information;
import br.ufrn.imd.sise.engine.search.NewsSearchEngine;
import br.ufrn.imd.sise.engine.search.SearchEngine;
import br.ufrn.imd.sise.oauth.RequestAuthorization;
import br.ufrn.imd.sise.oauth.exceptions.RequestException;
import br.ufrn.imd.sise.oauth.exceptions.UnauthorizedServiceRequestException;
import br.ufrn.imd.sise.user.model.Prefferences;
import br.ufrn.imd.sise.user.search.PrefferencesSearch;
import br.ufrn.imd.sise.user.search.exceptions.ServiceAPIException;

public class NotificationsManager {
	
	public static void main(String args[]) throws UnauthorizedServiceRequestException, RequestException, ServiceAPIException, ParseException{
		
		NotificationsManager maneger = new NotificationsManager();
		
		//Cria autorização (Por enquanto o ToKEN está estático)
		//TODO Fazer parte de autenticação dinâmica
		RequestAuthorization authorization = new RequestAuthorization();
		
		//Cria o mecanismo de busca de preferencia do cliente passando a autorização correspondente
		PrefferencesSearch pSearchEngine = new PrefferencesSearch(authorization);
		
		//Busca as preferencias usando o mecanismo criado anteirormente
		Prefferences prefferences = pSearchEngine.searchPrefferences();
		
		//TESTE EXIBIR
		System.out.println(prefferences.toString());

		//Cria um motor de busca, usando uma estraégia para buscar notícias
		SearchEngine sn = new NewsSearchEngine();

		//Busca as noticas já no formato de informações
		List<Information> news = sn.buscar();
		
		//TESTE EXIBIR 
		//TODO (REMOVER DEPOIS)
		maneger.printDemonstration(news);
		
		System.out.println();
		
		Filter filter = new Filter();
		filter.filtrar(news, prefferences);
		
	}
	
	void printDemonstration(List<Information> informations){
    	for (Information information : informations) {
    		System.out.println(information.getTitle());
			System.out.println(information.getContent());
			System.out.println(information.getDate());
			System.out.println("--------------------------------");
		}
	}

}
