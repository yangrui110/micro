package com.yangrui.util.prePath;

import javax.servlet.http.HttpServletRequest;

public class PrePathUtil {

	public static void setPath(HttpServletRequest request) {
		request.getSession().setAttribute("prePath", request.getRequestURI());
	}
	public static Object getPrePath(HttpServletRequest request) {
		return request.getSession().getAttribute("prePath");
	}
}
