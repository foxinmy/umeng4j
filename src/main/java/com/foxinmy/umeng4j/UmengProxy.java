package com.foxinmy.umeng4j;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.umeng4j.cast.UmengCast;
import com.foxinmy.umeng4j.exception.UmengException;
import com.foxinmy.umeng4j.http.ApiResult;
import com.foxinmy.umeng4j.http.HttpRequest;
import com.foxinmy.umeng4j.http.Response;
import com.foxinmy.umeng4j.model.TaskStatus;
import com.foxinmy.umeng4j.type.Consts;
import com.foxinmy.umeng4j.util.UMConfigUtil;

/**
 * 友盟消息接口实现
 * 
 * @className UmengProxy
 * @author jy
 * @date 2015年1月30日
 * @since JDK 1.7
 * @see
 */
public class UmengProxy {

	private final HttpRequest request = new HttpRequest();
	private final String masterSecret;
	private final String appKey;

	public UmengProxy() {
		this.appKey = UMConfigUtil.getValue("app_key");
		this.masterSecret = UMConfigUtil.getValue("master_secret");
	}

	public UmengProxy(String appkey, String masterSecret) {
		this.appKey = appkey;
		this.masterSecret = masterSecret;
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
	public ApiResult pushMsg(UmengCast umengCast) throws UmengException {
		umengCast.setAppkey(appKey);
		Response response = request.post(Consts.UMENG_MSG_PUSH_URL,
				JSON.toJSONString(umengCast), masterSecret);
		return response.getAsResult();
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
		Response response = request.post(Consts.UMENG_MSG_STATUS_URL,
				para.toJSONString(), masterSecret);
		return response.getAsObject(TaskStatus.class);
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
		Response response = request.post(Consts.UMENG_MSG_CANCEL_URL,
				para.toJSONString(), masterSecret);
		return response.getAsResult();
	}

	/**
	 * 文件上传,文件上传接口支持两种应用场景:
	 * 
	 * a. 发送类型为"filecast"的时候, 开发者批量上传device_token;<br/>
	 * b. 发送类型为"customizedcast"时, 开发者批量上传alias。 上传文件后获取file_id,
	 * 从而可以实现通过文件id来进行消息批量推送的目的。 文件自创建起，服务器会保存两个月。开发者可以在有效期内重复使用该file-id进行消息发送。
	 * <font color="red">注意: 上传的文件不超过10M.</font>
	 * 
	 * @param content
	 *            文件内容,多个device_token/alias请用回车符"\n"分隔。
	 * @return 接口调用结果
	 * @see <a hre="http://dev.umeng.com/push/android/api-doc#2_4">文件上传</a>
	 * @see com.foxinmy.umeng4j.cast.FileCast
	 * @see com.foxinmy.umeng4j.cast.CustomizedCast
	 * @throws UmengException
	 */
	public ApiResult uploadFile(String content) throws UmengException {
		JSONObject para = new JSONObject();
		para.put("appkey", appKey);
		para.put("timestamp", System.currentTimeMillis());
		para.put("content", content);
		Response response = request.post(Consts.UMENG_MSG_UPLOAD_URL,
				para.toJSONString(), masterSecret);
		return response.getAsResult();
	}
}
