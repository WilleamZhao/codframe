/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.common;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

/**
 * 加密算法工具类
 * 
 * @author willeam
 * 
 */
public class CodCommonEncryption {

	/**
	 * 加解密算法/模式/填充方式
	 * 可以任意选择，为了方便后面与iOS端的加密解密，采用与其相同的模式与填充方式
	 * ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个参数iv
	 */
	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

	public static String getSign(String url){
		return CodCommonEncryption.MD5(url).substring(0, 8);
	}

	public static String getPwd(String url){
		return CodCommonEncryption.MD5(url).substring(0, 8);
	}



	/**
	 * 利用MD5进行加密
	 *
	 * @param str
	 *            待加密的字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException
	 *             没有这种产生消息摘要的算法
	 * @throws UnsupportedEncodingException
	 */
	public static String EncoderByMd5(String str) {
		// 确定计算方法
		MessageDigest md5;
		byte[] b = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			b = md5.digest(str.getBytes("utf-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// BASE64Encoder base64en = new BASE64Encoder();
		// 加密后的字符串
		// String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		String result = "";

		// 转换16进制方式

		// 1.
		// BigInteger bigInteger = new BigInteger(1, b);
		// return bigInteger.toString(16);

		// 2.
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			result += hex.toString();
		}
		return result;
	}

	/**
	 * SHA1方式加密
	 * @param decript
	 * @return
	 */
	public static String SHA1(String decript) {
		return DigestUtils.sha1Hex(decript);
	}

	public static String SHA(String decript) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * MD5加密
	 * @param input
	 * @return
	 */
	public static String MD5(String input) {
		return DigestUtils.md5Hex(input);
	}

	/**
	 * AES加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String encryptAES128ECB(String content, String password) {
		// 判断Key是否正确
		if (StringUtils.isEmpty(password)) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (password.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}

		try {
			byte[] raw = password.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
			return encryptBASE64(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content 待解密内容
	 * @param password 解密密钥
	 * @return
	 */
	public static String decryptAES128ECB(String content, String password) {
		// 判断Key是否正确
		if (StringUtils.isEmpty(password)) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (password.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		try {
			byte[] raw = password.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			//先用base64解密
			byte[] original = cipher.doFinal(CodCommonEncryption.decryptBASE64(content));
			return new String(original,"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成密钥
	 */
	private static byte[] generateKey(String aesKey) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		return aesKey.getBytes();
	}

	/**
	 * 生成iv
	 */
	private static AlgorithmParameters generateIV(String ivVal) throws Exception{
		byte[]iv=ivVal.getBytes();
		AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
		params.init(new IvParameterSpec(iv));
		return params;
	}

	/**
	 * 转化成JAVA的密钥格式
	 */
	private static Key convertToKey(byte[] keyBytes) {
		return new SecretKeySpec(keyBytes, "AES");
	}

	/**
	 * 加密 AES-128-CBC
	 */
	public static String encryptAES128CBC(String plainText, String aesKey, String ivVal) throws Exception {
		byte[] data=plainText.getBytes();
		AlgorithmParameters iv = generateIV(ivVal);
		byte[] keyBytes = generateKey(aesKey);
		//转化为密钥
		Key key = convertToKey(keyBytes);
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		//设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, key,iv);
		byte[] encryptData= cipher.doFinal(data);
		return bytesToHexString(encryptData);
	}

	/**
	 * 解密 AES-128-CBC
	 */
	public static String decryptAES128CBC(String encryptedStr,String aesKey,String ivVal) throws Exception{
		byte[] encryptedData=hexStringToByte(encryptedStr);
		byte[] keyBytes = generateKey(aesKey);
		Key key = convertToKey(keyBytes);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		AlgorithmParameters iv=generateIV(ivVal);
		//设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, key,iv);
		byte[] decryptData=cipher.doFinal(encryptedData);
		return new String(decryptData);
	}



	/**
	 * 十六进制字符串转换成数组
	 * @param hex
	 * @return
	 */
	private static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789abcdef".indexOf(c);
		return b;
	}


	/**
	 * 把字节数组转换成16进制字符串
	 * @param bArray
	 * @return
	 */
	private static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2){
				sb.append(0);
			}
			sb.append(sTemp.toLowerCase());
		}
		return sb.toString();
	}

	/**
	 * BASE64加密
	 *
	 * @param content 待加密内容
	 * @return 加密内容
	 */
	public static String encryptBASE64(String content) {
		return encryptBASE64(content.getBytes(Charset.forName("UTF-8")));
	}

	/**
	 * BASE64加密
	 *
	 * @param bytes 待加密内容
	 * @return 加密内容
	 */
	public static String encryptBASE64(byte[] bytes) {
		return new Base64().encodeToString(bytes);
	}

	/**
	 * BASE64解密
	 * 
	 * @param content 待解密内容
	 * @return 解密内容
	 */
	public static byte[] decryptBASE64(String content) {
		return decryptBASE64(content.getBytes(Charset.forName("UTF-8")));
	}

	/**
	 * BASE64解密
	 *
	 * @param bytes 待解密内容
	 * @return 解密内容
	 */
	public static byte[] decryptBASE64(byte[] bytes) {
		return new Base64().decode(bytes);
	}


	public static void main(String[] args) {

		System.out.println("-----AES-128-CBC-----");
		//明文
		String plainTextString = "password";
		System.out.println("明文 : "+plainTextString);
		String aesKey="EMvCB4Vzvj85WG2d";
		String ivVal="KMTObkdI6z31qnHq";
		try {
			//进行加密
			String encryptedData = encryptAES128CBC(plainTextString, aesKey,ivVal);
			//输出加密后的数据
			System.out.println("加密后的数据 : ");
			System.out.println(encryptedData);
			System.out.println();
			String data = CodCommonEncryption.decryptAES128CBC(encryptedData, aesKey,ivVal);
			System.out.println("解密得到的数据 : " + data);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("-----AES-128-ECB-----");
		System.out.println("明文 : "+plainTextString);
		String encryptedData = CodCommonEncryption.encryptAES128ECB(plainTextString, aesKey);
		System.out.println("加密数据: " + encryptedData);
		String data = CodCommonEncryption.decryptAES128ECB(encryptedData, aesKey);
		System.out.println("解密数据: " + data);
	}
}
