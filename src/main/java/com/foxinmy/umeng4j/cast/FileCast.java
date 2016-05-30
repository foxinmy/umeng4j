package com.foxinmy.umeng4j.cast;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.umeng4j.payload.Payload;
import com.foxinmy.umeng4j.type.CastType;

/**
 * 文件播
 * 
 * @className ListCast
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月30日
 * @since JDK 1.7
 * @see
 */
public class FileCast extends UmengCast {

	/**
	 * 可选 当type=filecast时，file内容为多条device_token, device_token以回车符分隔
	 * 。注意，使用文件播前需要先调用文件上传接口获取file_id
	 */
	@JSONField(name = "file_id")
	private String fileId;

	public FileCast(String fileId, Payload payload) {
		super(CastType.FILECAST, payload);
		this.fileId = fileId;
	}

	public String getFileId() {
		return fileId;
	}

	@Override
	public String toString() {
		return "FileCast [fileId=" + fileId + ", " + super.toString() + "]";
	}
}
