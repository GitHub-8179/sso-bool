package com.sjc.sso.jwt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sjc.sso.login.util.RSAUtils;

public class TestJWT {

	static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDO68vPef3mEbFrpOjZecFzNyKdG3gqagQb757zEIbRwwPPMP0uedFl6NOrhuMwe0T4Vr/cvCimCyGiLkMznjAP42NFln8GOpBFn4BIM9O/etb5a1Uu9nCEfBAw6qbphjN+R9x8pkx5w464jhgIZMkJGnzCOVvjXvFhalgOvxWozQIDAQAB";
	static String privateKey ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM7ry895/eYRsWuk6Nl5wXM3Ip0beCpqBBvvnvMQhtHDA88w/S550WXo06uG4zB7RPhWv9y8KKYLIaIuQzOeMA/jY0WWfwY6kEWfgEgz07961vlrVS72cIR8EDDqpumGM35H3HymTHnDjriOGAhkyQkafMI5W+Ne8WFqWA6/FajNAgMBAAECgYBzpW2fY7r3nuPeGajaAyaPUj1SOUwLyHzLcD0R9CaljazPW73k/8h8gIfs0dKgAgUmFrXclHzTcF0320tLyhlphimlEvExtuX+2vKWZofQuigb6FFvmZXZW0BKYpTuFmZ/3gWwc4Q+3NRKTB7n7oQ3pQFk2PnB7WGIE6W5oJksMQJBAPOesJxc0zQpcPSu1VL66riwc9fQErN6wRB0RTEcBtsD0+Cet+OJ/Htv1JY6f/Il4FnJHYGIAOrJcTBni7vC1X8CQQDZb66/xaFCAewsG6oc8EOX2JeFgRXPdeWFGBfgWe2wNtzVaHD3SRzLopnS9DvHyuYKFLlq31rB9qPzuj0iAB+zAkEAj2QB86a3MWaK85nMOdEyQo6pQIdZ2oYQCM1GxIcIifSQyw7gkW7wSv8hk2LPWAlvKam0Uj9ousjidGkd19/vpQJACCANRqtK8F2EBs3t2HD+BaGZX+rH9t2v+v5Afii+IhSawfrKJeN8iPuFhgWMalXhrMTDfQPlr1PZIpV2DVw10wJAOWDfjhU2aAiRnUBe7/mOjWtehcxSdvGCWv3G5bWe6rQoE4CshzfguG16PFlxQQrD8bWgAEDCiW6Gj/2sOvufwQ==" ; 

	static String secret = "sjc";
	public static void main(String[] args) {
		try {
			Long stateTime = System.currentTimeMillis();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sj", "sjc");
			map.put("test", "testJWT");
			String token = JWT.create()
			.withHeader(map) //
			.withClaim("age", 123)
			.withJWTId(UUID.randomUUID()+"")
			.withIssuer("") //jwt签发者
			.withSubject("")  //jwt所面向的用户
			.withAudience("") //接收jwt的一方
			.withNotBefore(new Date()) //定义在什么时间之前，该jwt都是不可用的.
			.withJWTId(UUID.randomUUID().toString())  //jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
			.withIssuedAt(new Date(stateTime)) // jwt的签发时间
			.withExpiresAt(new Date(stateTime+1*1000*60)) // jwt的过期时间，这个过期时间必须要大于签发时间
//		.sign(Algorithm.HMAC384(secret));
			.sign(Algorithm.RSA384(RSAUtils.getPublicKey(publicKey),RSAUtils.getPrivateKey( privateKey)));
			
			System.out.println(token);
			Map<String ,Claim> m = s(token);
			System.out.println(m.get("age"));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (JWTCreationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Map s (String token) {
		DecodedJWT jwt;
		try {
			JWTVerifier verifier = JWT.require(Algorithm.RSA384(RSAUtils.getPublicKey(publicKey),RSAUtils.getPrivateKey( privateKey)))
	//		JWTVerifier verifier = JWT.require(Algorithm.HMAC384(secret))
					.build();
			jwt = verifier.verify(token);
		} catch (Exception e) {
			e.printStackTrace();
			return new HashMap<>();
		}
		return jwt.getClaims();
	}
}
