package com.foxinmy.umeng4j.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.umeng4j.type.MessageStatus;

/**
 * 任务状态
 * 
 * @className TaskStatus
 * @author jy
 * @date 2015年1月31日
 * @since JDK 1.7
 * @see
 */
public class TaskStatus implements Serializable {

	private static final long serialVersionUID = -1337189677186595410L;

	@JSONField(name = "task_id")
	private String taskId;
	/**
	 * 消息状态: 0-排队中, 1-发送中，2-发送完成，3-发送失败，4-消息被撤销， // 5-消息过期,
	 * 6-筛选结果为空，7-定时任务尚未开始处理
	 */
	private int status;
	@JSONField(name = "total_count")
	private int totalCount; // 消息总数
	@JSONField(name = "accept_count")
	private int acceptCount; // 消息受理数
	/**
	 * Android:</br>
	 * sent_count表示消息送达设备的数量。由于设备可能不在线，在消息有效时间内(expire_time)，设备上线后还会收到消息。
	 * 所以"sent_count"的数字会一直增加，直至到达消息过期时间后，该值不再变化。</br>
	 * iOS: </br>
	 * sent_count表示友盟成功推送到APNs平台的数字
	 * ，不代表送达设备的个数。友盟将消息发送到APNs服务器之后，如果APNs没有返回错误，友盟认为发送成功
	 * 。因此accept_count会和sent_count两个数字一样。
	 */
	@JSONField(name = "sent_count")
	private int sentCount; // 消息实际发送数
	@JSONField(name = "open_count")
	private int openCount;// 打开数
	@JSONField(name = "dismiss_count")
	private int dismissCount;// 忽略数

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public MessageStatus getStatus() {
		return MessageStatus.values()[status];
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getAcceptCount() {
		return acceptCount;
	}

	public void setAcceptCount(int acceptCount) {
		this.acceptCount = acceptCount;
	}

	public int getSentCount() {
		return sentCount;
	}

	public void setSentCount(int sentCount) {
		this.sentCount = sentCount;
	}

	public int getOpenCount() {
		return openCount;
	}

	public void setOpenCount(int openCount) {
		this.openCount = openCount;
	}

	public int getDismissCount() {
		return dismissCount;
	}

	public void setDismissCount(int dismissCount) {
		this.dismissCount = dismissCount;
	}

	@Override
	public String toString() {
		return "TaskStatus [taskId=" + taskId + ", status=" + status
				+ ", totalCount=" + totalCount + ", acceptCount=" + acceptCount
				+ ", sentCount=" + sentCount + ", openCount=" + openCount
				+ ", dismissCount=" + dismissCount + "]";
	}
}
