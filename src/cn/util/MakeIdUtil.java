package cn.util;

import java.util.UUID;

public class MakeIdUtil {
	//�������Ψһʶ����
	
	public static String makeId() {
		return UUID.randomUUID().toString();
	}

}
