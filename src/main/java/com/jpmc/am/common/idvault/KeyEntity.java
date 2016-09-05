package com.jpmc.am.common.idvault;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyEntity {

		@Id
		@GeneratedValue
		private Long id;  // id

		@JsonIgnore
		@Lob
		private PrivateKey privateKey;
		@Lob
		private PublicKey publicKey;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
		public PrivateKey getPrivateKey() {
			return privateKey;
		}

		public void setPrivateKey(PrivateKey privateKey) {
			this.privateKey = privateKey;
		}

		public PublicKey getPublicKey() {
			return publicKey;
		}

		public void setPublicKey(PublicKey publicKey) {
			this.publicKey = publicKey;
		}

		public KeyEntity() {
		}

		@Override
		public String toString() {
			return "KeyEntity [id=" + id + "]";
		}
		
}
