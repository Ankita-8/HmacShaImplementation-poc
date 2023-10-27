package com.cpt.payments.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSha256Utils {
	
	private HmacSha256Utils() {
		
	}
    public static String generateSignature(String secretKey, String jsonInput) {
    	String signature=null;
        try {
            // Create an HMAC-SHA256 hash object using the secret key
            Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSHA256.init(secretKeySpec);

            // Convert the JSON input to bytes
            byte[] jsonBytes = jsonInput.getBytes(StandardCharsets.UTF_8);

            // Generate the HMAC-SHA256 signature as a byte array
            byte[] hmacBytes = hmacSHA256.doFinal(jsonBytes);

            // Encode the HMAC bytes as a Base64 string and return it
            signature= Base64.getEncoder().encodeToString(hmacBytes);
            return signature;
            
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return signature; // Handle the exception appropriately in your code
        }
    }
}
