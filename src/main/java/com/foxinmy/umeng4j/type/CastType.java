package com.foxinmy.umeng4j.type;

/**
 * 消息发送类型
 * 
 * @className CastType
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月30日
 * @since JDK 1.7
 * @see
 */
public enum CastType {
	UNICAST, // 单播
	LISTCAST, // 列播(要求不超过500个device_token)
	FILECAST, // 文件播(多个device_token可通过文件形式批量发送）
	BROADCAST, // 广播
	GROUPCAST, // 组播(按照filter条件筛选特定用户群, 具体请参照filter参数)
	CUSTOMIZEDCASE; // 开发者通过自有的alias进行推送, 可以针对单个或者一批alias进行推送，也可以将alias存放到文件进行发送
}
