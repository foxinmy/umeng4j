package com.foxinmy.umeng4j.cast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.umeng4j.payload.Payload;
import com.foxinmy.umeng4j.type.CastType;
import com.foxinmy.umeng4j.util.UmengStringUtil;

/**
 * 友盟消息
 * 
 * @className UmengCast
 * @author jy
 * @date 2015年1月30日
 * @since JDK 1.7
 * @see UniCast-单播
 * @see ListCast-列播(要求不超过500个device_token)
 * @see FileCast-文件播(多个device_token可通过文件形式批量发送
 * @see BroaDcast-广播
 * @see GroupCast-组播(按照filter条件筛选特定用户群, 具体请参照filter参数)
 * @see Customizedcast(通过开发者自有的alias进行推送)
 */
public abstract class UmengCast {

	private String appkey; // 必填 应用唯一标识
	private String timestamp; // 必填 时间戳，10位或者13位均可，时间戳有效期为10分钟
	private String type; // 必填 消息发送类型

	/**
	 * 可选 当type=customizedcast时，必填，alias的类型, alias_type可由开发者自定义,开发者在SDK中
	 * 调用setAlias(alias, alias_type)时所设置的alias_type
	 */
	@JSONField(name = "alias_type")
	private String aliasType;
	/**
	 * 可选 当type=customizedcast时, 开发者填写自己的alias。 要求不超过50个alias,多个alias以英文逗号间隔。
	 * 在SDK中调用setAlias(alias, alias_type)时所设置的alias
	 */
	private String alias;
	/**
	 * 可选 终端用户筛选条件,如用户标签、地域、应用版本以及渠道等
	 */
	private JSONObject filter;

	@JSONField(name = "production_mode")
	private String productionMode; // 可选 正式/测试模式。测试模式下，只会将消息发给测试设备。
	private String description; // 可选 发送消息描述，建议填写。
	@JSONField(name = "thirdparty_id")
	private String thirdpartyId; // 可选 开发者自定义消息标识ID, 开发者可以为同一批发送的多条消息
									// 提供同一个thirdparty_id, 便于友盟后台后期合并统计数据。

	private JSONObject payload; // 必填 消息内容(Android最大为1840B), 包含参数说明如下(JSON格式)

	private Policy policy; // 可选 发送策略

	public UmengCast(CastType castType, Payload payload) {
		this.timestamp = Long.toString(System.currentTimeMillis());
		this.type = castType.name().toLowerCase();
		this.productionMode = "true";
		this.payload = payload.asContent();
	}

	public JSONObject getPayload() {
		return (JSONObject) payload.clone();
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getType() {
		return type;
	}

	public String getAliasType() {
		return aliasType;
	}

	public void setAliasType(String aliasType) {
		this.aliasType = aliasType;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setAlias(String... alias) {
		this.alias = UmengStringUtil.concatComma(alias);
	}

	public JSONObject getFilter() {
		return filter;
	}

	public void setFilter(JSONObject filter) {
		this.filter = filter;
	}

	public String getProductionMode() {
		return productionMode;
	}

	public void setProductionMode(boolean productionMode) {
		this.productionMode = Boolean.toString(productionMode);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThirdpartyId() {
		return thirdpartyId;
	}

	public void setThirdpartyId(String thirdpartyId) {
		this.thirdpartyId = thirdpartyId;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Policy getPolicy() {
		return policy;
	}

	@Override
	public String toString() {
		return "appkey=" + appkey + ", timestamp=" + timestamp + ", type="
				+ type + ", aliasType=" + aliasType + ", alias=" + alias
				+ ", filter=" + filter + ", productionMode=" + productionMode
				+ ", description=" + description + ", thirdpartyId="
				+ thirdpartyId + ", payload=" + payload + ", policy=" + policy;
	}
}
