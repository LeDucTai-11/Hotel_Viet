package com.ductai.controller.web.city;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ductai.bo.CityBO;

@WebServlet(urlPatterns = {"/city"})
public class CityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CityBO cityBO = new CityBO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listCities", this.cityBO.findAll());
		req.getRequestDispatcher("/view/web/home.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	

}
