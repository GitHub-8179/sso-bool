package com.sjc.login.signature;

import java.security.Key;
import java.util.Map;

public class SignatureTest {

	public static void main(String[] args) {
		try {
			Map map= RSAUtils.genKeyPair();
			
			String publicKey = RSAUtils.getPublicKey(map);
			String privateKey = RSAUtils.getPrivateKey(map);

	        String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
	        byte[] data = source.getBytes();
//			byte[] data = "this is test project for 宋金宸".getBytes();
//			byte[] priveteK = RSAUtils.encryptByPrivateKey(data, privateKey);
//			byte[] publicpK = RSAUtils.encryptByPublicKey(data, publicKey);

//			System.out.println(new String (RSAUtils.decryptByPrivateKey(publicpK, privateKey)) );
//			System.out.println(new String (RSAUtils.decryptByPublicKey(priveteK, publicKey)) );

//			String value = RSAUtils.encryptedDataOnJava(source, publicKey);
//			RSAUtils.decryptDataOnJava(value, privateKey);
			
			String sign = RSAUtils.signByPrivateKey(data, privateKey);
			System.out.println("私钥生成数字指纹："+sign);
			boolean bool = RSAUtils.verifyByPrivate(data, publicKey, sign);
			System.out.println("公钥解开数字指纹："+bool);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
