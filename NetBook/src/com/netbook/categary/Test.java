package com.netbook.categary;

import java.util.List;
import java.util.Map;

import com.netbook.categary.domain.Categary;
import com.netbook.categary.web.CategaryServlet;

/**
 * 测试类
 */

public class Test {

	public static void main(String[] args) {
		Map<Categary, List<Categary>> map = new CategaryServlet().getMap();
		/*
		 * Iterator<Categary> iterator = map.keySet().iterator();
		 * Iterator<Categary> iterator2 = null; while (iterator.hasNext()) {
		 * iterator2 = map.get(iterator.next()).iterator(); while
		 * (iterator2.hasNext()) {
		 * System.out.println(iterator.next().getCname());
		 * System.out.println(iterator2.next().getCname()); } }
		 */
		System.out.println(map);
	}
}
