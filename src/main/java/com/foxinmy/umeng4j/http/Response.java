package com.foxinmy.umeng4j.http;

import java.io.InputStream;

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
