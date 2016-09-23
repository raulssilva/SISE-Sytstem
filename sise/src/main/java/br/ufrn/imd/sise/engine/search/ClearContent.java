package br.ufrn.imd.sise.engine.search;

public class ClearContent{
	public static String clear(String content){
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
		return contentCleared.trim();
	}
}
