package com.foxinmy.umeng4j.cast;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.umeng4j.payload.Payload;
import com.foxinmy.umeng4j.type.CastType;

public class CustomizedCast extends UmengCast {
	/**
	 * 可选 file内容为多条alias， alias以回车符分隔，注意同一个文件内的alias所对应
	 * 的alias_type必须和接口参数alias_type一致。注意，使用前需要先调用文件上传接口获取file_id
	 */
	@JSONField(name = "file_id")
	private String fileId;

	public CustomizedCast(String aliasType, Payload payload) {
		super(CastType.CUSTOMIZEDCASE, payload);
		super.setAliasType(aliasType);
	}

	public CustomizedCast(String aliasType, Payload payload, String... alias) {
		super(CastType.CUSTOMIZEDCASE, payload);
		super.setAliasType(aliasType);
		super.setAlias(alias);
	}

	public CustomizedCast(String aliasType, Payload payload, String fileId) {
		super(CastType.CUSTOMIZEDCASE, payload);
		super.setAliasType(aliasType);
		this.fileId = fileId;
	}

	public String getFileId() {
		return fileId;
	}

	@Override
	public String toString() {
		return "CustomizedCast [fileId=" + fileId + ", " + super.toString()
				+ "]";
	}
}
