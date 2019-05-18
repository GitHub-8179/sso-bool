package com.sjc.sso.rsa;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import com.sjc.sso.login.util.Base64Utils;
import com.sjc.sso.login.util.RSAUtils;

import net.iharder.Base64;

public class RSATest {
	static String BasePublicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDO68vPef3mEbFrpOjZecFzNyKdG3gqagQb757zEIbRwwPPMP0uedFl6NOrhuMwe0T4Vr/cvCimCyGiLkMznjAP42NFln8GOpBFn4BIM9O/etb5a1Uu9nCEfBAw6qbphjN+R9x8pkx5w464jhgIZMkJGnzCOVvjXvFhalgOvxWozQIDAQAB";
	static String BasePrivateKey ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM7ry895/eYRsWuk6Nl5wXM3Ip0beCpqBBvvnvMQhtHDA88w/S550WXo06uG4zB7RPhWv9y8KKYLIaIuQzOeMA/jY0WWfwY6kEWfgEgz07961vlrVS72cIR8EDDqpumGM35H3HymTHnDjriOGAhkyQkafMI5W+Ne8WFqWA6/FajNAgMBAAECgYBzpW2fY7r3nuPeGajaAyaPUj1SOUwLyHzLcD0R9CaljazPW73k/8h8gIfs0dKgAgUmFrXclHzTcF0320tLyhlphimlEvExtuX+2vKWZofQuigb6FFvmZXZW0BKYpTuFmZ/3gWwc4Q+3NRKTB7n7oQ3pQFk2PnB7WGIE6W5oJksMQJBAPOesJxc0zQpcPSu1VL66riwc9fQErN6wRB0RTEcBtsD0+Cet+OJ/Htv1JY6f/Il4FnJHYGIAOrJcTBni7vC1X8CQQDZb66/xaFCAewsG6oc8EOX2JeFgRXPdeWFGBfgWe2wNtzVaHD3SRzLopnS9DvHyuYKFLlq31rB9qPzuj0iAB+zAkEAj2QB86a3MWaK85nMOdEyQo6pQIdZ2oYQCM1GxIcIifSQyw7gkW7wSv8hk2LPWAlvKam0Uj9ousjidGkd19/vpQJACCANRqtK8F2EBs3t2HD+BaGZX+rH9t2v+v5Afii+IhSawfrKJeN8iPuFhgWMalXhrMTDfQPlr1PZIpV2DVw10wJAOWDfjhU2aAiRnUBe7/mOjWtehcxSdvGCWv3G5bWe6rQoE4CshzfguG16PFlxQQrD8bWgAEDCiW6Gj/2sOvufwQ==" ; 
	public static void main(String[] args) {
		
		try {
//			Map<String, Object> keyMap =  RSAUtils.genKeyPair() ;
//			String privateKey = RSAUtils.getPrivateKey(keyMap);
//			String publicKey = RSAUtils.getPublicKey(keyMap);
				
//			byte[] b = Base64.decode(privateKey);
//			byte[] bb = Base64.decode(publicKey);
//			KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever
			PrivateKey privateKey = RSAUtils.getPrivateKey(BasePrivateKey);
			PublicKey publicKey = RSAUtils.getPublicKey(BasePublicKey);
//			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(b);
//	        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//	        RSAPrivateKey publicK = (RSAPrivateKey) keyFactory.generatePublic(x509KeySpec);
		     
			System.out.println(new String( Base64Utils.encode(privateKey.getEncoded())));
			System.out.println(new String( Base64Utils.encode(publicKey.getEncoded())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
