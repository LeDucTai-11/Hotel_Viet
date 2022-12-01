package com.ductai.constant;

public class MessageConstant {
	public static String getMessage(String message) {
		String msg = "";
		if(message.equals("accountInvalid")) {
			msg = "Tài khoản không chính xác";
		}else if(message.equals("notLogin")) {
			msg = "Vui lòng đăng nhập";
		}else if(message.equals("not_permission")) {
			msg = "Bạn không có quyền truy cập";
		}
		return msg;
	}
}
