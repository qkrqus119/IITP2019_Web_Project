package com.soccermatching.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soccermatching.dao.BoardDAO;

@WebServlet("/boards")
public class BoardController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json;
		
		Map<String, String[]> map = request.getParameterMap();
		
		if (map.containsKey("southLat") && map.containsKey("northLat") && map.containsKey("westLng") && map.containsKey("eastLng")) {
			String southLat = request.getParameter("southLat");
			String northLat = request.getParameter("northLat");
			String westLng = request.getParameter("westLng");
			String eastLng = request.getParameter("eastLng");
			
			json = objectMapper.writeValueAsString(new BoardDAO().read(southLat, northLat, westLng, eastLng));
		} else {
			json = objectMapper.writeValueAsString(new BoardDAO().readAll());
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
	}

}