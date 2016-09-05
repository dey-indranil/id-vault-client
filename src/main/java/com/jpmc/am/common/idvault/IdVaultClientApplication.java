package com.jpmc.am.common.idvault;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootApplication
public class IdVaultClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdVaultClientApplication.class, args);
	}
}

@Component
class NexonHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		return Health.status("I do <3 Nexon!!").build();
	}
}

	@Component
	class KeyGenerationCLR implements CommandLineRunner {

		public static final String ALGORITHM = "RSA";
		
		@Autowired
		public KeyGenerationCLR() {
		}
	
		@Override
		public void run(String... args) throws Exception {
			KeyEntity keyEntityClient = generateKey();
			KeyEntity keyEntityServer = getPublicKeyFromServer();
			RequestPayload requestPayload = createPayload(keyEntityClient,keyEntityServer);
			ResponsePayload response = getSecret(requestPayload);
			String plaintextSecret = decryptSecret(keyEntityClient.getPrivateKey(),keyEntityServer.getPublicKey(), response);
			System.out.println(plaintextSecret);
		}
		
		private String decryptSecret(PrivateKey privateKey, PublicKey publicKey, ResponsePayload response) {
			// TODO Auto-generated method stub
			return null;
		}

		private ResponsePayload getSecret(RequestPayload requestPayload) {
			// TODO Auto-generated method stub
			return null;
		}

		private RequestPayload createPayload(KeyEntity keyEntityClient,
				KeyEntity keyEntityServer) throws JsonProcessingException {
			RequestPayload reqPayload = new RequestPayload();
			
			KeyToSecret keyToSecret = new KeyToSecret();
			keyToSecret.setAppSignature("a1");
			keyToSecret.setUsrDomain("ud1");
			
			ObjectMapper mapper = new ObjectMapper();
			String keyToSecretStr = mapper.writeValueAsString(keyToSecret);
			
			byte[] encryptedPayloadByPrivKey = EncryptionUtil
					.encrypt(keyToSecretStr, keyEntityClient.getPrivateKey());
			byte[] encryptedPayloadByPubKey  = EncryptionUtil
					.encrypt(new String(encryptedPayloadByPrivKey), keyEntityServer.getPublicKey());;
			
			reqPayload.setCipherText(new String(encryptedPayloadByPubKey));
			reqPayload.setPublicKey(new String(keyEntityClient.getPublicKey().getEncoded()));
			return reqPayload;
		}

		private KeyEntity getPublicKeyFromServer() {
			final String uri = "http://localhost:8899/keyEntityById?id=1";
		    RestTemplate restTemplate = new RestTemplate();
		     
		    KeyEntity result = restTemplate.getForObject(uri, KeyEntity.class);
		    return result;
		}

		public KeyEntity generateKey() {
			KeyEntity keyEntity = null;
		    try {
		      final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
		      keyGen.initialize(1024);
		      final KeyPair key = keyGen.generateKeyPair();
		      keyEntity = new KeyEntity();
		      keyEntity.setPrivateKey(key.getPrivate());
		      keyEntity.setPublicKey(key.getPublic());
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return keyEntity;
		  }
}


