package priv.sjc.base.pass;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import net.iharder.Base64;

/**
 * 			var encrypt = new JSEncrypt();
           encrypt.setPublicKey($("#publickey").val());
           encrypt.setPrivateKey($("#privatekey").val());
           var password = $("#password").val();
           var uname = $("#uname").val();
           password = encrypt.encrypt(password)
           
 * @author 20162
 *
 */
public class RSAUtils {

	  /**
	   * 加密算法RSA
	   */
	  public static final String KEY_ALGORITHM = "RSA";

	  /**
	   * 签名算法
	   */
	  public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	  /**
	   * 获取公钥的key
	   */
	  private static final String PUBLIC_KEY = "RSAPublicKey";

	  /**
	   * 获取私钥的key
	   */
	  private static final String PRIVATE_KEY = "RSAPrivateKey";

	  /**
	   * RSA最大加密明文大小
	   */
	  private static final int MAX_ENCRYPT_BLOCK = 117;

	  /**
	   * RSA最大解密密文大小
	   */
	  private static final int MAX_DECRYPT_BLOCK = 128;

	  /**
	   * 生成密钥对(公钥和私钥)
	   * @return
	   * @throws Exception
	   */
	  public static Map<String, Object> genKeyPair() throws Exception {
	      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
          SecureRandom secureRandom = new SecureRandom(new Date().toString().getBytes()); 
	      keyPairGen.initialize(1024,secureRandom); //这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
	      KeyPair keyPair = keyPairGen.generateKeyPair();
	      
	      RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
	      RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
	      Map<String, Object> keyMap = new HashMap<String, Object>(2);
	      keyMap.put(PUBLIC_KEY, publicKey);
	      keyMap.put(PRIVATE_KEY, privateKey);
	      //可将其保存再文件里，不方便时使用base64加密存储串
//	     System.out.println(publicKey.getEncoded());
//	     System.out.println(privateKey.getEncoded());

          
	      return keyMap;
	  }
	  
	  /**
	   * <p>
	   * 获取私钥Base64
	   * </p>
	   * 
	   * @param keyMap
	   *            密钥对
	   * @return
	   * @throws Exception
	   */
	  public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
	      Key key = (Key) keyMap.get(PRIVATE_KEY);
	      System.out.println("生成私钥："+Base64Utils.encode(key.getEncoded()));
	      
	      return new String( Base64Utils.encode(key.getEncoded()));
	  }

	  /**
	   * <p>
	   * 获取公钥Base64
	   * </p>
	   * @param keyMap
	   *            密钥对
	   * @return
	   * @throws Exception
	   */
	  public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
	      Key key = (Key) keyMap.get(PUBLIC_KEY);
	      System.out.println("生成公钥："+Base64Utils.encode(key.getEncoded()));
	      return new String (Base64Utils.encode(key.getEncoded()));
	  }
	
	  /**
	   * 根据Base64字符串公钥转成PublicKey
	   * @param publicKey
	   * @return
	   * @throws Exception
	   */
	  public static RSAPublicKey getPublicKey(String publicKey) throws Exception {
		byte[] publicKeys = Base64.decode(publicKey);
		KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever
	    return  (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(publicKeys));
	  }
	  
	  /**
	   * 根据Base64字符串私钥钥转成PublicKey
	   * @param publicKey
	   * @return
	   * @throws Exception
	   */
	  public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception {
		byte[] privateKeys = Base64.decode(privateKey);
		KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever
	    return  (RSAPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(privateKeys));
	  }

	  /**
	   * 公钥加密
	   * @param data
	   *            源数据
	   * @param publicKey
	   *            公钥(BASE64编码)
	   * @return
	   * @throws Exception
	   */
	  public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
	      byte[] keyBytes = Base64Utils.decode(publicKey);
//	      System.out.println(new String(keyBytes));
	      X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
