package br.ufrn.imd.sise.engine.filter;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import br.ufrn.imd.sise.engine.filter.model.UserPreferences;

public class GoogleModelAssociation implements ModelAssociation{

	private static GoogleModelAssociation googleModelAssociation = new GoogleModelAssociation();
	
	private GoogleModelAssociation() {
		// TODO Auto-generated constructor stub
	}
	
	public double calculate(UserPreferences termPreferences, String termsInformation) {
		double sp = searchResult(termPreferences.getTermo())*1.0;
		double st = searchResult(termsInformation)*1.0;
		double spst = searchResult("\""+termsInformation + "\" \"" + termPreferences.getTermo()+"\"")*1.0;
		double value = (spst/sp)+(spst/st);
		return value;
	}

	public static GoogleModelAssociation getInstance() {
		return googleModelAssociation;
	}
	
	private int searchResult(String term){
    	String url = "https://www.google.com/search?q="+term;
    	Document document = null;
		if(term == "" || term != null){
			return 0;
		}
    	
    	try {
			document = Jsoup //
			                   .connect(url) //
			                   .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
			                   //.userAgent("Mozilla/5.0 (Windows; U; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)")
			                   .get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("[LOG] Erro conection. Unable to find results for '"+term+"'");
		}

		if (document!=null) {
			Element divResultStats = document.select("div#resultStats").first();
	    	
			if (divResultStats==null) {
	    		//TODO Colocar exceção
	    	    //throw new RuntimeException("Unable to find results stats.");
	    		System.err.println("Unable to find results for '"+term+"'");
	    	}
	    	String result = divResultStats.text().trim().split(" ")[1].replace(".", "");
	    	int x = Integer.parseInt(result);
	    	
	    	return x;
		}
			
    	return 0;
    }

}
