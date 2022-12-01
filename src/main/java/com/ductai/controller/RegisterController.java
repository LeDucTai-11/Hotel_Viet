package com.ductai.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.ductai.bo.UserBO;
import com.ductai.model.UserModel;
import com.ductai.utils.SessionUtil;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		System.out.println(UserBO.Instance().findByUserName(req.getParameter("userName")));
		if(UserBO.Instance().findByUserName(req.getParameter("userName")) != null) {
			req.setAttribute("alert", "danger");
			req.setAttribute("message", "USERNAME đã bị trùng, vui lòng tạo USERNAME khác!");
			req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
		}else {
			UserModel user = new UserModel();
			try {
				BeanUtils.populate(user,req.getParameterMap());
				user.setRole_id(2);
				user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				user.setCreatedBy("REGISTER");
				UserBO.Instance().saveUser(user);
				SessionUtil.Instance().putValue(req, "USERMODEL", user);
				resp.sendRedirect(req.getContextPath()+"/trang-chu");
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
