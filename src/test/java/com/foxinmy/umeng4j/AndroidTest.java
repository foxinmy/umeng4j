package com.foxinmy.umeng4j;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.umeng4j.cast.FileCast;
import com.foxinmy.umeng4j.cast.Policy;
import com.foxinmy.umeng4j.cast.UmengCast;
import com.foxinmy.umeng4j.cast.UniCast;
import com.foxinmy.umeng4j.exception.UmengException;
import com.foxinmy.umeng4j.payload.AndroidPayload;
import com.foxinmy.umeng4j.payload.Payload;

public class AndroidTest {
	private final UmengProxy umengProxy = new UmengProxy();

	@Test
	public void payloadMessage() {
		JSONObject custom = new JSONObject();
		AndroidPayload payload = new AndroidPayload(custom);
		System.err.println(payload.asContent());
	}

	@Test
	public void payloadNotification() {
		AndroidPayload payload = new AndroidPayload("ticker", "title", "text");
		payload.afterOpenActivity("activity").sound("sound")
				.extra("key", "value");
		System.err.println(payload.asContent());
	}

	@Test
	public void unicastTest() throws UmengException {
		String deviceToken = "ArMnkxghq4EM3Kt-iirebwTk58ILUBfMaQ7ea3O84ETq";
		AndroidPayload payload = new AndroidPayload("通知栏提示文字", "通知标题", "通知文字描述");
		payload  = new AndroidPayload("测试数据");
		//payload.afterOpenActivity("me.huijian.meeting.chat.ChatListActivity");
		payload.afterOpenCustom();
		payload.extra("json", "2");
		Policy policy = new Policy();
		UniCast uniCast = new UniCast(deviceToken, payload);
		// policy.setStartTime(new Date());
		uniCast.setPolicy(policy);
		uniCast.setProductionMode(false);
		//System.err.println(JSON.toJSONString(uniCast));
		System.err.println(umengProxy.pushMessage(uniCast));
	}
	
	@Test
	public void filecastTest() throws UmengException {
		Payload payload = new AndroidPayload("测试", "测试标题", "测试打开会见");
		Policy policy = new Policy();
		UmengCast cast = new FileCast("PF190361422673311078", payload);
		// policy.setStartTime(new Date());
		cast.setPolicy(policy);
		cast.setProductionMode(false);
		//System.err.println(JSON.toJSONString(uniCast));
		System.err.println(umengProxy.pushMessage(cast));
	}
}
