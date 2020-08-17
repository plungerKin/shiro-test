/**
 *
 */
package com.earth.shiro.config.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

public class HttpClientUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private static final String ENCODE_UTF8 = "UTF8";

	/**
	 * Post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, Map<String,String> params){
		String rt = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//设置请求配置
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(2000)
				.setSocketTimeout(5000)
				.build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		//填充请求参数
		if(params != null){
			for(Entry<String,String> entry:params.entrySet()){
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		CloseableHttpResponse response = null;

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE_UTF8));
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			rt = EntityUtils.toString(entity);
		} catch(Exception e){
			logger.error("http post error", e);
		} finally {
			try {if(httpclient!=null) httpclient.close();} catch (IOException e) {}
			try {if(response!=null) response.close();} catch (IOException e) {}
		}

		return rt;
	}

	/**
	 * Post请求
	 *
	 * @param url     请求url
	 * @param params  请求参数
	 * @param headers 请求头参数
	 * @return
	 */
	public static String post(String url, Map<String, String> params, Map<String, String> headers) {

		String rt = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//设置请求配置
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(100000)
				.setSocketTimeout(100000)
				.build();
		HttpPost httpPost = new HttpPost(url);

		Set<Entry<String, String>> set = headers.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String> en = it.next();
			httpPost.addHeader(en.getKey(), en.getValue());
		}
		httpPost.setConfig(requestConfig);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		//填充请求参数
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		CloseableHttpResponse response = null;

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE_UTF8));
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			rt = EntityUtils.toString(entity);
		} catch (Exception e) {
			logger.error("http post error", e);
		} finally {
			try {
				if (httpclient != null) httpclient.close();
			} catch (IOException e) {
			}
			try {
				if (response != null) response.close();
			} catch (IOException e) {
			}
		}

		return rt;
	}


	/**
	 * Post请求
	 * @param url
	 * @param entityStr 直接传输到服务端的一整块参数包装
	 * @return
	 */
	public static String post(String url, String entityStr){
		String rt = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//设置请求配置
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(2000)
				.setSocketTimeout(5000)
				.build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		CloseableHttpResponse response = null;

		try {
			httpPost.setEntity(new StringEntity(entityStr,ENCODE_UTF8));
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			rt = EntityUtils.toString(entity);
		} catch(Exception e){
			logger.error("http post error", e);
		} finally {
			try {if(httpclient!=null) httpclient.close();} catch (IOException e) {}
			try {if(response!=null) response.close();} catch (IOException e) {}
		}

		return rt;
	}

	/**
	 * Get请求
	 * @param url
	 * @return
	 */
	public static String get(String url){
		String rt = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//设置请求配置
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(100000)
				.setSocketTimeout(100000)
				.build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = null;

		try {
			response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			rt = EntityUtils.toString(entity);
		} catch(Exception e){
			logger.error("http get error", e);
		} finally {
			try {if(httpclient!=null) httpclient.close();} catch (IOException e) {}
			try {if(response!=null) response.close();} catch (IOException e) {}
		}

		return rt;
	}

	/**
	 * Get请求
	 * @param url
	 * @return
	 */
	public static String get(String url , Map<String, String> headers){
		String rt = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//设置请求配置
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(100000)
				.setSocketTimeout(100000)
				.build();
		HttpGet httpGet = new HttpGet(url);

		Set<Entry<String, String>> set = headers.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String> en = it.next();
			httpGet.addHeader(en.getKey(), en.getValue());
		}

		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = null;

		try {
			response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			rt = EntityUtils.toString(entity);
		} catch(Exception e){
			logger.error("http get error", e);
		} finally {
			try {if(httpclient!=null) httpclient.close();} catch (IOException e) {}
			try {if(response!=null) response.close();} catch (IOException e) {}
		}

		return rt;
	}

	/**
	 * put 请求
	 * @param uri
	 * @param headers
	 * @return
	 */
	public static String put(String uri,Map<String,String> headers){
		String rt = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(uri);
		//httpPut.addHeader(AUTHORIZATION_HEADER, auth);
		Set<Entry<String, String>> set = headers.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while(it.hasNext()){
			Entry<String, String> en = it.next();
			httpPut.addHeader(en.getKey(),en.getValue());
		}
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPut);
			HttpEntity entity = response.getEntity();
			rt = EntityUtils.toString(entity);
		} catch (Exception e) {
			logger.error("http put error", e);
		} finally{
			try {if(httpclient!=null) httpclient.close();} catch (IOException e) {}
			try {if(response!=null) response.close();} catch (IOException e) {}
		}

		return rt;
	}

	public static String urlEncode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String urlDecode(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 去掉url中的路径，留下请求参数部分
	 * @param strURL url地址
	 * @return url请求参数部分
	 * @author lzf
	 */
	private static String TruncateUrlPage(String strURL){
		String strAllParam=null;
		String[] arrSplit=null;
		strURL=strURL.trim().toLowerCase();
		arrSplit=strURL.split("[?]");
		if(strURL.length()>1){
			if(arrSplit.length>1){
				for (int i=1;i<arrSplit.length;i++){
					strAllParam = arrSplit[i];
				}
			}
		}
		return strAllParam;
	}

	/**
	 * 解析出url参数中的键值对
	 * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	 * @param URL  url地址
	 * @return  url请求参数部分
	 * @author lzf
	 */
	public static Map<String, String> urlSplit(String URL){
		Map<String, String> mapRequest = new HashMap<String, String>();
		String[] arrSplit=null;
		String strUrlParam=TruncateUrlPage(URL);
		if(strUrlParam==null){
			return mapRequest;
		}
		arrSplit=strUrlParam.split("[&]");
		for(String strSplit:arrSplit){
			String[] arrSplitEqual=null;
			arrSplitEqual= strSplit.split("[=]");
			//解析出键值
			if(arrSplitEqual.length>1){
				//正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
			}else{
				if(arrSplitEqual[0]!=""){
					//只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}

}
