package com.jpmc.am.common.idvault;

public class RequestPayload {
	private String cipherText;
	private String publicKey;
	public String getCipherText() {
		return cipherText;
	}
	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	@Override
	public String toString() {
		return "RequestPayload [cipherText=" + cipherText + ", publicKey="
				+ publicKey + "]";
	}
	
	
}
