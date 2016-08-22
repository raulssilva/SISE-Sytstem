package br.ufrn.imd.sise.model;

public abstract class Information {
	
	private String date;
	private String title;
	private String content;
	
	public abstract void informationDescription();
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
