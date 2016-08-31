package br.ufrn.imd.sise.engine;

import java.util.List;

import br.ufrn.imd.sise.engine.search.NewsSearchEngine;
import br.ufrn.imd.sise.engine.search.SearchEngine;
import br.ufrn.imd.sise.model.Information;

public class NotificationsManager {
	
	public static void main(String args[]){
		NotificationsManager maneger = new NotificationsManager();

		SearchEngine sn = new NewsSearchEngine();

		List<Information> news = sn.buscar();
		
		maneger.printDemonstration(news);
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
