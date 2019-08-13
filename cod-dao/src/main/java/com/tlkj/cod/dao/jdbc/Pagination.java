/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T> {

	/**
	 * 默认每页显示的条数
	 */
	public static final int DEFAULT_PER_PAGE = 10;

	/**
	 * 页面的页码显示几条
	 */
	public static final int DEFAULT_PAGE_LIST = 10;

	/**
	 * start from 1
 	 */
	public final int page;
	public final int perPage;
	public final int total;

	private List<T> data = new ArrayList<T>();

	public Pagination(int page, int perPage, int total) {
		this.page = page < 1 ? 1 : page;
		this.perPage = perPage;
		this.total = total;
	}

	public void setData(List<T> data) {
		this.data.clear();
		this.data.addAll(data);
	}

	public List<T> getData() {
		return data;
	}

	public boolean isNull() {
		return this.data == null || this.data.size() == 0;
	}

	public int getCurrentPage(){
		int currentPage = data.size();
		return currentPage;
	}

	public int totalPages() {
		return (total + perPage - 1) / perPage;
	}

	/**
	 * start from 1;
	 * 
	 * @return
	 */
	public int offset() {
		return (page - 1) * perPage + 1;
	}

	public int nextPage() {
		if (page < totalPages()) {
			return page + 1;
		}
		return -1;
	}

	public int previousPage() {
		return page <= 1 ? -1 : page - 1;
	}

	public <V> Pagination<V> replace(List<V> data) {
		Pagination<V> result = new Pagination<V>(page, perPage, total);
		result.setData(data);
		return result;
	}

	public List<Integer> pageNavigation() {
		List<Integer> pages = new ArrayList<Integer>();
		int min = 1;
		int max = totalPages();

		int innerWindow = 2;
		int from = Math.max(min, page - innerWindow);
		int to = Math.min(max, page + innerWindow);

		boolean leftGap = min + 2 < from;
		boolean rightGap = to + 2 < max;
		if (!leftGap) {
			from = min;
		}
		if (!rightGap) {
			to = max;
		}

		if (min != from) {
			pages.add(min);
		}
		if (leftGap) {
			pages.add(-1);
		}
		for (int i = from; i <= to; i++) {
			pages.add(i);
		}
		if (rightGap) {
			pages.add(-1);
		}
		if (max != to) {
			pages.add(max);
		}

		return pages;
	}

	@Override
	public String toString() {
		return "Pagination [page=" + page + ", perPage=" + perPage + ", total="
				+ total + ", data=" + data + "]";
	}

}
