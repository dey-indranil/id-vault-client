package com.jpmc.am.common.idvault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyToSecret {
	private String appSignature;
	private String usrDomain;
	private String secret;
	
	public String getAppSignature() {
		return appSignature;
	}
	public void setAppSignature(String appSignature) {
		this.appSignature = appSignature;
	}
	public String getUsrDomain() {
		return usrDomain;
	}
	public void setUsrDomain(String usrDomain) {
		this.usrDomain = usrDomain;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	@Override
	public String toString() {
		return "KeyToSecret [appSignature=" + appSignature + ", usrDomain="
				+ usrDomain + "]";
	}
}
