package com.foxinmy.umeng4j.payload;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.umeng4j.type.PayloadType;

/**
 * 安卓消息提醒
 * 
 * @className AndroidPayload
 * @author jy
 * @date 2015年1月30日
 * @since JDK 1.7
 * @see
 */
public class AndroidPayload implements Payload {

	private JSONObject payloadContent;
	private JSONObject payloadBody;
	private JSONObject payloadExtar;

	/**
	 * 默认 NOTIFICATION 类型
	 * 
	 * @param ticker
	 *            通知栏提示文字
	 * @param title
	 *            通知标题
	 * @param text
	 *            通知文字描述
	 */
	public AndroidPayload(String ticker, String title, String text) {
		payloadContent = new JSONObject();
		payloadBody = new JSONObject();
		payloadExtar = new JSONObject();
		payloadContent.put("display_type", PayloadType.NOTIFICATION.name()
				.toLowerCase());
		payloadBody.put("ticker", ticker);
		payloadBody.put("title", title);
		payloadBody.put("text", text);
	}

	/**
	 * 消息类型
	 * 
	 * @param payloadType
	 *            值可以为: notification-通知，message-消息
	 * @return
	 */
	public AndroidPayload displayType(PayloadType payloadType) {
		payloadContent.put("display_type", payloadType.name().toLowerCase());
		return this;
	}

	/**
	 * 可选 状态栏图标ID, R.drawable.[smallIcon], 如果没有, 默认使用应用图标。
	 * 图片要求为24*24dp的图标,或24*24px放在drawable-mdpi下。 注意四周各留1个dp的空白像素
	 * 
	 * @param icon
	 * @return
	 */
	public AndroidPayload icon(String icon) {
		payloadBody.put("icon", icon);
		return this;
	}

	/**
	 * 可选 通知栏拉开后左侧图标ID, R.drawable.[largeIcon]. 图片要求为64*64dp的图标,
	 * 可设计一张64*64px放在drawable-mdpi下, 注意图片四周留空，不至于显示太拥挤
	 * 
	 * @param largeIcon
	 * @return
	 */
	public AndroidPayload largeIcon(String largeIcon) {
		payloadBody.put("largeIcon", largeIcon);
		return this;
	}

	/**
	 * 可选 通知栏大图标的URL链接。该字段的优先级大于largeIcon。 该字段要求以http或者https开头
	 * 
	 * @param image
	 * @return
	 */
	public AndroidPayload image(String image) {
		payloadBody.put("img", image);
		return this;
	}

	/**
	 * 可选 通知声音，R.raw.[sound]. 如果该字段为空，采用SDK默认的声音, 即res/raw/下的
	 * umeng_push_notification_default_sound声音文件 如果SDK默认声音文件不存在，
	 * 则使用系统默认的Notification提示音
	 * 
	 * @param sound
	 * @return
	 */
	public AndroidPayload sound(String sound) {
		payloadBody.put("sound", sound);
		return this;
	}

	/**
	 * 自定义通知样式 可选 默认为0，用于标识该通知采用的样式。使用该参数时, 开发者必须在SDK里面实现自定义通知栏样式
	 * 
	 * @param themeId
	 * @return
	 */
	public AndroidPayload theme(String themeId) {
		payloadBody.put("builder_id", themeId);
		return this;
	}

	/**
	 * 通知到达设备后的提醒方式 可选 收到通知是否震动,默认为"true".
	 * 
	 * @param vibrate
	 * @return
	 */
	public AndroidPayload playVibrate(boolean vibrate) {
		payloadBody.put("play_vibrate", Boolean.toString(vibrate));
		return this;
	}

	/**
	 * 可选 收到通知是否闪灯,默认为"true"
	 * 
	 * @param light
	 * @return
	 */
	public AndroidPayload playLight(boolean light) {
		payloadBody.put("play_lights", Boolean.toString(light));
		return this;
	}

	/**
	 * 可选 收到通知是否发出声音,默认为"true"
	 * 
	 * @param sound
	 * @return
	 */
	public AndroidPayload playSound(boolean sound) {
		payloadBody.put("play_sound", Boolean.toString(sound));
		return this;
	}

	/**
	 * 点击"通知"的后续行为，打开app。
	 * 
	 * @return
	 */
	public AndroidPayload afterOpenApp() {
		payloadBody.put("after_open", "go_app");
		return this;
	}

	/**
	 * 点击"通知"的后续行为，打开自定义。
	 * 
	 * @return
	 */
	public AndroidPayload afterOpenCustom() {
		payloadBody.put("after_open", "go_custom");
		return this;
	}

	/**
	 * 可选 display_type=message, 或者 display_type=notification且
	 * "after_open"为"go_custom"时
	 * 
	 * @param custom
	 *            用户自定义内容, 可以为字符串或者JSON格式
	 * @return
	 */
	public AndroidPayload afterOpenCustom(Object custom) {
		payloadBody.put("after_open", "go_custom");
		payloadBody.put("custom", custom);
		return this;
	}

	/**
	 * 点击"通知"的后续行为，跳转到URL。
	 * 
	 * @param url
	 * @return
	 */
	public AndroidPayload afterOpenUrl(String url) {
		payloadBody.put("after_open", "go_url");
		payloadBody.put("url", url);
		return this;
	}

	/**
	 * 点击"通知"的后续行为，跳转到URL。
	 * 
	 * @param url
	 * @return
	 */
	public AndroidPayload afterOpenActivity(String activity) {
		payloadBody.put("after_open", "go_activity");
		payloadBody.put("activity", activity);
		return this;
	}

	/**
	 * 可选 用户自定义key-value。只对"通知" (display_type=notification)生效。
	 * 可以配合通知到达后,打开App,打开URL,打开Activity使用。
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public AndroidPayload extra(String key, Object value) {
		payloadExtar.put(key, value);
		return this;
	}

	/**
	 * 可选 用户自定义扩展属性
	 * 
	 * @param extras
	 * @return
	 */
	public AndroidPayload extra(Map<? extends String, ? extends Object> extras) {
		payloadExtar.putAll(extras);
		return this;
	}

	/**
	 * payload 内容
	 * 
	 * @return
	 */
	public JSONObject asContent() {
		if (!payloadBody.containsKey("play_vibrate")) {
			playVibrate(true);
		}
		if (!payloadBody.containsKey("play_lights")) {
			playLight(true);
		}
		if (!payloadBody.containsKey("play_sound")) {
			playSound(true);
		}
		if (!payloadBody.containsKey("after_open")) {
			afterOpenApp();
		}
		payloadContent.put("body", payloadBody);
		payloadContent.put("extra", payloadExtar);
		return payloadContent;
	}
}
