package com.foxinmy.umeng4j;

import org.junit.Test;

import com.foxinmy.umeng4j.payload.IOSPayload;

public class IOSTest {

	@Test
	public void payload() {
		IOSPayload payload = new IOSPayload("alert");
		payload.keyValue("key", "value");
		System.err.println(payload.asContent());
	}
}
