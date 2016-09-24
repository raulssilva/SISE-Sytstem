package br.ufrn.imd.sise.engine.filter;

import java.util.List;
import br.ufrn.imd.sise.engine.model.Information;
import br.ufrn.imd.sise.user.model.Prefferences;

public class Filter{
	

	public List<Information> filtrar(List<Information> listInformation, Prefferences prefferences){
		
//		SyntaticFilter syntaticFilter;
		
		//SemanticFilter semanticFilter;
		
//		Cleaner cleaner = Cleaner.getInstance();//Limpa se precisar
		
		Analyzer analyzer = Analyzer.getInstance();//Faz as analises 

		List<Information> filtredList = analyzer.analyze(listInformation, prefferences, GoogleModelAssociation.getInstance());//analisa a lista de informações com a perferencias do usuairo
		
		return filtredList;
		
		
		//List<Information> filtredList = null;
//		getSetTerms(getListTerms(listInformation));
//		return filtredList;
	}
	
//	private Set<String> getSetTerms(List<String> listTerms){
//		Set<String> setTerms = new HashSet<String>();
//		for (String term : listTerms) {
//			setTerms.add(term.toLowerCase());
//		}
//		
//		for (String term : setTerms) {
//			System.out.println(term);
//		}
//		
//		return setTerms;
//	}
//	
//	private List<String> getListTerms(List<Information> listInformation){
//		List<String> listTerms = new ArrayList<String>();
//		for (Information information : listInformation) {
//			String content = information.getContent();
//			int flag = 0;
//			String term = "";
//			for(int i = 0; i < content.length(); i++){
//				if(!((content.charAt(i) <= 47 && content.charAt(i) >= 33) || (content.charAt(i) <= 64 && content.charAt(i) >= 58) || (content.charAt(i) <= 93 && content.charAt(i) >= 91) || (content.charAt(i) == 95) || (content.charAt(i) <= 125 && content.charAt(i) >= 123) || (content.charAt(i) == '\n'))){
//					term += content.charAt(i);
//				}
//				
//				if(content.charAt(i) == ' '){
//					listTerms.add(term.trim());
//					term = "";
//				}
//			}
//		}
//		
////		for (String term : listTerms) {
////			System.out.println(term);
////		}
//		
//		return listTerms;
//	}
//
//	
//	public List<String> stractTerms(Information information) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<String> filter(List<String> terms) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Information> ranker(List<Information> informations) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public double comparator(String preferenceTerm, String informationTerm) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public double comparatorBoolean(String preferenceTerm, String informationTerm) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	
}