//	       PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);

	      KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	      Key publicK = keyFactory.generatePublic(x509KeySpec);
	      // 对数据加密
	      Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	      cipher.init(Cipher.ENCRYPT_MODE, publicK);
	      int inputLen = data.length;
	      ByteArrayOutputStream out = new ByteArrayOutputStream();
	      int offSet = 0;
	      byte[] cache;
	      int i = 0;
	      
	      // 对数据分段加密
	      while (inputLen - offSet > 0) {
	          if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
	              cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
	          } else {
	              cache = cipher.doFinal(data, offSet, inputLen - offSet);
	          }
	          out.write(cache, 0, cache.length);
	          i++;
	          offSet = i * MAX_ENCRYPT_BLOCK;
	      }
	      byte[] encryptedData = out.toByteArray();
	      out.close();
	      System.out.println("公钥加密结果:"+new String(encryptedData));

	      return encryptedData;
	  }

	  /** */
	  /**
	   * 私钥加密
	   * @param data
	   *            源数据
	   * @param privateKey
	   *            私钥(BASE64编码)
	   * @return
	   * @throws Exception
	   */
	  public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
	      byte[] keyBytes = Base64Utils.decode(privateKey);
	      PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
	      KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	      Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
	      Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	      cipher.init(Cipher.ENCRYPT_MODE, privateK);
	      int inputLen = data.length;
	      ByteArrayOutputStream out = new ByteArrayOutputStream();
	      int offSet = 0;
	      byte[] cache;
	      int i = 0;
	      // 对数据分段加密
	      while (inputLen - offSet > 0) {
	          if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
	              cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
	          } else {
	              cache = cipher.doFinal(data, offSet, inputLen - offSet);
	          }
	          out.write(cache, 0, cache.length);
	          i++;
	          offSet = i * MAX_ENCRYPT_BLOCK;
	      }
	      byte[] encryptedData = out.toByteArray();
	      out.close();
	      System.out.println("私钥加密结果:"+new String(encryptedData));
	      return encryptedData;
	  }

	  

	  /**
	   * 私钥解密
	   * @param encryptedData
	   *            已加密数据
	   * @param privateKey
	   *            私钥(BASE64编码)
	   * @return
	   * @throws Exception
	   */
	  public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
	      byte[] keyBytes = Base64Utils.decode(privateKey);
	      PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
	      KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	      Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
	      Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	      cipher.init(Cipher.DECRYPT_MODE, privateK);
	      int inputLen = encryptedData.length;
	      ByteArrayOutputStream out = new ByteArrayOutputStream();
	      int offSet = 0;
	      byte[] cache;
	      int i = 0;
	      // 对数据分段解密
	      while (inputLen - offSet > 0) {
	          if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
	              cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
	          } else {
	              cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
	          }
	          out.write(cache, 0, cache.length);
	          i++;
	          offSet = i * MAX_DECRYPT_BLOCK;
	      }
	      byte[] decryptedData = out.toByteArray();
	      out.close();
	      System.out.println("私钥解密结果:"+new String(decryptedData));

	      return decryptedData;
	  }

	  /**
	   * 公钥解密
	   * @param encryptedData
	   *            已加密数据
	   * @param publicKey
	   *            公钥(BASE64编码)
	   * @return
	   * @throws Exception
	   */
	  public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
	      byte[] keyBytes = Base64Utils.decode(publicKey);
	      X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
	      KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	      Key publicK = keyFactory.generatePublic(x509KeySpec);
	      Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	      cipher.init(Cipher.DECRYPT_MODE, publicK);
	      int inputLen = encryptedData.length;
	      ByteArrayOutputStream out = new ByteArrayOutputStream();
	      int offSet = 0;
	      byte[] cache;
	      int i = 0;
	      // 对数据分段解密
	      while (inputLen - offSet > 0) {
	          if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
	              cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
	          } else {
	              cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
	          }
	          out.write(cache, 0, cache.length);
	          i++;
	          offSet = i * MAX_DECRYPT_BLOCK;
	      }
	      byte[] decryptedData = out.toByteArray();
	      out.close();
	      System.out.println("公钥钥解密结果:"+new String(decryptedData));
	      return decryptedData;
	  }
	  
	  
	  /**
	    * java端公钥加密,转换成Base64字节输出
	    */
	   public static String encryptedDataOnJava(String data, String PUBLICKEY) {
	       try {
	           data = Base64Utils.encode(encryptByPublicKey(data.getBytes(), PUBLICKEY));
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       System.out.println("公钥加密转成base64输出："+data);
	       return data;
	   }

	   /**
	    * java端私钥解密,转换成Base64字节（utf-8）输出
	    */
	   public static String decryptDataOnJava(String data, String PRIVATEKEY) {
	       String temp = "";
	       try {
	           byte[] rs = Base64Utils.decode(data);
	           temp = new String(RSAUtils.decryptByPrivateKey(rs, PRIVATEKEY),"UTF-8"); //以utf-8的方式生成字符串
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       System.out.println("私钥解密转成base64输出："+temp);
	       return temp;
	   }

	   
	   
	   /**
	    * 用私钥对信息生成数字签名
	    * @param data
	    *            已加密数据
	    * @param privateKey
	    *            私钥(BASE64编码)
	    * @return
	    * @throws Exception
	    */
	   public static String signByPrivateKey(byte[] data, String privateKey) throws Exception {
	       byte[] keyBytes = Base64Utils.decode(privateKey);
	       PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
	       KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	       PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
	       Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
	       signature.initSign(privateK);
	       signature.update(data);
	       return Base64Utils.encode(signature.sign());
	   }

	   /**
	    * 校验数字签名
	    * @param data
	    *            已加密数据
	    * @param publicKey
	    *            公钥(BASE64编码)
	    * @param sign
	    *            数字签名
	    * @return
	    * @throws Exception
	    * 
	    */
	   public static boolean verifyByPrivate(byte[] data, String publicKey, String sign) throws Exception {
	       byte[] keyBytes = Base64Utils.decode(publicKey);
	       X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
	       KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	       PublicKey publicK = keyFactory.generatePublic(keySpec);
	       Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
	       signature.initVerify(publicK);
	       signature.update(data);
	       return signature.verify(Base64Utils.decode(sign));
	   }

}
