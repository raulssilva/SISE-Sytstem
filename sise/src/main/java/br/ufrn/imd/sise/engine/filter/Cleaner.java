package br.ufrn.imd.sise.engine.filter;

import java.util.List;

import br.ufrn.imd.sise.engine.model.Information;

public class Cleaner{
	
	private static Cleaner limpador = new Cleaner();
	
	private Cleaner() {
		// TODO Auto-generated constructor stub
	}
	
	public static Cleaner getInstance(){
		return limpador;
	}

	private Information clean(Information information) {
		String content = information.getContent();
		String contentCleared = "";
		int flag = 0;
		for(int i = 0; i < content.length(); i++){
			if(content.charAt(i) == '<'){
				flag = 1;
			}
			
			if(flag == 0){
				if(content.charAt(i) != '\n'){
					contentCleared += content.charAt(i);					
				}
			}
			
			if(content.charAt(i) == '>'){
				flag = 0;
			}
		}
		information.setContent(contentCleared.trim());
		return information;
	}
	
	public List<Information> clean(List<Information> informations) {
		for (Information information : informations) {
			information = clean(information);
		}
		return informations;
	}
	
}
