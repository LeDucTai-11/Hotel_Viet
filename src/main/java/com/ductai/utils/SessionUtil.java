package com.ductai.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
	private static SessionUtil _Instance = null;
	public static SessionUtil Instance() {
		if(_Instance == null) {
			_Instance = new SessionUtil();
		}
		return _Instance;
	}
	
	public void putValue(HttpServletRequest req,String key,Object value) {
		req.getSession().setAttribute(key, value);
	}
	public Object getValue(HttpServletRequest req,String key) {
		return req.getSession().getAttribute(key);
	}
	public void removeValue(HttpServletRequest req,String key) {
		req.getSession().removeAttribute(key);
	}

}
