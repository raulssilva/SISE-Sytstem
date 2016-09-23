package br.ufrn.imd.sise.engine.filter;

import java.util.List;

import br.ufrn.imd.sise.engine.model.Information;
import br.ufrn.imd.sise.user.model.Prefferences;

public class Filter {
	public List<Information> filtrar(List<Information> listInformation, Prefferences prefferences){
		List<Information> filtredList = null;
		return filtredList;
	}
	
	private List<String> getListTerms(List<Information> listInformation){
		List<String> listTerms = null;
		for (Information information : listInformation) {
			String content = information.getContent();
		}
		return listTerms;
	}
}
