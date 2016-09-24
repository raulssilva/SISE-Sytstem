package br.ufrn.imd.sise.engine.filter.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.sise.engine.model.Information;

public class ComparativeInformation {
	
	private Information information;
	private List<ComparativeTerm> comparativeTerms;
	private double weight;

	public ComparativeInformation(Information information) {
		this.information = information;
		this.comparativeTerms = new ArrayList<ComparativeTerm>();
	}

//	public Set<String> getTermsSet() {
//		Set<String> setTerms = new HashSet<String>();
//		String content = information.getContent();
//		String term = "";
//		for(int i = 0; i < content.length(); i++){
//			if(!((content.charAt(i) <= 47 && content.charAt(i) >= 33) || (content.charAt(i) <= 64 && content.charAt(i) >= 58) || (content.charAt(i) <= 93 && content.charAt(i) >= 91) || (content.charAt(i) == 95) || (content.charAt(i) <= 125 && content.charAt(i) >= 123) || (content.charAt(i) == '\n'))){
//				term += content.charAt(i);
//			}
//			
//			if(content.charAt(i) == ' '){
//				setTerms.add(term.trim());
//				term = "";
//			}
//		}
//		return setTerms;
//	}

	public void add(ComparativeTerm compTerm) {
		this.comparativeTerms.add(compTerm);
		//TODO ADICIONAR NA ORDEM DECRESCENTE
	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	public List<ComparativeTerm> getComparativeTerms() {
		return comparativeTerms;
	}

	public void setComparativeTerms(List<ComparativeTerm> comparativeTerms) {
		this.comparativeTerms = comparativeTerms;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	

}
