package cn.util;

import java.util.UUID;

public class MakeIdUtil {
	//随机生成唯一识别码
	
	public static String makeId() {
		return UUID.randomUUID().toString();
	}

}
