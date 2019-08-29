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

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * httpClient工具类
 * 
 * @author willeam
 *
 */
public class CodCommonHttpClient {
	private static final Logger logger = LoggerFactory.getLogger(CodCommonHttpClient.class);

	private static String CHARSET = "utf-8";
	private static Integer PROCOTOL_HTTP = 0;
	private static Integer PROCOTOL_HTTPS = 1;

	public static HttpResponse httpGet(String url) throws IOException, URISyntaxException {
		return httpGet(url, null, null, null, "");
	}

	public static HttpResponse httpGet(String url, String charset) throws IOException, URISyntaxException {
		return httpGet(url, null,  null,null, charset);
	}

	public static HttpResponse httpGet(String url, List<NameValuePair> nvps) throws IOException, URISyntaxException {
		return httpGet(url, nvps, null, null, "");
	}

	public static HttpResponse httpGet(String url, StringEntity entity) throws IOException, URISyntaxException {
		return httpGet(url, entity, null);
	}

	public static HttpResponse httpGet(String url, StringEntity entity, Header[] headers) throws IOException, URISyntaxException {
		return httpGet(url, null, entity, headers, "");
	}
	
	public static HttpResponse httpGet(String url, List<NameValuePair> nvps, Header[] headers) throws IOException, URISyntaxException {
		return httpGet(url, nvps, null, headers, "");
	}

	public static HttpResponse httpGet(String url, List<NameValuePair> nvps, StringEntity entity, Header[] headers, String charset) throws IOException, URISyntaxException {
		HttpGet get = new HttpGet();
		String nvpString = "";
		boolean isok = false;
		if (nvps != null && nvps.size() > 0){
			nvpString = EntityUtils.toString(new UrlEncodedFormEntity(nvps), StringUtils.isBlank(charset) ? CHARSET : charset);
			isok = true;
		}

		String entityString = "";
		if (entity != null){
			entityString = (isok ? "&" : "") + EntityUtils.toString(entity, StringUtils.isBlank(charset) ? CHARSET : charset);
		}
		get.setURI(new URI(url + "?" + nvpString + entityString));

		if (headers != null && headers.length > 0){
			get.setHeaders(headers);
		}

		return execute(get, PROCOTOL_HTTP);
	}

	public static HttpResponse httpPost(String url) throws URISyntaxException, IOException {
		HttpPost post = new HttpPost();
		post.setURI(new URI(url));
		return execute(post, PROCOTOL_HTTP);
	}

	public static HttpResponse httpPost(String url, List<NameValuePair> nvps) throws URISyntaxException, IOException {
		HttpPost post = new HttpPost();
		post.setEntity(new UrlEncodedFormEntity(nvps));
		post.setURI(new URI(url));
		return execute(post, PROCOTOL_HTTP);
	}

	/**
	 * https GET
	 */
	public static HttpResponse httpsGet(String url) throws IOException, URISyntaxException {
		return httpsGet(url, null);
	}

	public static HttpResponse httpsGet(String url, List<NameValuePair> nvps) throws IOException, URISyntaxException {
		return httpsGet(url, nvps, "");
	}

	public static HttpResponse httpsGet(String url, List<NameValuePair> nvps, String cookie) throws IOException, URISyntaxException {
		return httpsGet(url, nvps, null, cookie);
	}

	public static HttpResponse httpsGet(String url, List<NameValuePair> nvps, Header[] headers, String cookie) throws IOException, URISyntaxException {
		return httpsGet(url, nvps, headers, cookie, "");
	}

	/*public static HttpResponse httpsGet(String url, Header header, String cookie) throws IOException, URISyntaxException {
		HttpGet get = new HttpGet();
		get.setURI(new URI(url));
		if (header != null){
			get.setHeader(header);
		}

		if (StringUtils.isNotBlank(cookie)){
			get.addHeader(new BasicHeader("Cookie", cookie));
		}
		return execute(get);
	}*/

	public static HttpResponse httpsGet(String url, List<NameValuePair> nvps, Header[] headers, String cookie, String charset) throws IOException, URISyntaxException {
		HttpGet get = new HttpGet();

		if (nvps != null && nvps.size() > 0){
			url = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(nvps), StringUtils.isBlank(charset) ? CHARSET : charset);
		}

		get.setURI(new URI(url));

		if (headers != null && headers.length > 0){
			get.setHeaders(headers);
		}

		if (StringUtils.isNotBlank(cookie)){
			get.addHeader(new BasicHeader("Cookie", cookie));
		}
		return execute(get, PROCOTOL_HTTPS);
	}

	public static HttpResponse httpsPost(String url, List<NameValuePair> nvps, String cookie) throws IOException, URISyntaxException {
		return httpsPost(url, null, null, null);
	}

	public static HttpResponse httpsPost(String url, List<NameValuePair> nvps) throws IOException, URISyntaxException {
		return httpsPost(url, nvps, null, null);
	}

	public static HttpResponse httpsPost(String url, List<NameValuePair> nvps, StringEntity body) throws IOException, URISyntaxException {
		return httpsPost(url, nvps, body, null);
	}

	public static HttpResponse httpsPost(String url, StringEntity body) throws IOException, URISyntaxException {
		return httpsPost(url, null, body, null);
	}

	public static HttpResponse httpsPost(String url, StringEntity body, Header header) throws IOException, URISyntaxException {
		return httpsPost(url, null, body, null, new Header[]{header});
	}

	public static HttpResponse httpsPost(String url, String cookie) throws IOException, URISyntaxException {
		return httpsPost(url, null, null, cookie);
	}

	public static HttpResponse httpsPost(String url, List<NameValuePair> nvps, StringEntity body, String cookie) throws URISyntaxException, IOException {
		HttpPost post = new HttpPost();

		if (nvps != null){
			post.setEntity(new UrlEncodedFormEntity(nvps));
		}

		if (body!=null){
			post.setEntity(body);
		}

		if (StringUtils.isNotBlank(cookie)){
			post.addHeader(new BasicHeader("Cookie", cookie));
		}

		post.setURI(new URI(url));
		return httpsPost(url, nvps, body, cookie, new Header[]{});
	}

	public static HttpResponse httpsPost(String url, List<NameValuePair> nvps, StringEntity body, String cookie, Header[] headers) throws URISyntaxException, IOException {
		HttpPost post = new HttpPost();

		if (nvps != null) {
			post.setEntity(new UrlEncodedFormEntity(nvps));
		}

		if (headers.length > 0){
			post.setHeaders(headers);
		}

		if (body != null) {
			post.setEntity(body);
		}

		if (StringUtils.isNotBlank(cookie)) {
			post.addHeader(new BasicHeader("Cookie", cookie));
		}

		post.setURI(new URI(url));
		return execute(post, PROCOTOL_HTTPS);
	}

	/**
	 * 执行请求
	 * @param request 请求头
	 * @param prot    请求协议
	 */
	private static HttpResponse execute(HttpUriRequest request, int prot) throws IOException {
		CloseableHttpClient httpClient = prot == PROCOTOL_HTTP ? HttpClients.createDefault() : CodCommonHttpClient.createSSLClientDefault();
		return httpClient.execute(request);
	}

	/**
	 * 创建ssl链接
	 * 
	 * @return
	 */
	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

}
