package com.api.util;

import java.util.UUID;

/**
 * 
 * @ClassName: ToolsUtil
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: drj
 * @date: 2018年11月10日 上午10:40:55
 * 
 * @Copyright: 2018
 *
 */
public class ToolsUtil {
	public static String GetGUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static void main(String[] args) {
		System.out.println(GetGUID());
	}
}
