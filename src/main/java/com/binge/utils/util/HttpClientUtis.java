package com.binge.utils.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class HttpClientUtis {

	public static String sendHttpPost(String httpUrl, Map<String, Object> maps) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		// 创建参数队列
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, (String) maps.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(150000).setConnectTimeout(150000)
				.setConnectionRequestTimeout(150000).build();
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 
	 * @Title: sendHttpPost   
	 * @Description: http post 请求 header中放置签名
	 * @param: @param httpUrl
	 * @param: @param sign 签名 header
	 * @param: @param requestBody
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String sendHttpPost(String httpUrl, String sign, String requestBody) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		httpPost.addHeader("Content-Type", "application/json");
		StringEntity reqEntity = new StringEntity(requestBody, Charset.forName("UTF-8"));
		httpPost.setEntity(reqEntity);
		httpPost.addHeader("sign", sign);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(150000).setConnectTimeout(150000)
				.setConnectionRequestTimeout(150000).build();
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		HttpClientBuilder builder = HttpClients.custom();
		try {
			// 创建默认的httpClient实例.
			if (httpUrl.startsWith("https://")) {
				SSLContext sslContext = SSLContext.getInstance("SSL");
				X509TrustManager tm = new X509TrustManager() {
					@Override
					public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {

					}

					@Override
					public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
					}

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
				};
				sslContext.init(null, new TrustManager[]{tm}, null);
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"},
						null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
				builder.setSSLSocketFactory(sslsf);
			}
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = builder.build().execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (builder != null) {
					builder.build().close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 
	* @Title:httpsGet
	* @Description: 根据地址获取图片流 https 
	* @param  
	* @return byte[]   
	* @throws
	 */
	public static byte[] httpsGet(String Url) {
		RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		byte[] responseBody = null;
		try {
			HttpGet httpGet = new HttpGet(Url);
			// 创建默认的httpClient实例.
			PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader
					.load(new URL(httpGet.getURI().toString()));
			DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
			httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
			httpGet.setConfig(defaultRequestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			if (entity != null) {
				InputStream inStream = entity.getContent();
				ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
				byte[] buffer = new byte[2048];
				int len = 0;
				while ((len = inStream.read(buffer)) != -1) {
					outputstream.write(buffer, 0, len);
				}
				inStream.read(outputstream.toByteArray());
				inStream.close();
				responseBody = outputstream.toByteArray();
				outputstream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseBody;
	}

	public static void main(String[] args) {
		String url = "http://10.132.4.143/papers/3d88759f6ffa11eab6890242ac11000f";
		String contentType = "data:image/jpeg;base64,";
		byte[] picBytes = httpsGet(url);
		String bseStr=Base64.getEncoder().encodeToString(picBytes);
		Map<String,Object>map = new HashMap<>();
		map.put("json",bseStr);
		map.put("sign","24F8D1857F0DBAFC921F33DD471B578A");
		String s = sendHttpPost("https://test.urtrust.com.cn/openapi/image/uploadSync?partnerid=elimen", map);
		System.out.println(s);
	}

}
