package br.ufrn.imd.sise.engine;

import java.util.List;

import br.ufrn.imd.sise.engine.search.EventSearchEngine;
import br.ufrn.imd.sise.engine.search.InternshipSearchEngine;
import br.ufrn.imd.sise.engine.search.NewsSearchEngine;
import br.ufrn.imd.sise.engine.search.SearchEngine;
import br.ufrn.imd.sise.model.Information;

public class NotificationsManager {
	
	public NotificationsManager(){
		
		SearchEngine se = new EventSearchEngine();
		SearchEngine sn = new NewsSearchEngine();
		SearchEngine si = new InternshipSearchEngine();
		
		List<Information> lse = se.buscar();
		List<Information> lsn = sn.buscar();
		List<Information> lsi = si.buscar();
		
		
	}

}
