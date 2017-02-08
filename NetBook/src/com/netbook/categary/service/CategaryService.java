package com.netbook.categary.service;

import java.util.List;

import com.netbook.categary.dao.factory.DaoFactory;

public class CategaryService {

	public String maxCid() {
		String cid = null;
		List<String> cidList = DaoFactory.getCategaryDao().findFirstCName();
		int[] buf = new int[1024];
		for (int i = 0; i < cidList.size(); i++) {
			buf[i] = Integer.parseInt(cidList.get(i));
		}
		// System.out.println(buf);
		int temp = 0;
		for (int i = buf.length - 1; i > 0; --i) {
			for (int j = 0; j < i; ++j) {
				if (buf[j + 1] > buf[j]) {
					temp = buf[j];
					buf[j] = buf[j + 1];
					buf[j + 1] = temp;
				}
			}
		}
		// System.out.println(cidList + ":" + buf[0] + ":" + buf[buf.length -
		// 1]);
		cid = String.valueOf(buf[0] + 1);

		return cid;
	}
}
