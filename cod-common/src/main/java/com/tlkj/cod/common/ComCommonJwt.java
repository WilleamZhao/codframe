/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.common;

import com.tlkj.cod.common.constant.CodCommonJWTConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 *
 * desc token工具
 * @author sourcod
 * @date 2018/6/29 下午5:15
 **/
public class ComCommonJwt {

	/**
	 *
	 * desc 该方法使用HS256算法和Secret:l生成signKey
	 * @author sourcod
	 * @date 2018/6/29 下午5:45
	 * @return 秘钥
	 **/
	private static Key getKeyInstance() {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(CodCommonJWTConstant.SECRET);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		return signingKey;
	}

	/**
	 *
	 * desc 使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
	 * @author sourcod
	 * @date 2018/6/29 下午5:53
	 * @param claims 签名字段
	 * @return token
	 **/
	public static String createJavaWebToken(Map<String, ?> claims) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		JwtBuilder jwtBuilder = Jwts.builder();
		// JWT_ID
		jwtBuilder.setId(CodCommonJWTConstant.ID);
		// 接受者
		jwtBuilder.setAudience("codframe");
		// 自定义属性
		jwtBuilder.setClaims((Map<String, Object>) claims);
		// 主题
		jwtBuilder.setSubject("all");
		// 签发者
		jwtBuilder.setIssuer("tlkj");
		// 签发时间
		jwtBuilder.setIssuedAt(new Date());
		// 失效时间
		jwtBuilder.setNotBefore(CodCommonDate.add(CodCommonDate.now(), Calendar.MINUTE, 30));
		// 过期时间
		jwtBuilder.setExpiration(CodCommonDate.add(CodCommonDate.now(), Calendar.MINUTE, 30));
		jwtBuilder.signWith(signatureAlgorithm, getKeyInstance());
		return jwtBuilder.compact();
	}

	public static boolean verifyToken(String token){
		Claims claims = parseJWT(token);
		claims.getId();
		claims.getSubject();
		claims.getIssuer();
		return true;
	}

	/**
	 *
	 * desc 验证token
	 * @author sourcod
	 * @date 2018/6/29
	 * @param jwt
	 * @return io.jsonwebtoken.Claims
	 **/
	public static Claims parseJWT(String jwt) {
		Key key = getKeyInstance();
		return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
	}
    
}
