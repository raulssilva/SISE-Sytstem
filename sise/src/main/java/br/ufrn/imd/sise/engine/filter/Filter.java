package br.ufrn.imd.sise.engine.filter;

import java.util.ArrayList;
import java.util.List;

public class Filter {
	
	private String blackListChar;
	private List<String> blackListTerms;
	
	public Filter(){
		blackListChar = "! @#$%&*()_-+=,.:{}[]|" + "\"" + "\\" + "\'";
		blackListTerms = new ArrayList<String>();
	}
	
	public Filter(String blackListChar, List<String> blackListTerms){
		this.blackListChar = blackListChar;
		this.blackListTerms = blackListTerms;
	}
	
	public String getBlackListChar() {
		return blackListChar;
	}

	public void setBlackListChar(String blackListChar) {
		this.blackListChar = blackListChar;
	}

	public List<String> getBlackListTerms() {
		return blackListTerms;
	}

	public void setBlackListTerms(List<String> blackListTerms) {
		this.blackListTerms = blackListTerms;
	}
}
