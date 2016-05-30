package com.foxinmy.umeng4j;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.umeng4j.cast.UmengCast;
import com.foxinmy.umeng4j.exception.UmengException;
import com.foxinmy.umeng4j.model.TaskStatus;
import com.foxinmy.umeng4j.type.Consts;
import com.foxinmy.umeng4j.util.Umeng4jConfigUtil;
import com.foxinmy.umeng4j.util.UmengErrorUtil;
import com.foxinmy.weixin4j.http.ContentType;
import com.foxinmy.weixin4j.http.HttpClient;
import com.foxinmy.weixin4j.http.HttpClientException;
import com.foxinmy.weixin4j.http.HttpMethod;
import com.foxinmy.weixin4j.http.HttpRequest;
import com.foxinmy.weixin4j.http.HttpResponse;
import com.foxinmy.weixin4j.http.HttpStatus;
import com.foxinmy.weixin4j.http.entity.StringEntity;
import com.foxinmy.weixin4j.http.factory.HttpClientFactory;
import com.foxinmy.weixin4j.util.DigestUtil;
import com.foxinmy.weixin4j.util.StringUtil;

/**
 * 友盟消息接口实现
 *
 * @className UmengProxy
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月30日
 * @since JDK 1.6
 * @see
 */
public class UmengProxy {

	private final HttpClient httpClient;
	private final String masterSecret;
	private final String appKey;

	public UmengProxy() {
		this(Umeng4jConfigUtil.getValue("umeng4j.app.key"), Umeng4jConfigUtil
				.getValue("umeng4j.app.master.secret"));
	}

	public UmengProxy(String appkey, String masterSecret) {
		this.appKey = appkey;
		this.masterSecret = masterSecret;
		this.httpClient = HttpClientFactory.getInstance();
	}

	/**
	 * 消息推送
	 *
	 * @param umengCast
	 *            发送对象
	 * @return 接口调用结果
	 * @see <a hre="http://dev.umeng.com/push/android/api-doc#2_1">消息推送</a>
	 * @see com.foxinmy.umeng4j.cast.UniCast
	 * @see com.foxinmy.umeng4j.cast.ListCast
	 * @see com.foxinmy.umeng4j.cast.BroadCast
	 * @see com.foxinmy.umeng4j.cast.GroupCast
	 * @see com.foxinmy.umeng4j.cast.FileCast
	 * @see com.foxinmy.umeng4j.cast.CustomizedCast
	 * @throws UmengException
	 */
	public ApiResult pushMessage(UmengCast umengCast) throws UmengException {
		umengCast.setAppkey(appKey);

		JSONObject response = execute(Consts.UMENG_MSG_PUSH_URL,
				JSON.toJSONString(umengCast));
		return JSON.toJavaObject(response, ApiResult.class);
	}

	/**
	 * 任务查询,当消息发送的类型为任务时，包括"broadcast","groupcast",
	 * "filecast","customizedcast(通过file_id传参)"时, 可以通过"task_id"来查询当前的消息状态。
	 * 注意，当次发送任务如果发送总量小于500个的话，后台会按照列播的方式推送，不再按照任务的方式来处理。 该接口会不生效。
	 *
	 * @param taskId
	 *            消息发送时，从返回消息中获取的task_id
	 * @return 接口调用结果
	 * @see <a hre="http://dev.umeng.com/push/android/api-doc#2_1">任务查询</a>
	 * @see com.foxinmy.umeng4j.model.TaskStatus
	 * @see com.foxinmy.umeng4j.type.MessageStatus
	 * @throws UmengException
	 */
	public TaskStatus findTask(String taskId) throws UmengException {
		JSONObject para = new JSONObject();
		para.put("appkey", appKey);
		para.put("timestamp", System.currentTimeMillis());
		para.put("task_id", taskId);
		JSONObject response = execute(Consts.UMENG_MSG_STATUS_URL,
				para.toJSONString());
		return JSON.toJavaObject(response, TaskStatus.class);
	}

	/**
	 * 任务取消,当消息发送的type类型任务时，包括broadcast,groupcast,
	 * filecast或者customizedcast且file_id不为空时, 可以撤销正在进行中的发送任务。 注意:
	 * 撤销仅对排队中(status=0)或者发送中(status=1)的任务有效。 注意:
	 * 当次发送任务如果发送总量小于500个的话，后台会按照列播的方式推送，不再按照任务的方式来处理。 该接口会不生效。
	 *
	 * @param taskId
	 *            消息发送时，从返回消息中获取的task_id
	 * @return 接口调用结果
	 * @see <a hre="http://dev.umeng.com/push/android/api-doc#2_3">任务取消</a>
	 * @throws UmengException
	 */
	public ApiResult cancelTask(String taskId) throws UmengException {
		JSONObject para = new JSONObject();
		para.put("appkey", appKey);
		para.put("timestamp", System.currentTimeMillis());
		para.put("task_id", taskId);
		JSONObject response = execute(Consts.UMENG_MSG_CANCEL_URL,
				para.toJSONString());
		return JSON.toJavaObject(response, ApiResult.class);
	}

	/**
	 * 文件上传,文件上传接口支持两种应用场景:
	 *
	 * a. 发送类型为"filecast"的时候, 开发者批量上传device_token;</br> b.
	 * 发送类型为"customizedcast"时, 开发者批量上传alias。 上传文件后获取file_id,
	 * 从而可以实现通过文件id来进行消息批量推送的目的。 文件自创建起，服务器会保存两个月。开发者可以在有效期内重复使用该file-id进行消息发送。
	 * <font color="red">注意: 上传的文件不超过10M.</font>
	 *
	 * @param content
	 *            文件内容,多个device_token/alias请用回车符"\n"分隔。
	 * @return 接口调用结果
	 * @see <a href="http://dev.umeng.com/push/android/api-doc#2_4">文件上传</a>
	 * @see com.foxinmy.umeng4j.cast.FileCast
	 * @see com.foxinmy.umeng4j.cast.CustomizedCast
	 * @throws UmengException
	 */
	public ApiResult uploadFile(String content) throws UmengException {
		JSONObject para = new JSONObject();
		para.put("appkey", appKey);
		para.put("timestamp", System.currentTimeMillis());
		para.put("content", content);
		JSONObject response = execute(Consts.UMENG_MSG_UPLOAD_URL,
				para.toJSONString());
		return JSON.toJavaObject(response, ApiResult.class);
	}

	private JSONObject execute(String url, String body) throws UmengException {
		String sign = DigestUtil.MD5(String.format("POST%s%s%s", url, body,
				masterSecret));
		HttpRequest request = new HttpRequest(HttpMethod.POST, String.format(
				"%s?sign=%s", url, sign));
		request.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
		try {
			HttpResponse response = httpClient.execute(request);
			JSONObject result = JSON.parseObject(StringUtil
					.newStringUtf8(response.getContent()));
			if (response.getStatus().getStatusCode() != HttpStatus.SC_OK
					|| !result.getString("ret").equals(
							com.foxinmy.umeng4j.type.Consts.SUCCESS)) {
				String code = result.getJSONObject("data").getString(
						"error_code");
				throw new UmengException(code, UmengErrorUtil.getText(code));
			}
			return result;
		} catch (HttpClientException e) {
			throw new UmengException(e.getMessage());
		}
	}

	public static final String VERSION = "1.1.3";
}
