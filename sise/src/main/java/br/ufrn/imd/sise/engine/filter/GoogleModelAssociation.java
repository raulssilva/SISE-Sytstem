package br.ufrn.imd.sise.engine.filter;

import br.ufrn.imd.sise.engine.filter.model.UserPreferences;

public class GoogleModelAssociation implements ModelAssociation{

	private static GoogleModelAssociation googleModelAssociation = new GoogleModelAssociation();
	
	private GoogleModelAssociation() {
		// TODO Auto-generated constructor stub
	}
	
	public double calculate(UserPreferences termPreferences, String termsInformation) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static GoogleModelAssociation getInstance() {
		return googleModelAssociation;
	}

}
