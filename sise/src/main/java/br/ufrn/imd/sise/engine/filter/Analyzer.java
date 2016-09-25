package br.ufrn.imd.sise.engine.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.sise.engine.filter.model.ComparativeInformation;
import br.ufrn.imd.sise.engine.filter.model.ComparativeTerm;
import br.ufrn.imd.sise.engine.filter.model.PreferencesTerms;
import br.ufrn.imd.sise.engine.filter.model.UserPreferences;
import br.ufrn.imd.sise.engine.model.Information;
import br.ufrn.imd.sise.user.model.Prefferences;

public class Analyzer{
	
	private static Analyzer analyze = new Analyzer();
	
	private Analyzer() {
		// TODO Auto-generated constructor stub
	}
	
	public static Analyzer getInstance(){
		return analyze;
	}


	public List<Information> analyze(List<Information> informations, Prefferences prefferences, ModelAssociation modelAssociation) {
		
		List<ComparativeInformation> comparativeInformationList = new ArrayList<ComparativeInformation>();
		
		//TODO Rever essa parte
		PreferencesTerms preferencesTerms = new PreferencesTerms(prefferences);
		
		for (Information information : informations) {
			
			//Compara cada Informação (cada noticia) com as preferências de acordo com um modelo de associação
			ComparativeInformation comparativeInformation = association(information, preferencesTerms, modelAssociation);
			
			//Adiciona na lista
			comparativeInformationList.add(comparativeInformation);
		}
		
		List<Information> rankInformationList = sortInformations(comparativeInformationList);
		
		return rankInformationList;
	}
	
	
	private ComparativeInformation association(Information information, PreferencesTerms preferencesTerms, ModelAssociation modelAssociation) {
		
		ComparativeInformation compInformation = new ComparativeInformation(information);
		
		Filter filter = new Filter();
		
		Set<String> termsSetInformation = filter.sintaticFilter(information.getContent());
		for(String term : termsSetInformation){
			System.out.println(term);
		}
		//Set<String> termsSetInformation = extractTermsSetFromInformation(information);
		
		for (UserPreferences termPreferences : preferencesTerms.getUserPreferences()) {
			for (String termsInformation : termsSetInformation) {
				double weight = modelAssociation.calculate(termPreferences, termsInformation);
				ComparativeTerm compTerm = new ComparativeTerm(termPreferences, termsInformation, weight);
				compInformation.add(compTerm);
			}
		}
		
		return compInformation;
	}

	private List<Information> sortInformations(List<ComparativeInformation> comparativeInformationList) {
		// TODO FAZER Ordenação
//		List<Information> informations = new ArrayList<Information>();
//		for (ComparativeInformation comparativeInformation : comparativeInformationList) {
//			informations.add(comparativeInformation.getInformation());
//		}
		return null;
	}
	
}
