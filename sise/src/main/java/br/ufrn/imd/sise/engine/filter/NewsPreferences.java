package br.ufrn.imd.sise.engine.filter;

public class NewsPreferences {
	
	private String termo;
	private int repeticoes;
	private String tituloNoticia; //(tituloNoticia) REFERENCES Noticia(titulo) 
	
	
	public NewsPreferences(){
		this.termo = "";
		this.repeticoes = 0;
		this.tituloNoticia = "";
	}
	
	public NewsPreferences(String n_termo, String n_tituloNoticia){
		this.termo = n_termo;
		this.repeticoes = 1;
		this.tituloNoticia = n_tituloNoticia;
	}
	
	public void setTermo(String n_termo){
		this.termo = n_termo;
	}
	
	public void setRepeticoes(int n_repeticoes){
		this.repeticoes = n_repeticoes;
	}
	
	public void setTituloNoticia(String n_tituloNoticia){
		this.tituloNoticia = n_tituloNoticia;
	}
	
	public String getTermo(){
		return this.termo;
	}
	
	public int getRepeticoes(){
		return this.repeticoes;
	}
	
	public String getTituloNoticia(){
		return this.tituloNoticia;
	}
	
	public void addQuantidade() {
		setRepeticoes(getRepeticoes() + 1);
	}
	
}
