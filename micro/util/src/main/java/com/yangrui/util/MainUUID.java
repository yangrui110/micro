package com.yangrui.util;

import java.util.UUID;

public class MainUUID {

	public static String getMainId() {
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
