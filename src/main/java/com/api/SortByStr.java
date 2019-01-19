package com.api;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByStr implements Comparator<Map<String,Object>> {
	@Override
	public int compare(Map<String,Object> o1, Map<String,Object> o2) {
		// 拼音 Comparator 比较器
		Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
		return cmp.compare(o1.get("py"), o2.get("py"));
	}

	public static void main(String[] args) {
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("戴瑞姐");
		arrayList.add("ttts");
		arrayList.add("aybc");
		arrayList.add("bttts");
		arrayList.add("abbc");
		arrayList.add("aattts");
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> hash = null;
		String[] s = { "b", "j", "a" };
		for (int i = 0; i < 3; i++) {
			hash = new HashMap<String, Object>();
			hash.put("name", "戴瑞姐" + s[i]);
			hash.put("py", s[i]);
			list.add(hash);
		}
		Collections.sort(list, new SortByStr());
		for (Map<String, Object> map : list) {
			System.out.println(map.get("name"));
		}
	}

}
