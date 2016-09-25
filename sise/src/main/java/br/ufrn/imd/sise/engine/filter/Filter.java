package br.ufrn.imd.sise.engine.filter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.sise.engine.model.Information;
import br.ufrn.imd.sise.user.model.Prefferences;

public class Filter{
	public Filter(){
	}
	
	//TODO Refatorar para extração, filtragem e formatação
	public Set<String> sintaticFilter(String content){
		Set<String> setTerms = new HashSet<String>();
		String term = "";
		for(int i = 0; i < content.length(); i++){
			if(!((content.charAt(i) <= 47 && content.charAt(i) >= 33) || (content.charAt(i) <= 64 && content.charAt(i) >= 58) || (content.charAt(i) <= 93 && content.charAt(i) >= 91) || (content.charAt(i) == 95) || (content.charAt(i) <= 125 && content.charAt(i) >= 123) || (content.charAt(i) == '\n'))){
				term += content.charAt(i);
			}
			
			if(content.charAt(i) == ' '){
				setTerms.add(term.trim().toLowerCase());
				term = "";
			}
		}
		return setTerms;
	}
	
	public Set<String> semanticFilter(Set<String> setTerms, Set<String> blackListTerms){
		Set<String> keyTerms = new HashSet<String>();
		int flag = 0;
		
		for(String term : setTerms){
			for(String blackTerm : blackListTerms){
				if(term == blackTerm){
					flag = 1;
				}
			}
			
			if(flag == 0){
				keyTerms.add(term);
			}
			
			flag = 0;
		}
		
		return keyTerms;
	}
}
