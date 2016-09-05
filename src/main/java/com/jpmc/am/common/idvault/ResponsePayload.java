package com.jpmc.am.common.idvault;

public class ResponsePayload {
	private String response;
	//appSig,usrDomain,secret

	@Override
	public String toString() {
		return "ResponsePayload [response=" + response + "]";
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}
