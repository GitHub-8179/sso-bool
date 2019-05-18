package com.sjc.sso.login.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtil {


	private static String publiceKey;
	
	private static String privateKey;
	
	public static String createJWT() throws Exception {
		Long stateTime = System.currentTimeMillis();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sj", "sjc");
		map.put("test", "testJWT");
		
		String token = JWT.create()
		.withHeader(map) //
		.withClaim("age", 123)
		.withJWTId(UUID.randomUUID()+"333")
		.withIssuer("sso server") //jwt签发者
		.withSubject("")  //jwt所面向的用户
		.withAudience("") //接收jwt的一方
//		.withNotBefore(new Date()) //定义在什么时间之前，该jwt都是不可用的.
		.withJWTId(UUID.randomUUID().toString())  //jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
		.withIssuedAt(new Date(stateTime)) // jwt的签发时间
		.withExpiresAt(new Date(stateTime+50*1000*60)) // jwt的过期时间，这个过期时间必须要大于签发时间
		.sign(Algorithm.RSA384(RSAUtils.getPublicKey(publiceKey),RSAUtils.getPrivateKey( privateKey)));

		return token;
		
	}
	
	public static boolean validJWT(String token) {
		try {
		DecodedJWT jwt;
			JWTVerifier verifier = JWT.require(Algorithm.RSA384(RSAUtils.getPublicKey(publiceKey),RSAUtils.getPrivateKey( privateKey)))
					.build();
			jwt = verifier.verify(token);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	
	public static String getPublicKey() {
		return publiceKey;
	}

	@Value("${sso.publiceKey}")
	public void setPublicKey(String publiceKey) {
		JwtUtil.publiceKey = publiceKey;
	}

	public static String getPrivateKey() {
		return privateKey;
	}

	@Value("${sso.privateKey}")
	public void setPrivateKey(String privateKey) {
		JwtUtil.privateKey = privateKey;
	}
	
	
}
