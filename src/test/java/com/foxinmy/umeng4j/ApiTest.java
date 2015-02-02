package com.foxinmy.umeng4j;

import org.junit.Test;

import com.foxinmy.umeng4j.exception.UmengException;
import com.foxinmy.umeng4j.http.ApiResult;
import com.foxinmy.umeng4j.model.FilterCriteria;
import com.foxinmy.umeng4j.model.TaskStatus;
import com.foxinmy.umeng4j.type.FilterType;

public class ApiTest {
	private final UmengProxy umengProxy = new UmengProxy();

	@Test
	public void findTask() throws UmengException {
		TaskStatus status = umengProxy.findTask("uu64514142267285026801");
		System.err.println(status);
	}

	@Test
	public void cacenlTask() throws UmengException {
		ApiResult result = umengProxy.cancelTask("uu64514142267285026801");
		System.err.println(result);
	}

	@Test
	public void uploadFile() throws UmengException {
		ApiResult result = umengProxy
				.uploadFile("AnWrbNeF55Z1Y589jX_SnJE7e_Qb4tmE7u_C38EAF6eX");
		System.err.println(result);// PF190361422673311078
	}

	@Test
	public void filterTest() throws UmengException {
		// and条件示例:已注册的(registered_user) 并且
		// 版本(app_version)是1.0的用户群，且这些用户在2014-11-15之后活跃过。
		FilterCriteria criteria = FilterCriteria.get().and(FilterType.tag,
				"registered_user");
		criteria.and(FilterType.app_version, "1.0").and(FilterType.launch_from,
				"2014-11-15");
		System.err.println(criteria.asContent());
		// or条件示例:已注册(registered_user)用户 或者 2014-11-15之后不活跃用户 或者 北京市的用户群
		criteria = FilterCriteria.get().or(FilterType.tag, "registered_user");
		criteria.or(FilterType.not_launch_from, "2014-11-15").or(
				FilterType.province, "北京");
		System.err.println(criteria.asContent());
		// not条件示例:未注册的(registered_user)的用户群
		criteria = FilterCriteria.get().not(FilterType.tag, "registered_user");
		System.err.println(criteria.asContent());
		// and, or, not组合条件示例:发送给分渠道 非 360 或者 "版本号为1.2" 并且 "2014-11-15之后不活跃"的用户
		criteria = FilterCriteria.get().not(FilterType.channel, "360");
		criteria.or(FilterType.app_version, "1.2").and(
				FilterType.not_launch_from, "2014-11-15");
		System.err.println(criteria.asContent());
	}
}
