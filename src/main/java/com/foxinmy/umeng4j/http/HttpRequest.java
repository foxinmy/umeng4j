package com.foxinmy.umeng4j.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import com.foxinmy.umeng4j.exception.UmengException;

/**
 * 
 * @className HttpRequest
 * @author jy
 * @date 2014年8月21日
 * @since JDK 1.7
 * @see
 */
public class HttpRequest {

	protected AbstractHttpClient client;

	public HttpRequest() {
		this(150, 100, 10000, 10000);
	}

	public HttpRequest(int maxConPerRoute, int maxTotal, int socketTimeout,
			int connectionTimeout) {
		PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager();
		// 指定IP并发最大数
		connectionManager.setDefaultMaxPerRoute(maxConPerRoute);
		// socket最大创建数
		connectionManager.setMaxTotal(maxTotal);

		client = new DefaultHttpClient(connectionManager);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				socketTimeout);
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
		client.getParams().setBooleanParameter(
				CoreConnectionPNames.TCP_NODELAY, false);
		client.getParams().setParameter(
				CoreConnectionPNames.SOCKET_BUFFER_SIZE, 1024 * 1024);
		client.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.IGNORE_COOKIES);
		client.getParams().setParameter(
				CoreProtocolPNames.HTTP_CONTENT_CHARSET, Consts.UTF_8);
		client.getParams().setParameter(HttpHeaders.CONTENT_ENCODING,
				Consts.UTF_8);
		client.getParams().setParameter(HttpHeaders.ACCEPT_CHARSET,
				Consts.UTF_8);
	}

	public Response post(String url, String body, String appMasterSecret)
			throws UmengException {
		String sign = DigestUtils.md5Hex(String.format("POST%s%s%s", url, body,
				appMasterSecret));
		HttpPost method = new HttpPost(String.format("%s?sign=%s", url, sign));
		method.setEntity(new StringEntity(body, ContentType.create(
				ContentType.APPLICATION_JSON.getMimeType(), Consts.UTF_8)));
		return doRequest(method);
	}

	protected Response doRequest(HttpRequestBase request) throws UmengException {
		Response response = null;
		try {
			HttpResponse httpResponse = client.execute(request);
			StatusLine statusLine = httpResponse.getStatusLine();
			HttpEntity httpEntity = httpResponse.getEntity();
			int status = statusLine.getStatusCode();
			byte[] data = EntityUtils.toByteArray(httpEntity);
			response = new Response();
			response.setBody(data);
			response.setStatusCode(status);
			response.setStatusText(statusLine.getReasonPhrase());
			response.setStream(new ByteArrayInputStream(data));
			response.setText(new String(data, Consts.UTF_8));
			EntityUtils.consume(httpEntity);
			ApiResult result = response.getAsResult();
			if (status != HttpStatus.SC_OK
					|| !result.getRet().equals(
							com.foxinmy.umeng4j.type.Consts.SUCCESS)) {
				int code = result.getData()
						.getIntValue("error_code");
				throw new UmengException(Integer.toString(code),
						response.getTextError(code));
			}
		} catch (IOException e) {
			throw new UmengException(e.getMessage());
		} finally {
			request.releaseConnection();
		}
		return response;
	}
}
