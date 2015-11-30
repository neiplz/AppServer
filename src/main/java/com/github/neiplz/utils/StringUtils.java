package com.github.neiplz.utils;

import java.util.UUID;

public class StringUtils {

	public static String getPrimaryKey() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
