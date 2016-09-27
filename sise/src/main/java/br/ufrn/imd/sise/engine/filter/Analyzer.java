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
		System.out.println("analyze(Size Informations: "+informations.size()+", Prefferences"+prefferences.getUser().getName() +", "+modelAssociation.getClass().getCanonicalName()+")");
		List<ComparativeInformation> comparativeInformationList = new ArrayList<ComparativeInformation>();
		
		//TODO Rever essa parte
		PreferencesTerms preferencesTerms = new PreferencesTerms(prefferences);
		
		for (Information information : informations) {
			
			//Compara cada Informação (cada noticia) com as preferências de acordo com um modelo de associação
			ComparativeInformation comparativeInformation = association(information, preferencesTerms, modelAssociation);
			
			//Adiciona na lista
			comparativeInformationList.add(comparativeInformation);
		}
		
		//TODO REITIRAR
		for (ComparativeInformation information : comparativeInformationList) {System.out.println(information.getInformation().getTitle() +", "+ information.getWeight());}System.out.println("----------------------");
		
		List<ComparativeInformation> rankInformationList = sortInformations(comparativeInformationList);
		
		//TODO REITIRAR
		for (ComparativeInformation information : rankInformationList) {System.out.println(information.getInformation().getTitle() +", "+ information.getWeight());}System.out.println("----------------------");
		
		List<Information> listInformation = toListInformation(rankInformationList);
		
		//TODO REITIRAR
		for (Information information : listInformation) {System.out.println(information.getTitle() +", "+ information.getContent().length());}
		
		return listInformation;
	}
	
	
	private ComparativeInformation association(Information information, PreferencesTerms preferencesTerms, ModelAssociation modelAssociation) {
		System.out.println("association(Size Information: "+information.getContent().length()+", preferencesTerms"+preferencesTerms.getUserPreferences().size()+", "+modelAssociation.getClass().getCanonicalName()+")");
		
		ComparativeInformation compInformation = new ComparativeInformation(information);
		
		Extractor extractor = new Extractor();
		Filter filter = new Filter();
		
		Set<String> termsSetInformation = extractor.extract(information.getContent(), filter, true);
		
		for (UserPreferences termPreferences : preferencesTerms.getUserPreferences()) {
			for (String termsInformation : termsSetInformation) {
				double weight = modelAssociation.calculate(termPreferences, termsInformation);
				ComparativeTerm compTerm = new ComparativeTerm(termPreferences, termsInformation, weight);
				compInformation.add(compTerm);

				//TODO (TEMPORARIO) RETIRAR, ISSO NÃO DEVERIA ESTAR AQUI...
				compInformation.setWeight(compInformation.getWeight()+weight);
			}
		}
		
		//TODO REITRAR
		System.out.println("	compInformation = " +compInformation.toString());
		
		return compInformation;
	}

	//TODO Otimizar.
	private List<ComparativeInformation> sortInformations(List<ComparativeInformation> comparativeInformationList) {
		
		List<ComparativeInformation> listInformation = new ArrayList<ComparativeInformation>();
		int size = comparativeInformationList.size();
		int index = 0;
		for(int i=0; i < size;i++){
			for(int j=i; j < size;j++){
				if(comparativeInformationList.get(j).getWeight() > comparativeInformationList.get(i).getWeight()) {
					if(listInformation.size() > 0 && !listInformation.contains(comparativeInformationList.get(j)))
						index = j;
				}
			}
					
			listInformation.add(comparativeInformationList.get(index));
			index = i;
		} 
				
		return listInformation;
	}
	
	//TODO Verificar possibilidade de colocar no pacote utils.
	private List<Information> toListInformation(List<ComparativeInformation> comparativeInformationList) {
		
		List<Information> listInformation = new ArrayList<Information>(); 
		for (ComparativeInformation element : comparativeInformationList) {
			listInformation.add(element.getInformation());
		}
		
		return listInformation;
		
	}
	
}

