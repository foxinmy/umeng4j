package com.foxinmy.umeng4j.type;

/**
 * 消息状态
 * 
 * @className MessageStatusType
 * @author jy
 * @date 2015年1月31日
 * @since JDK 1.7
 * @see
 */
public enum MessageStatus {
	QUEUEIING, // 排队中
	SENDING, // 发送中
	COMPLETED, // 发送完成
	FAILED, // 发送失败
	CANCEL, // 撤销发送
	EXPIRED, // 消息过期
	EMPTY, // 筛选结果为空
	WAIT; // 定时任务尚未开始处理
}
