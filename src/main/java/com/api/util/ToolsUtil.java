package com.api.util;

import java.util.UUID;

/**
 * 
 * @ClassName: ToolsUtil
 * @Description:TODO(������һ�仰��������������)
 * @author: drj
 * @date: 2018��11��10�� ����10:40:55
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
