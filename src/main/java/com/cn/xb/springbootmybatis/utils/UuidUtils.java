package com.cn.xb.springbootmybatis.utils;
import java.util.UUID;

// 自动生成32位的UUid
public class UuidUtils {

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}