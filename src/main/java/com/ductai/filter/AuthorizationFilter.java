package com.ductai.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ductai.model.UserModel;
import com.ductai.utils.SessionUtil;

@WebFilter(urlPatterns = {"/admin*"})
public class AuthorizationFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse respone = (HttpServletResponse) servletResponse;
		String url = request.getServletPath();
		if(url.startsWith("/admin")) {
			UserModel user = (UserModel) SessionUtil.Instance().getValue(request,"USERMODEL");
			if(user != null) {
				if(user.isAdmin()) {
					filterChain.doFilter(request, respone);
				}else {
					respone.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_permission&alert=danger");
				}
			}else {
				respone.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=notLogin&alert=danger");
			}
		}else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		
	}

}
