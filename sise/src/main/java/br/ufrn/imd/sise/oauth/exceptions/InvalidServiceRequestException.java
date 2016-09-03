package br.ufrn.imd.sise.oauth.exceptions;

import javax.ws.rs.core.Response;

public class InvalidServiceRequestException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidServiceRequestException(Response response) {

	}


}
