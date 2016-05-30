package com.foxinmy.umeng4j.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.umeng4j.type.FilterType;

/**
 * 过滤条件
 * 
 * @className FilterCriteria
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月31日
 * @since JDK 1.7
 * @see<a href="http://dev.umeng.com/push/android/api-doc#4_7">过滤条件示例</a>
 */
public class FilterCriteria {
	private JSONArray and;
	private JSONArray or;
	private JSONArray not;

	public FilterCriteria() {
		and = new JSONArray();
		or = new JSONArray();
		not = new JSONArray();
	}

	public static FilterCriteria get() {

		return new FilterCriteria();
	}

	public FilterCriteria and(FilterType filterType, String filterValue) {
		JSONObject criteria = new JSONObject();
		criteria.put(filterType.name(), filterValue);
		and.add(criteria);
		return this;
	}

	public FilterCriteria or(FilterType filterType, String filterValue) {
		JSONObject criteria = new JSONObject();
		criteria.put(filterType.name(), filterValue);
		or.add(criteria);
		return this;
	}

	public FilterCriteria not(FilterType filterType, String filterValue) {
		JSONObject criteria = new JSONObject();
		criteria.put(filterType.name(), filterValue);
		not.add(criteria);
		return this;
	}

	public JSONObject asContent() {
		JSONObject filter = new JSONObject();
		JSONObject _and = new JSONObject();
		_and.put("and", and);
		JSONObject _or = new JSONObject();
		_or.put("or", or);
		JSONObject _not = new JSONObject();
		_not.put("not", not);
		if (not.isEmpty()) {
			if (and.isEmpty() && !or.isEmpty()) {
				filter.put("where", _or);
			} else if (!and.isEmpty()) {
				and.add(_or);
				filter.put("where", _and);
			}
		} else {
			and.add(_not);
			and.add(_or);
			filter.put("where", _and);
		}
		return filter;
	}
}
