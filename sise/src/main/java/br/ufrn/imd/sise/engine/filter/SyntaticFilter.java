package br.ufrn.imd.sise.engine.filter;

import java.util.List;
import java.util.Set;

public class SyntaticFilter {
	
	public List<String> filter(List<String> terms, List<String> tokens){
		terms.removeAll(tokens);
		return terms;
	}
	
	public Set<String> filter(Set<String> terms, Set<String> tokens){
		terms.removeAll(tokens);
		return terms;
	}

}
