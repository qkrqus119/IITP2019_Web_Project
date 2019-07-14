package com.soccermatching.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soccermatching.dao.ApiInfoDAO;

@WebServlet("/")
public class SampleController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("kakaoMapJavascriptKey", new ApiInfoDAO().read("kakao_map_javascript").getApiKey());
		
		request.getRequestDispatcher("sample.jsp").forward(request, response);
	}

}
