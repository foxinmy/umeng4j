package com.foxinmy.umeng4j.http;

import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Response {

	private String text;
	private int statusCode;
	private String statusText;
	private byte[] body;
	private InputStream stream;

	public Response() {
	}

	public Response(String text) {
		this.text = text;
	}

	public String getAsString() {
		return text;
	}

	public ApiResult getAsResult() {
		return JSON.parseObject(text, ApiResult.class);
	}

	public JSONObject getAsJson() {
		return JSON.parseObject(text);
	}

	public <T> T getAsObject(Class<T> clazz) {
		return getAsJson().getObject("data", clazz);
	}

	/**
	 * <a href= "http://dev.umeng.com/push/android/api-doc#4_8">错误码</a>
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public String getTextError(int code) {
		String text = "unknown error";
		InputStream is = null;
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			is = Response.class.getResourceAsStream("error.xml");
			doc = reader.read(is);
			Node node = doc.getRootElement().selectSingleNode(
					String.format("error/code[text()=%d]", code));
			if (node != null) {
				Node _node = node.getParent().selectSingleNode("text");
				if (_node != null) {
					text = _node.getStringValue();
				}
			}
		} catch (DocumentException e) {
			; // ignore
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					;
				}
			}
		}
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public byte[] getBody() {
		return (byte[]) body.clone();
	}

	/**
	 * May expose internal representation by incorporating reference to mutable
	 * object
	 */
	public void setBody(byte[] body) {
		this.body = (byte[]) body.clone();
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	@Override
	public String toString() {
		return "Response [text=" + text + ", statusCode=" + statusCode
				+ ", statusText=" + statusText + "]";
	}
}
