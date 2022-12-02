package com.ductai.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ductai.constant.MessageConstant;
import com.ductai.model.bean.UserModel;
import com.ductai.model.bo.UserBO;
import com.ductai.utils.SessionObject;
import com.ductai.utils.SessionUtil;

@WebServlet(urlPatterns = {"/dang-nhap"})
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if(message != null && alert != null) {
				req.setAttribute("message", MessageConstant.getMessage(message));
				req.setAttribute("alert", alert);
			}
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		}else if(action != null && action.equals("logout")) {
			SessionUtil.Instance().removeValue(req, "USERMODEL");
			resp.sendRedirect(req.getContextPath()+"/trang-chu");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("login")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			UserModel user = UserBO.Instance().findByUserNameAndPasswordAndStatus(username, password);
			if(user != null) {
				SessionUtil.Instance().putValue(req, "USERMODEL", new SessionObject(username, user.getFullName()));
				if(user.isAdmin()) {
					resp.sendRedirect(req.getContextPath()+"/admin-home");
				}else {
					resp.sendRedirect(req.getContextPath()+"/trang-chu");
				}
			}else {
				resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=accountInvalid&alert=danger");
			}
		}
	}
	

}
