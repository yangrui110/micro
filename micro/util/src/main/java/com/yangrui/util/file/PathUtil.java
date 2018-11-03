package com.yangrui.util.file;

public class PathUtil {

	public static String systemPath() {
		String basePath="";
		String operate=System.getProperty("os.name");
		String op=System.getProperty("file.separator");
		if(operate.startsWith("Win"))
			basePath="E:/FileUpload/";
		else {
			basePath="/home/yangrui/";
		}
		return basePath.replace("/", op);
	}
}
